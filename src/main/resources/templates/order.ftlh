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
    <form action="/order" method="GET">
        <input type="text" name="query" id="search_box" value="" autocomplete="off">
        <input type="submit" value="Поиск">
    </form>
    <div id="search_advice_wrapper"></div>
</div>
<hr>
${status}
</body>
</html>