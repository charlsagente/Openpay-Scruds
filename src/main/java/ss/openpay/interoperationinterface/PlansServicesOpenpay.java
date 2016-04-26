package ss.openpay.interoperationinterface;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ss.openpay.logicimpl.openpay.PlanScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

import javax.ws.rs.*;

/**
 * Created by carlos on 31/03/16.
 */
@Path("/openpay/plans")
public class PlansServicesOpenpay {
    @PUT
    @Produces("application/json")
    public String setSuscription(String requestedObject){
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonParams = (JsonObject) jsonParser.parse(requestedObject);

        String planname = UtilOpenPay.getJsonValue(jsonParams, "planname");
        String price = UtilOpenPay.getJsonValue(jsonParams, "price");
        String repetir = UtilOpenPay.getJsonValue(jsonParams, "repetir");
        String unidad = UtilOpenPay.getJsonValue(jsonParams, "unidad");
        String diasprueba = UtilOpenPay.getJsonValue(jsonParams, "diasprueba");

        return PlanScruds.createPlan(planname, price, repetir, unidad, diasprueba);
    }

    @DELETE
    @Produces("application/json")
    public String delPlan(@QueryParam("idPlan") String idPlan){

        return PlanScruds.deletePlan(idPlan);
    }

    @GET
    @Produces("application/json")
    public String getPlans(){
        return PlanScruds.getPlans();
    }
}
