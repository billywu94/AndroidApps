package org.turntotech.addressbook;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

//source: http://www.higherpass.com/Android/Tutorials/Working-With-Android-Contacts/
public class MainActivity extends ListActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
        Log.i("TurnToTech", "Project name - AddressBook");
		//1. The class ContactsContract allows access to various types of data stored in the phone
		//Examples: Contacts, Phone settings, Contact groups etc.

		Cursor cursor = getContentResolver().query(
				ContactsContract.Contacts.CONTENT_URI, null, null, null, null);

		ArrayAdapter<String> list = new ArrayAdapter<String>(this,
				R.layout.activity_main);

		//2. Using the ContactsContract class above, we got a cursor to the data. Now we iterate through it to get all the data
		
		while (cursor.moveToNext()) {
			
			//3. In our example, we get the DISPLAY_NAME but other data elements are also available
			
			String name = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
			if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0){
				Cursor phoneCursor =  getContentResolver().query(
						ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID
							+" = ?", new String []{id},null);
				while(phoneCursor.moveToNext() ){
					String contactInfo = name + ": " + phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
					list.add(contactInfo);
				}
			}
		}

		setListAdapter(list);

	}

}