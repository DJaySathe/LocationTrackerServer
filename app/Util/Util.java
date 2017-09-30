package Util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;

public class Util {
    public static ObjectNode createResponse(String response, boolean status) {

        ObjectNode result = Json.newObject();
        result.put("isSuccessfull", status);
        if (response instanceof String) {
            result.put("body", (String) response);
        }
        else {
            result.putPOJO("body",response);
        }
        return result;
    }
}
