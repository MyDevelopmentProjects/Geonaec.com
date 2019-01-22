package ge.mgl.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VerifyCaptcha {

    private static Logger LOG = LoggerFactory.getLogger(VerifyCaptcha.class);

    //Site Key   6LfKWD4UAAAAAOOTfQQUjxgzd7IJla8Zqeak6OIx
    //Site Key   6LfKWD4UAAAAAOOTfQQUjxgzd7IJla8Zqeak6OIx
    public static final String URL = "https://www.google.com/recaptcha/api/siteverify";
    public static final String SECRET = "6LfKWD4UAAAAAFkJXnSJ5A6lOmRuzJoO_ZgTitfO";

    public static boolean verify(String gRecaptchaResponse) {
        if (gRecaptchaResponse == null || "".equals(gRecaptchaResponse)) {
            return false;
        }

        try {
            HttpResponse<JsonNode> connection = Unirest.post(URL)
                    .field("secret", SECRET)
                    .field("response", gRecaptchaResponse)
                    .asJson();
            if(connection.getStatus() == 200){
                // retrieve the parsed JSONObject from the response
                JSONObject result = connection.getBody().getObject();
                LOG.info(result.toString());
                return result.getBoolean("success");
            }
        } catch (UnirestException | JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
