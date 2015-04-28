package com.example.shameal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shameal.adapters.FeedListAdapter;
import com.example.shameal.data.FeedItem;

public class ResultActivity extends ListActivity{
	private TextView ocrResultText;
	private TextView numOfResult;
	private String place;
	
	private Socket client;
	private PrintWriter printwriter;
	String message;
	private BufferedReader in;
	public static String json;
	
	private static final String TAG = ResultActivity.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		place = bundle.getString(CaptureActivity.text);
		setContentView(R.layout.result);
		numOfResult = (TextView) findViewById(R.id.num_of_result);
		ocrResultText = (TextView)findViewById(R.id.ocr_result_text);
		ocrResultText.setText(place);

		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#488214")));
        getActionBar().setIcon(
                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
		
		listView = (ListView) findViewById(android.R.id.list);
        feedItems = new ArrayList<FeedItem>();
        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);
		
	    SendMessage sendMessageTask = new SendMessage();
		sendMessageTask.execute();
	}
	
	 private class SendMessage extends AsyncTask<Void, Void, String> {
	    	
			@Override
			protected String doInBackground(Void... params) {
				String msg=""; //luu du lieu tu server
				int bytesRead;
				ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
				byte[] buffer = new byte[1024];
				
				try {
					client = new Socket("128.199.160.37", 80); // connect to the server
					printwriter = new PrintWriter(client.getOutputStream());
					in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					InputStream inputStream = client.getInputStream();
//					printwriter.println(place); // write the message to output stream
					printwriter.println("KFC");
					printwriter.flush();
					
					/*while ((bytesRead = inputStream.read(buffer)) != -1){
		                 byteArrayOutputStream.write(buffer, 0, bytesRead);
		                 msg += byteArrayOutputStream.toString("UTF-8");
		             }*/
					msg = in.readLine();
					Log.i("FromServer", msg);
					inputStream.close();
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
				JSONArray jsonArr;
				if (msg != null) {
					try {
						String result = "";
						json = msg;
						jsonArr = new JSONArray(msg);
						for (int i=0; i<jsonArr.length(); i++) {
							//result = result + jsonArr.get(i).toString() + '\n';
							JSONObject feedObj = (JSONObject) jsonArr.get(i); 
			                FeedItem item = new FeedItem();
			                
			                item.setId(feedObj.getInt("id"));
			                item.setName(feedObj.getString("name"));
			                item.setAddress(feedObj.getString("address"));
			                item.setDescription(feedObj.getString("description"));
							String image = feedObj.isNull("avatar") ? null : feedObj
									.getString("avatar");
							item.setImage(image);
							
							String tele = feedObj.isNull("telephone") ? null : feedObj
									.getString("telephone");

							String menu = feedObj.isNull("image1") ? null : feedObj
									.getString("image1");
							item.setMenu(menu);
							
							item.setRate(feedObj.getInt("rate"));

							feedItems.add(item);
							
						}
						listAdapter.notifyDataSetChanged();
						
						String numResult = "Tìm thấy ";
						numResult = numResult + jsonArr.length() + " địa điểm";
						numOfResult.setText(numResult);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	 
	 @Override
		protected void onListItemClick(ListView l, View v, int position, long id) {
			// TODO Auto-generated method stub
			super.onListItemClick(l, v, position, id);
			Intent intent = new Intent(this, PlaceActivity.class);
			
			intent.putExtra(json, json);
			startActivity(intent);
		}

}
