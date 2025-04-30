<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MVC</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }

        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }

        header {
            background-color: #fff;
            padding: 20px 0;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        header nav {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        header .logo a {
            display: block;
        }

        header .logo img {
            max-width: 100px;
            height: auto;
        }

        header ul {
            list-style: none;
            padding: 0;
            margin: 0;
            display: flex;
            align-items: center;
        }

        header ul li {
            margin-left: 20px;
        }

        header ul li:first-child {
            margin-left: 0;
        }

        header a {
            text-decoration: none;
            color: #333;
            transition: color 0.3s ease;
        }

        header a:hover {
            color: #007bff;
        }

        .search_area {
            text-align: center;
        }

        .search_area input[type="text"] {
            width: 250px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin-right: 10px;
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

        .search_area input[type="submit"]:hover {
            background-color: #0056b3;
        }

        .product-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-top: 20px;
        }

        .product-card {
            border: 1px solid #ddd;
            border-radius: 5px;
            padding: 15px;
            text-align: center;
            background-color: #fff;
            transition: transform 0.3s ease;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .product-image {
            max-width: 100%;
            height: auto;
            margin-bottom: 10px;
        }

        .product-name {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .product-card a {
            text-decoration: none;
            color: #333;
        }

        .product-card a:hover {
            color: #007bff;
        }

        footer {
            background-color: #333;
            color: #fff;
            text-align: center;
            padding: 20px 0;
            margin-top: 30px;
        }

        footer .separator {
            border-bottom: 1px solid #555;
            margin: 10px 0;
        }

        footer .footer-content {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        footer .copyright {
            display: flex;
            align-items: center;
            justify-content: center;
        }

        footer .copyright img {
            margin-right: 5px;
            max-width: 30px;
            height: auto;
        }

    </style>
</head>
<body>

<header>
    <div class="container">
        <nav>
            <div class="logo">
                <a href="/">
                    <img src="/images/logo.jpg" alt="Logo">
                </a>
            </div>
            <ul>
                <li>
                    <div class="search_area">
                        <form action="/" method="GET">
                            <input type="text" name="query" id="search_box" value="" autocomplete="off" placeholder="Поиск">
                            <input type="submit" value="Поиск">
                        </form>
                        <div id="search_advice_wrapper"></div>
                    </div>
                </li>
                <li><a href="/basket">Корзина</a></li>
                <li><a href="/user-account">Аккаунт</a></li>
            </ul>
        </nav>
    </div>
</header>

<main class="container">
    <#if products??>
        <div class="product-grid">
            <#list products as product>
                <div class="product-card">
                    <a href="/product/${product.id}">
                        <img src="/images/download/${imgService.getPathByProductID(product.id)}" alt="${product.name}" class="product-image">
                        <div class="product-name">${product.name}</div>
                        ${product.price} ₽
                    </a>
                </div>
            </#list>
        </div>
    <#else>
        <p>Нет доступных товаров.</p>
    </#if>
</main>

<footer>
    <div class="separator"></div>
    <div class="footer-content">
        <p>Почта помощи: support@s.ru</p>
        <div class="copyright">
            <p>&copy; 2025 NukeTree3</p>
        </div>
    </div>
</footer>

</body>
</html>
