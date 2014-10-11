package com.codess.android_coderdojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SetProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_profile);
	}
	
	public void gotoSurvey(View v){
		Intent intent = new Intent(this, SurveyActivity.class);
		startActivity(intent);
	}

}
