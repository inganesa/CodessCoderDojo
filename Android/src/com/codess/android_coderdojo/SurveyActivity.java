package com.codess.android_coderdojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SurveyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survey);
		
	}
	
	 public void gotoAdditional(View v){
	    	
	    	Intent intent = new Intent(this, AdditionalQuestionsActivity.class);
	    	startActivity(intent);
	    	
	    }

}
