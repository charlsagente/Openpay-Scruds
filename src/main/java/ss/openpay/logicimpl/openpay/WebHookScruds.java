package ss.openpay.logicimpl.openpay;

import mx.openpay.client.Webhook;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import ss.openpay.logicimpl.OpenpayPayment;
import ss.openpay.logicimpl.util.UtilOpenPay;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Perez on 22/04/2016.
 *
 * Doc: http://www.openpay.mx/docs/webhooks.html
 */
public class WebHookScruds {
    /**
     * Al crear un nuevo webhook se hará una petición a la url indicada para verificar el webhook.
     * Al momento de guardar el webhook se generará un id que podrá ser usado para eliminar o simplemente obtener la información no sensible del webhook.
     * @param url dirección de nuestro servicio al que openpay enviará notificaciones
     * @param listEvents Una lista con todos los eventos que queremos recibir en ese servicio, lista completa en: http://www.openpay.mx/docs/api/#objeto-webhook
     * @return Regresa un objeto webhook cuando se creó correctamente o una respuesta de error si ocurrió algún problema en la creación.
     */
    public static String createWebHook(String url, ArrayList<String> listEvents){
        OpenpayAPI api= OpenpayPayment.createApi();
        Webhook request = new Webhook();
        request.setUrl(url);

        request.addEventTypes("charge.succeeded"); //en este caso se limitó a este evento.

        String json;

        try {
            Webhook webhook = api.webhooks().create(request);
            json = UtilOpenPay.toJson(webhook);
        } catch (OpenpayServiceException e) {
            json="{\"success\":false,\"description\":\""+e.getDescription()+"\"}";
        } catch (ServiceUnavailableException e) {
            json="{\"success\":false,\"description\":\""+e.getMessage()+"\"}";
        }
        return json;
    }

    /**
     * Si el webhook se borra correctamente la respuesta es vacía, si no se puede borrar se regresa un objeto error indicando el motivo.
     * @param id id del webhook
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static void deleteWebHook(String id) throws OpenpayServiceException, ServiceUnavailableException {
        OpenpayAPI api=OpenpayPayment.createApi();
        api.webhooks().delete(id);
    }

    /**
     * Obtiene los detalles de un webhook solicitándolo con su id.
     * @param id
     * @return Regresa un objeto webhook
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Webhook getWebHook(String id) throws OpenpayServiceException, ServiceUnavailableException {
        OpenpayAPI api=OpenpayPayment.createApi();
        return api.webhooks().get(id);
    }

    /**
     * Regresa una lista de webhooks registrados por comercio.
     * Nota: Nunca se regresarán datos sensibles como son el password para accesar al webhook.
     * @return Lista de webhooks
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static List<Webhook> listWebHooks() throws OpenpayServiceException, ServiceUnavailableException {
        OpenpayAPI api=OpenpayPayment.createApi();
        return api.webhooks().list();
    }
}
