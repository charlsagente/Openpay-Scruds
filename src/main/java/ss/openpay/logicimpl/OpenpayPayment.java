package ss.openpay.logicimpl;

import com.google.gson.Gson;
import mx.openpay.client.*;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.core.requests.transactions.CreateBankChargeParams;
import mx.openpay.client.core.requests.transactions.CreateCardChargeParams;
import mx.openpay.client.core.requests.transactions.CreateStoreChargeParams;
import mx.openpay.client.core.requests.transactions.RefundParams;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ss.openpay.logicimpl.openpay.CardScruds;
import ss.openpay.logicimpl.openpay.CustomerScruds;
import ss.openpay.logicimpl.util.Configuration;

import java.math.BigDecimal;

/**
 * Created by Carlos Perez on 29/03/2016.
 */

/**
 * POC para distintos métodos de pago con openpay.
 */
public class OpenpayPayment {
    private static final transient Logger log = LoggerFactory.getLogger(OpenpayPayment.class);


    /**
     * Método que se inicializa el objeto principal del api de openpay.
     *
     * @return
     */
    public static OpenpayAPI createApi() {
        return new OpenpayAPI(Configuration.OPENPAY_BASE_URL, Configuration.OPENPAY_PRIVATE_KEY, Configuration.OPENPAY_ID);
    }


