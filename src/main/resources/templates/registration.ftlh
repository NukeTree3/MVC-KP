<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
HELLO<hr>
<form action="/registration" method="post">
    имя: <input type="text" name="firstName">
    фамилия: <input type="text" name="lastName">
    номер телефона: <input type="text" name="phone">
    дата рождения: <input type="date" name="birthday">
    почта: <input type="email" name="email">
    пароль <input type="password" name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться">
</form>
<#if error??>
    <h2 style="color: red">${error}
</#if>
</body>
</html>