package com.ud.mobile.worldbank;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	
	private EditText  username = null;
	private EditText  password = null;
	private TextView attempts;
	private Button login;
	int counter = 3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		username = (EditText)findViewById(R.id.editUsername);
		password = (EditText)findViewById(R.id.editPassword);
		login = (Button)findViewById(R.id.buttonLogin);
		attempts = (TextView)findViewById(R.id.textAttempts);
		attempts.setText(Integer.toString(counter));
	}
	
	//called from "login" button click
	public void login(View view){
		if(username.getText().toString().equals("admin") &&
				password.getText().toString().equals("admin")){
			Toast.makeText(getApplicationContext(), "Redirecting...", 
				      Toast.LENGTH_SHORT).show();
			
			// starting HomeActivity
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("User", username.getText().toString());			
			startActivity(intent);
			
		}else{
			Toast.makeText(getApplicationContext(), "wrong login details...", 
				      Toast.LENGTH_SHORT).show();
			attempts.setBackgroundColor(Color.RED);			
			attempts.setText(Integer.toString(--counter));
			if(counter == 0){
				login.setEnabled(false);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
