package com.simarro.memory;

import android.os.Bundle;
import android.view.KeyEvent;
import android.app.Activity;
import android.content.Intent;

public class Preferencias extends Activity 
{

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		getFragmentManager().beginTransaction().replace(android.R.id.content,new Opciones()).commit();
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent  event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	Intent i = new Intent(Preferencias.this, MainActivity.class);
			startActivity(i);
        	
        }
        return true;
	}
}
