package ge.mgl.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsAPI {

    public static boolean DEBUG_MODE = false;

    private static final Logger LOG = LoggerFactory.getLogger(SmsAPI.class);

    // PHP Rest Api URL
    private static final String API_URL = "http://msg.ge/bi/sendsms.php?";

    public static boolean sendSMS(String mNumber, String text) {

        LOG.info("Trying to generate SMS transaction");
        String username = "smartwash";
        String password = "u74g7f26sr";
        String client_id = "382";
        String service_id = "0382";
        String url = API_URL + String.format("username=%s&password=%s&client_id=%s&service_id=%s&to=995%s&text=%s",
                username, password, client_id, service_id, mNumber, text.replaceAll(" ", "+"));

        if (DEBUG_MODE) {
            url = "http://165.227.165.180:8090/sms.php?mNumber=" + mNumber + "&text=" + text.replaceAll(" ", "+");
        }

        try {
            if (DEBUG_MODE) {
                HttpResponse<JsonNode> request = Unirest.get(url)
                        .asJson();
                if(request.getStatus() == 200){
                    JSONObject response = request.getBody().getObject();
                    if (response.getBoolean("SUCCESS")) {
                        if (response.getString("MESSAGE").contains("0000-")) {
                            LOG.info("SMS CODE SUCCESSFULLY SENT FOR MOBILE NUMBER -> " + mNumber);
                            return true;
                        }
                    }
                    LOG.error("SMS CODE COULD NOT BE SENT FOR MOBILE NUMBER -> " + mNumber);
                }
            } else {
                HttpResponse request = Unirest.get(url).asString();
                if(request.getStatus() == 200){
                    String response = request.getBody().toString();
                    if (response.contains("0000-")) {
                        LOG.info("SMS CODE SUCCESSFULLY SENT FOR MOBILE NUMBER -> " + mNumber + ". This is response " + response);
                        return true;
                    }
                    LOG.error("SMS CODE COULD NOT BE SENT FOR MOBILE NUMBER -> " + mNumber + ". This is response " + response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
