<jsp:useBean id="rickroll" scope="request" type="java.lang.String"/>

<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Gotcha</title>
        <link rel="icon"
              href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4"
              type="image/png">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/css/rickroll.css"
              type="text/css">
    </head>
    <body>
        <div class="video-container">
            <iframe width="100vw" height="100vh"
                    src="${rickroll}?&autoplay=1&mute=1" border="0"
                    allowfullscreen></iframe>
        </div>
    </body>
</html>
