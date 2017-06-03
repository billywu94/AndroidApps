/*
 * We know to add UI elements by using XML;however, here we will provide a simple
 * guideline for generating rows dynamically in your Android code.
 */


package com.example.dynamictableview;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import android.view.LayoutInflater;

public class MainActivity extends Activity {

	ScrollView sv;
	TableLayout myTableLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        Log.i("TurnToTech", "Project Name - Dynamic table view");
		sv = new ScrollView(this);
	    myTableLayout = new TableLayout (this);
	    drawScreen();
	}

	 public void drawScreen()
	 {
		    // this might be a redraw, so we clean the view's container	    
		    myTableLayout.removeAllViews();
		    sv.removeAllViews();

		    // special rows
		    TableRow buttonRow = new TableRow(this);
		    TableRow titleRow = new TableRow(this);
		    
		    // margins
		    buttonRow.setPadding( 20,10,20,10);
		    titleRow.setPadding( 20,10,20,10);

		    // the title
		    TextView title = new TextView(this);
		    title.setText( "Data input form" );
		    title.setTextSize(14);
		    title.setTextColor(Color.BLACK);
		    title.setGravity(Gravity.CENTER_HORIZONTAL);
						    
		    titleRow.addView(title);
		    titleRow.setGravity(Gravity.CENTER);
		    
		    // the title tablelayout
		    TableLayout titleTableLayout = new TableLayout(this);		     
		    titleTableLayout.addView(titleRow);

		    // we add the title layout to the main one 
		    myTableLayout.addView(titleTableLayout);


		    /// the 5 input rows
		    inputRow(myTableLayout, "Name", 30, 10000);
		    inputRow( myTableLayout, "Last Name", 20, 10001);
		    inputRow( myTableLayout, "Age", 3, 10002);
		 	inputRow( myTableLayout, "Street Address", 40, 10003);
		 	inputRow( myTableLayout, "City, State, ZIP", 40, 10004);
		    //

		 	// the buttons table layout
		    // purpose : right align for both buttons
		    TableLayout buttonsLayout = new TableLayout(this);
		    buttonRow.setPadding(20,50,40,0);

		 	//Alerts
		 	final AlertDialog alertDialog;
		 	alertDialog = new AlertDialog.Builder(this).create();
		 	alertDialog.setTitle("Save");
		 	//LayoutInflater inflater = getLayoutInflater(); //created 3/4/17 (Not used anymore)
			//alertDialog.setView(inflater.inflate(R.layout.custom_alert_dialog,null)); //created 3/4/17 (Not used anymore)

		 	final AlertDialog alertDialog1;
		 	alertDialog1 = new AlertDialog.Builder(this).create();
		 	alertDialog1.setTitle("Cancel");
		 	alertDialog1.setMessage("Rejected");
		    
		    // the accept and cancel buttons
		    Button btAccept = new Button(this);
		    btAccept.setText("Save");
		    btAccept.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{
					//http://stackoverflow.com/questions/35586562/how-to-display-users-inputs-on-dialog
					EditText nameInput = (EditText)myTableLayout.findViewById(10000);
					String userName = nameInput.getText().toString();
					EditText input2 = (EditText)myTableLayout.findViewById(10001);
					String uName2 = input2.getText().toString();
					EditText input3 = (EditText)myTableLayout.findViewById(10002);
					String uName3 = input3.getText().toString();
					EditText input4 = (EditText)myTableLayout.findViewById(10003);
					String uName4 = input4.getText().toString();
					EditText input5 = (EditText)myTableLayout.findViewById(10004);
					String uName5 = input5.getText().toString();
					alertDialog.setMessage(userName + '\n' + uName2 + '\n' + uName3 + '\n' + uName4 + '\n' + uName5);
					alertDialog.show();
				}
			});
		    
		    Button btCancel = new Button(this);	    
		    btCancel.setText( "Cancel");
		    btCancel.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{
					
					alertDialog1.show();
				}
			});
		    
		    
		    buttonRow.addView( btAccept);
		    buttonRow.addView(btCancel);
		    buttonRow.setGravity(Gravity.RIGHT);

		    buttonsLayout.addView(buttonRow);

		    
		    myTableLayout.addView(buttonsLayout);
		    sv.addView(myTableLayout);

		    // set the screen content to table layout's
		    setContentView(sv);
	    }

	    //
	    // inputRow :
	    // adds a label with an inputtextbox as a tablerow to a an existing tablelayout
	    //
	    // Tablelayout  :  layout to fill
	    // label	    :  input's label
	    // inputsize    :  in character, for drawing an aprox size
	    // inputID	  :  IMPORTANT ! you will use that id to get the input value with a getID
	    //				 taking into account that you will do the validation at the end (by looping the IDS)
	    //				 or implementing an unique listener which will receive the editText as a parameter
	    //
	    public void inputRow( TableLayout tl, String label, int inputSize, int inputID )
	    {
		    TableRow inputRow = new TableRow(this);
		    TextView tv = new TextView(this);
		    EditText edit = new EditText(this);

		    // some margin
		    inputRow.setPadding( 20,10,20,0);
		    tv.setText(label);
		    edit.setMaxWidth( inputSize*7)  ;
		    edit.setMinimumWidth(inputSize*7);
		    edit.setId( inputID );
		    edit.setGravity(Gravity.RIGHT);
		    inputRow.addView(tv);
		    inputRow.addView(edit);

			tl.addView(inputRow);
	    }
}
