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
	public static String json = "";
	public static JSONArray jsonArr;
	
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
					printwriter.println("Cafe");
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
				
				if (msg != null) {
					try {
						String result = "";
						int j=0;
						while(j<10) {
							msg = msg.replace("\\xe0", "à");
							msg = msg.replace("\\xe1", "á");
							msg = msg.replace("\\xe2", "â");
							msg = msg.replace("\\xea", "ê");
							msg = msg.replace("\\xf5", "õ");
							msg = msg.replace("\\xe9/", "e ");
							msg = msg.replace("\\u00e0", "à");
							msg = msg.replace("\\u00e1", "á");
							msg = msg.replace("\\u1ea3", "ả");
							msg = msg.replace("\\u00e3", "ã");
							msg = msg.replace("\\u1ea1", "ạ");						
							msg = msg.replace("\\u0103", "ă");
							msg = msg.replace("\\u1eb1", "ằ");
							msg = msg.replace("\\u1eaf", "ắ");
							msg = msg.replace("\\u1eb3", "ẳ");
							msg = msg.replace("\\u1eb5", "ẵ");
							msg = msg.replace("\\u1eb7", "ặ");
							msg = msg.replace("\\u00e2", "â");
							msg = msg.replace("\\u1ea7", "ầ");
							msg = msg.replace("\\u1ea5", "ấ");
							msg = msg.replace("\\u1ea9", "ẩ");
							msg = msg.replace("\\u1eab", "ẫ");
							msg = msg.replace("\\u1ead", "ậ");
							msg = msg.replace("\\u0111", "đ");
							msg = msg.replace("\\u00e8", "è");
							msg = msg.replace("\\u00e9", "é");
							msg = msg.replace("\\u1ebb", "ẻ");
							msg = msg.replace("\\u1ebd", "ẽ");
							msg = msg.replace("\\u1eb9", "ẹ");
							msg = msg.replace("\\u00ea", "ê");
							msg = msg.replace("\\u1ec1", "ề");
							msg = msg.replace("\\u1ebf", "ế");
							msg = msg.replace("\\u1ec3", "ể");
							msg = msg.replace("\\u1ec5", "ễ");
							msg = msg.replace("\\u1ec7", "ệ");
							msg = msg.replace("\\u00f2", "ò");
							msg = msg.replace("\\u00f3", "ó");
							msg = msg.replace("\\u1ecf", "ỏ");
							msg = msg.replace("\\u00f5", "õ");
							msg = msg.replace("\\u1ecd", "ọ");
							msg = msg.replace("\\u00f4", "ô");
							msg = msg.replace("\\u1ed3", "ồ");
							msg = msg.replace("\\u1ed1", "ố");
							msg = msg.replace("\\u1ed5", "ổ");
							msg = msg.replace("\\u1ed7", "ỗ");
							msg = msg.replace("\\u1ed9", "ộ");
							msg = msg.replace("\\u01a1", "ơ");
							msg = msg.replace("\\u1edd", "ờ");
							msg = msg.replace("\\u1edb", "ớ");
							msg = msg.replace("\\u1edf", "ở");
							msg = msg.replace("\\u1ee1", "ỡ");
							msg = msg.replace("\\u1ee3", "ợ");
							msg = msg.replace("\\u00f9", "ù");
							msg = msg.replace("\\u00fa", "ú");
							msg = msg.replace("\\u1ee7", "ủ");
							msg = msg.replace("\\u0169", "ũ");
							msg = msg.replace("\\u1ee5", "ụ");
							msg = msg.replace("\\u01b0", "ư");
							msg = msg.replace("\\u1eeb", "ừ");
							msg = msg.replace("\\u1ee9", "ứ");
							msg = msg.replace("\\u1eed", "ử");
							msg = msg.replace("\\u1eef", "ữ");
							msg = msg.replace("\\u1ef1", "ự");
							msg = msg.replace("\\u00c0", "À");
							msg = msg.replace("\\u00c1", "Á");
							msg = msg.replace("\\u1ea2", "Ả");
							msg = msg.replace("\\u00c3", "Ã");
							msg = msg.replace("\\u1ea0", "Ạ");
							msg = msg.replace("\\u0102", "Ă");
							msg = msg.replace("\\u1eb0", "Ằ");
							msg = msg.replace("\\u1eae", "Ắ");
							msg = msg.replace("\\u1eb2", "Ẳ");
							msg = msg.replace("\\u1eb4", "Ẵ");
							msg = msg.replace("\\u1eb6", "Ặ");
							msg = msg.replace("\\u00c2", "Â");
							msg = msg.replace("\\u1ea6", "Ầ");
							msg = msg.replace("\\u1ea4", "Ấ");
							msg = msg.replace("\\u1ea8", "Ẩ");
							msg = msg.replace("\\u1eaa", "Ẫ");
							msg = msg.replace("\\u1eac", "Ậ");
							msg = msg.replace("\\u0110", "Đ");
							msg = msg.replace("\\u00c8", "È");
							msg = msg.replace("\\u00c9", "É");
							msg = msg.replace("\\u1eba", "Ẻ");
							msg = msg.replace("\\u1ebc", "Ẽ");
							msg = msg.replace("\\u1eb8", "Ẹ");
							msg = msg.replace("\\u00ca", "Ê");
							msg = msg.replace("\\u1ec0", "Ề");
							msg = msg.replace("\\u1ebe", "Ế");
							msg = msg.replace("\\u1ec2", "Ể");
							msg = msg.replace("\\u1ec4", "Ễ");
							msg = msg.replace("\\u1ec6", "Ệ");
							msg = msg.replace("\\u00d2", "Ò");
							msg = msg.replace("\\u00d3", "Ó");
							msg = msg.replace("\\u1ece", "Ỏ");
							msg = msg.replace("\\u00d5", "Õ");
							msg = msg.replace("\\u1ec6", "Ọ");
							msg = msg.replace("\\u00d4", "Ô");
							msg = msg.replace("\\u1ed2", "Ồ");
							msg = msg.replace("\\u1ed0", "Ố");
							msg = msg.replace("\\u1ed4", "Ổ");
							msg = msg.replace("\\u1ed6", "Ỗ");
							msg = msg.replace("\\u1ed8", "Ộ");
							msg = msg.replace("\\u01a0", "Ơ");
							msg = msg.replace("\\u1edc", "Ờ");
							msg = msg.replace("\\u1eda", "Ớ");
							msg = msg.replace("\\u1ede", "Ở");
							msg = msg.replace("\\u1ee0", "Õ");
							msg = msg.replace("\\u1ee2", "Ợ");
							msg = msg.replace("\\u00d9", "Ù");
							msg = msg.replace("\\u00da", "Ú");
							msg = msg.replace("\\u1ee6", "Ủ");
							msg = msg.replace("\\u0168", "Ũ");
							msg = msg.replace("\\u1ee4", "Ụ");
							msg = msg.replace("\\u01af", "Ư");
							msg = msg.replace("\\u1eea", "Ừ");
							msg = msg.replace("\\u1ee8", "Ứ");
							msg = msg.replace("\\u1eec", "Ử");
							msg = msg.replace("\\u1eee", "Ữ");
							msg = msg.replace("\\u1ef0", "Ự");
							msg = msg.replace("\\u00ec", "ì");
							msg = msg.replace("\\u00ed", "í");
							msg = msg.replace("\\u1ec9", "ỉ");
							msg = msg.replace("\\u0129", "ĩ");
							msg = msg.replace("\\u1ecb", "ị");
							msg = msg.replace("\\u00cc", "Ì");
							msg = msg.replace("\\u00cd", "Í");
							msg = msg.replace("\\u1ec8", "Ỉ");
							msg = msg.replace("\\u0128", "Ĩ");
							msg = msg.replace("\\u1eca", "Ị");
							msg = msg.replace("\\u1ef3", "ỳ");
							msg = msg.replace("\\u00fd", "ý");
							msg = msg.replace("\\u1ef7", "ỷ");
							msg = msg.replace("\\u1ef9", "ỹ");
							msg = msg.replace("\\u1ef5", "ỵ");
							msg = msg.replace("\\u1ef2", "Ỳ");
							msg = msg.replace("\\u00dd", "Ý");
							msg = msg.replace("\\u1ef6", "Ỷ");
							msg = msg.replace("\\u1ef8", "Ỹ");
							msg = msg.replace("\\u1ef4", "Ỵ");
							j++;
						}
						Log.i("FromServer", msg);
						
						//json = msg;
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
							
							//item.setRate(feedObj.getInt("rate"));

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
			
			intent.putExtra(json, position);
			startActivity(intent);
		}

}
