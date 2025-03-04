<jsp:useBean id="user" scope="request" type="ua.kpi.jakartaee.repository.model.User"/>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${user.username}</title>
    <link rel="icon"
          type="image/png"
          href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4"
    >
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/user2.css" type="text/css">
</head>
<body>
<div class="page-container">
    <div class="content-header">
        <a href="${pageContext.request.contextPath}/" class="content-header__home-link">Home</a>
        <p class="content-header__header">Jakarta EE</p>
    </div>

    <div class="content-section">
        <p class="content-section__text">Explore Jakarta EE to build scalable and enterprise-level web applications:</p>
        <a href="https://jakarta.ee/learn/docs/jakartaee-tutorial/current/index.html"
           target="_blank"
           class="content-section__link"
        >
            <img src="${pageContext.request.contextPath}/images/jakartaee-logo.png"
                 alt="JakartaEE logo image"
                 class="content-section__logo"
            >
        </a>
    </div>
</div>
</body>
</html>
