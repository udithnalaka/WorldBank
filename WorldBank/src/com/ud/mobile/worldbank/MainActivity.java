package com.ud.mobile.worldbank;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	String logged_user = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Bundle extras = getIntent().getExtras();

		// Extract data using passed keys
		logged_user = extras.getString("User");

		Toast.makeText(getApplicationContext(), "Welcome " + logged_user,
				Toast.LENGTH_SHORT).show();
	}
	
	//called from Accounts button
	public void viewAccountList(View view){
		// starting HomeActivity
		Intent iHome = new Intent(this, ManageAccountsActivity.class);
		iHome.putExtra("User", logged_user);
		startActivity(iHome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
