<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" scope="request" type="ua.kpi.jakartaee.repository.model.User"/>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>${user.name}</title>
        <link rel="icon" href="${user.githubAvatarLink}"
              type="image/png">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user5.css"
              type="text/css">
    </head>
    <body>
        <a href="${pageContext.request.contextPath}/" class="home-button">
            <img src="${pageContext.request.contextPath}/images/home.png" alt="Home"> Home
        </a>
        <div class="container">
            <img src="${user.githubAvatarLink}" alt="Profile Picture" class="profile-img">
            <h1>${user.name}</h1>
            <p>C++/UE Developer</p>
            <p>Group: ${user.group}</p>
            <p>Github: <a href="${user.githubLink}" target="_blank" class="github-link">${user.githubLink}</a></p>
        </div>
    </body>
</html>
