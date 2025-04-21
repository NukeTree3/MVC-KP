<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Аккаунт пользователя</title>
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

        .user-info {
            margin-bottom: 20px;
        }

        .user-info p {
            margin-bottom: 8px;
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

        .logout-form input[type="submit"] {
            background-color: #dc3545;
            color: #fff;
            padding: 12px 25px;
            border: none;
            border-radius: 5px;
            font-size: 1.1em;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .logout-form input[type="submit"]:hover {
            background-color: #c82333;
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

    <div class="user-info">
        <h2>Информация об аккаунте</h2>
        <p>Имя: ${firstname}</p>
        <p>Фамилия: ${lastname}</p>
        <p>Email: ${email}</p>
        <p>Номер телефона: ${phone}</p>
        <p>Дата рождения: ${birthdate}</p>
    </div>

    <form class="logout-form" action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Выйти">
    </form>
</div>

</body>
</html>
