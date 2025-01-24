<!DOCTYPE html>
<html lang="en">
<head>
    <title>
        MVC
    </title>
</head>
<body>
HELLO<hr>
<#list products as product>
    <div>
        <a href="/product"${product}>${product}
    </div>
</#list>
</body>
</html>