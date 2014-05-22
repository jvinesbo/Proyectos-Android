package com.simarro.memory;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class New_Game extends Activity 
{
	
	private Button btnStartGame;
	private RadioGroup rbgGroup;
	private String valor;
	private boolean boleano = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new__game);
	
		btnStartGame = (Button)findViewById(R.id.btnStartGame);
		rbgGroup = (RadioGroup)findViewById(R.id.rbgGroup);
		
		rbgGroup.clearCheck();
		
		rbgGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		{		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) 
			{
				if (checkedId == R.id.rbEasy) 
				{
					valor = "easy";
					boleano = true;
				} 
				else if(checkedId == R.id.rbMedium)
				{
					valor = "medium";
					boleano = true;
				}
				else
				{
					valor = "difficult";
					boleano = true;
				}
			}
		});
		
		btnStartGame.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
				if(!boleano)
				{
					Toast.makeText(getApplicationContext(),"Selecciona una opción de juego", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if (valor.equals("easy")) 
					{
						Intent intent = new Intent(New_Game.this, Easy.class);
						Bundle bundle = new Bundle();
						bundle.putString("VALOR", valor);
						intent.putExtras(bundle);
						startActivity(intent);
					} 
					else if (valor.equals("medium")) 
					{
						Intent intent = new Intent(New_Game.this, Medium.class);
						Bundle bundle = new Bundle();
						bundle.putString("VALOR", valor);
						intent.putExtras(bundle);
						startActivity(intent);
					}
					else
					{
						Intent intent = new Intent(New_Game.this, Difficult.class);
						Bundle bundle = new Bundle();
						bundle.putString("VALOR", valor);
						intent.putExtras(bundle);
						startActivity(intent);
					}
				}
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent  event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	Intent i = new Intent(New_Game.this, MainActivity.class);
			startActivity(i);
        	
        }
        return true;
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
	        	Intent intent = new Intent(New_Game.this,New_Game.class);
	        	startActivity(intent);
	        break;
	             
	        case R.id.it_scores:
	           	 Intent intent1 = new Intent(New_Game.this,Scores.class);
	           	Bundle b = new Bundle();
				b.putString("TIEMPO", "-1");
				intent1.putExtras(b);
	        	startActivity(intent1);
	        break;
	            
	        case R.id.it_about:
	        	Intent intent2 = new Intent(New_Game.this,About.class);
	           	startActivity(intent2);
		    break;
		    
	        case R.id.it_configuration:
	        	Intent intent3 = new Intent(New_Game.this,Preferencias.class);
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
