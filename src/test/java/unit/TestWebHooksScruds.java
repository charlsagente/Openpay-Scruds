package unit;

import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import org.junit.Test;
import ss.openpay.logicimpl.openpay.WebHookScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

import java.util.ArrayList;

/**
 * Created by Carlos Perez on 22/04/2016.
 */
public class TestWebHooksScruds {
    @Test
    public void createWebHook(){
        ArrayList<String> listEvents= new ArrayList<>();

        listEvents.add("charge.succeeded");

        System.out.println(WebHookScruds.createWebHook("dirección pública de un servicio", listEvents));
    }

    @Test
    public void listWebHooks() {
        try {
            System.out.println(UtilOpenPay.toJson(WebHookScruds.listWebHooks()));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getWebHook(){
        try {
            System.out.println(UtilOpenPay.toJson(WebHookScruds.getWebHook("wtr7xg26k2lbhsh7bbuh")));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteWebHook(){
        try {
            WebHookScruds.deleteWebHook("");
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }
}
