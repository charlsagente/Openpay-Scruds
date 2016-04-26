package ss.openpay.interoperationinterface;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ss.openpay.logicimpl.OpenpayPayment;

import ss.openpay.logicimpl.util.UtilOpenPay;

import javax.ws.rs.*;

/**
 * Created by Carlos Perez on 29/03/2016.
 */

@Path("/openpay")
public class PaymentServicesOpenpay {
    private static final transient Logger log = LoggerFactory
            .getLogger(PaymentServicesOpenpay.class);

    @POST
    @Path("/payment/recurrent")
    @Produces("application/json")
    public String subscribe(String jsonString){

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(jsonString);
        String token_id = UtilOpenPay.getJsonValue(jsonParams, "token_id");
        String user_name = UtilOpenPay.getJsonValue(jsonParams, "user_name");
        String email = UtilOpenPay.getJsonValue(jsonParams, "email");
        String device_sesion_id = UtilOpenPay.getJsonValue(jsonParams, "device_sesion_id");
        String planId = UtilOpenPay.getJsonValue(jsonParams, "planId");



        return OpenpayPayment.Suscribe(token_id, user_name, email, device_sesion_id, planId);
    }


    @POST
    @Path("/payment/ondemand")
    @Produces("application/json")
    public String ondemand(String jsonString){

        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(jsonString);
        String token_id = UtilOpenPay.getJsonValue(jsonParams, "token_id");
        String user_name = UtilOpenPay.getJsonValue(jsonParams, "user_name");
        String email = UtilOpenPay.getJsonValue(jsonParams, "email");
        String device_sesion_id = UtilOpenPay.getJsonValue(jsonParams, "device_sesion_id");


        return OpenpayPayment.onDemand(token_id, user_name, email,device_sesion_id);
    }

    @POST
    @Path("/payment/tdc")
    @Produces("application/json")
    public String tdc(String jsonString){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(jsonString);
        String token_id = UtilOpenPay.getJsonValue(jsonParams, "token_id");
        String amount = UtilOpenPay.getJsonValue(jsonParams, "amount");
        String device_sesion_id = UtilOpenPay.getJsonValue(jsonParams, "device_sesion_id");
        String email = UtilOpenPay.getJsonValue(jsonParams, "email");
        String user_name = UtilOpenPay.getJsonValue(jsonParams, "user_name");


        return OpenpayPayment.directPayTDC(token_id,amount,device_sesion_id,email,user_name);

    }

    @POST
    @Path("payment/store")
    @Produces("application/json")
    public String store(String jsonString){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(jsonString);
        String description = UtilOpenPay.getJsonValue(jsonParams, "description");
        String amount = UtilOpenPay.getJsonValue(jsonParams, "amount");
        String name = UtilOpenPay.getJsonValue(jsonParams, "name");
        String mail = UtilOpenPay.getJsonValue(jsonParams, "mail");
        return OpenpayPayment.payStore(description,amount,name,mail);
    }

    @POST
    @Path("payment/bank")
    @Produces("application/json")
    public String bank(String jsonString){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(jsonString);
        String description = UtilOpenPay.getJsonValue(jsonParams, "description");
        String amount = UtilOpenPay.getJsonValue(jsonParams, "amount");
        String name = UtilOpenPay.getJsonValue(jsonParams, "name");
        String mail = UtilOpenPay.getJsonValue(jsonParams, "mail");
        return OpenpayPayment.payBank(description,amount,name,mail);
    }

}
