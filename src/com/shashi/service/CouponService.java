package com.shashi.service;

public interface CouponService {
    boolean validCoupon(String code);
    double getCouponDiscount(String code);
    int getCouponMaxApplicableQuantity(String code);
}
