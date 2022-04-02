package com.furnitureshop;

import com.furnitureshop.product.entity.Product;
import com.furnitureshop.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FurnitureShopApplicationTests {
    @Autowired
    private ProductService service;
}
