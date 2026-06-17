// CSRF helpers
function csrfHeader() { return document.querySelector('meta[name="_csrf_header"]')?.content; }
function csrfToken() { return document.querySelector('meta[name="_csrf"]')?.content; }

// Cart operations
function updateCartItem(itemId, quantity) {
    if (quantity < 1) return removeCartItem(itemId);
    const h = { 'Content-Type': 'application/x-www-form-urlencoded' };
    h[csrfHeader()] = csrfToken();
    fetch('/cart/update', {
        method: 'POST',
        headers: h,
        body: 'itemId=' + itemId + '&quantity=' + quantity
    })
    .then(r => r.json())
    .then(data => {
        location.reload();
    });
}

function removeCartItem(itemId) {
    if (!confirm('Remove this item?')) return;
    const h = { 'Content-Type': 'application/x-www-form-urlencoded' };
    h[csrfHeader()] = csrfToken();
    fetch('/cart/remove', {
        method: 'POST',
        headers: h,
        body: 'itemId=' + itemId
    })
    .then(r => r.json())
    .then(() => {
        location.reload();
    });
}
