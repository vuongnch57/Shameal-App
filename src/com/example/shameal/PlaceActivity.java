package com.example.shameal;

import java.io.InputStream;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

public class PlaceActivity extends Activity {

	private int msg;
	private TextView tv;
	private JSONObject jsonObj;
	private String[] imageArr;
	private SliderLayout imageSlider;
	private ImageView img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place);
		Bundle bundle = getIntent().getExtras();
		msg = bundle.getInt(ResultActivity.json);
		try {
			jsonObj = (JSONObject) ResultActivity.jsonArr.get(msg);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		tv = (TextView) findViewById(R.id.textView1);
		String s = "";
		s = s + msg;
		tv.setText(jsonObj.toString());
		
		imageArr = new String[5];
		try {
			imageArr[0] = jsonObj.isNull("image1") ? null : jsonObj
					.getString("image1");
			imageArr[1] = jsonObj.isNull("image2") ? null : jsonObj
					.getString("image2");
			imageArr[2] = jsonObj.isNull("image3") ? null : jsonObj
					.getString("image3");
			imageArr[3] = jsonObj.isNull("image4") ? null : jsonObj
					.getString("image4");
			imageArr[4] = jsonObj.isNull("image5") ? null : jsonObj
					.getString("image5");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//tv.setText(imageArr[0] + "\n" + imageArr[1] + "\n" + imageArr[2] + "\n" + imageArr[3] + "\n" + imageArr[4]);
		imageSlider = (SliderLayout)findViewById(R.id.slider);
		//img = (ImageView) findViewById(R.id.imageView);
		HashMap<String,String> url_maps = new HashMap<String, String>();
		if(!imageArr[0].isEmpty())
			url_maps.put("Hannibal", imageArr[0]);
		if(!imageArr[1].isEmpty())
			url_maps.put("Big Bang Theory", imageArr[1]);
		if(!imageArr[2].isEmpty())
			url_maps.put("House of Cards", imageArr[2]);
		if(!imageArr[3].isEmpty())
			url_maps.put("Game of Thrones", imageArr[3]);
		if(!imageArr[4].isEmpty())
			url_maps.put("Game", imageArr[4]);

        for(String name : url_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
                    

            //add your extra information
            textSliderView.getBundle()
                    .putString("extra",name);

           imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(4000);
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#488214")));
        getActionBar().setIcon(
                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
	}


	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;
	    String url;

	    public DownloadImageTask(ImageView bmImage, String url) {
	        this.bmImage = bmImage;
	        this.url = url;
	    }

	    protected Bitmap doInBackground(Void... params) {
	        String urldisplay = url;
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }

		@Override
		protected Bitmap doInBackground(String... params) {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
