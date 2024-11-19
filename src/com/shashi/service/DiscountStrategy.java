package com.shashi.service;

import com.shashi.beans.CouponBean;

public interface DiscountStrategy {
    double applyDiscount(double originalPrice, CouponBean coupon);
}
