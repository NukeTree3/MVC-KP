<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
    HELLO | <a href="/login">Войти</a><hr>
    <div class="search_area">
        <form action="/" method="GET">
            <input type="text" name="query" id="search_box" value="" autocomplete="off">
            <input type="submit" value="Поиск">
        </form>
        <div id="search_advice_wrapper"></div>
    </div>
    <#list products as product>
    <div>
        <a href="/product/${product.id}">${product.name}
    </div>
    </#list>
</body>
</html>