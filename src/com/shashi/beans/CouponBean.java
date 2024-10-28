package com.shashi.beans;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CouponBean implements Serializable {

	public CouponBean() {
	}

	public String code;

	public double discount;
	
	public String validUntil;
	
	public String ptype;

	public int maxQuantity;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public String getValidUntil() {
		return validUntil;
	}
	
	public void setValidUntil(String date) {
		this.validUntil = date;
	}

	public String getPType() {
		return ptype;
	}
	
	public void setPType(String ptype) {
		this.ptype = ptype;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int quantity) {
		this.maxQuantity = quantity;
	}

	public CouponBean(String code, double discount, String validUntil, String ptype, int quantity) {
		super();
		this.code = code;
		this.discount = discount;
		this.validUntil = validUntil;
		this.ptype = ptype;
		this.maxQuantity = quantity;
	}

}
