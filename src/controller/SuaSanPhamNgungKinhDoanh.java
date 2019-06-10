package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import model.Manufacturer;
import model.Product;

@WebServlet("/SanPhamNgungKinhDoanh/SuaSanPham")
public class SuaSanPhamNgungKinhDoanh extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SuaSanPhamNgungKinhDoanh() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Product product = new Product(Integer.parseInt(request.getParameter("productId")));
			ProductDAO.getProduct(product);
			request.setAttribute("product", product);

			Boolean updateInactProductError = (Boolean) getServletContext().getAttribute("updateInactProductError");
			if (updateInactProductError != null) {
				getServletContext().removeAttribute("updateInactProductError");
			} else {
				updateInactProductError = false;
			}
			request.setAttribute("updateInactProductError", updateInactProductError);

			request.getRequestDispatcher("/WEB-INF/SuaSanPhamNgungKinhDoanh.jsp").forward(request, response);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamNgungKinhDoanh");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			Product product = new Product(Integer.parseInt(request.getParameter("productId")));

			String productName = StringEscapeUtils.escapeHtml(request.getParameter("productName"));
			product.setName(productName);

			String productPrice = request.getParameter("productPrice");
			if (productPrice.length() == 0) {
				product.setPrice(null);
			} else {
				product.setPrice(Integer.parseInt(productPrice));
			}

			String productImageBase64 = request.getParameter("productImageBase64");
			if (productImageBase64.length() == 0) {
				product.setImageBase64(null);
			} else {
				if (productImageBase64.startsWith("data:image/jpeg")) {
					product.setImageBase64(productImageBase64.replace("data:image/jpeg;base64,", ""));
				} else if (productImageBase64.startsWith("data:image/png")) {
					product.setImageBase64(productImageBase64.replace("data:image/png;base64,", ""));
				}
			}

			String productScreenSize = request.getParameter("productScreenSize");
			if (productScreenSize.length() == 0) {
				product.setScreenSize(null);
			} else {
				product.setScreenSize(Float.parseFloat(productScreenSize));
			}

			String productScreenTechnology = StringEscapeUtils
					.escapeHtml(request.getParameter("productScreenTechnology"));
			if (productScreenTechnology.length() == 0) {
				product.setScreenTechnology(null);
			} else {
				product.setScreenTechnology(productScreenTechnology);
			}

			String productOs = StringEscapeUtils.escapeHtml(request.getParameter("productOs"));
			if (productOs.length() == 0) {
				product.setOs(null);
			} else {
				product.setOs(productOs);
			}

			String productFrontCamera = StringEscapeUtils.escapeHtml(request.getParameter("productFrontCamera"));
			if (productFrontCamera.length() == 0) {
				product.setFrontCamera(null);
			} else {
				product.setFrontCamera(productFrontCamera);
			}

			String productPosteriorCamera = StringEscapeUtils
					.escapeHtml(request.getParameter("productPosteriorCamera"));
			if (productPosteriorCamera.length() == 0) {
				product.setPosteriorCamera(null);
			} else {
				product.setPosteriorCamera(productPosteriorCamera);
			}

			String productCpu = StringEscapeUtils.escapeHtml(request.getParameter("productCpu"));
			if (productCpu.length() == 0) {
				product.setCpu(null);
			} else {
				product.setCpu(productCpu);
			}

			String productRam = StringEscapeUtils.escapeHtml(request.getParameter("productRam"));
			if (productRam.length() == 0) {
				product.setRam(null);
			} else {
				product.setRam(productRam);
			}

			String productInternalMemory = StringEscapeUtils.escapeHtml(request.getParameter("productInternalMemory"));
			if (productInternalMemory.length() == 0) {
				product.setInternalMemory(null);
			} else {
				product.setInternalMemory(productInternalMemory);
			}

			String productMemoryStick = StringEscapeUtils.escapeHtml(request.getParameter("productMemoryStick"));
			if (productMemoryStick.length() == 0) {
				product.setMemoryStick(null);
			} else {
				product.setMemoryStick(productMemoryStick);
			}

			String productSim = StringEscapeUtils.escapeHtml(request.getParameter("productSim"));
			if (productSim.length() == 0) {
				product.setSim(null);
			} else {
				product.setSim(productSim);
			}

			String productPin = StringEscapeUtils.escapeHtml(request.getParameter("productPin"));
			if (productPin.length() == 0) {
				product.setPin(null);
			} else {
				product.setPin(productPin);
			}

			List<String> productColors = new ArrayList<String>();
			String[] colors = request.getParameter("productColors").split(",");
			for (int i = 0; i < colors.length; i++) {
				colors[i] = colors[i].trim();
				productColors.add(StringEscapeUtils.escapeHtml(colors[i]));
			}
			product.setColors(productColors);

			Manufacturer manuf = new Manufacturer(Integer.parseInt(request.getParameter("manufId")));
			product.setManufacturer(manuf);

			if (request.getParameter("waterproof") != null) {
				product.setWaterproof(true);
			} else {
				product.setWaterproof(false);
			}

			if (request.getParameter("dualCamera") != null) {
				product.setDualCamera(true);
			} else {
				product.setDualCamera(false);
			}

			if (request.getParameter("fastCharging") != null) {
				product.setFastCharging(true);
			} else {
				product.setFastCharging(false);
			}

			if (request.getParameter("faceSecurity") != null) {
				product.setFaceSecurity(true);
			} else {
				product.setFaceSecurity(false);
			}

			if (request.getParameter("fingerprintSecurity") != null) {
				product.setFingerprintSecurity(true);
			} else {
				product.setFingerprintSecurity(false);
			}

			boolean updateInactProductError = ProductDAO.updateProduct(product);
			if (updateInactProductError == true) {
				getServletContext().setAttribute("updateInactProductError", updateInactProductError);
				response.sendRedirect(
						request.getContextPath() + "/SanPhamNgungKinhDoanh/SuaSanPham?productId=" + product.getId());
			} else {
				response.sendRedirect(
						request.getContextPath() + "/SanPhamNgungKinhDoanh/HangSanPham?manufId=" + manuf.getId());
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/SanPhamNgungKinhDoanh");
		}
	}

}
