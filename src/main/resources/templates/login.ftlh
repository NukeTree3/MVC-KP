<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
HELLO<hr>
<form action="/login" method="post">
    почта: <input type="email" name="username">
    пароль <input type="password" name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Войти">
</form>
<a href="/registration"> Регистрация</a>
</body>
</html>