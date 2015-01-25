package IPMEDT4.groep2.e_health;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class OveronsFragment extends Fragment{

	View rootview;
	@Nullable
	@Override

public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

	rootview = inflater.inflate(R.layout.overons,container, false);
	return rootview;
}
	
}
