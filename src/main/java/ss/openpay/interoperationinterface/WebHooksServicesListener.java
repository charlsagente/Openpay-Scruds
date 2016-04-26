package ss.openpay.interoperationinterface;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ss.openpay.logicimpl.openpay.pojos.WebHookPojo;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * La URL de los Servicios REST creados aquí, se deben pasar en los SCRUDS de WebHooks
 *
 */
@Path("/openpay/webhooks/succeded")
public class WebHooksServicesListener {
    private static final transient Logger log = LoggerFactory
            .getLogger(WebHooksServicesListener.class);
    /**
     * Servicio para recibir objetos Webhooks desde openpay, en la documentación se especifica tener
     * un servicio(s) tipo POST y configurar que tipo de notificaciones se desean recibir
     * http://www.openpay.mx/docs/webhooks.html
     * @param requestedObject
     * @return
     */
    @POST
    @Produces("text/html")
    public Response setSuscription(String requestedObject){
        log.info("Conexión recibida: {}",requestedObject);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        WebHookPojo obj=null; //Objeto tipo pojo creado en base al json de ejemplo: http://www.openpay.mx/docs/webhooks.html
        String out=null;
        try {
            obj = mapper.readValue(requestedObject, WebHookPojo.class);

        } catch (IOException e) {
            log.error("Error al mapear la clase pojo: {}", e.getMessage());
        }
        //Lógica para el objeto WebHook......

        return Response.ok(requestedObject).build(); //Se le debe contestar al servidor openpay con un response status 200
    }
}
