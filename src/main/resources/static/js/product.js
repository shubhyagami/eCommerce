// Add to cart
function addToCart(productId) {
    const qty = document.getElementById('qtyInput') ? parseInt(document.getElementById('qtyInput').value) : 1;
    const h = { 'Content-Type': 'application/x-www-form-urlencoded' };
    h[csrfHeader()] = csrfToken();
    fetch('/cart/add', {
        method: 'POST',
        headers: h,
        body: 'productId=' + productId + '&quantity=' + qty
    })
    .then(r => r.json())
    .then(data => {
        document.getElementById('cartCount').textContent = data.itemCount;
        showToast('Cart', 'Added to cart successfully!');
    })
    .catch(() => showToast('Error', 'Please login first'));
}

// Buy now
function buyNow(productId) {
    const qty = document.getElementById('qtyInput') ? parseInt(document.getElementById('qtyInput').value) : 1;
    const h = { 'Content-Type': 'application/x-www-form-urlencoded' };
    h[csrfHeader()] = csrfToken();
    fetch('/cart/add', {
        method: 'POST',
        headers: h,
        body: 'productId=' + productId + '&quantity=' + qty
    })
    .then(r => r.json())
    .then(() => { window.location.href = '/checkout'; })
    .catch(() => { window.location.href = '/login'; });
}

// Quantity selector on detail page
function changeQty(delta) {
    const input = document.getElementById('qtyInput');
    let val = parseInt(input.value) + delta;
    const max = parseInt(input.max);
    if (val < 1) val = 1;
    if (val > max) val = max;
    input.value = val;
}

// Rating input
$(document).ready(function() {
    $('.rating-input .star').on('click', function() {
        const val = $(this).data('val');
        $('#ratingVal').val(val);
        $('.rating-input .star').each(function() {
            $(this).toggleClass('active', $(this).data('val') <= val);
        });
    });
});
