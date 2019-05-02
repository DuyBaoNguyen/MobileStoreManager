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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/list_order.css">

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
                        <a href="${pageContext.request.contextPath}/DonHangDaThanhToan" class="menu-item" id="active-item">
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
                        <a href="${pageContext.request.contextPath}/BinhLuanChuaTraLoi" class="menu-item">
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
        <h4 class="title">Đơn hàng đã thanh toán</h4>
    </div>
    <div class="main-container">
        <div class="main-content">
            <table id="orders" class="table table-striped table-bordered" style="width:100%">
                <thead>
                    <tr>
                        <th>Mã đơn hàng</th>
                        <th>Thời gian đặt hàng</th>
                        <th>Giá trị</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                	<c:forEach var="order" items="${orders}">
	                    <tr>
	                        <td>${order.id}</td>
	                        <td>
		                        <fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${order.timeOrder}"/>
	                        </td>
	                        <td>${order.money}</td>
	                        <td>
	                            <a href="${pageContext.request.contextPath}/ChiTietDonHangDaThanhToan?orderId=${order.id}" class="choose-btn sub-func-btn">Xem</a>
	                        </td>
	                    </tr>
                	</c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    
    <script>
        $(document).ready(function () {
            $('#orders').DataTable();
        });
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