package com.simarro.memory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity 
{

	private Button btnExit;
	private Button btnNewGame;
	private Button btnScores;
	private Button btnAbout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnExit = (Button)findViewById(R.id.btnExit);
		btnNewGame = (Button)findViewById(R.id.btnNewGame);
		btnScores = (Button)findViewById(R.id.btnScores);
		btnAbout = (Button)findViewById(R.id.btnAbout);
		
		btnExit.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Toast.makeText(getApplicationContext(), "Gracias por utilizar Memory" , Toast.LENGTH_SHORT).show();
				finish();
			}
		});
		
		btnAbout.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainActivity.this,About.class);
	        	startActivity(intent);
			}
		});
		
		btnNewGame.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainActivity.this,New_Game.class);
	        	startActivity(intent);
			}
		});
		
		btnScores.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				Intent intent = new Intent(MainActivity.this,Scores.class);
				Bundle b = new Bundle();
				b.putString("TIEMPO", "-1");
				intent.putExtras(b);
	        	startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) 
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) 
	{
		switch (item.getItemId()) 
	    {
	    	case R.id.it_new_game:
	        	Intent intent = new Intent(MainActivity.this,New_Game.class);
	        	startActivity(intent);
	        break;
	             
	        case R.id.it_scores:
	           	 Intent intent1 = new Intent(MainActivity.this,Scores.class);
	           	Bundle b = new Bundle();
				b.putString("TIEMPO", "-1");
				intent1.putExtras(b);
	        	startActivity(intent1);
	        break;
	            
	        case R.id.it_about:
	        	Intent intent2 = new Intent(MainActivity.this,About.class);
	           	startActivity(intent2);
		    break;
		    
	        case R.id.it_configuration:
	        	Intent intent3 = new Intent(MainActivity.this,Preferencias.class);
	           	startActivity(intent3);
		    break;
		            
	        case R.id.it_exit:
	        	Toast.makeText(getApplicationContext(), "Gracias por utilizar Memory" , Toast.LENGTH_SHORT).show();
				finish();
		    break;
	     }
	     return true;
	}
}

