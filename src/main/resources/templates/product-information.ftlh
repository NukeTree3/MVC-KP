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
    </style>
</head>
<body>
<a href="/">HELLO<hr></a>
<#--&lt;#&ndash;<img src="${product.imagePath}" alt="${product.name}" width="500" height="500">&ndash;&gt;-->
<#--название ${product.name}-->
<#--цена ${product.price}-->
<#--изготовитель ${product.producer}-->
<#--${product.allAttribute}-->
<#list product.allAttribute?keys as key>
    <#assign value = product.allAttribute[key]>
    ${key} : ${value}<hr>
</#list>
<#--<form action="/product/${product.id}" method="post">-->
<#--    <input type="hidden" name="count" value="${product.id}">-->
<#--    <button type="submit">Добавить в корзину</button>-->
<#--</form>-->
<form action="/product/${product.id}" method="post"> <!-- Добавили тег form -->
    <div class="quantity-container">
        <button class="quantity-button" type="button" onclick="this.parentNode.querySelector('input[type=number]').stepDown()">-</button>
        <input type="number" id="quantity" name="quantity" class="quantity-input" value="0" min="0" max="${count}">
        <button class="quantity-button" type="button" onclick="this.parentNode.querySelector('input[type=number]').stepUp()">+</button>
    </div>

    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>>  <!-- Скрытое поле productId -->
    <button type="submit" class="add-to-cart-button">Добавить в корзину</button>  <!-- Кнопка submit -->
</form>
</body>
</html>