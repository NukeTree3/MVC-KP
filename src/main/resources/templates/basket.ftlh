<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Корзина</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
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

        header {
            background-color: #fff;
            padding: 10px 0;
            margin-bottom: 20px;
            border-bottom: 1px solid #ddd;
        }

        header ul {
            display: flex;
            align-items: center;
            justify-content: space-between;
            list-style: none;
            padding: 0;
            margin: 0;
        }

        header a {
            text-decoration: none;
            color: #333;
        }

        header .logo_images {
            width: 75px;
            height: auto;
        }

        header .search_area {
            text-align: center;
        }

        header .search_area input[type="text"] {
            width: 200px;
            padding: 5px;
        }

        header .search_area input[type="submit"] {
            padding: 5px;
        }

        .cart-items {
            margin-bottom: 20px;
        }

        .cart-item {
            display: flex;
            align-items: center;
            padding: 10px;
            border-bottom: 1px solid #eee;
        }

        .cart-item img {
            width: 100px;
            height: auto;
            margin-right: 15px;
        }

        .cart-item-details {
            flex-grow: 1;
        }

        .cart-item-details h3 {
            margin: 0;
            font-size: 1.2em;
        }

        .cart-item-details p {
            margin: 5px 0;
        }

        .order-button {
            display: inline-block;
            background-color: #28a745;
            color: #fff;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .order-button:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<div class="container">
    <header>
        <ul>
            <li><a href="/"><img src="/images/logo.jpg" alt="Logo" class="logo_images"></a></li>
            <li>
                <div class="search_area">
                    <form action="/" method="GET">
                        <input type="text" name="query" id="search_box" value="" autocomplete="off">
                        <input type="submit" value="Поиск">
                    </form>
                </div>
            </li>
            <li>
                <a href="/basket">Корзина</a>
                <a href="/user-account">Аккаунт</a>
            </li>
        </ul>
    </header>

    <div class="cart-items">
        <h2>Товары в корзине:</h2>
        <#list basket.products?keys as key>
            <#assign value = basket.getQuantity(key)>
            <div class="cart-item">
                <img src="${imgService.getPathByProductID(key.id)}" alt="${key.name}">
                <div class="cart-item-details">
                    <h3>${key.name}</h3>
                    <p>Количество: ${value}</p>
                    <p>Цена за единицу: ${key.price}</p>
                </div>
            </div>
        </#list>
    </div>
    <p>Итоговая стоимость: ${totalCost}</p>
    <a href="/order" class="order-button">Заказать</a>
</div>

</body>
</html>
