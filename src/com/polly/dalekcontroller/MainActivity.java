package com.polly.dalekcontroller;

import java.io.IOException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
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
				new UrlRequestTask().execute(getString(R.string.up_url));
			}
		});

		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed left");
				new UrlRequestTask().execute(getString(R.string.up_url));
			}
		});

		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed right");
				new UrlRequestTask().execute(getString(R.string.up_url));
			}
		});
		
		exterminateButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d(TAG, "pressed exterminate");
				new UrlRequestTask().execute(getString(R.string.up_url));
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
