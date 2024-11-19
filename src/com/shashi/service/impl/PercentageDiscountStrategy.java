package com.shashi.service.impl;

import com.shashi.beans.CouponBean;
import com.shashi.service.DiscountStrategy;

public class PercentageDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double price, CouponBean coupon) {
        double discountRate = coupon.getDiscount(); 
        return price - (price * discountRate); // Subtract percentage discount
    }
}
