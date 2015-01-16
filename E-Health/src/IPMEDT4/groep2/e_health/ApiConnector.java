package IPMEDT4.groep2.e_health;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by Maarten van der Meer on 15-1-15.
 */
public class ApiConnector {

    public JSONArray getAllId() {

        // URL voor het ophalen van alle IDs

        String url = "http://149.210.186.53/getAlles.php";



        HttpEntity httpEntity = null;

        try {

            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);

            HttpResponse httpResponse = httpClient.execute(httpGet);

            httpEntity = httpResponse.getEntity();

        } catch (ClientProtocolException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

        JSONArray jsonArray = null;

        if (httpEntity != null) {

            try {

                String entityResponse = EntityUtils.toString(httpEntity);

                Log.e("Entity Response : ", entityResponse);

                jsonArray = new JSONArray(entityResponse);

            } catch (JSONException e) {

                e.printStackTrace();

            } catch (IOException e) {

                e.printStackTrace();

            }

        }

        return jsonArray;

    }

}
