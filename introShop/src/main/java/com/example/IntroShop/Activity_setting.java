package com.example.IntroShop;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Activity_setting extends AppCompatActivity {
	private Button about = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		about = (Button) findViewById(R.id.about);
		about.setOnClickListener(new OnClick());
	}

	public class OnClick implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.about:
				Intent intent = new Intent();
				intent.setClass(Activity_setting.this, About.class);
				startActivity(intent);
				break;

			default:
				break;
			}
		}

	}
}
