<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="users" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JakartaEE-Team-1</title>
  <link rel="icon" type="image/png"
        href="https://avatars.githubusercontent.com/u/198439363?s=400&u=41d28b536c7a57d37acefcb97c157bc4b261c4bd&v=4">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css" type="text/css">
</head>

<body>
<nav>
  <a id="logo-container" class="logo-container" href="${pageContext.request.contextPath}/">
    <img class="jakarta-ee-logo" src="${pageContext.request.contextPath}/images/jakartaee-logo.png"
         alt="JakarataEE logo">
    <div class="logo-info">Розроблення застосунків на платформі JakartaEE</div>
  </a>
  <a class="kpi-info" href="https://kpi.ua/">
    <div class="kpi-name">Національний технічний університет України «Київський політехнічний інститут імені
      Ігоря Сікорського»
    </div>
    <img class="kpi-logo" src="${pageContext.request.contextPath}/images/kpi-logo.png" alt="JakarataEE logo">
  </a>
</nav>
<header>
  <div class="header-img-container">
    <div class="header-img-wrapper">
      <img id="header-img" class="header-img" src="${pageContext.request.contextPath}/images/kpi-photo.jpg"
           alt="Image 1">
    </div>
    <div class="text-over-header-img">
      <div>Команда №1</div>
      <div id="member-group-info">
        <c:forEach var="user" items="${users}">
          <div class="team-member"><c:out value="${user.group}" /> <c:out value="${user.name}" /></div>
        </c:forEach>
      </div>
    </div>
  </div>
</header>
<main>
  <div id="scroll-container" class="scroll-container">
    <c:forEach var="user" items="${users}">
      <div class="member-item">
        <div class="account-img-circle">
          <c:choose>
            <c:when test="${not empty user.githubAvatarLink}">
              <img class="account-img" src="${user.githubAvatarLink}" alt="Account">
            </c:when>
            <c:otherwise>
              <img class="account-img" src="${pageContext.request.contextPath}/images/account-icon.png"
                   alt="Account">
            </c:otherwise>
          </c:choose>
        </div>
        <div class="member-info">
          <div class="member-name"><c:out value="${user.name}" /></div>
          <div class="member-group"><c:out value="${user.group}" /></div>
          <a class="member-links" href="${user.githubLink}">GitHub</a>
        </div>
        <a class="visit-button" href="${pageContext.request.contextPath}/team/members/${user.username}">More</a>
      </div>
    </c:forEach>
  </div>
</main>
<footer>
  <a class="footer-info-container" href="https://github.com/KPI-JakartaEE">
    <img class="kpi-logo" src="${pageContext.request.contextPath}/images/github-logo.png" alt="JakartaEE logo">
    <div>Our GitHub</div>
  </a>
</footer>
<div id="backToTop" class="back-to-top">Back to Top</div>

<script type="module" src="${pageContext.request.contextPath}/js/script.js"></script>
</body>

</html>