package com.virtualpairprogrammers.isbntools;

public class StockManager {
    private ExternalISBNDataService dataBaseService;
    private ExternalISBNDataService webService;

    public void setDataBaseService(ExternalISBNDataService dataBaseService) {
        this.dataBaseService = dataBaseService;
    }

    public void setWebService(ExternalISBNDataService webService) {
        this.webService = webService;
    }

    public String getLocatorCode(String isbn) {
        Book book = dataBaseService.lookup(isbn);
        if(book==null) book = webService.lookup(isbn);
        StringBuilder locatorCode = new StringBuilder();
        locatorCode.append(isbn.substring(isbn.length()-4));
        locatorCode.append(book.getAuthor().substring(0,1));
        locatorCode.append(book.getTitle().split(" ").length);
        return locatorCode.toString();
    }
}
