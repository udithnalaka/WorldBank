package com.ud.mobile.worldbank;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ud.mobile.worldbank.util.AccountUtil;

public class ManageAccountsActivity extends Activity {
	
	private Spinner accountList;
	TextView txtaccountBalanace = null;
	TextView txtaccountInterest = null;
	String selectedAccount = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        
        Bundle extras = getIntent().getExtras();

        // Extract data using passed keys
        String logged_user = extras.getString("User");        
        Toast.makeText(getApplicationContext(), "Welcome " + logged_user , 
			      Toast.LENGTH_SHORT).show();
        
        accountList = (Spinner)findViewById(R.id.spinnerAccounts);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
				AccountUtil.getAllAccounts());
        accountList.setAdapter(arrayAdapter);	
        accountsDropdownListener();
    }


    //Listener for items selected from the Account List spinner
    private void accountsDropdownListener() {
		
		accountList.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				
				selectedAccount = parent.getItemAtPosition(position).toString();

				if(!selectedAccount.isEmpty()){
					Toast.makeText(parent.getContext(),
							"Selected : " + selectedAccount, Toast.LENGTH_SHORT).show();
					
					getAccountDetails(selectedAccount);
				}
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}


	protected void getAccountDetails(String account) {
		List<String> accountDetails = null;
		
		
		//currently using hardcoded account details		
		for (String accountName : account.split("-")) {
			accountDetails = AccountUtil.retrieveAccountDetails(accountName);
			
			if(accountDetails != null && accountDetails.size()> 0){
				txtaccountBalanace = (TextView)findViewById(R.id.textViewAccountBalance);
				txtaccountBalanace.setText(accountDetails.get(0));
				
				txtaccountInterest = (TextView)findViewById(R.id.textViewAccountInterest);
				txtaccountInterest.setText(accountDetails.get(1));
			}
		}

	}
	
	// called from Money Transfer button
	public void transferMoney(View view) {
		// starting HomeActivity
		Intent iTransfer = new Intent(this, MoneyTransferActivity.class);
		iTransfer.putExtra("AccountName", selectedAccount);
		iTransfer.putExtra("AccountBalance", txtaccountBalanace.getText());
		//iTransfer.putExtra("AccountName", selectedAccount);

		startActivity(iTransfer);
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }
    
}
