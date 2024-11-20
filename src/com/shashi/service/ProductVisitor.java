package com.shashi.service;

import com.shashi.beans.ProductBean;

public interface ProductVisitor {
    void visit(ProductBean product);
}
