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
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends Activity 
{

	private Button btnInsertar;
	private EditText txtPuntos;
	private EditText txtNombre;
	private EditText txtDificultad;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insertar);
		
		btnInsertar = (Button)findViewById(R.id.btnInsertar);
		txtPuntos = (EditText)findViewById(R.id.txtTiempo);
		txtNombre = (EditText)findViewById(R.id.txtIntroducir);
		txtDificultad = (EditText)findViewById(R.id.txtDificultad);
		
		
		Bundle bundle = getIntent().getExtras();
		final String resul = bundle.getString("VALOR").toString();
		txtPuntos.setText(resul);
		txtPuntos.setEnabled(false);
		
		final String dificultad = bundle.getString("DIFICULTAD").toString();
		txtDificultad.setText(dificultad);
		txtDificultad.setEnabled(false);
		
		btnInsertar.setOnClickListener(new OnClickListener() 
		{
			@Override
			public void onClick(View v) 
			{
					String nombre = txtNombre.getText().toString();
					
					Intent intent = new Intent(Insertar.this,Scores.class);
					Bundle bundle = new Bundle();
					bundle.putString("TIEMPO", resul);
					bundle.putString("NOMBRE", nombre);
					bundle.putString("DIFICULTAD", dificultad);
					intent.putExtras(bundle);
					
					startActivity(intent);
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent  event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	Intent i = new Intent(Insertar.this, MainActivity.class);
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
	        	Intent intent = new Intent(Insertar.this,New_Game.class);
	        	startActivity(intent);
	        break;
	             
	        case R.id.it_scores:
	           	 Intent intent1 = new Intent(Insertar.this,Scores.class);
	           	Bundle b = new Bundle();
				b.putString("TIEMPO", "-1");
				intent1.putExtras(b);
	        	startActivity(intent1);
	        break;
	            
	        case R.id.it_about:
	        	Intent intent2 = new Intent(Insertar.this,About.class);
	           	startActivity(intent2);
		    break;
		    
	        case R.id.it_configuration:
	        	Intent intent3 = new Intent(Insertar.this,Preferencias.class);
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
