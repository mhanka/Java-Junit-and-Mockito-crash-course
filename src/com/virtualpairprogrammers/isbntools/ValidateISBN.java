package com.virtualpairprogrammers.isbntools;

public class ValidateISBN {

    private static final int ISBN_LENGTH = 10;

    public boolean checkISBN(String isbn) {
        containsStringValueOrNot(isbn);
        isIt10Digits(isbn);
        int total =0;
        for (int i = 0; i< ISBN_LENGTH; i++){
            total+= isbn.charAt(i)*(ISBN_LENGTH-i);
        }
        return total % (ISBN_LENGTH+1) == 0;
    }

    private static void isIt10Digits(String isbn) {
        if(isbn.length()!=ISBN_LENGTH) throw new NumberFormatException("isbn not 10 digits");
    }

    private static void containsStringValueOrNot(String isbn) {
        if (!isbn.matches(".*\\d.*")) {
            throw new NumberFormatException("The string contains words.");
        }
    }
}