    /**
     * Se genera un cargo a TDC con suscripción, se solicita el mínimo de datos.
     * http://www.openpay.mx/docs/api/#crear-una-nueva-suscripción
     *
     * @param token_id
     * @param user_name
     * @param email
     * @param device_sesion_id
     * @param planId
     * @return revisar documentación  para ver objeto completo.
     */
    public static String Suscribe(String token_id, String user_name, String email, String device_sesion_id, String planId,Boolean preaprobed) {
        OpenpayAPI api = createApi();
        Customer customer = new Customer();
        customer.name(user_name);
        customer.email(email);
        String idCustomer = null;
        try {
            customer = CustomerScruds.createCustomer(api, customer);
            idCustomer = customer.getId();
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

        Subscription request = new Subscription();
        request.planId(planId);
        request.sourceId(token_id);



        String json = "{\"success\":false}";
        try {
            request = api.subscriptions().create(idCustomer, request);
            Gson gson = new Gson();
            json = gson.toJson(request);
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

        return json;
    }

    /**
     * Pago único con TDC este método no guarda datos de la TDC ni del usuario, solo realiza el pago
     * http://www.openpay.mx/docs/api/#con-id-de-tarjeta-o-token
     *
     * @param token_id
     * @param amount
     * @param device_sesion_id
     * @param email
     * @param user_name
     * @return
     */
    public static String directPayTDC(String token_id, String amount, String device_sesion_id, String email, String user_name, Boolean preaprobed) {
        OpenpayAPI api = createApi();

        Customer customer = new Customer();
        customer.setName(user_name);
        customer.setEmail(email);


        CreateCardChargeParams request = new CreateCardChargeParams();
        request.cardId(token_id);
        request.amount(new BigDecimal(amount));
        request.description("Cargo directo a TDC");
        request.deviceSessionId(device_sesion_id);
        request.customer(customer);
        if (preaprobed)
            request.capture(false); //Para preaprobar el pago
        final DeferralPayments deferralPayments = new DeferralPayments(3);
        //request.deferralPayments(deferralPayments);

        String json;
        try {
            Charge charge = api.charges().create(request);
            Gson gson = new Gson();
            json = gson.toJson(charge);
        } catch (OpenpayServiceException e) {
            json = "{\"success\":false,\"description\":\"" + e.getDescription() + "\"}";
        } catch (ServiceUnavailableException e) {
            json = "{\"success\":false,\"description\":\"" + e.getMessage() + "\"}";
        }
        return json;
    }

    /**
     * Generación de referencia para pago con tiendas de conveniencia
     * http://www.openpay.mx/docs/api/#cargo-en-tienda
     *
     * @param description
     * @param amount
     * @param name
     * @param mail
     * @return
     */
    public static String payStore(String description, String amount, String name, String mail) {
        OpenpayAPI api = createApi();
        String json;
        Customer customer = new Customer();
        customer.name(name);
        customer.email(mail);
        try {
            Charge charge = api.charges().create(new CreateStoreChargeParams()
                    .description(description)
                    .amount(new BigDecimal(amount))
                    .customer(customer)
            );         // Amount is in MXN


            Gson gson = new Gson();
            json = gson.toJson(charge);
        } catch (OpenpayServiceException e) {
            json = "{\"success\":false,\"description\":\"" + e.getDescription() + "\"}";
        } catch (ServiceUnavailableException e) {
            json = "{\"success\":false,\"description\":\"" + e.getMessage() + "\"}";
        }
        return json;
    }

    /**
     * Generación de referencia bancaria.
     * http://www.openpay.mx/docs/api/#cargo-en-banco
     *
     * @param description
     * @param amount
     * @param name
     * @param mail
     * @return checar doc para ver el objeto completo.
     */
    public static String payBank(String description, String amount, String name, String mail) {
        OpenpayAPI api = createApi();
        String json;
        Customer customer = new Customer();
        customer.name(name);
        customer.email(mail);
        try {
            Charge charge = api.charges().create(new CreateBankChargeParams()
                    .description(description)
                    .amount(new BigDecimal(amount))
                    .customer(customer)
            );         // Amount is in MXN
            Gson gson = new Gson();
            json = gson.toJson(charge);
        } catch (OpenpayServiceException e) {
            json = "{\"success\":false,\"description\":\"" + e.getDescription() + "\"}";
        } catch (ServiceUnavailableException e) {
            json = "{\"success\":false,\"description\":\"" + e.getMessage() + "\"}";
        }
        return json;

    }


    /**
     * Método que realiza el cobro al usuario, este es llamado cada 15 del mes por un cron, se debe contar con id de usuario y id de la tarjeta
     *
     * @param customer se obtiene en CustomerScruds.getCustomer, enviándole id del cliente
     * @param cardId   se envia directamente el id de la tarjeta guardada previamente
     * @param amount   monto del cobro
     * @return objeto json desde el api de openpay.
     * véase: http://www.openpay.mx/docs/api/?java#con-id-de-tarjeta-o-token
     */
    public static Charge customRecurrentPayment(Customer customer, String cardId, String amount) {
        OpenpayAPI api = createApi();
        CreateCardChargeParams request = new CreateCardChargeParams();
        request.cardId(cardId); // =source_id
        request.amount(new BigDecimal(amount));

        request.description("Cargo inicial/recurrente por licencia ondemand");
        request.customer(customer);
        Charge charge = null;
        try {
            charge = api.charges().create(request);
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }


        return charge;
    }

    public static String onDemand(String token_id, String user_name, String email, String device_sesion_id) {
        OpenpayAPI api = createApi();
        Customer customer = new Customer()
                .email(email)
                .name(user_name)
                .requiresAccount(false);

        try {
            customer = CustomerScruds.createCustomer(api, customer);
            String customerId = customer.getId();
            System.out.println(customer.getId());//Guardar el ID en la BD

        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

        Card card = new Card();
        card.setTokenId(token_id);
        card.setDeviceSessionId(device_sesion_id);

        try {
            card = CardScruds.createCard(api, customer.getId(), card);
            System.out.println(card.getId()); //Guardar el id de la tarjeta en la BD
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

        String json;


        Charge charge = customRecurrentPayment(customer, card.getId(), "200"); //Para el cobro inicial
        Gson gson = new Gson();
        json = gson.toJson(charge);

        return json;
    }

    public static String refund(String idTransaccion) {
        OpenpayAPI api = createApi();
        RefundParams request = new RefundParams();
        request.chargeId(idTransaccion);
        request.description("Monto de cargo devuelto");
        String json="";
        try {
            Charge charge=api.charges().refund(request);
            Gson gson = new Gson();
            json = gson.toJson(charge);
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
        return json;
    }
}
