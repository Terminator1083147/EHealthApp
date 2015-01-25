package IPMEDT4.groep2.e_health;

///I made some comments and stuff to test the github0
//Bla die bla die bla
//
//Shitterdeshit test

//quirijn
//bert-jan
//Maarten

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	
	private FragmentNavigationDrawer dlDrawer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar bar = getActionBar();
    	bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#EA4D4E")));
        
     // Find our drawer view
        dlDrawer = (FragmentNavigationDrawer) findViewById(R.id.drawer_layout);
        // Setup drawer view
        dlDrawer.setupDrawerConfiguration((ListView) findViewById(R.id.lvDrawer), 
                     R.layout.drawer_nav_item, R.id.flContent);
        // Add nav items
        dlDrawer.addNavItem("Informatie", "Informatie", InformatieFragment.class);
        dlDrawer.addNavItem("Filters", "Filters", FiltermenuFragment.class);
        dlDrawer.addNavItem("Resultaten", "Resultaten", ResultatenFragment.class);
        dlDrawer.addNavItem("Over ons", "Over ons", OveronsFragment.class);
       
        // Select default
        if (savedInstanceState == null) {
            dlDrawer.selectDrawerItem(0);   
        }
     
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content
        if (dlDrawer.isDrawerOpen()) {
            // Uncomment to hide menu items                        
            // menu.findItem(R.id.mi_test).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
    	//getMenuInflater().inflate(R.menu.main, menu);
       // getMenuInflater().inflate(R.menu.main_activity_action, menu);
        //return true;
    	MenuInflater test = getMenuInflater();
    	test.inflate(R.menu.main_activity_action, menu);
    	return super.onCreateOptionsMenu(menu);
    	//return true;
        
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// The action bar home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (dlDrawer.getDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        dlDrawer.getDrawerToggle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggles
        dlDrawer.getDrawerToggle().onConfigurationChanged(newConfig);
    }
}
