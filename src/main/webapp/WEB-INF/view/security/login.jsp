<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JakartaEE-Team-1</title>
    <link rel="icon" type="image/png"
          href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/security/login.css" type="text/css">
</head>
<body>
<div class="login-container">
    <div class="login-box">
        <h2>Login</h2>

        <c:if test="${authErrorPresent == true}">
            <div class="error-message">
                <p style="color: red;">Invalid username or password. Please try again.</p>
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/login" method="POST">
            <div class="login-textbox">
                <label>
                    <input type="text" placeholder="Username" name="username" required>
                </label>
            </div>
            <div class="login-textbox">
                <label>
                    <input type="password" placeholder="Password" name="password" required>
                </label>
            </div>
            <input type="submit" value="Login" class="login-btn">
        </form>
    </div>
</div>
</body>
</html>