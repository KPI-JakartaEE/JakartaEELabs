<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="user" scope="request" type="ua.kpi.jakartaee.repository.model.User"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user.username}</title>
    <link rel="icon" type="image/png" href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user1.css">
</head>
<body>
<nav>
    <a class="arrow-container" href="${pageContext.request.contextPath}/">
        <img class="home-img" src="${pageContext.request.contextPath}/images/home.png" alt="Back to Home Image">
    </a>
</nav>
<main>
    <div class="user-info">
        <div class="user-img-container">
            <img class="user-img" src="${user.githubAvatarLink}" alt="User image">
        </div>
        <div class="username">${user.name}</div>
        <div class="user-group">${user.group}</div>
        <a class="github-container" href="${user.githubLink}">
            <img class="github" src="${pageContext.request.contextPath}/images/github-logo.png" alt="GitHub">
            <div class="user-group">GitHub</div>
        </a>
    </div>
</main>
</body>
</html>
