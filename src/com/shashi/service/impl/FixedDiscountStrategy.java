package com.shashi.service.impl;

import com.shashi.beans.CouponBean;
import com.shashi.service.DiscountStrategy;

public class FixedDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double originalPrice, CouponBean coupon) {
        return originalPrice - coupon.getDiscount();
    }
}