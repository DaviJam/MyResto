<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>MyResto</title>
    </head>
    <body>
        <form action="login" method="post" class="form-example">
          <div class="form-example">
            <label for="email">Enter your email: </label>
            <input type="email" name="login" id="email" required>
          </div>
          <div class="form-example">
            <label for="password">Enter your passord: </label>
            <input type="text" name="pwd" id="password" required>
          </div>
          <div class="form-example">
            <input type="submit" value="Login">
          </div>
        </form>
    </body>
</html>
