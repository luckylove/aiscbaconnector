package com.ais.cba.service;

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

       // AISService.GetCallbackRequestLog("11", "aaaaaaddddddddddddd");
        /*CBA_REQUEST re = new CBA_REQUEST();
        re.setIdx("afe22");
        re.setAgentId("aaaaaa");
        re.setAgentVdn("fffffff");
        AISService.AddCallbackRequest("cc", re);*/
       /* CBA_ACT_DETAIL atc = new CBA_ACT_DETAIL();
        atc.setActdId("dd");
        atc.setActId("afie");
        atc.setResult("adddfie");
        AISService.AddActDetail("aa", atc);*/

        //AISService.GetSMSConf("aaaaaaa", "1234", 1l, 2l);

      /*  AV_INFOVIEW oneX = new AV_INFOVIEW();
        oneX.setAccBal("aaaaaaaaaa");
        oneX.setSiteid("kkk");
        oneX.setGuid("5555");
        oneX.setAni("bbb");
        AISService.AddOneX("aaaaaaaa", oneX);*/

        AISService.GetService("11", "12");

    }


}


