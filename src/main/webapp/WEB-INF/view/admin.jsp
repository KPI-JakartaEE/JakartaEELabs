<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="books" scope="request" type="java.util.List"/>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Admin page</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css" type="text/css">

    <script>
        function addKeyword(containerId) {
            let container = document.getElementById("keywords-container-" + containerId);
            let input = document.createElement("input");
            input.type = "text";
            input.name = "keywords";
            input.placeholder = "Ключове слово";
            container.appendChild(input);
        }
    </script>

</head>

<body>
<div class="container">
    <h1>ADMIN</h1>

    <div class="add-book">
        <h2>Додати нову книгу</h2>
        <form method="post" action="admin">
            <input type="hidden" name="_method" value="POST">
            <label><input type="text" name="title" placeholder="Назва" required/></label>
            <label><input type="text" name="author" placeholder="Автор" required/></label>
            <label><input type="text" name="genre" placeholder="Жанр" required/></label>

            <div id="keywords-container-creation-form">
                <label><input type="text" name="keywords" placeholder="Ключове слово" required/></label>
            </div>
            <p><button type="button" onclick="addKeyword('creation-form')">Додати ключове слово</button></p>

            <label><textarea name="description" placeholder="Опис" required></textarea></label>
            <button type="submit" name="action" value="add">Додати книгу</button>
        </form>
    </div>

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
                    <input type="hidden" name="_method" value="DELETE">
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <label><input type="hidden" name="keywords" value="${book.keywords}" required/></label>
                    <button type="submit" name="action" value="delete">Видалити</button>
                </form>

                <form method="post" action="admin">
                    <input type="hidden" name="_method" value="PUT">
                    <input type="hidden" name="bookId" value="${book.bookId}"/>
                    <label><input type="text" name="title" value="${book.title}"/></label>
                    <label><input type="text" name="author" value="${book.author}" required/></label>
                    <label><input type="text" name="genre" value="${book.genre}" required/></label>
                    <p>Ключові слова:<p>
                    <div id="keywords-container-${book.bookId}">
                        <c:forEach var="keyword" items="${book.keywords}">
                            <label><input type="text" name="keywords" placeholder="Ключове слово" value="${keyword}" required/></label>
                        </c:forEach>
                    </div>
                    <p><button type="button" onclick="addKeyword('${book.bookId}')">Додати ключове слово</button></p>
                    <label><textarea name="description" required>${book.description}</textarea></label>
                    <button type="submit" name="action" value="edit">Редагувати</button>
                </form>
            </div>
        </c:forEach>

    </div>
</div>
</body>

</html>
