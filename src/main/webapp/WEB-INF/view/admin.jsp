<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<jsp:useBean id="books" scope="request" type="java.util.List"/>--%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">
</head>

<body>
<div class="container">
    <h1>Admin page</h1>

    <div class="book-list">

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

                <form method="post" action="admin">
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <button type="submit" name="action" value="delete">Видалити</button>
                </form>

                <form method="post" action="admin">
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <input type="text" name="title" value="${book.title}" required/>
                    <input type="text" name="author" value="${book.author}" required/>
                    <input type="text" name="genre" value="${book.genre}" required/>
                    <input type="text" name="keywords" value="${book.keywords}" required/>
                    <textarea name="description" required>${book.description}</textarea>
                    <button type="submit" name="action" value="edit">Редагувати</button>
                </form>
            </div>
        </c:forEach>

    </div>



    <div class="add-book">
        <h2>Додати нову книгу</h2>
        <form method="post" action="admin">
            <input type="text" name="bookId" placeholder="ID книги" required/>
            <input type="text" name="title" placeholder="Назва" required/>
            <input type="text" name="author" placeholder="Автор" required/>
            <input type="text" name="genre" placeholder="Жанр" required/>
            <input type="text" name="keywords" placeholder="Ключові слова (через кому)" required/>
            <textarea name="description" placeholder="Опис" required></textarea>
            <button type="submit" name="action" value="add">Додати книгу</button>
        </form>
    </div>
</div>
</body>

</html>
