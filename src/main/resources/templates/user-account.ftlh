<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
<a href="/">HELLO</a>
<a href="/basket"> Корзина</a><hr>
имя - ${firstname}
фамилия - ${lastname}
email ${email}
номер телефона ${phone}
дата рождения ${birthdate}
<form action="/logout" method="post">
    <input type="hidden" name="_csrf", value="${_csrf.token}">
    <input type="submit" value="Выйти">
</form>
</body>
</html>