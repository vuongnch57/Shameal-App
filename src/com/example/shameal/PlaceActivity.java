package com.example.shameal;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.TextView;

public class PlaceActivity extends Activity {

	private String msg;
	private TextView tv;
	private JSONArray jsonArr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);
		Bundle bundle = getIntent().getExtras();
		msg = bundle.getString(ResultActivity.json);
		if(msg != null) {
			try {
				jsonArr = new JSONArray(msg);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		tv = (TextView) findViewById(R.id.textView1);
		String s = "";
		s = s + jsonArr.length();
		tv.setText(s);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#488214")));
        getActionBar().setIcon(
                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
	}

}
