package com.example.shameal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity{
	
	private TextView ocrResultText;
	private TextView numOfResult;
	private String place;
	private String num;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		place = bundle.getString(CaptureActivity.text);
		//num = bundle.getString(CaptureActivity.text2);
		setContentView(R.layout.result);
		ocrResultText = (TextView)findViewById(R.id.ocr_result_text);
		//numOfResult = (TextView)findViewById(R.id.num_of_result);
		
		ocrResultText.setText(place);
		//numOfResult.setText(num);
	}

	/*@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}*/
	
}
