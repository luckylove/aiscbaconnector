package com.ais.cba.service;

import com.ais.cba.service.model.config.CBA_REQUEST;
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
       // AISService.GetPriority("cc", 1l);
       // AISService.GetInboundConf("cc", "dc", "dc", "dc", "dc", "dc", "dc");

        CBA_REQUEST re = new CBA_REQUEST();
        re.setIdx("aaaaaaddddddddddddd");
        re.setAgentId("aaaaaa");
        re.setAgentVdn("ddddd");
        AISService.AddCallbackRequest("cc", re);

    }


}


