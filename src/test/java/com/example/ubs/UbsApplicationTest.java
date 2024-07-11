package com.example.ubs;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UbsApplicationTest {

    @Test
    public void contextLoads() {
        assertDoesNotThrow(() -> UbsApplication.main(new String[] {}));
    }
}
