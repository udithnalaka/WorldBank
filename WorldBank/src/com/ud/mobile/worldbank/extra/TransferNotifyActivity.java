package com.ud.mobile.worldbank.extra;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ud.mobile.worldbank.R;
import com.ud.mobile.worldbank.services.CustomerService;

public class TransferNotifyActivity extends Activity {
	
	public static final int PICK_CONTACT_SUBACTIVITY = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transfer_notify);
		
		Bundle extras = getIntent().getExtras();
		String msgBody = extras.getString("MsgBody");	
		
		EditText txtContactMsgBody = (EditText) findViewById(R.id.editMsgBody);
		txtContactMsgBody.setText(msgBody);
		
	}
	
	public void pickSenderFromContactList(View view){			 
		
		 Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI); 
		 startActivityForResult(intent, PICK_CONTACT_SUBACTIVITY); 
	}
	
	//this method is called when a sub activity is closed and returned to the calling activity
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case (PICK_CONTACT_SUBACTIVITY): {
			if (resultCode == Activity.RESULT_OK) {
				
				String contactData = CustomerService.getContactDetailsFromSavedContacts(data, getBaseContext());
				
				EditText txtContactName = (EditText) findViewById(R.id.editContactNum);
				txtContactName.setText(contactData);				
			}
			break;
		}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transfer_notify, menu);
		return true;
	}

}
