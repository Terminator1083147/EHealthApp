package IPMEDT4.groep2.e_health;

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
		
		//Haal alle gegevens op uit de resultaten.
		
		//Kijk of er filters zijn ingevuld
		
		//Geen filter = zoek op alles, een of meer filters = stuur filters naar volgende methode.
		// if (result0 && result1 && result2 == "Geen filter" {
		//		SELECT * FROM ehealth
		//}
		
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