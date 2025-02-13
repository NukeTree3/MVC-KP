<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
<a href="/">HELLO</a>
<form id="queryForm" action="admin" method="post">
    <label for="Query">Запрос:</label><br>
    <textarea id="Query" name="Query" rows="4" cols="50"></textarea><br><br>

    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <button type="submit">Выполнить</button>
</form>
<#if queryStatus??>
    ${queryStatus}
</#if>
</body>
</html>