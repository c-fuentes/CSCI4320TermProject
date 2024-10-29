package com.shashi.service;

import java.util.List;

import com.shashi.beans.CouponBean;

public interface CouponService{
	
	public boolean validCoupon(String code);
	
	public double getCouponDiscount(String code);
	
	public int getCouponMaxApplicableQuantity(String code);
}