<#--<!DOCTYPE html>-->
<#--<html lang="en">-->
<#--<head>-->
<#--    <title>-->
<#--        MVC-->
<#--    </title>-->
<#--</head>-->
<#--<body>-->
<#--<a href="/">HELLO</a>-->
<#--<a href="/basket"> Корзина</a>-->
<#--<a href="/user-account"> Аккаунт</a>-->
<#--<div class="search_area">-->
<#--    <form action="/" method="GET">-->
<#--        <input type="text" name="query" id="search_box" value="" autocomplete="off">-->
<#--        <input type="submit" value="Поиск">-->
<#--    </form>-->
<#--    <div id="search_advice_wrapper"></div>-->
<#--</div>-->
<#--<hr>-->
<#--    <#list products as product>-->
<#--    <div>-->
<#--        <a href="/product/${product.id}">${product.name}-->
<#--    </div>-->
<#--    </#list>-->
<#--</body>-->
<#--</html>-->
<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>

    <style>

        ul.nav{

            margin-left: 0px;

            padding-left: 0px;

            list-style: none;

            }

        .nav li {

            float: left;

            }

        ul.nav a {

            display: block;

            width: 5em;

            padding:10px;

            margin: 0 5px;

            background-color: #f4f4f4;

            border: 1px dashed #333;

            text-decoration: none;

            color: #333;

            text-align: center;

            }

        ul.nav a:hover{

            background-color: #333;

            color: #f4f4f4;

            }

        </style>


</head>
<body>
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

<style>
    /* Общие стили (можно вынести в отдельный CSS-файл) */
    header {
        background-color: #ffffff;
        padding: 10px;
    }

    header ul {
        list-style: none; /* Убираем маркеры списка */
        padding: 0;        /* Убираем внутренние отступы списка */
        margin: 0;         /* Убираем внешние отступы списка */
        display: flex;      /* Включаем flexbox */
        align-items: center; /* Выравниваем элементы по вертикали */
        justify-content: space-between; /* Равномерно распределяем элементы по ширине */
    }

    header li {
        margin: 0; /* Убираем отступы у элементов списка */
    }

    /* Стили для поисковой панели */
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

    /* Стили для ссылок (можно переопределить) */
    header a {
        text-decoration: none; /* Убираем подчеркивание ссылок */
        color: #333;            /* Задаем цвет ссылок */
        margin-left: 10px;    /* Добавляем отступ слева (между ссылками) */
    }
    header a:first-child{
        margin-left: 0;
    }
</style>



<style>
    .product-card {
        border: 1px solid #ccc;
        padding: 10px;
        margin: 10px;
        width: 250px; /* или другую подходящую ширину */
        text-align: center;
    }

    .product-image {
        width: 100%; /* чтобы изображение занимало всю ширину карточки */
        height: auto; /* чтобы сохранялось соотношение сторон */
        margin-bottom: 10px;
    }

    .product-name {
        font-weight: bold;
    }

    .product-grid{
        display: grid;
        grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));

        gap: 10px
    }

</style>
<hr>
<div>
    <#if products??>
        <div class="product-grid">
            <#list products as product>

                <div class="product-card">
                    <img src="${imgService.getPathByProductID(product.id)}" alt="" class="product-image">
                    <a href="/product/${product.id}">
                        <div class="product-name">${product.name}</div>
                        ${product.price}
                    </a>
                </div>
            </#list>
        </div>
    <#else>
        <p>Нет доступных товаров.</p>
    </#if>
</div>
<footer class="mainDivEnd">
    <div class="separator"></div>
    <div class="footer-content">
        <p>Почта помощи: support@s.ru</p>
        <div class="copyright">

            <p>&copy; 2025 NukeTree3</p>
        </div>
    </div>
</footer>

<style>
    /* Общие стили (можно вынести в отдельный CSS-файл) */
    .mainDivEnd {
        color: black;
        background-color: #ffffff; /* Добавим темный фон для видимости белого текста */
        padding: 10px;          /* Добавим отступы */
        text-align: center;      /* Центрируем контент */
    }

    .separator {
        border-bottom: 1px solid #ccc;
        margin: 10px 0;
    }

    .footer-content {
        display: flex;             /* Используем flexbox */
        flex-direction: column;    /* Располагаем элементы вертикально */
        align-items: center;        /* Центрируем элементы по горизонтали */
    }

    .copyright {
        display: flex;             /* Используем flexbox */
        align-items: center;        /* Центрируем элементы по вертикали */
    }

    .copyright img {
        margin-right: 5px;         /* Добавим отступ справа от логотипа */
    }
</style>

</body>
</html>