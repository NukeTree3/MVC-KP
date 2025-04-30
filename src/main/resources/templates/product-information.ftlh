<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Страница товара - ${product.name}</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          integrity="sha512-9usAa10IRO0HhonpyAIVpjrylPvoDwiPUiKdWk5t3PyolY1cOd4DSE0Ga+ri4AuTroPR5aQvXU9xC6qOPnzFeg=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f8f8;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        .product-grid {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
            padding: 30px;
        }

        .product-image {
            text-align: center;
        }

        .product-image img {
            max-width: 60%;
            height: auto;
            border-radius: 8px;
        }

        .product-details {
            padding: 20px;
        }

        .product-title {
            font-size: 2em;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .product-price {
            font-size: 1.5em;
            color: #007bff;
            margin-bottom: 15px;
        }

        .product-description {
            line-height: 1.6;
            margin-bottom: 20px;
        }

        .product-features {
            margin-bottom: 20px;
        }

        .product-features ul {
            list-style: none;
            padding: 0;
        }

        .product-features li {
            margin-bottom: 8px;
            display: flex;
            align-items: center;
        }

        .product-features i {
            margin-right: 8px;
            color: #007bff;
        }

        .add-to-cart-button {
            background-color: #007bff;
            color: #fff;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .add-to-cart-button:hover {
            background-color: #0056b3;
        }

        .reviews {
            padding: 30px;
            background-color: #f2f2f2;
        }

        .review {
            margin-bottom: 20px;
            padding: 15px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
        }

        .review-author {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .review-rating {
            color: #f39c12;
            margin-bottom: 5px;
        }

        .footer {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: #fff;
        }

        @media (max-width: 768px) {
            .container {
                width: 95%;
            }

            .product-grid {
                grid-template-columns: 1fr;
            }

            .product-image {
                text-align: center;
            }
        }

        .quantity-container {
            display: flex;
            align-items: center;
        }

        .quantity-button {
            padding: 5px 10px;
            margin: 0 5px;
            border: 1px solid #ccc;
            cursor: pointer;
            background-color: #f0f0f0;
        }

        .quantity-button:hover {
            background-color: #e0e0e0;
        }

        .quantity-input {
            width: 50px;
            text-align: center;
            border: 1px solid #ccc;
            padding: 5px;
        }

        .add-to-cart-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }

        .add-to-cart-button:hover {
            background-color: #3e8e41;
        }

        .rating {
            display: inline-block;
        }

        .rating input {
            display: none;
        }

        .rating label {
            float: right;
            color: #ddd;
            font-size: 30px;
            padding: 0 2px;
            cursor: pointer;
        }

        .rating label:hover,
        .rating label:hover ~ label,
        .rating input:checked ~ label {
            color: gold;
        }

        .logo_images {
            width: 75px;
            height: auto;

        }

        .search_area input[type="submit"] {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
    </style>
</head>
<body>

<div class="container">
    <header>
        <ul style="display: flex; align-items: center; justify-content: space-between; list-style: none; padding: 0; margin: 0;">
            <a href="/"> <img src="/images/logo.jpg" alt="[]" class="logo_images"> </a>
            <li style="flex-grow: 1; text-align: center;">
                <div class="search_area">
                    <form action="/" method="GET">
                        <input type="text" name="query" id="search_box" value="" autocomplete="off"
                               style="width: 200px; padding: 5px;">
                        <input type="submit" value="Поиск" style="padding: 5px;">
                    </form>
                    <div id="search_advice_wrapper"></div>
                </div>
            </li>
            <li style="display: flex; align-items: center;">
                <a href="/basket" style="margin-right: 10px;">Корзина</a>
                <a href="/user-account">Аккаунт</a>
            </li>
        </ul>
    </header>

    <hr>
    <div class="product-grid">
        <div class="product-image">
            <img src="/images/download/${imgService.getPathByProductID(product.id)}" alt="${product.name}"
                 class="product-image">
        </div>
        <div class="product-details">
            <h1 class="product-title">${product.name}</h1>
            <p class="product-price">Цена: ${product.price} ₽</p>

            <p class="product-description">
            <ul>
                <#list product.allAttribute?keys as key>
                    <#assign value = product.allAttribute[key]>
                    <li><b>${key}</b>: ${value}</li>
                </#list>
            </ul>
            </p>
            <div class="review-rating">
                <#assign maxRating = 5>
                <#list 1..maxRating as i>
                    <#if i <= rating>
                        <i class="fas fa-star"></i>
                    <#else>
                        <i class="far fa-star"></i>
                    </#if>
                </#list>
            </div>

            <form action="/product/${product.id}" method="post">
                <div class="quantity-container">
                    <button class="quantity-button" type="button"
                            onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-
                    </button>
                    <input type="number" id="quantity" name="quantity" class="quantity-input"
                           value="${countProductFromBasket}" min="0" max="${count}">
                    <button class="quantity-button" type="button"
                            onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+
                    </button>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="add-to-cart-button">
                    <i class="fa fa-shopping-cart"></i>
                    Добавить в корзину
                </button>
            </form>

        </div>
    </div>

    <hr>

    <div class="reviews">
        <h2>Добавить комментарий:</h2>
        <form id="commentForm" action="/product/${product.id}/add-comment" method="post">
            <label for="commentText">Ваш комментарий:</label><br>
            <textarea id="commentText" name="commentText" rows="4" cols="50"></textarea><br><br>

            <label>Оценка (1-5):</label><br>
            <div class="rating">
                <input type="radio" id="star5" name="rating" value="5">
                <label for="star5" title="5 stars">★</label>
                <input type="radio" id="star4" name="rating" value="4">
                <label for="star4" title="4 stars">★</label>
                <input type="radio" id="star3" name="rating" value="3">
                <label for="star3" title="3 stars">★</label>
                <input type="radio" id="star2" name="rating" value="2">
                <label for="star2" title="2 stars">★</label>
                <input type="radio" id="star1" name="rating" value="1">
                <label for="star1" title="1 star">★</label>
            </div>
            <br><br>

            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Отправить комментарий и оценку</button>
        </form>

        <h2>Комментарии:</h2>
        <hr>
        <#list comments as comment>
            <div class="review">
                <#if userID??>
                    <#if comment.userID == userID>
                        <form id="deleteCommentForm" action="/product/${product.id}/delete-comment/${comment.id}" method="post">
                            <input type="hidden" name="_csrf" value="${_csrf.token}">
                            <button type="submit">удалить</button>
                        </form>
                    </#if>
                </#if>
                <p class="review-author">${serviceToComment.getUserByID(comment.userID).lastName} ${serviceToComment.getUserByID(comment.userID).firstName}</p>
                <div class="review-rating">
                    <#assign maxRating = 5>
                    <#list 1..maxRating as i>
                        <#if i <= comment.rating>
                            <i class="fas fa-star"></i>
                        <#else>
                            <i class="far fa-star"></i>
                        </#if>
                    </#list>
                </div>
                <p>${comment.commentsText}</p>
            </div>
            <hr>
        </#list>
    </div>
</div>
<footer class="footer">
    <p>Почта помощи: support@s.ru</p>
    <div class="copyright">
        <p>&copy; 2025 NukeTree3</p>
    </div>
</footer>

<script>
    const form = document.getElementById('commentForm');
    const commentText = document.getElementById('commentText');

    form.addEventListener('submit', (event) => {
        if (commentText.value.trim() === '') {
            alert('Пожалуйста, введите комментарий.');
            event.preventDefault();
            return;
        }

        const ratingRadios = document.querySelectorAll('input[name="rating"]');
        let ratingSelected = false;

        for (const radio of ratingRadios) {
            if (radio.checked) {
                ratingSelected = true;
                break;
            }
        }

        if (!ratingSelected) {
            alert('Пожалуйста, выберите оценку.');
            event.preventDefault();
            return;
        }
    });
</script>

</body>
</html>