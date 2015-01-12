package IPMEDT4.groep2.e_health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

	/*
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
       // Log.i("test", "hoi");
	
        TextView test = (TextView) getActivity().findViewById(R.id.text_startpagina);
        test.setText(R.string.startpagina);
        
        
        */
	
		View rootview;
		@Nullable
		@Override
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	
		rootview = inflater.inflate(R.layout.startpagina,container, false);
		return rootview;
}
}