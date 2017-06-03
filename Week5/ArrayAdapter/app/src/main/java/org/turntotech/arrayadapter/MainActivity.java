

package org.turntotech.arrayadapter;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.i("TurnToTech", "Project Name - Array Adapter");
		// 1. Create an array with the data we want to show in a table. Each element is 1 row.
		
		final String[] animals = new String[] { "Alligator", "Parrot", "Golden Retriever",
				"Black Bear", "Penguin", "Cat", "Fish", "Kangaroo",
				"Rabbit", "Elephant" };

		// 2. Define a new ArrayAdapter - which helps map an array to a list on the screen

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
				R.layout.activity_main, animals);

		//sources: http://stackoverflow.com/questions/18316925/listview-with-toast-message-on-click
		//https://developer.android.com/guide/topics/ui/notifiers/toasts.html#Basics
		ListView listView = getListView();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				//the second parameter shows the text value of the index when the user clicks on a certain
				//animal in the array.
				Toast toast = Toast.makeText(getApplicationContext(), String.valueOf(i), Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.BOTTOM,0,9); //positioning
				toast.show();
			}
		});

		// 3. Tell the list about the arrayAdapter. That list will now show the array data is rows.
		setListAdapter(arrayAdapter);
	}
}