package com.codess.android_coderdojo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CollaboratorActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collaborator_profile);
	}
	
	
	//button back home
	public void gotoMyProfile(View v){
		Intent intent = new Intent(this, SetProfileActivity.class);
		startActivity(intent);
	}
}
