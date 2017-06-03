package org.turntotech.navigatesample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View; //BW
import android.widget.AdapterView; //BW
import android.widget.AdapterView.OnItemClickListener; //BW

public class ChildActivity extends ListActivity implements OnItemClickListener{

	int position = 0; //The index of the parent activity 0:Samsung 1:Apple
	@Override
	public void onCreate(Bundle savedInstanceState) {//initialize activity
		super.onCreate(savedInstanceState);

		String[][]data = {
				{ "Galaxy Tab", "Galaxy Smart Phones", "Galaxy Gear" },
				{ "iPhone", "iPad", "iPod" } };
		int[][] icons = {
				{ R.drawable.gala, R.drawable.duos, R.drawable.star },
				{ R.drawable.a, R.drawable.b, R.drawable.c } };
		Intent intent = getIntent();
		position = intent.getIntExtra("POSITION", 0);
		
		// Provide the cursor for the list view. 
		setListAdapter(new CustomListAdapter(this, data[position],
				icons[position]));
		getListView().setOnItemClickListener(this); //3.14.17 BW SetOnClick for the electronic products

	}

	@Override
	public void onItemClick(AdapterView<?> child, View view, int childPosition,
							long id) {
		Intent intent = new Intent(child.getContext(), DetailActivity.class); //create new Intent which leads to the detailActivity class
		intent.putExtra("POSITION", childPosition);

		System.out.println(childPosition);
		//position represent the parent activity, childPosition represents the electronic products
		if(position == 0 && childPosition == 0){
			intent.putExtra("Devices", getString(R.string.galaxy_tab));
		}else if(position == 0 && childPosition == 1){
			intent.putExtra("Devices", getString(R.string.galaxy_smart_phone));
		}else if(position == 0 && childPosition == 2){
			intent.putExtra("Devices", getString(R.string.galaxy_gear));
		}else if(position == 1 && childPosition == 0){
			intent.putExtra("Devices", getString(R.string.iPhone));
		}else if(position == 1 && childPosition == 1){
			intent.putExtra("Devices", getString(R.string.iPad));
		}else if(position ==1 && childPosition == 2){
			intent.putExtra("Devices", getString(R.string.iPod));
		}
		startActivity(intent);

	}

}
