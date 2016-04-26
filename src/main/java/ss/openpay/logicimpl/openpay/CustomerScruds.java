package ss.openpay.logicimpl.openpay;

import mx.openpay.client.Customer;
import mx.openpay.client.core.OpenpayAPI;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;

import java.util.List;

/**
 * Created by Carlos Perez on 21/04/2016.
 */
public class CustomerScruds {
    /**
     * Crea un objeto cliente
     * @param api objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param customer objeto customer con las propiedades requeridas en la doc. http://www.openpay.mx/docs/api/#crear-un-nuevo-cliente
     * @return Un objeto cliente en caso que se hayan enviado todos los datos correctamente, o una respuesta de error si ocurrió algun problema en la creación.
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Customer createCustomer(OpenpayAPI api, Customer customer) throws OpenpayServiceException, ServiceUnavailableException {
        return api.customers().create(customer);
    }

    /**
     * Actualiza uno o más datos del cliente.
     * @param api objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param customer Objeto Customer con los datos que se requieran actualizar y el Id del cliente
     * @return Regresa un objeto cliente con la información actualizada, o una respuesta de error si ocurrió algun problema en la actualización.
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Customer updateCustomer(OpenpayAPI api, Customer customer) throws OpenpayServiceException, ServiceUnavailableException {
        return api.customers().update(customer);
    }

    /**
     * Obtiene la información actual de un cliente existente. Solo es necesario especificar el identificador que fue regresado al momento de crear el cliente.
     * @param api objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param idCustomer Cadena con el id del cliente
     * @return Si el identificador existe regresa un objeto cliente con la información del cliente.
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static Customer getCustomer(OpenpayAPI api, String idCustomer) throws OpenpayServiceException, ServiceUnavailableException {
        return api.customers().get(idCustomer);
    }

    /**
     * Elimina un cliente permanentemente. Openpay mantiene los registros de las operaciones.
     * Si el cliente se borra correctamente la respuesta es vacía, si no se puede borrar se regresa un objeto error indicando el motivo.
     * @param api objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param idCustomer String con el id del cliente
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static void deleteCustomer(OpenpayAPI api, String idCustomer) throws OpenpayServiceException, ServiceUnavailableException {
        api.customers().delete(idCustomer);
    }

    /**
     * Regresa una lista de los cliente registrados, si desea delimitar el resultado se pueden utilizar los filtros.
     * @param api objeto principal del api que se puede crear con OpenpayPayment.createApi()
     * @param request objeto tipo SearchParams donde se especifican los filtros.
     * @return Regresa un arreglo de objetos cliente.
     * @throws OpenpayServiceException
     * @throws ServiceUnavailableException
     */
    public static List<Customer> listCustomers(OpenpayAPI api, SearchParams request) throws OpenpayServiceException, ServiceUnavailableException {
        return  api.customers().list(request);

    }
}
