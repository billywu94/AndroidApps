package org.turntotech.navigatesample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View; //BW
import android.widget.TextView;

public class DetailActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        Intent intent = getIntent();
        String description = intent.getStringExtra("Devices"); //getting the device description to place in TextView object
        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText(description);
    }

}
