package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateISBNJavaTest {

    @Test
    public void checkValidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("1761561839");
        assertTrue(result);
    }
    @Test
    public void checkInvalidISBN(){
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("1761561836");
        assertFalse(result);
    }
    @Test
    public void checkNoOfDigits(){
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,
                ()->{
                    validator.checkISBN("176156189");
                });

    }
    @Test
    public void checkAllDigitsAreNumbers(){
        ValidateISBN validator = new ValidateISBN();
        assertThrows(NumberFormatException.class,()->{validator.checkISBN("Hello World");});
    }

}
