<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Mobile Store</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
   
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mng_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/limit_main_content.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/comment.css">

    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
</head>

<body>
    <div class="left-menu-container">
        <a class="nav-logo" href="${pageContext.request.contextPath}/SanPhamHot">
            <img src="${pageContext.request.contextPath}/images/logo.png" width="40">
            Mobile Store
        </a>
        <div class="left-menu-content">
            <div class="left-menu">
                <button class="accordion">
                    <i class="material-icons">home</i>
                    Trang chủ
                </button>
                <div class="panel">
                    <div class="menu-item-container">
                        <a href="${pageContext.request.contextPath}/SanPhamHot" class="menu-item">
                            Sản phẩm hot
                        </a>
                        <a href="${pageContext.request.contextPath}/SanPhamMoi" class="menu-item">
                            Sản phẩm mới
                        </a>
                    </div>
                </div>

                <a class="menu-btn" href="${pageContext.request.contextPath}/HangSanPham">
                    <i class="material-icons">account_balance</i>
                    Hãng sản phẩm
                </a>

                <button class="accordion">
                    <i class="material-icons">phone_iphone</i>
                    Sản phẩm
                </button>
                <div class="panel">
                    <div class="menu-item-container">
                        <a href="${pageContext.request.contextPath}/SanPhamDangKinhDoanh" class="menu-item">
                            Đang kinh doanh
                        </a>
                        <a href="${pageContext.request.contextPath}/SanPhamNgungKinhDoanh" class="menu-item">
                            Ngừng kinh doanh
                        </a>
                    </div>
                </div>

                <button class="accordion">
                    <i class="material-icons">list_alt</i>
                    Đơn hàng
                </button>
                <div class="panel">
                    <div class="menu-item-container">
                        <a href="${pageContext.request.contextPath}/DonHangChuaThanhToan" class="menu-item">
                            Chưa thanh toán
                        </a>
                        <a href="${pageContext.request.contextPath}/DonHangDaThanhToan" class="menu-item">
                            Đã thanh toán
                        </a>
                    </div>
                </div>

                <button class="accordion">
                    <i class="material-icons">comment</i>
                    Bình luận
                </button>
                <div class="panel">
                    <div class="menu-item-container">
                        <a href="${pageContext.request.contextPath}/BinhLuanChuaTraLoi" class="menu-item" id="active-item">
                            Chưa trả lời
                        </a>
                        <a href="${pageContext.request.contextPath}/BinhLuanDaTraLoi" class="menu-item">
                            Đã trả lời
                        </a>
                    </div>
                </div>
                <a class="menu-btn" href="${pageContext.request.contextPath}/TaiKhoan">
                    <i class="material-icons">account_circle</i>
                    Tài khoản
                </a>
                <a class="menu-btn" href="${pageContext.request.contextPath}/DangXuat">
                    <i class="material-icons">arrow_right_alt</i>
                    Đăng xuất
                </a>
            </div>
        </div>
    </div>
    <div class="top-nav">
        <h4 class="title">Bình luận sản phẩm ${comment.product.name}</h4>
    </div>
    <div class="main-container">
        <div class="main-content">
        	<div class="comment">
	        	<c:if test="${comment.answerd == true}">
	            	<div class="media">
	            </c:if>
	            <c:if test="${comment.answerd == false }">
	            	<div class="media active-comment">
	            </c:if>
                    <div class="media-left">
                        <c:if test="${comment.personSex == true}">
                           	<img src="${pageContext.request.contextPath}/images/man.png" class="media-object" style="width:45px">
                        </c:if>
                        <c:if test="${comment.personSex == false}">
                        	<img src="${pageContext.request.contextPath}/images/woman.png" class="media-object" style="width:45px">
                        </c:if>	
                    </div>
                    <div class="media-body">
                        <h5 class="media-heading">
                        	${comment.personName}
                        	<small><i><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${comment.time}"/></i></small>
                       	</h5>
                        <p>${comment.content}</p>
                        <button class="answer-btn" id="${comment.id}" onclick="answer(this)">Trả lời</button>
                        
			            <c:if test="${comment.answerd == false }">
			            	<a href="${pageContext.request.contextPath}/BoQuaBinhLuan?commentId=${comment.id}&ignoreCommentId=${comment.id}" class="ignore-btn">Bỏ qua</a>
			            </c:if>
                        <button class="del-btn" onclick="delComment('${comment.id}')">Xóa</button>
                    </div>
                </div>
                <hr>
                <div class="answer-form" id="af${comment.id}">
                    <form action="${pageContext.request.contextPath}/TraLoiBinhLuan" method="get">
                    	<input type="hidden" name="commentId" value="${comment.id}">
                    	<input type="hidden" name="answerCommentId" value="${comment.id}">
                    	<input type="hidden" name="productId" value="${comment.product.id}">
                        <textarea class="cmt-txt" name="content" placeholder="Hãy nhập bình luận" required></textarea>
                        <input type="submit" class="func-btn send-btn" value="Gửi">
                        <button type="button" id="close_af${comment.id}" class="func-btn" onclick="closeaf(this)">Đóng</button>
                    </form>
                </div>
                
                <c:forEach var="comment1" items="${comment.comments}">
                	<c:if test="${comment1.answerd == true}">
	            		<div class="media">
		            </c:if>
		            <c:if test="${comment1.answerd == false }">
		            	<div class="media active-comment">
		            </c:if>
	                    <div class="media-left">
	                        <c:if test="${comment1.personSex == true || (comment1.answerAccount != null && comment1.answerAccount.sex == true)}">
                            	<img src="${pageContext.request.contextPath}/images/man.png" class="media-object" style="width:45px">
                            </c:if>
                            <c:if test="${comment1.personSex == false || (comment1.answerAccount != null && comment1.answerAccount.sex == false)}">
                            	<img src="${pageContext.request.contextPath}/images/woman.png" class="media-object" style="width:45px">
                            </c:if>	
	                    </div>
	                    <div class="media-body">
	                        <h5 class="media-heading">
	                        	${comment1.personName}
	                        	<c:if test="${comment1.answerAccount != null}">
		                        	${comment1.answerAccount.displayName} <mark>Quản trị viên</mark>
		                        </c:if>
	                        	<small><i><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${comment1.time}"/></i></small>
	                       	</h5>
	                        <p>${comment1.content}</p>
	                        <c:if test="${comment1.answerAccount == null }">
	                        	<button class="answer-btn" id="${comment1.id}" onclick="answer(this)">Trả lời</button>
	                        </c:if>
				            <c:if test="${comment1.answerd == false }">
				            	<a href="${pageContext.request.contextPath}/BoQuaBinhLuan?commentId=${comment.id}&ignoreCommentId=${comment1.id}" class="ignore-btn">Bỏ qua</a>
				            </c:if>
	                        <button class="del-btn" onclick="delComment('${comment1.id}')">Xóa</button>
	                    </div>
	                </div>
	                <div class="answer-form" id="af${comment1.id}">
	                    <form action="${pageContext.request.contextPath}/TraLoiBinhLuan" method="get">
	                        <input type="hidden" name="commentId" value="${comment.id}">
	                        <input type="hidden" name="answerCommentId" value="${comment1.id}">
                        	<input type="hidden" name="productId" value="${comment.product.id}">
	                        <textarea class="cmt-txt" name="content" placeholder="Hãy nhập bình luận" required></textarea>
	                        <input type="submit" class="func-btn send-btn" value="Gửi">
	                        <button type="button" id="close_af${comment1.id}" class="func-btn" onclick="closeaf(this)">Đóng</button>
	                   	</form>
	                </div>
	                
	                <c:forEach var="comment2" items="${comment1.comments}">
	            		<div class="media">
		                    <div class="media-left">
		                        <c:if test="${comment2.answerAccount.sex == true}">
		                           	<img src="${pageContext.request.contextPath}/images/man.png" class="media-object" style="width:45px">
		                        </c:if>
		                        <c:if test="${comment2.answerAccount.sex == false}">
		                        	<img src="${pageContext.request.contextPath}/images/woman.png" class="media-object" style="width:45px">
		                        </c:if>	
		                    </div>
		                    <div class="media-body">
		                        <h5 class="media-heading">
		                        	${comment2.answerAccount.displayName} <mark>Quản trị viên</mark>
			                        <small><i><fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${comment1.time}"/></i></small>
		                       	</h5>
		                        <p>${comment2.content}</p>
		                        <button class="del-btn" onclick="delComment('${comment2.id}')">Xóa</button>
		                    </div>
		                </div>
	                </c:forEach>
	                
                </c:forEach>
                
            </div>
        </div>
    </div>
    
    <script>
        function delComment(commentId) {
            if (window.confirm("Bạn chắc chắn muốn xóa?")) {
                window.location = "${pageContext.request.contextPath}/XoaBinhLuan?commentId=" + commentId;
            }
        }
    </script>

	<c:if test="${deleteCommentError == true || ignoreCommentError == true || replyCommentError == true}">
	    <script>
	        $(document).ready(function () {
	            setTimeout(function () {
	                window.alert("Đã có lỗi xảy ra");
	            }, 100);
	        });
	    </script>
    </c:if>
    
    <script>
        function answer(button) {
            var af_id = "af" + button.id;
            var answer_form = document.getElementById(af_id);
            answer_form.style.display = "block";
        }
    </script>

    <script>
        function closeaf(button) {
            var af_id = button.id.replace('close_', '');
            var answer_form = document.getElementById(af_id);
            answer_form.style.display = "none";
        }
    </script>

    <script>
        var acc = document.getElementsByClassName("accordion");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function () {
                this.classList.toggle("active");
                var panel = this.nextElementSibling;
                if (panel.style.maxHeight) {
                    panel.style.maxHeight = null;
                } else {
                    panel.style.maxHeight = panel.scrollHeight + "px";
                }
            });
        }
    </script>
</body>

</html>