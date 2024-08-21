$(document).ready(function () {
    const pathArray = window.location.pathname.split('/');
    const shopName = pathArray[2];
    const productListContainer = $('#product-list');

    // Формируем URL с учетом версии API
    const url = `/${apiVersion}/shops/${shopName}/products`;

    $.ajax({
        url: url,
        method: 'GET',
        success: function (products) {
            if (products.length > 0) {
                products.forEach(function (product, index) {
                    const productCard = `
                    <div class="product-card">
                        <section class="product-card-assets">
                            <a href="/product-url" class="product-card-media">
                                <span style="display: block; position: relative;">
                                    <img src="${product.imgPath}" alt="${product.name}" class="product-image">
                                </span>
                                <div class="product-card-price">
                                    <div class="price-row">
                                        <div class="price-item">$${product.price}</div>
                                    </div>
                                </div>
                            </a>
                            <div class="product-card-wishlist">
                                <button class="wishlist-button" aria-pressed="false">
                                    <span class="wishlist-icon">
                                        <svg class="icon">
                                            <use xlink:href="#wishlist-inactive"></use>
                                            <title>Wishlist inactive</title>
                                        </svg>
                                    </span>
                                    <span class="visually-hidden">Add to Wishlist</span>
                                </button>
                            </div>
                        </section>
                    </div>`;

                    productListContainer.append(productCard);
                });
            } else {
                productListContainer.append('<p>No products available for this shop.</p>');
            }
        },
        error: function () {
            productListContainer.append('<p>Failed to load products. Please try again later.</p>');
        }
    });
});
