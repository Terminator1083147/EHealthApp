package IPMEDT4.groep2.e_health;

///I made some comments and stuff to test the github0
//Bla die bla die bla
//
//Shitterdeshit test

//quirijn
//bert-jan
//Maarten

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        
    	//getMenuInflater().inflate(R.menu.main, menu);
       // getMenuInflater().inflate(R.menu.main_activity_action, menu);
        //return true;
    	MenuInflater test = getMenuInflater();
    	test.inflate(R.menu.main_activity_action, menu);
    	//return super.onCreateOptionsMenu(menu);
    	return true;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	//als over ons drukt voer code uit
            return true;
        }
        else if (id == R.id.menu1) {
            
        	//als over ons drukt voer code uit
        	return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
