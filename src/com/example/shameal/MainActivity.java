package com.example.shameal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	public ProgressDialog dialog;
	public Button startCapture;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		if(isInternetOn()==false) {
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
		    builder.setTitle("Cảnh báo !!!")
		    	   .setMessage("Kết nối Internet trước khi bắt đầu.")
		           .setCancelable(false)
		           .setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                    MainActivity.this.finish();
		               }
		           });
		    AlertDialog alert = builder.create();
		    alert.show();
		} else {
			setContentView(R.layout.activity_main);
			startCapture = (Button) findViewById(R.id.captureButton);
			
			getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fd482f")));
	        getActionBar().setIcon(
	                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
	        
		}
	}
	
	public void onClick(View view) {
		Intent intent = new Intent(this, CaptureActivity.class);
		startActivity(intent);
	}
	
	public final boolean isInternetOn() {
        
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =  
                       (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
         
           // Check for network connections
            if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                 connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                 connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                 connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
                
                // if connected with internet
                 
                //Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
                return true;
                 
            } else if ( 
              connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
              connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
               
                //Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
                return false;
            }
          return false;
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
	                    MainActivity.this.finish();
	               }
	           })
	           .setNegativeButton("Cancel", null);
	    AlertDialog alert = builder.create();
	    alert.show();
	}

}
