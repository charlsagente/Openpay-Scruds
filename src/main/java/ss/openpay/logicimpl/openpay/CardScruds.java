package ss.openpay.logicimpl.openpay;

import mx.openpay.client.Card;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;

import java.util.List;

/**
 * Created by Carlos Perez on 21/04/2016.
 */
public class CardScruds {

    /**
     * Método que guarda la tarjeta de crédito utilizando un token_id que recibimos en javascript y nos genera un ID
     * Al momento de guardar la tarjeta se generará un id que podrá ser usado para hacer cargos a la tarjeta, envíos a una tarjeta o simplemente obtener la información no sensible de la tarjeta.
     * @param api objeto principal que se puede crear con OpenpayPayment.createApi()
     * @param customerId id del cliente de openpay
     * @param card Objeto tipo Card con el token_id setteado
     * @return Regresa un objeto tarjeta cuando se creó correctamente o una respuesta de error si ocurrió algún problema en la creación.
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Card createCard(OpenpayAPI api,String customerId, Card card) throws OpenpayServiceException, ServiceUnavailableException {
        return api.cards().create(customerId, card);
    }

    /**
     * Obtiene los detalles de una tarjeta solicitándola con su id.
     *  Nunca se regresarán datos sensibles como son el código de seguridad y del número de tarjeta sólo se mostraran los primeros 6 y los últimos 4 dígitos.
     * @param api objeto principal que se puede crear con OpenpayPayment.createApi()
     * @param customerId Id del cliente de openpay
     * @param cardId Id de la tarjeta de openpay
     * @return Objeto Card
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Card getCard(OpenpayAPI api, String customerId, String cardId) throws OpenpayServiceException, ServiceUnavailableException {
        return api.cards().get(customerId, cardId);
    }

    /**
     * Elimina una tarjeta del cliente o comercio. Una vez eliminada no se permitirá hacer movimientos, sin embargo,
     * se mantendrán todos los registros de operaciones que haya realizado y se podrán consultar en el dashboard.
     * @param api Objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param cardId Id de la tarjeta
     * @param customerId Id del cliente que la tiene asociada
     * @throws OpenpayServiceException Si la tarjeta se borra correctamente la respuesta es vacía, si no se puede borrar se regresa un objeto error indicando el motivo.
     * @throws ServiceUnavailableException
     */
    public static void deleteCard(OpenpayAPI api,String cardId, String customerId) throws OpenpayServiceException, ServiceUnavailableException {
        api.cards().delete(customerId, cardId);

    }

    /**
     * Regresa una lista de las tarjetas registrados por comercio o cliente,
     * si desea delimitar el resultado se pueden utilizar los filtros en el objeto SearchParams.
     * @param api Objeto principal del API
     * @param customerId id del usuario
     * @param searchParams Objeto con los filtros deseados
     * @return Lista de objetos tipo Card
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static List<Card> listCards(OpenpayAPI api, String customerId,SearchParams searchParams) throws OpenpayServiceException, ServiceUnavailableException {
        return api.cards().list(customerId, searchParams);
    }
}
