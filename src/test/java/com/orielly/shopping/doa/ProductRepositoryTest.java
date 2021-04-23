package com.orielly.shopping.doa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ProductRepositoryTest {

    @Autowired
    private ProductRepository doa;

    @Test
    void autoWiringWorked(){
        assertNotNull(doa);
    }

    @Test @DisplayName("Their should be at-least 3 products in DB")
    void productsInDB(){
        assertEquals(3,doa.count());

    }

}