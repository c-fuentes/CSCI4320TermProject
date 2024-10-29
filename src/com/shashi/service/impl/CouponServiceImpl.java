package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shashi.beans.CouponBean;
import com.shashi.beans.ProductBean;
import com.shashi.service.CouponService;
import com.shashi.utility.DBUtil;

public class CouponServiceImpl implements CouponService{
	@Override
	public boolean validCoupon(String code) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		boolean result = true;

		try {
			ps = con.prepareStatement("select * from coupon where code=?");

			ps.setString(1, code);

			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull()) {
				result = true;
				System.out.println(result);
			}else {
				result = false;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		System.out.println(result);
		
		return result;
		
	}
	
	@Override
	public double getCouponDiscount(String code) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		double discount = 0.0;
		
		try {
			ps = con.prepareStatement("select discount from coupon where code=?");

			ps.setString(1, code);

			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull()) {
				discount = rs.getDouble(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		return discount;
	}
	
	@Override
	public int getCouponMaxApplicableQuantity(String code) {
		Connection con = DBUtil.provideConnection();

		PreparedStatement ps = null;

		ResultSet rs = null;
		
		int maxQuantity = 999999999;
		
		try {
			ps = con.prepareStatement("select max_Quantity from coupon where code=?");

			ps.setString(1, code);

			rs = ps.executeQuery();

			if (rs.next() && !rs.wasNull()) {
				maxQuantity = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		DBUtil.closeConnection(con);
		DBUtil.closeConnection(ps);
		DBUtil.closeConnection(rs);
		
		return maxQuantity;
	}
}