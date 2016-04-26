package unit;

import mx.openpay.client.Address;
import mx.openpay.client.Customer;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import org.junit.Test;
import ss.openpay.logicimpl.OpenpayPayment;
import ss.openpay.logicimpl.openpay.CustomerScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Carlos Perez on 22/04/2016.
 */
public class TestCustomerScruds {

    @Test
    public void createCustomer(){

        Customer customer = new Customer()
                .email("charlsagente@gmail.com")
                .name("Carlos Perez")
                .requiresAccount(false); //http://www.openpay.mx/docs/api/#crear-un-nuevo-cliente
        try {
            customer = CustomerScruds.createCustomer(OpenpayPayment.createApi(), customer);
            System.out.println(UtilOpenPay.toJson(customer));

        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void updateCustomer(){
        Customer customer = new Customer()
                .email("charlsagente@gmail.com")
                .name("Charls")
                .lastName("Agente")
                .phoneNumber("1554422")
                .address(
                        new Address()
                            .city("Oax")
                            .countryCode("MX")
                            .line1("Matamoros No. 555")
                            .postalCode("9191919")
                            .state("Oax")
                );

        customer.setId("a1otuphu4xi7zs1usyw5");

        try {
            customer= CustomerScruds.updateCustomer(OpenpayPayment.createApi(), customer);
            System.out.println(UtilOpenPay.toJson(customer));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getCustomer(){
        Customer customer;
        try {
            customer= CustomerScruds.getCustomer(OpenpayPayment.createApi(), "a1otuphu4xi7zs1usyw5");
            System.out.println(UtilOpenPay.toJson(customer));


        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteCustomer(){
        try {
            CustomerScruds.deleteCustomer(OpenpayPayment.createApi(), "ayaylrs70lpzlqf30jfe");
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listCustomers(){
        final Calendar dateGte = Calendar.getInstance();
        final Calendar dateLte = Calendar.getInstance();
        dateGte.set(2016, 3, 20, 0, 0, 0);
        dateLte.set(2016, 3, 25, 0, 0, 0);

        SearchParams request= new SearchParams();
        request.creationGte(dateGte.getTime());
        request.creationLte(dateLte.getTime());
        request.offset(0);
        request.limit(100);

        try {
            List<Customer> customers = CustomerScruds.listCustomers(OpenpayPayment.createApi(), request);
            System.out.println(UtilOpenPay.toJson(customers));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }
}
