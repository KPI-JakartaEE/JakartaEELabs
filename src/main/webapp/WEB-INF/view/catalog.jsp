<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="books" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Каталог бібліотеки</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/catalog.css" type="text/css">
</head>

<body>
<div class="container">
    <h1>Каталог бібліотеки</h1>

    <div class="search-section">
        <form class="search-form" action="${pageContext.request.contextPath}/library/books" method="get" accept-charset="UTF-8">
            <div class="search-fields">
                <div class="field">
                    <label for="author">Пошук за автором:</label>
                    <input type="text" id="author" name="author" value="<c:out value="${param.author}" />">
                </div>

                <div class="field">
                    <label for="title">Пошук за назвою:</label>
                    <input type="text" id="title" name="title" value="<c:out value="${param.title}" />">
                </div>

                <div class="field">
                    <label for="keyword">Пошук за ключовим словом:</label>
                    <input type="text" id="keyword" name="keyword" value="<c:out value="${param.keyword}" />">
                </div>

                <div class="field">
                    <label for="genre">Пошук за жанром:</label>
                    <input type="text" id="genre" name="genre" value="<c:out value="${param.genre}" />">
                </div>
            </div>

            <div class="search-button">
                <button type="submit">Пошук</button>
            </div>
        </form>
    </div>

    <c:if test="${not empty param.author || not empty param.title || not empty param.keyword || not empty param.genre}">
        <div class="search-results-info">
            <p class="search-results-title">Результати пошуку:</p>
            <c:if test="${not empty param.author}">
                <span class="search-param">Автор <strong><c:out value="${param.author}"/></strong></span>
            </c:if>
            <c:if test="${not empty param.title}">
                <span class="search-param">Назва <strong><c:out value="${param.title}"/></strong></span>
            </c:if>
            <c:if test="${not empty param.keyword}">
                <span class="search-param">Ключове слово <strong><c:out value="${param.keyword}"/></strong></span>
            </c:if>
            <c:if test="${not empty param.genre}">
                <span class="search-param">Жанр <strong><c:out value="${param.genre}"/></strong></span>
            </c:if>
        </div>
    </c:if>

    <div class="book-list">
        <c:choose>
            <c:when test="${empty books}">
                <div class="no-results">
                    <p>Книг не знайдено. Спробуйте змінити параметри пошуку.</p>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="book" items="${books}">
                    <div class="book-item">
                        <div class="book-title"><c:out value="${book.title}"/></div>
                        <div class="book-author">Автор: <c:out value="${book.author}"/></div>
                        <div class="book-description"><c:out value="${book.description}"/></div>
                        <div class="book-genre">Жанр: <c:out value="${book.genre}"/></div>

                        <div class="book-keywords">
                            Ключові слова:
                            <c:forEach var="keyword" items="${book.keywords}">
                                <span class="keyword"><c:out value="${keyword}"/></span>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

</html>