<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
    <style>
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
        <img src="" alt="[]">
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
<#list basket.products?keys as key>
    <#assign value = basket.getQuantity(key)>
    <img src="${imgService.getPathByProductID(key.id)}" alt="[]"> ${key.name} : ${value} : ${key.price}<hr>
</#list>
<#--<form action="/order" method="POST">-->
<#--    <input type="submit" value="Заказать">-->
    <a href="/order">Заказать</a>
<#--</form>-->
</body>
</html>