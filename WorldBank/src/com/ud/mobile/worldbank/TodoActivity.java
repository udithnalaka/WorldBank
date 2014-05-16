package com.ud.mobile.worldbank;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class TodoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_todo);
		
		Bundle extras = getIntent().getExtras();

        // Extract data using passed keys
        String logged_user = extras.getString("User");        
        Toast.makeText(getApplicationContext(), "Welcome " + logged_user , 
			      Toast.LENGTH_SHORT).show();
        
        ListView itemListView = (ListView)findViewById(R.id.itemListView);
        final EditText addItemText = (EditText)findViewById(R.id.addItemText);
        
        final ArrayList<String> todoItems = new ArrayList<String>();
        
        final ArrayAdapter<String> aa = new ArrayAdapter<String>(this, R.layout.todolist_item,
        		todoItems);
        
        itemListView.setAdapter(aa);
        
        addItemText.setOnKeyListener(new OnKeyListener() {			
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if(event.getAction() == KeyEvent.ACTION_DOWN){
					if(keyCode == KeyEvent.KEYCODE_ENTER){
						todoItems.add(0, addItemText.getText().toString());
						aa.notifyDataSetChanged();
						addItemText.setText("");
						return true;
					}
				}
				
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.todo, menu);
		return true;
	}

}
