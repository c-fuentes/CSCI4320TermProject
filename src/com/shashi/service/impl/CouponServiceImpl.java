package com.shashi.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shashi.beans.CouponBean;
import com.shashi.service.CouponService;
import com.shashi.service.DiscountStrategy;
import com.shashi.utility.DBUtil;

public class CouponServiceImpl implements CouponService {
    private DiscountStrategy discountStrategy;

    public void setDiscountStrategy(DiscountStrategy strategy) {
        this.discountStrategy = strategy;
    }

    @Override
    public boolean validCoupon(String code) {
        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean result = false;

        try {
            ps = con.prepareStatement("SELECT * FROM coupon WHERE code=?");
            ps.setString(1, code);
            rs = ps.executeQuery();

            if (rs.next()) { // If a record exists, the coupon is valid
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
            DBUtil.closeConnection(ps);
            DBUtil.closeConnection(rs);
        }
        return result;
    }

    @Override
    public double getCouponDiscount(String code) {
        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        double discount = 0.0;

        try {
            ps = con.prepareStatement("SELECT discount, type FROM coupon WHERE code=?");
            ps.setString(1, code);
            rs = ps.executeQuery();

            if (rs.next()) {
                double discountValue = rs.getDouble("discount");
                String type = rs.getString("type");

                System.out.println("Coupon found: Discount = " + discountValue + ", Type = " + type);

                // Create a CouponBean object
                CouponBean coupon = new CouponBean();
                coupon.setDiscount(discountValue);
                coupon.setPType(type);

                // Set the discount strategy
                if ("PERCENTAGE".equalsIgnoreCase(type)) {
                    setDiscountStrategy(new PercentageDiscountStrategy());
                } else if ("FIXED".equalsIgnoreCase(type)) {
                    setDiscountStrategy(new FixedDiscountStrategy());
                } else {
                    System.out.println("Unknown discount type: " + type);
                }

                // Apply the discount strategy
                if (discountStrategy != null) {
                    // Replace 100.0 with the actual price
                    discount = discountStrategy.applyDiscount(100.0, coupon);
                    System.out.println("Calculated discount: " + discount);
                } else {
                    System.out.println("No discount strategy found for type: " + type);
                }
            } else {
                System.out.println("Coupon code not found: " + code);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
            DBUtil.closeConnection(ps);
            DBUtil.closeConnection(rs);
        }
        return discount;
    }


    @Override
    public int getCouponMaxApplicableQuantity(String code) {
        Connection con = DBUtil.provideConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        int maxQuantity = Integer.MAX_VALUE; 

        try {
            ps = con.prepareStatement("SELECT max_Quantity FROM coupon WHERE code=?");
            ps.setString(1, code);
            rs = ps.executeQuery();

            if (rs.next()) {
                maxQuantity = rs.getInt("max_Quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(con);
            DBUtil.closeConnection(ps);
            DBUtil.closeConnection(rs);
        }
        return maxQuantity;
    }
}
