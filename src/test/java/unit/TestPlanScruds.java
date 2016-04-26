package unit;

import mx.openpay.client.exceptions.OpenpayServiceException;
import mx.openpay.client.exceptions.ServiceUnavailableException;
import org.junit.Test;
import ss.openpay.logicimpl.openpay.PlanScruds;
import ss.openpay.logicimpl.util.UtilOpenPay;

/**
 * Created by Carlos Perez on 22/04/2016.
 */
public class TestPlanScruds {
    @Test
    public void createPlan(){
        String planname = "Licencia mensual";
        String price =  "450";
        String repetir = "1";
        String unidad = "1"; //Ver UtilOpenpay.PlanRepeatUnit (1 es semanas, 2 es meses, 3 es años)
        String diasprueba = "1";

        System.out.println(PlanScruds.createPlan(planname, price, repetir, unidad, diasprueba));
    }

    @Test
    public void getPlan(){
        try {
            System.out.println(UtilOpenPay.toJson(PlanScruds.getPlan("plqqmci4xani5wqg5irq")));
        } catch (OpenpayServiceException e) {
            e.printStackTrace();
        } catch (ServiceUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listPlans(){
        System.out.println(PlanScruds.getPlans());

    }

    @Test
    public void deletePlan(){
        PlanScruds.deletePlan("psqox3fyalxvmlx0xdav");
    }
}
