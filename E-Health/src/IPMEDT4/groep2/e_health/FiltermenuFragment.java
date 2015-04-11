package IPMEDT4.groep2.e_health;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class FiltermenuFragment extends Fragment {

	static String r0 = "Ongeselecteerd";
	static String r1 = "Ongeselecteerd";
	static String r2 = "Ongeselecteerd";

	View rootview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final String[] groups = { "Gebruikers", "Zorgproces", "Technologie" };

		final String[][] children = {
				{ "Ongeselecteerd","Zorgverleners in eigen context",
						"Zorgverlener - zorgverlener",
						"Zorgverlener - patient", "Zorgverlener - anderen",
						"Patient - patient",
						"Patient in persoonlijke levenssfeer" },
				{ "Ongeselecteerd","e-Diagnose", "e-Therapie", "e-Care" },
				{ "Ongeselecteerd","Webapplicaties", "Mobiele apps",
						"Elektronische patientendossier", "Health sensoren",
						"Videocommunicatie", "Domotica", "Robotica" }, };
		

		rootview = inflater.inflate(R.layout.filtermenu, container, false);
		Button button;
		button = (Button) rootview.findViewById(R.id.button);
	    button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(checkInternetConnection()) {
					
					new ZoekInDB().execute("");
					Fragment nextFrag = new ResultatenFragment();
					FragmentTransaction transaction = getFragmentManager().beginTransaction();
					transaction.replace(R.id.flContent, nextFrag);
					transaction.addToBackStack(null);
					transaction.commit();
					
				} else {
					
					//Context context = getApplicationContext();
					Context context = v.getContext();
					CharSequence text = "Er is geen internetconnectie beschikbaar. zorg dat er een internetconnectie is " +
							"zodat er gegevens kunnen worden gestuurd.";
					int duration = Toast.LENGTH_SHORT;

					Toast toast = Toast.makeText(context, text, duration);
					toast.show();	
					
				}
				
			}
		});
	    
		ExpandableListView elv = (ExpandableListView) rootview
				.findViewById(R.id.list);
		elv.setAdapter(new SavedTabsListAdapter(groups, children));

		// Listview on child click listener
		elv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				
				TextView result0 = (TextView) rootview.findViewById(R.id.result0);
				TextView result1 = (TextView) rootview.findViewById(R.id.result1);		
				TextView result2 = (TextView) rootview.findViewById(R.id.result2);
				
				String group = children[groupPosition][childPosition].toString();				
				
				if (groupPosition == 0) {
					
					result0.setText(group);
					r0 = group;
					
				} else if (groupPosition == 1) {

					result1.setText(group);
					r1 = group;
					
				} else if (groupPosition == 2) {

					result2.setText(group);
					r2 = group;
				
				}

				return false;
			}
			
		});

		return rootview;
	} 
	
	private boolean checkInternetConnection() {
	    ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
	    // test for connection
	    if (cm.getActiveNetworkInfo() != null
	            && cm.getActiveNetworkInfo().isAvailable()
	            && cm.getActiveNetworkInfo().isConnected()) {
	        return true;
	    } else {
	    	Log.d("debug", "No connection available!");
	        //Log.v(TAG, "Internet Connection Not Present");
	        return false;
	    }
	}
	
	public class ZoekInDB extends AsyncTask<String, Void, String> {
		
		@Override
		protected String doInBackground(String... params) {
			
			String gebruiker, zorgprocess, technologie;
			gebruiker = r0;
			zorgprocess = r1;
			technologie = r2;
			
			

			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
			nameValuePairs.add(new BasicNameValuePair("geb", gebruiker ));
			nameValuePairs.add(new BasicNameValuePair("zorg", zorgprocess ));
			nameValuePairs.add(new BasicNameValuePair("tech", technologie )); 
			
			try {
			
			HttpClient httpClient = new DefaultHttpClient();
				
			HttpPost httpPost = new HttpPost("http://149.210.186.53/filterpagina.php");
					
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
			HttpResponse response = httpClient.execute(httpPost);
					
			HttpEntity entity = response.getEntity();
					
			InputStream is = entity.getContent();
					
			} catch (ClientProtocolException e) {
					
			Log.e("Log_tag", "ClientProtocol");
			e.printStackTrace();
					
			} catch(IOException e) {
					
			Log.e("Log_tag", "IOException");
			e.printStackTrace();
					
			}
			
			return null;
		}	
		
        @Override
        protected void onPostExecute(String result) {
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected void onProgressUpdate(Void... values) {
        }
 
    }
	
	public class SavedTabsListAdapter extends BaseExpandableListAdapter {

		String[] groups;
		String[][] children;
		
		public SavedTabsListAdapter(String[] groups, String[][] children) {
			this.groups = groups;
			this.children = children;
		}

		@Override
		public int getGroupCount() {
			return groups.length;
		}

		@Override
		public int getChildrenCount(int i) {
			return children[i].length;
		}

		@Override
		public Object getGroup(int i) {
			return groups[i];
		}

		@Override
		public Object getChild(int i, int i1) {
			return children[i][i1];
		}

		@Override
		public long getGroupId(int i) {
			return i;
		}

		@Override
		public long getChildId(int i, int i1) {
			return i1;
		}

		@Override
		public boolean hasStableIds() {
			return true;
		}

		@Override
		public View getGroupView(int i, boolean b, View view,
				ViewGroup viewGroup) {

			TextView textView = new TextView(
					FiltermenuFragment.this.getActivity());
			// textView.setHeight(100);
			// textView.setTextSize(50);
			textView.setHeight((int) getResources().getDimension(
					R.dimen.listview));
			textView.setWidth((int) getResources().getDimension(
					R.dimen.listwidth));
			textView.setTextSize((int) getResources().getDimension(
					R.dimen.listtextgrote));

			textView.setText(getGroup(i).toString());
			return textView;

		}

		@Override
		public View getChildView(int i, int i1, boolean b, View view,
				ViewGroup viewGroup) {
			TextView textView = new TextView(
					FiltermenuFragment.this.getActivity());
			
			textView.setTextSize((int) getResources().getDimension(
					R.dimen.childlisttextgrote));
			
			textView.setText(getChild(i, i1).toString());
			return textView;
		}

		@Override
		public boolean isChildSelectable(int i, int i1) {
			return true;
		}

	}
}