package IPMEDT4.groep2.e_health;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SecondFragment extends Fragment {
	
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
	return rootview;

}
}