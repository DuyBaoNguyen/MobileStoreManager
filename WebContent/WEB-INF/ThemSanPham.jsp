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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
        crossorigin="anonymous">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
   
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/mng_styles.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/add_edit_prod.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/limit_main_content.css">
    
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
                        <a href="${pageContext.request.contextPath}/SanPhamDangKinhDoanh" class="menu-item" id="active-item">
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
        <h4 class="title">Thêm sản phẩm</h4>
    </div>
    <div class="main-container">
        <div class="main-content">
            <form action="${pageContext.request.contextPath}/SanPhamDangKinhDoanh/ThemSanPham" method="post">
                <div class="img-container">
                    <div class="product-img">
                        <img id="image" src="" alt="your image" width="120" height="120">
                    </div>
                    <input id="imageBase64" type="hidden" name="productImageBase64">
                    <label for="upload-photo" class="edit-img-btn sub-func-btn">Sửa ảnh</label>
                    <input type="file" name="prod_image" id="upload-photo" value="" onchange="readURL(this);" />
                    <button type="button" class="del-img-btn sub-func-btn" onclick="delImage">Xóa ảnh</button>
                </div>

                <p class="sub-title">Tên điện thoại</p>
                <input type="text" name="productName" required autocomplete="off">
                
                <p class="sub-title">Giá</p>
                <input type="text" name="productPrice" required autocomplete="off">
                
                <p class="prod-info-title">Cấu hình</p>
                <p class="sub-title">Kích thước màn hình</p>
                <input type="text" name="productScreenSize" autocomplete="off">
                
                <p class="sub-title">Công nghệ màn hình</p>
                <input type="text" name="productScreenTechnology" autocomplete="off">
                
                <p class="sub-title">Hệ điều hành</p>
                <input type="text" name="productOs" autocomplete="off">
                
                <p class="sub-title">Camera trước</p>
                <input type="text" name="productFrontCamera"  autocomplete="off">
                
                <p class="sub-title">Camera sau</p>
                <input type="text" name="productPosteriorCamera" autocomplete="off">
                
                <p class="sub-title">CPU</p>
                <input type="text" name="productCpu" autocomplete="off">
                
                <p class="sub-title">RAM</p>
                <input type="text" name="productRam" autocomplete="off">
                
                <p class="sub-title">Bộ nhớ trong</p>
                <input type="text" name="productInternalMemory" autocomplete="off">
                
                <p class="sub-title">Thẻ nhớ</p>
                <input type="text" name="productMemoryStick" autocomplete="off">
                
                <p class="sub-title">Thẻ sim</p>
                <input type="text" name="productSim" autocomplete="off">
                
                <p class="sub-title">Dung lượng pin</p>
                <input type="text" name="productPin" autocomplete="off">
                
                <p class="sub-title">Màu sắc</p>
                <input type="text" name="productColors" required autocomplete="off">
                
                <input type="hidden" name="manufId" value="${manuf.id}">

                <p class="prod-info-title">Tính năng</p>
                <label class="func-item">
                    <input type="checkbox" name="waterproof">
                    Chống nước
                </label>
                <label class="func-item">
                    <input type="checkbox" name="dualCamera">
                    Camera kép
                </label>
                <label class="func-item">
                    <input type="checkbox" name="fastCharging">
                    Sạc pin nhanh
                </label>
                <label class="func-item">
                    <input type="checkbox" name="faceSecurity">
                    Bảo mật khuôn mặt
                </label>
                <label class="func-item">
                    <input type="checkbox" name="fingerprintSecurity">
                    Bảo mật vân tay
                </label>
                <input type="submit" class="save-btn func-btn" value="Lưu">
            </form>
        </div>
    </div>
    
	<c:if test="${insertProductError == true}">
	    <script>
	        $(document).ready(function () {
	            setTimeout(function () {
	                window.alert("Đã có lỗi xảy ra");
	            }, 100);
	        });
	    </script>
    </c:if>
    
    <script>
        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#image')
                        .attr('src', e.target.result)
                        .width(120)
                        .height(120);
                    $('#imageBase64')
                    .attr('value', e.target.result);
                };

                reader.readAsDataURL(input.files[0]);
            }
        }
    </script>
    
    <script>
    	function delImage() {
    		document.getElementById("image").src = "";
    		document.getElementById("imageBase64").value = "";
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