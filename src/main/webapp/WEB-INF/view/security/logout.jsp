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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/security/logout.css" type="text/css">
</head>
<body>
<div class="logout-container">
    <div class="logout-box">
        <h2>Are you sure you want to logout?</h2>
        <form action="logout" method="post">
            <input type="submit" value="Logout" class="logout-btn">
        </form>
    </div>
</div>
</body>
</html>
