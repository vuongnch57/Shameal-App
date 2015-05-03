package com.example.shameal;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.example.shameal.controller.AppController;

public class PlaceActivity extends Activity{

	private int msg;
	private TextView tv;
	private JSONObject jsonObj;
	private String[] imageArr;
	private SliderLayout imageSlider;
	private ImageView img;
	private NetworkImageView avatar;
	private TextView name;
	private TextView address;
	private TextView description;
	private TextView time;
	private TextView telephone;
	ImageLoader imageLoader = AppController.getInstance().getImageLoader();

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
		
		/*tv = (TextView) findViewById(R.id.textView1);
		String s = "";
		s = s + msg;
		tv.setText(jsonObj.toString());*/
		
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
			url_maps.put("1", imageArr[0]);
		if(!imageArr[1].isEmpty())
			url_maps.put("2", imageArr[1]);
		if(!imageArr[2].isEmpty())
			url_maps.put("3", imageArr[2]);
		if(!imageArr[3].isEmpty())
			url_maps.put("4", imageArr[3]);
		if(!imageArr[4].isEmpty())
			url_maps.put("5", imageArr[4]);

        for(String name : url_maps.keySet()){
            DefaultSliderView textSliderView = new DefaultSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .image(url_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
           imageSlider.addSlider(textSliderView);
        }
        imageSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        imageSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        imageSlider.setCustomAnimation(new DescriptionAnimation());
        imageSlider.setDuration(4000);
        
        avatar = (NetworkImageView) findViewById(R.id.avatar);
        name = (TextView) findViewById(R.id.name);
        address = (TextView) findViewById(R.id.address);
        description = (TextView) findViewById(R.id.description);
        time = (TextView) findViewById(R.id.time);
        telephone = (TextView) findViewById(R.id.telephone);
        try {
			name.setText(jsonObj.getString("name"));
			address.setText(jsonObj.getString("address"));
			description.setText(jsonObj.getString("description"));
			String ava = jsonObj.isNull("avatar") ? null : jsonObj
					.getString("avatar");
			avatar.setImageUrl(ava, imageLoader);
			/*String tele =  jsonObj .getString("telephone");
			if(tele=="")
				telephone.setText("Đang cập nhật");
			else
				telephone.setText(tele);*/
			String t = "";
			t = t + jsonObj.getString("opentime") + " - " + jsonObj.getString("closetime");
			time.setText(t);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#fd482f")));
        getActionBar().setIcon(
                   new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getActionBar().setDisplayHomeAsUpEnabled(true);
	}
}
