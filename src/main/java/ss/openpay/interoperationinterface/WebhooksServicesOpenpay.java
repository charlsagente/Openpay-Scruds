package ss.openpay.interoperationinterface;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import mx.openpay.client.Webhook;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import ss.openpay.logicimpl.openpay.WebHookScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by carlos on 1/04/16.
 */

/**
 * Servicios REST para SCRUD Webhooks
 * En estos servicios se dan de alta los servicios a los que openpay se va a comunicar con las notificaciones configuradas
 * DOC: http://www.openpay.mx/docs/webhooks.html
 */
@Path("/openpay/webhooks")
public class WebhooksServicesOpenpay {
    @PUT
    @Produces("application/json")
    public String setSuscription(String requestedObject){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(requestedObject);
        JsonArray events=jsonParams.getAsJsonArray("events");
        String url = UtilOpenPay.getJsonValue(jsonParams, "url");
        Iterator<JsonElement> crunchifyIterator = events.iterator();
        List<JsonElement> listEvents= new ArrayList<>();

        while (crunchifyIterator.hasNext()) {

            listEvents.add(crunchifyIterator.next());
        }
        return WebHookScruds.createWebHook(url, (ArrayList) listEvents);
    }

    @DELETE
    @Produces("application/json")
    public Response deleted(String requestedObject) {
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(requestedObject);
        String idhw = UtilOpenPay.getJsonValue(jsonParams, "idwh");
        try {
            WebHookScruds.deleteWebHook(idhw);
            return Response.ok().build();
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
            return Response.status(300).build();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
            return Response.status(300).build();
        }
    }


    @GET
    @Produces("application/json")
    public String getWebHook(@QueryParam("idWebHook") String idWebHook){
        String obj="";
        try {
            Webhook Webhook=WebHookScruds.getWebHook(idWebHook);
            obj=UtilOpenPay.toJson(Webhook);

        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @POST
    @Produces("application/json")
    public String getLists(){
        String json="";
        try {
            json = UtilOpenPay.toJson(WebHookScruds.listWebHooks());
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
        return json;
    }
}
