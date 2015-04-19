package com.example.shameal;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.activity_main_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Take appropriate action for each action item click
		switch (item.getItemId()) {
		case R.id.action_home:
			Intent intent1 = new Intent(this, MainActivity.class);
			startActivity(intent1);
			return true;
		case R.id.action_search:
			// location found
			
			return true;
		case R.id.action_camera:
			Intent intent = new Intent(this, CaptureActivity.class);
			startActivity(intent);
			return true;
		case R.id.action_history:
			// help action
			return true;
		
		default:
			return super.onOptionsItemSelected(item);
		}
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
