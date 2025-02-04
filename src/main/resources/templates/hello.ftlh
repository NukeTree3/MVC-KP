<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
    HELLO | <a href="/login">Войти</a><hr>
    <#list products as product>
    <div>
        <a href="/product/${product.name}"><img src="${product.imagePath}" alt="${product.name}" width="100" height="100">${product.name}
    </div>
    </#list>
</body>
</html>