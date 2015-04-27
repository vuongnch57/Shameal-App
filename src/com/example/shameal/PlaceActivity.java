package com.example.shameal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

public class PlaceActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#488214")));
        getActionBar().setIcon(
                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		//super.onBackPressed();
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    builder.setMessage("Thoát ứng dụng?")
	           .setCancelable(true)
	           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                    PlaceActivity.this.finish();
	                    System.exit(0);
	               }
	           })
	           .setNegativeButton("Cancel", null);
	    AlertDialog alert = builder.create();
	    alert.show();
	}
}
