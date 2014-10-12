package com.codess.android_coderdojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PairedActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.paired);
	}
	
	
	public void gotoCollaborator(View v){
		Intent intent = new Intent(this, CollaboratorActivity.class);
		startActivity(intent);
	}

}
