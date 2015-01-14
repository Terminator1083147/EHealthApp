package IPMEDT4.groep2.e_health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class FiltermenuFragment extends Fragment {
	
	/*
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       // Log.i("test", "hoi");
	
        TextView test = (TextView) getActivity().findViewById(R.id.text_filtermenu);
        test.setText(R.string.filtermenu);
        
        */
	
	View rootview;
	@Nullable
	@Override

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

	rootview = inflater.inflate(R.layout.filtermenu,container, false);
	 ExpandableListView elv = (ExpandableListView) rootview.findViewById(R.id.list);
	 elv.setAdapter(new SavedTabsListAdapter()); 
	return rootview;

}
	 public class SavedTabsListAdapter extends BaseExpandableListAdapter {
		 
		 private String[] groups = { "Gebruikers", "Zorgproces", "Technologie" };
		  
		 private String[][] children = {
		 { "Arnold", "Barry", "Chuck", "David" },
		 { "Ace", "Bandit", "Cha-Cha", "Deuce" },
		 { "Fluffy", "Snuggles" },
		 };
		  
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
		 public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
			 
		 TextView textView = new TextView(FiltermenuFragment.this.getActivity());
		 //textView.setHeight(100);
		 //textView.setTextSize(50);
		 textView.setHeight ( (int) getResources().getDimension(R.dimen.listview));
		 textView.setTextSize ( (int) getResources().getDimension(R.dimen.listtextgrote));
		 
		 textView.setText(getGroup(i).toString());
		 return textView;
		 
		 }
		  
		 @Override
		 public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
		 TextView textView = new TextView(FiltermenuFragment.this.getActivity());
		 textView.setText(getChild(i, i1).toString());
		 return textView;
		 }
		  
		 @Override
		 public boolean isChildSelectable(int i, int i1) {
		 return true;
		 }
		  
		 } 
}