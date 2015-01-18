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
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class FiltermenuFragment extends Fragment {

	String r0, r1, r2;
	/*
	 * public void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * 
	 * // Log.i("test", "hoi");
	 * 
	 * TextView test = (TextView)
	 * getActivity().findViewById(R.id.text_filtermenu);
	 * test.setText(R.string.filtermenu);
	 */

	View rootview;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		final String[] groups = { "Gebruikers", "Zorgproces", "Technologie" };

		final String[][] children = {
				{ "Zorgverleners in eigen context",
						"Zorgverlener - zorgverlener",
						"Zorgverlener - patient", "Zorgverlener - anderen",
						"Patient - patient",
						"Patient in persoonlijke levenssfeer" },
				{ "e-Diagnose", "e-Therapie", "e-Care" },
				{ "Webapplicaties", "Mobiele apps",
						"Elektronische patientendossier", "Health sensoren",
						"Videocommunicatie", "Domotica", "Robotica" }, };
		

		rootview = inflater.inflate(R.layout.filtermenu, container, false);
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
					//String r0 = (String) result0.getText();
					
				} else if (groupPosition == 1) {

					result1.setText(group);
					
				} else if (groupPosition == 2) {

					result2.setText(group);
				
				}

				return false;
				
			}
			
		});

		return rootview;

	} 
	
	public static void zoekOpFilter(View v) {
		
		InputStream is = null;
		String gebruiker, zorgprocess, technologie;
		gebruiker = v.getResources().getString(R.string.result0);
					//(String) v.getResources().getText(R.string.result0);
		zorgprocess = v.getResources().getString(R.string.result1);
		technologie = v.getResources().getString(R.string.result2);
			
		if (gebruiker == "Filter gebruiker" && zorgprocess == "Filter process" && technologie == "Filter technologie") {
				
			Log.i("ALLES","SELECT * FROM ehealth");
				
		} else if (gebruiker != "Filter gebruiker" && zorgprocess == "Filter process" && technologie == "Filter technologie") {
				
			Log.i("ZOEKOPr0","SELECT * FROM ehealth WHERE gebruiker = gebruiker");
					
		} else if (gebruiker == "Filter gebruiker" && zorgprocess != "Filter process" && technologie == "Filter technologie") {
				
			Log.i("ZOEKOPr0","SELECT * FROM ehealth WHERE zorgprocess = zorgprocess");
				
		} else if (gebruiker == "Filter gebruiker" && zorgprocess == "Filter process" && technologie != "Filter technologie") {
				
			Log.i("ZOEKOPr0","SELECT * FROM ehealth WHERE technologie = technologie");
				
		} else if (gebruiker != "Filter gebruiker" && zorgprocess != "Filter process" && technologie == "Filter technologie") {
				
			Log.i("ZOEKOPr0+r1","SELECT * FROM ehealth WHERE gebruiker = gebruiker AND zorgprocess = zorgprocess");
				
		} else if (gebruiker != "Filter gebruiker" && zorgprocess == "Filter process" && technologie != "Filter technologie") {
				
			Log.i("ZOEKOPr0+r2","SELECT * FROM ehealth WHERE gebruiker = gebruiker AND technologie = technologie");
				
		} else if (gebruiker == "Filter gebruiker" && zorgprocess != "Filter process" && technologie != "Filter technologie") {
				
			Log.i("ZOEKOPr1+r2","SELECT * FROM ehealth WHERE zorgprocess = zorgprocess AND technologie = technologie");
				
		} else if (gebruiker != "Filter gebruiker" && zorgprocess != "Filter process" && technologie != "Filter technologie") {
				
			Log.i("ZOEKOPr1+r2+r3","SELECT * FROM ehealth WHERE gebruiker = gebruiker AND zorgprocess = zorgprocess AND technologie = technologie");
				
		}
		
		if (gebruiker == "") {
					
			gebruiker = null;
					
		}
				
		if (zorgprocess == "") {
					
			zorgprocess = null;
					
		}
				
		if (technologie == "") {
					
			technologie = null;
				
		}
				
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("geb", gebruiker ));
		nameValuePairs.add(new BasicNameValuePair("zorg", zorgprocess ));
		nameValuePairs.add(new BasicNameValuePair("tech", technologie ));
				
		try {
					
			HttpClient httpClient = new DefaultHttpClient();
				
			HttpPost httpPost = new HttpPost("http://149.210.186.53/getAlles");
					
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					
			HttpResponse response = httpClient.execute(httpPost);
					
			HttpEntity entity = response.getEntity();
					
			is = entity.getContent();
					
		} catch (ClientProtocolException e) {
					
			Log.e("Log_tag", "ClientProtocol");
			e.printStackTrace();
					
		} catch(IOException e) {
					
			Log.e("Log_tag", "IOException");
			e.printStackTrace();
					
		}
				
				
	} 
			
			
	//Kijk of het mogelijk is om de strings result0, result1 en result2 op te halen uit de app.
	//Log.i(TAG,result0);
	//Log.i(TAG,result1);
	//Log.i(TAG,result2);
			
	//Kijk of er filters zijn ingevuld
			
	//Geen filter = zoek op alles, een of meer filters = stuur filters naar volgende methode.
	// if (result0 && result1 && result2 == "Geen filter" {
	//		SELECT * FROM ehealth
	//}
			
	
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