package com.ud.mobile.worldbank;

import java.util.ArrayList;
import java.util.List;

import com.ud.mobile.worldbank.extra.TransferNotifyActivity;
import com.ud.mobile.worldbank.util.AccountUtil;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MoneyTransferActivity extends Activity {
	
	protected static final int PICK_CONTACT = 1;
	String accountNameVal = null;
	String accountBalanaceVal = null;
	Spinner toAccountList = null;
	ArrayAdapter<CharSequence> dataAdapter = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_money_transfer);
		
		Bundle extras = getIntent().getExtras();		
		accountNameVal = extras.getString("AccountName");
		accountBalanaceVal = extras.getString("AccountBalance");
		
		TextView accountName = (TextView)findViewById(R.id.viewAccountName);
		accountName.setText(accountNameVal);
		TextView accountBalance = (TextView)findViewById(R.id.viewAccountBalance);
		accountBalance.setText(accountBalanaceVal);			
		
		toAccountList = (Spinner) findViewById(R.id.spinnerAccounts);		
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				AccountUtil.getAllAccounts());
		toAccountList.setAdapter(arrayAdapter);		
		
		//add "other account" to the accounts list		
		arrayAdapter.add("other account...");
		arrayAdapter.notifyDataSetChanged();	
		
	}
	
	public void transferMoney(View view){
		 Toast.makeText(getApplicationContext(), "transferring money.....", 
			      Toast.LENGTH_SHORT).show();
		 
		 EditText amount = (EditText)findViewById(R.id.editTransferAmount);
		 
		 final TextView transferMsg = (TextView)findViewById(R.id.transferMsg);
			transferMsg.setText("Amount " + amount.getText().toString() + " AUD transfered successfully" +
			 		" to account: " + toAccountList.getSelectedItem().toString());
			
			TextView txtNotifyReceiver = (TextView)findViewById(R.id.notifyMoneyReceiver);
			txtNotifyReceiver.setVisibility(View.VISIBLE);
			Button btnNotifyReceiver = (Button)findViewById(R.id.btnSendSMS);
			btnNotifyReceiver.setVisibility(View.VISIBLE);
			
		btnNotifyReceiver.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				Intent intent = new Intent(view.getContext(),	TransferNotifyActivity.class);
				intent.putExtra("MsgBody", transferMsg.getText().toString());
				startActivity(intent);
			}
		});		

	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.money_transfer, menu);
		return true;
	}

}
