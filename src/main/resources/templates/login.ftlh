<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
<a href="/">HELLO</a><hr>
<form action="/login" method="post">
    почта: <input type="email" name="username">
    пароль <input type="password" name="password">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Войти">
</form>
<a href="/registration"> Регистрация</a>
<#if loginError??>
    <p style="color:red;">Invalid username or password. Account may not be activated.</p>
</#if>
</body>
</html>