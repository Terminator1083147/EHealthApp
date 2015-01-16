package IPMEDT4.groep2.e_health;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ResultatenFragment extends Fragment{
	
	View rootview;
	private TextView responseTextView;
	
	public ResultatenFragment() {
		
	}
	@Nullable
	@Override

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

	rootview = inflater.inflate(R.layout.resultaten,container, false);
	
    this.responseTextView = (TextView) rootview.findViewById(R.id.responseTextView);
    
    new GetAllIdTask().execute(new ApiConnector());
	
	return rootview;

}
	
    public void setTextToTextView(JSONArray jsonArray) {

        String s = "";
        for(int i=0; i<jsonArray.length();i++) {

            JSONObject json = null;
            try {
                json = jsonArray.getJSONObject(i);
                s = s +
                        "Id : "+json.getInt("id")+"\n"+
                        "Naam : "+json.getString("naam")+"\n"+
                        "Beschrijving : "+json.getString("beschrijving")+"\n"+
                        "Gebruiker : "+json.getString("gebruiker")+"\n"+
                        "Zorgproces : "+json.getString("zorgproces")+"\n"+
                        "Technologie : "+json.getString("technologie")+"\n\n";
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        this.responseTextView.setText(s);

    }

    private class GetAllIdTask extends AsyncTask<ApiConnector,Long,JSONArray> {

        @Override
        protected JSONArray doInBackground(ApiConnector... params) {

           return params[0].getAllId();

        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {

            setTextToTextView(jsonArray);

        }
    }
	
	public static void onClickButton1(View v) {
		 Log.i("CLICKLICKLICK", "MEEEEEEH");
	} 
}