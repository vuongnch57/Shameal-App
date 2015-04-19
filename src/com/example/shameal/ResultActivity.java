package com.example.shameal;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.TextView;

public class ResultActivity extends Activity{
	private TextView ocrResultText;
	private String place;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Bundle bundle = getIntent().getExtras();
		place = bundle.getString(CaptureActivity.text);
		setContentView(R.layout.result);
		ocrResultText = (TextView)findViewById(R.id.ocr_result_text);
		ocrResultText.setText(place);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	// fix to display split bar in tablet
		@Override
		public Resources getResources() {
		    return new ResourceFix(super.getResources());
		}

		private class ResourceFix extends Resources {
		    private int targetId = 0;

		    ResourceFix(Resources resources) {
		        super(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
		        targetId = Resources.getSystem().getIdentifier("split_action_bar_is_narrow", "bool", "android");
		    }

		    /**
		     * {@inheritDoc}
		     */
		    @Override
		    public boolean getBoolean(int id) throws Resources.NotFoundException {
		        return targetId == id || super.getBoolean(id);
		    }
		}
}
