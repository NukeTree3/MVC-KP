<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
<a href="/">HELLO</a>
<a href="/basket"> Корзина</a>
<div class="search_area">
    <form action="/" method="GET">
        <input type="text" name="query" id="search_box" value="" autocomplete="off">
        <input type="submit" value="Поиск">
    </form>
    <div id="search_advice_wrapper"></div>
</div>
<hr>
<#list basket.products?keys as key>
    <#assign value = basket.getQuantity(key)>
    ${key.name} : ${value} : ${key.price}<hr>
</#list>
<#--<form action="/order" method="POST">-->
<#--    <input type="submit" value="Заказать">-->
    <a href="/order">Заказать</a>
<#--</form>-->
</body>
</html>