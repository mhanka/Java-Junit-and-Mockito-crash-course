package com.virtualpairprogrammers.isbntools;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StockManagementTests {

    ExternalISBNDataService testWebService;
    ExternalISBNDataService testDataBaseService;
    StockManager stockManager;
    private static final String TITLE = "Of Mice and Men";
    private static final String AUTHOR = "J.Steinbeck";
    private static final String ISBN = "0140177396";

    @BeforeEach
    public void setup(){
        testDataBaseService = mock(ExternalISBNDataService.class);
        testWebService = mock(ExternalISBNDataService.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        stockManager.setDataBaseService(testDataBaseService);
    }

    @Test
    public void testCorrectLocatorCode(){
        when(testWebService.lookup(anyString())).thenReturn(new Book(ISBN, TITLE,AUTHOR));
        when(testDataBaseService.lookup(anyString())).thenReturn(null);
        String locatorCode= stockManager.getLocatorCode(ISBN);
        assertEquals("7396J4", locatorCode);
    }

    @Test
    public void dataIsPresentValidateDatabaseService(){
        when(testDataBaseService.lookup(ISBN)).thenReturn(new Book(ISBN,TITLE,AUTHOR));
        stockManager.getLocatorCode(ISBN);
        verify(testDataBaseService).lookup(ISBN);
        verify(testWebService,never()).lookup(anyString());
    }

    @Test
    public void dataIsNotPresentValidateWebService(){
        when(testDataBaseService.lookup(ISBN)).thenReturn(null);
        when(testWebService.lookup(ISBN)).thenReturn(new Book(ISBN, TITLE, AUTHOR));
        stockManager.getLocatorCode(ISBN);
        verify(testDataBaseService).lookup(ISBN);
        verify(testWebService).lookup(ISBN);

    }
}

