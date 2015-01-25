package IPMEDT4.groep2.e_health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformatieFragment extends Fragment {
	
		View rootview;
		@Nullable
		@Override
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	
		rootview = inflater.inflate(R.layout.informatie,container, false);
		return rootview;
}
}