package com.shashi.service.impl;

import com.shashi.service.ProductVisitor; 
import com.shashi.beans.ProductBean; 

public class ProductPriceUpdateVisitor implements ProductVisitor {
    private double newPrice;

    public ProductPriceUpdateVisitor(double newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public void visit(ProductBean product) {
        product.setProdPrice(newPrice);
    }
}

