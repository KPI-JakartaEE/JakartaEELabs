<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" scope="request" type="ua.kpi.jakartaee.repository.model.User"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user.name}</title>
    <link rel="icon" type="image/png"
          href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user4.css" type="text/css">
</head>
<body>
<main>
    <div class="user-name">${user.name} ${user.group}</div>
    <img src="${user.githubAvatarLink}" alt="Profile Picture" class="profile-img">
    <a class="visit-button" href="https://www.youtube.com/shorts/nDw2_Gea5vg">Birmingham</a>
    <a class="visit-button" href="${pageContext.request.contextPath}/">Home</a>

</main>
</body>
</html>
