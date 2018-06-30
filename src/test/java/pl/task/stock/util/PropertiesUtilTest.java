package pl.task.stock.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertiesUtilTest {

    private final static String URL = "https://www.alphavantage.co/query";

    @Test
    public void getUrlFromProperties() {
        String url = PropertiesUtil.getUrlFromProperties();
        assertEquals(URL, url);
    }
}