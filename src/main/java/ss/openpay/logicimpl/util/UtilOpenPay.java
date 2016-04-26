package ss.openpay.logicimpl.util;

/**
 * Created by carlos on 30/03/16.
 */

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import mx.openpay.client.enums.PlanRepeatUnit;

public class UtilOpenPay {

    public static PlanRepeatUnit repeatCase(String stringCase){
        if (stringCase.equals("1"))
            return PlanRepeatUnit.WEEK;
        else if (stringCase.equals("2"))
            return PlanRepeatUnit.MONTH;
        else if (stringCase.equals("3"))
            return PlanRepeatUnit.YEAR;

        return null;
    }
    public static String getJsonValue(JsonObject object, String key) {
        return (object.has(key)) ? object.get(key).getAsString() : null;
    }
    public static String toJson(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);

    }
}
