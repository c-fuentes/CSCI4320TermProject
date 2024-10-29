package com.shashi.srv;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shashi.service.impl.CouponServiceImpl;

/**
 * Servlet implementation class ApplyCoupon
 */
@WebServlet("/ApplyCoupon")
public class ApplyCoupon extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyCoupon() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String couponCode = request.getParameter("couponCode");
		CouponServiceImpl coupon = new CouponServiceImpl();
		HttpSession session = request.getSession();
		if(coupon.validCoupon(couponCode)) {
			session.setAttribute("coupon", couponCode);
			session.setAttribute("couponDiscount", coupon.getCouponDiscount(couponCode));
		}else {
			session.setAttribute("couponDiscount", 0.0);
		}
		RequestDispatcher rd = request.getRequestDispatcher("cartDetails.jsp");

		rd.include(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
