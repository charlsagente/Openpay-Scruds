package unit;

import mx.openpay.client.Address;
import mx.openpay.client.Card;
import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import mx.openpay.client.utils.SearchParams;
import org.junit.Test;
import ss.openpay.logicimpl.OpenpayPayment;
import ss.openpay.logicimpl.openpay.CardScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

import java.util.Calendar;
import java.util.List;


/**
 * Created by Carlos Perez on 22/04/2016.
 */
public class TestCardScruds {

    public void getCard(){

        try {
            Card card = CardScruds.getCard(OpenpayPayment.createApi(), "a1otuphu4xi7zs1usyw5","khqr4xqsusuuizwqwxal");
            System.out.println(UtilOpenPay.toJson(card));


        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void createCard(){
        //Para fines de prueba se crea un objeto card con todos sus campos
        //pero en producción solo se le debe settear el token_id generado desde el cliente javascript.
            // http://www.openpay.mx/docs/api/#crear-una-tarjeta-con-token
        Card request = new Card();
        request.holderName("Juan Perez Ramirez");
        request.cardNumber("4111111111111111");
        request.cvv2("110");
        request.expirationMonth(12);
        request.expirationYear(20);
        Address address = new Address();
        address.city("Queretaro");
        address.countryCode("MX");
        address.state("Queretaro");
        address.postalCode("79125");
        address.line1("Av. Pie de la cuesta #12");
        address.line2("Desarrollo San Pablo");
        address.line3("Qro. Qro.");
        request.address(address);
        try {
            Card card=CardScruds.createCard(OpenpayPayment.createApi(),
                    "a1otuphu4xi7zs1usyw5",
                        request);
            System.out.println(UtilOpenPay.toJson(card));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void deleteCard(){
        try {
            CardScruds.deleteCard(OpenpayPayment.createApi(),"khqr4xqsusuuizwqwxal","a1otuphu4xi7zs1usyw5");
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listCards(){
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
            List<Card> cards= CardScruds.listCards(OpenpayPayment.createApi(), "a1otuphu4xi7zs1usyw5", request);
            System.out.println(UtilOpenPay.toJson(cards));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

}
