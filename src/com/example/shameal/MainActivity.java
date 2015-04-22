package com.example.shameal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
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
		    builder.setTitle("Alert")
		    	   .setMessage("Please connect to internet before start.")
		           .setCancelable(false)
		           .setPositiveButton("Quit", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                    MainActivity.this.finish();
		               }
		           });
		    AlertDialog alert = builder.create();
		    alert.show();
		} else {
			setContentView(R.layout.activity_main);
			startCapture = (Button) findViewById(R.id.captureButton);
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
}
