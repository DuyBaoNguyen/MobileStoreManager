<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mobile Store</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
</head>

<body>
	<div class="main-content">
        <div id="title">Mobile store</div>
        <form action="" method="post">
            <input id="username" type="text" name="username" placeholder="Uesrname" required autofocus autocomplete="off">
            <br>
            <input id="password" type="password" name="password" placeholder="Password" required autocomplete="off">
            <br>
            <button id="login-btn" type="submit">Đăng nhập</button>
        </form>
    </div>
    
    <c:if test="${loginFail == true}">
	    <script>
	        $(document).ready(function () {
	            setTimeout(function () {
	                window.alert("Tên đăng nhập hoặc mật khẩu không chính xác");
	            }, 100);
	        });
	    </script>
    </c:if>
</body>

</html>