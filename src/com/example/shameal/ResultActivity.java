package com.example.shameal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends Activity{
	private TextView ocrResultText;
	private String place;
	
	private Socket client;
	private PrintWriter printwriter;
	String message;
	private BufferedReader in;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		place = bundle.getString(CaptureActivity.text);
		setContentView(R.layout.result);
		ocrResultText = (TextView)findViewById(R.id.ocr_result_text);
		ocrResultText.setText(place);
	
	    SendMessage sendMessageTask = new SendMessage();
		sendMessageTask.execute(); 
	}
	
	    private class SendMessage extends AsyncTask<Void, Void, String> {
	    	
			@Override
			protected String doInBackground(Void... params) {
				String msg; //luu du lieu tu server
				try {
					client = new Socket("128.199.160.37", 80); // connect to the server
					printwriter = new PrintWriter(client.getOutputStream());
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					printwriter.println(place); // write the message to output stream
					printwriter.flush();
					msg = in.readLine();
					Log.i("FromServer", msg);
					in.close();
					printwriter.close();
					client.close(); // closing the connection
					return msg;
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
			
			protected void onPostExecute(String msg) { //hien thi sau khi lay du lieu tu server 
				Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
			}
		}
}
