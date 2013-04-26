package com.polly.dalekcontroller;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class MainActivity extends Activity {

	protected String TAG = MainActivity.class.getName();

	public class UrlRequestTask extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			String url = params[0];
			Log.d(TAG, "pinging URL: " + url);
			int response;
			try {
				response = new HttpClient().pingUrl(url);
				Log.d(TAG, "got response " + response + " from url " + url);
			} catch (IOException e) {
				Log.e(TAG, "couldn't connect to url: " + url);
			}
			return null;
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ImageButton upButton = (ImageButton) findViewById(R.id.up);
		ImageButton leftButton = (ImageButton) findViewById(R.id.left);
		ImageButton rightButton = (ImageButton) findViewById(R.id.right);
		ImageButton exterminateButton = (ImageButton) findViewById(R.id.exterminate);

		upButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed up");
				new UrlRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getUrlPrefixString() + getString(R.string.forward));
			}
		});

		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed left");
				new UrlRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getUrlPrefixString() + getString(R.string.left));
			}
		});

		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed right");
				new UrlRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getUrlPrefixString() + getString(R.string.right));
			}
		});
		
		exterminateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed exterminate");
				new UrlRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getUrlPrefixString() + getString(R.string.exterminate));
			}
		});		
	}

	private String getUrlPrefixString() {
		SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
		final String ipAddress = sharedPref.getString("ip_address", "10.10.1.104");
		final String port = sharedPref.getString("port", "81");
		final String hostString = "http://" + ipAddress + ":" + port + "/";
		return hostString;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		final int itemId = item.getItemId();
		switch (itemId) {
			case R.id.action_settings:
				Log.d(TAG, "pressed settings");
				Intent i = new Intent(this, SettingsActivity.class);
				startActivity(i);
				break;
			case R.id.action_restart:
				Log.d(TAG, "pressed restart");
				new UrlRequestTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getUrlPrefixString() + getString(R.string.reboot));
				break;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
