package com.shashi.srv;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.shashi.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class WorkAroundAddProductLineSrv
 */
@WebServlet("/WorkAroundAddProductLineSrv")
@MultipartConfig(maxFileSize = 16177215)
public class WorkAroundAddProductLineSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		String userType = (String) session.getAttribute("usertype");
		String userName = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		int count = (int) session.getAttribute("pCount");
		if (userType == null || !userType.equals("admin")) {

			response.sendRedirect("login.jsp?message=Access Denied!");

		}

		else if (userName == null || password == null) {

			response.sendRedirect("login.jsp?message=Session Expired, Login Again to Continue!");
		}
		if(count > 0) {
		String status = "Product Registration Failed!";
		String prodName = request.getParameter("name");
		String prodType = request.getParameter("type");
		String prodInfo = request.getParameter("info");
		double prodPrice = Double.parseDouble(request.getParameter("price"));
		int prodQuantity = Integer.parseInt(request.getParameter("quantity"));

		Part part = request.getPart("image");

		InputStream inputStream = part.getInputStream();

		InputStream prodImage = inputStream;

		ProductServiceImpl product = new ProductServiceImpl();
		count +=1;
		session.setAttribute("pCount", count);
		System.out.println((int) session.getAttribute("pCount"));
		status = product.addProduct(prodName, prodType, prodInfo, prodPrice, prodQuantity, prodImage);
		RequestDispatcher rd = request.getRequestDispatcher("AddProductLine.jsp?message=" + status);
		rd.forward(request, response);
		}else{
			String prodType = request.getParameter("ptype");
			int typeQuantity = Integer.parseInt(request.getParameter("pnum"));
			session.setAttribute("ptype", prodType);
			session.setAttribute("numOfNewProducts", typeQuantity);
			count += 1;
			session.setAttribute("pCount", count);
			RequestDispatcher rd = request.getRequestDispatcher("AddProductLine.jsp");
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
