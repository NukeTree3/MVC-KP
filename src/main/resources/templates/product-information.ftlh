<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
    <style>
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

        .search_area {
            text-align: center; /* Центрируем содержимое */
        }

        .search_area input[type="text"] {
            width: 200px;      /* Задаем ширину текстовому полю */
            padding: 5px;      /* Добавляем отступы */
        }

        .search_area input[type="submit"] {
            padding: 5px;      /* Добавляем отступы кнопке */
        }

        .product-image {
            width: 25%; /* чтобы изображение занимало всю ширину карточки */
            height: auto; /* чтобы сохранялось соотношение сторон */
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<#--<a href="/">HELLO</a>-->
<#--<a href="/basket"> Корзина</a>-->
<#--<div class="search_area">-->
<#--    <form action="/" method="GET">-->
<#--        <input type="text" name="query" id="search_box" value="" autocomplete="off">-->
<#--        <input type="submit" value="Поиск">-->
<#--    </form>-->
<#--    <div id="search_advice_wrapper"></div>-->
<#--</div>-->
<header>
    <ul style="display: flex; align-items: center; justify-content: space-between; list-style: none; padding: 0; margin: 0;">
        <a href="/" ><img src="" alt="[]"></a>
        <li style="flex-grow: 1; text-align: center;">
            <div class="search_area">
                <form action="/" method="GET">
                    <input type="text" name="query" id="search_box" value="" autocomplete="off" style="width: 200px; padding: 5px;">
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
<#--&lt;#&ndash;<img src="${product.imagePath}" alt="${product.name}" width="500" height="500">&ndash;&gt;-->
<#--название ${product.name}-->
<#--цена ${product.price}-->
<#--изготовитель ${product.producer}-->
<#--${product.allAttribute}-->
<img src="${imgService.getPathByProductID(product.id)}" alt="[]" class="product-image">
<#list product.allAttribute?keys as key>
    <#assign value = product.allAttribute[key]>
    ${key} : ${value}<hr>
</#list>
Рейтинг: ${rating}
<hr>
<#--<form action="/product/${product.id}" method="post">-->
<#--    <input type="hidden" name="count" value="${product.id}">-->
<#--    <button type="submit">Добавить в корзину</button>-->
<#--</form>-->
<form action="/product/${product.id}" method="post"> <!-- Добавили тег form -->
    <div class="quantity-container">
        <button class="quantity-button" type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-</button>
        <input type="number" id="quantity" name="quantity" class="quantity-input" value="${countProductFromBasket}" min="0" max="${count}">
        <button class="quantity-button" type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+</button>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>  <!-- Скрытое поле productId -->
    <button type="submit" class="add-to-cart-button">Добавить в корзину</button>  <!-- Кнопка submit -->
</form>

<hr>
Добавить комментарий
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

<style>
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
</style>

<script>

    const form = document.getElementById('commentForm');
    const commentText = document.getElementById('commentText');

    form.addEventListener('submit', (event) => {
        if (commentText.value.trim() === '') {
            alert('Пожалуйста, введите комментарий.');
            event.preventDefault(); // Предотвратить отправку формы
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
            event.preventDefault(); // Предотвратить отправку формы
            return;
        }
    });
</script>
Комментарии:
<#list comments as comment>

    ${comment.rating}
    ${serviceToComment.getUserByID(comment.userID).lastName} ${serviceToComment.getUserByID(comment.userID).firstName}
    ${comment.commentsText}
    <hr>

</#list>
</body>
</html>