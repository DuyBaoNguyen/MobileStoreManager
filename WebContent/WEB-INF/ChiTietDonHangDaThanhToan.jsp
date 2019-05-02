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
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mng_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/order.css">

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
        <h4 class="title">Đơn hàng chưa thanh toán</h4>
    </div>
    <div class="main-container">
        <div class="main-content">
            <form action="${pageContext.request.contextPath}/SuaDonHang" method="get">
                <div class="left-container container-item">
                    <div class="order section-item">
                        <h4 class="title">Thông tin đơn hàng</h4>
                        <input type="hidden" name="orderId" value="${order.id}">
                        <div>
                            <span class="sub-title">Mã đơn hàng:</span>
                            <span class="order-info">${order.id}</span>
                        </div>
                        <div>
                            <span class="sub-title">Thời gian đặt hàng:</span>
                            <span class="order-info">
                            	<fmt:formatDate pattern="dd-MM-yyyy HH:mm:ss" value="${order.timeOrder}"/>
                            </span>
                        </div>
                        <div>
                            <span class="sub-title">Giá trị:</span>
                            <span class="order-info">${order.money}</span>
                        </div>
                        <hr>
                        <h4 class="title">Thông tin khách hàng</h4>
                        <p class="sub-title">Họ tên</p>
                        <input type="text" name="personName" value="${order.personName}" readonly required autocomplete="off">
                        <p class="sub-title">Số điện thoại</p>
                        <input type="text" name="personPhone" value="${order.personPhone}" readonly required autocomplete="off">
                        <br>
                        <label>
                            <input type="radio" name="personSex" value="male" readonly
                            	<c:if test="${order.personSex == true}">
                            		checked
                            	</c:if>
                            >
                            Anh
                        </label>
                        <label>
                            <input type="radio" name="personSex" value="female" readonly
                            	<c:if test="${order.personSex == false}">
                            		checked
                            	</c:if>
                            >
                            Chị
                        </label>
                        <hr>
                        <h4 class="title">Địa chỉ giao hàng</h4>
                        <p class="sub-title">Địa chỉ</p>
                        <input type="text" name="personAddress" value="${order.personAddress}" required autocomplete="off">
                  	</div>
                </div>
                <div class="right-container container-item">
                    <div class="section-item">
                    
                        <c:forEach var="orderDetail" items="${order.orderDetails}" varStatus="loop">
	                        <div class="product">
	                        	<input type="hidden" name="product${loop.index}Id" value="${orderDetail.product.id}">
	                        	<input type="hidden" name="product${loop.index}UnitPrice" value="${orderDetail.unitPrice}">
	                            <div class="img-container">
	                                <img src="data:image/jpeg;base64,${orderDetail.product.imageBase64}" alt="${orderDetail.product.name}" width="100px" height="100">
	                            </div>
	                            <div class="product-option">
	                                 <h5 class="product-name">
	                                   	<strong>${orderDetail.product.name}</strong>
	                                </h5>
	                                <h5 class="product-cost"><strong>${orderDetail.money}</strong></h5>
	                                <span>Màu sắc</span>
	                                <select name="product${loop.index}Color" readonly>
	                                   	<c:forEach var="color" items="${orderDetail.product.colors}">
	                                   		<option value="${color}"
	                                   			<c:if test="${orderDetail.color == color}">
	                                   				selected
	                                   			</c:if>
	                                   		>${color}</option>
	                                   	</c:forEach>
	                                </select>
	                                <br>
	                                <span>Số lượng</span>
	                                <div class="amount-container">
	                                    <button type="button" class="dec" onclick="dec(${loop.index})">-</button>
	                                    <input type="text" class="product-amount" name="product${loop.index}Amount"
	                                        value="${orderDetail.amountProduct}" required readonly>
	                                    <button type="button" class="inc" onclick="inc(${loop.index})">+</button>
	                                </div>
                                </div>
	                        </div>
	                        <hr>
                        </c:forEach>
                        
                    </div>
                </div>
            </form>
        </div>
    </div>
    
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