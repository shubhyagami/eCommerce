// CSRF helpers
function csrfHeader() { return document.querySelector('meta[name="_csrf_header"]')?.content; }
function csrfToken() { return document.querySelector('meta[name="_csrf"]')?.content; }

// Dark Mode Toggle
function initTheme() {
    const theme = localStorage.getItem('theme') || 'light';
    document.documentElement.setAttribute('data-theme', theme);
    updateThemeIcon(theme);
}

function toggleTheme() {
    const current = document.documentElement.getAttribute('data-theme');
    const next = current === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', next);
    localStorage.setItem('theme', next);
    updateThemeIcon(next);
}

function updateThemeIcon(theme) {
    const icon = document.querySelector('#themeToggle i');
    if (icon) {
        icon.className = theme === 'dark' ? 'bi bi-sun-fill' : 'bi bi-moon-fill';
    }
}

// Toast notifications
function showToast(title, message) {
    const toast = new bootstrap.Toast(document.getElementById('liveToast'));
    document.getElementById('toastTitle').textContent = title;
    document.getElementById('toastMessage').textContent = message;
    toast.show();
}

// Autocomplete search
$(document).ready(function() {
    initTheme();

    $('#themeToggle').click(toggleTheme);

    // Cart count
    function updateCartCount() {
        fetch('/cart/count').then(r => r.text()).then(n => {
            document.getElementById('cartCount').textContent = n;
        }).catch(() => {});
    }
    updateCartCount();

    // Autocomplete
    let searchTimeout;
    $('#searchInput').on('input', function() {
        clearTimeout(searchTimeout);
        const q = $(this).val();
        if (q.length < 2) { $('#autocompleteResults').hide(); return; }
        searchTimeout = setTimeout(() => {
            fetch('/autocomplete?q=' + encodeURIComponent(q))
                .then(r => r.json())
                .then(data => {
                    const results = $('#autocompleteResults');
                    results.empty().show();
                    data.forEach(item => {
                        results.append('<div class="autocomplete-item">' + item + '</div>');
                    });
                    if (data.length === 0) results.hide();
                });
        }, 300);
    });

    $(document).on('click', '.autocomplete-item', function() {
        $('#searchInput').val($(this).text());
        $('#autocompleteResults').hide();
    });

    $(document).click(function(e) {
        if (!$(e.target).closest('.autocomplete-wrap').length) {
            $('#autocompleteResults').hide();
        }
    });

    // Wishlist toggle
    $(document).on('click', '.wishlist-btn', function() {
        const btn = $(this);
        const productId = btn.data('product-id');
        const icon = btn.find('i');

        fetch('/wishlist/check?productId=' + productId)
            .then(r => r.text())
            .then(inWishlist => {
                if (inWishlist === 'true') {
                    fetch('/wishlist/remove', { method: 'POST', headers: {'Content-Type': 'application/x-www-form-urlencoded', [csrfHeader()]: csrfToken()}, body: 'productId=' + productId })
                        .then(() => { icon.className = 'bi bi-heart'; showToast('Wishlist', 'Removed from wishlist'); });
                } else {
                    fetch('/wishlist/add', { method: 'POST', headers: {'Content-Type': 'application/x-www-form-urlencoded', [csrfHeader()]: csrfToken()}, body: 'productId=' + productId })
                        .then(() => { icon.className = 'bi bi-heart-fill text-danger'; showToast('Wishlist', 'Added to wishlist'); });
                }
            });
    });

    // Update wishlist icon on load
    $('.wishlist-btn').each(function() {
        const btn = $(this);
        const productId = btn.data('product-id');
        fetch('/wishlist/check?productId=' + productId)
            .then(r => r.text())
            .then(inWishlist => {
                if (inWishlist === 'true') {
                    btn.find('i').className = 'bi bi-heart-fill text-danger';
                }
            });
    });
});

// Newsletter
function subscribeNewsletter() {
    const email = document.getElementById('newsletterEmail').value;
    if (email && email.includes('@')) {
        showToast('Newsletter', 'Thank you for subscribing!');
        document.getElementById('newsletterEmail').value = '';
    } else {
        showToast('Error', 'Please enter a valid email');
    }
}
