package com.ais.service;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * User: son.nguyen
 * Date: 10/24/13
 * Time: 1:34 PM
 */
public class MainTest {

    protected static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws Exception {
        //AISService.initService();
        //AISService.GetParam("cc", "EARLIEST_CALL_TIME");
       // AISService.GetBlackList("cc", "222222");
       // AISService.GetDNC("cc", "222222");
        AISService.GetTimeZone("cc", "zzz");

    }


}


