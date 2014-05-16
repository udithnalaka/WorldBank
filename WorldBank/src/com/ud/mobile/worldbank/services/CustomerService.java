package com.ud.mobile.worldbank.services;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.widget.EditText;

import com.ud.mobile.worldbank.R;

public class CustomerService {
	
	public static String getContactDetailsFromSavedContacts(Intent data, Context context){
		
		String contacId = null;
		String contactName = null;
		String contactPhoneNum = null;
		Uri contactData = data.getData();
		Cursor contactNameCursor = context.getContentResolver().query(contactData, null, null, null, null);
		if (contactNameCursor.moveToFirst()) {
			contactName = contactNameCursor.getString(contactNameCursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
			contacId = contactNameCursor.getString(contactNameCursor.getColumnIndex(ContactsContract.Contacts._ID));
			
			////Can't get phone number from selected contact
			/*Cursor contactPhoneCursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
					new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
					ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ? ", new String[] { contacId }, null);

			if (contactPhoneCursor.moveToFirst()) {
				contactPhoneNum = contactPhoneCursor.getString(contactPhoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			}
			contactPhoneCursor.close();	*/		
		}
		contactNameCursor.close();
		
		return contactName; // + "-" + contactPhoneNum;
	}

}
