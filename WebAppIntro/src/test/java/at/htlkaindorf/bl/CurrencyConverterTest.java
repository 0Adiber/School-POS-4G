/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlkaindorf.bl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CurrencyConverterTest {
    
    public CurrencyConverterTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }

    /**
     * Test of convertFromEurToIndex method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testConvertFromEurToIndex() {
        System.out.println("convertFromEurToIndex");
        float value = 1.0F;
        int targetIndex = 0;
        float expResult = 1.62F;
        float result = CurrencyConverter.convertFromEurToIndex(value, targetIndex);
        assertEquals(expResult, result, 0.0);
    }
    
    @org.junit.jupiter.api.Test
    public void testConvertFromEurToInvalidIndex() {
        System.out.println("convertFromEurToInvalidIndex");
        float value = 1.0F;
        int targetIndex = -4;
        try {
            CurrencyConverter.convertFromEurToIndex(value, targetIndex);
            
            fail();
        }catch(IllegalArgumentException e) {
            assertEquals("currency index not supported", e.getMessage());
        }
    }

    /**
     * Test of getCurrencyNameFromIndex method, of class CurrencyConverter.
     */
    @org.junit.jupiter.api.Test
    public void testGetCurrencyNameFromIndex() {
        System.out.println("getCurrencyNameFromIndex");
        int targetIndex = 0;
        String expResult = "Australische Dollar";
        String result = CurrencyConverter.getCurrencyNameFromIndex(targetIndex);
        assertEquals(expResult, result);
    }
    
}
