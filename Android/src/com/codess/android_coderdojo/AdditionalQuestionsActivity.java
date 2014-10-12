package com.codess.android_coderdojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdditionalQuestionsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.additional_questions);

	}
//	
//	public void onRadioButtonClicked(View view) {
//	    // Is the button now checked?
//	    boolean checked = ((RadioButton) view).isChecked();
//	    
//	    // Check which radio button was clicked
//	    switch(view.getId()) {
//	        case R.id.radioButton1:
//	            if (checked)
//	                // Pirates are the best
//	            break;
//	        case R.id.radioButton2:
//	            if (checked)
//	                // Ninjas rule
//	            break;
//	    }
//	}

	public void gotoPaired(View v) {

		Intent intent = new Intent(this, PairedActivity.class);
		startActivity(intent);

	}
}
