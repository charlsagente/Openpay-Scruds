package ss.openpay.logicimpl.openpay;

import mx.openpay.client.Plan;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.enums.PlanStatusAfterRetry;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import ss.openpay.logicimpl.OpenpayPayment;
import ss.openpay.logicimpl.util.UtilOpenPay;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * Created by Carlos Perez on 22/04/2016.
 */
public class PlanScruds {
    /**
     * Antes de realizar un cargo con suscripción se debe crear un plan para poderse asociar.
     * Al crear un nuevo plan, a él se podrán suscribir uno o varios clientes.
     * http://www.openpay.mx/docs/api/#crear-un-nuevo-plan
     * @param planname
     * @param price
     * @param repetir
     * @param unidad
     * @param diasprueba
     * @return Regresa un objeto plan creado o un error en caso de ocurrir algún problema.
     */
    public static String createPlan(String planname, String price, String repetir, String unidad, String diasprueba){
        OpenpayAPI api = OpenpayPayment.createApi();
        Plan request = new Plan();
        request.name(planname);
        request.amount(new BigDecimal(price));
        request.repeatEvery(Integer.parseInt(repetir), UtilOpenPay.repeatCase(unidad));
        request.retryTimes(2); //Número de veces que el sistema openpay trata de realizar el cobro tras intentos fallidos
        request.statusAfterRetry(PlanStatusAfterRetry.UNPAID); //Cuando no se puede realizar el cargo a la TDC se pone en estado unpaid, también se puede poner cancelled
        request.trialDays(Integer.parseInt(diasprueba)); //Días trial que se le regalan a cliente en la doc también puede ser una fecha.
        String json="{\"success\":false}";
        try {

            request = api.plans().create(request);


            json = UtilOpenPay.toJson(request);


        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

        return json;
    }

    /**
     * Al eliminar un plan no se permitirán crear mas suscripciones asociadas a él, sin embargo las suscripciones ya asociadas se mantienen y se continuan cobrando.
     * @param idPlan
     * @return Si el plan se borra correctamente la respuesta es vacía, si no se puede borrar se regresa un objeto error indicando el motivo.
     */
    public static String deletePlan(String idPlan) {
        OpenpayAPI api=OpenpayPayment.createApi();
        String success="{\"success\":true}";
        try {
            api.plans().delete(idPlan);
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
            success="{\"success\":false,\"description\":\""+e.getDescription()+"\"}";
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
            success="{\"success\":false,\"description\":\""+e.getMessage()+"\"}";
        }
        return success;
    }

    /**
     * Regresa los detalles de todos los planes que están activos.
     * http://www.openpay.mx/docs/api/#listado-de-planes
     * @return Listado de objetos plan registrados de acuerdo a los parámetros proporcionados, ordenadas por fecha de creación en orden descendente.
     */
    public static String getPlans() {
        OpenpayAPI api=OpenpayPayment.createApi();
        LocalDate date = LocalDate.of(2016, Month.MARCH, 31); //El día que se crearon los planes, checar la doc. del api
        SearchParams request = new SearchParams();
        request.creation(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        String json;
        try {
            List<Plan> plans = api.plans().list(request);

            json = UtilOpenPay.toJson(plans);

        } catch (OpenpayServiceException e) {
            json="{\"success\":false,\"description\":\""+e.getDescription()+"\"}";
        } catch (ServiceUnavailableException e) {
            json="{\"success\":false,\"description\":\""+e.getMessage()+"\"}";
        }

        return json;
    }

    /**
     * Obtiene los detalles de un plan.
     * @param idPlan
     * @return Regresa un objeto Plan
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Plan getPlan(String idPlan) throws OpenpayServiceException, ServiceUnavailableException {
        OpenpayAPI api=OpenpayPayment.createApi();
        return api.plans().get(idPlan);
    }
}
