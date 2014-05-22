package com.simarro.memory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Scores extends Activity 
{
	private ListView lista;
	private ArrayList<ScoresMemory> scores = new ArrayList<ScoresMemory>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scores);
		
		lista = (ListView)findViewById(R.id.list_1);
		
		Bundle bundle = getIntent().getExtras();
		String resultado = bundle.getString("TIEMPO");
		
		if (resultado.equals("-1")) 
		{
			try 
			{			
				    BufferedReader BR = new BufferedReader(new InputStreamReader(openFileInput("puntuaciones.txt")));
				    String nombre = BR.readLine();
				    String dificultad = BR.readLine();
				    String tiempo = BR.readLine();
				    
				    while(nombre != null)
				    {
				    	scores.add(new ScoresMemory(nombre,dificultad,tiempo));
						nombre = BR.readLine();
						dificultad = BR.readLine();
						tiempo = BR.readLine();
					}
				    BR.close();
			    
			}
			catch (FileNotFoundException e) 
			{
					Toast.makeText(getApplicationContext(), "No tienes puntuaciones", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(Scores.this, MainActivity.class);
					startActivity(intent);
				    Log.e("ERROR", "No a sido posible acceder al archivo" + e.toString());
			} 
			catch (IOException e) 
			{
				    Log.e("ERROR", "No ha sido posible leer el archivo" + e.toString());
			}
			
		}
		else
		{
			try
			{
				final String nombre = bundle.getString("NOMBRE").toString();
				final String dificultad = bundle.getString("DIFICULTAD").toString();

				PrintWriter OSW = new PrintWriter(new OutputStreamWriter(openFileOutput("puntuaciones.txt", MODE_APPEND)));
			    OSW.println(nombre);
			    OSW.println(dificultad);
			    OSW.println(resultado);
			    OSW.flush();
			    OSW.close();
			}
			catch (FileNotFoundException e) 
			{
			    Log.e("ERROR", "No ha sido posible crear el archivo" + e.toString());
			}
			
			try 
			{
			    BufferedReader BR = new BufferedReader(new InputStreamReader(openFileInput("puntuaciones.txt")));
			    String nombre = BR.readLine();
			    String dificultad = BR.readLine();
			    String tiempo = BR.readLine();
			    
			    while(nombre != null)
			    {
			    	scores.add(new ScoresMemory(nombre,dificultad,tiempo));
			    	nombre = BR.readLine();
			    	dificultad = BR.readLine();
				    tiempo = BR.readLine();
			    }
			    
			    BR.close();
			    
			}
			catch (FileNotFoundException e) 
			{
			    Log.e("ERROR", "No a sido posible acceder al archivo" + e.toString());
			} 
			catch (IOException e) 
			{
			    Log.e("ERROR", "No ha sido posible leer el archivo" + e.toString());
			}
		}
		
		final ScoresAdapter adaptador = new ScoresAdapter(this, scores);
		
		lista = (ListView)findViewById(R.id.list_1);
		lista.setAdapter(adaptador);
		
		lista.setOnItemClickListener(new OnItemClickListener() 
		{
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
			{
				AlertDialog.Builder dialogo = new AlertDialog.Builder(Scores.this);  
		        dialogo.setTitle("Importante");  
		        dialogo.setMessage("¿Esta seguro de borrar la puntuación?");            
		        dialogo.setCancelable(false);
		        
		        dialogo.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() 
		        {  
		            public void onClick(DialogInterface dialogo1, int id) 
		            {  
		            	scores.remove(position);
						adaptador.notifyDataSetChanged(); 
						
						lista.setAdapter(adaptador);
		            }  
		        });  
		        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() 
		        {  
		            public void onClick(DialogInterface dialogo1, int id) 
		            {  
		                Intent intent = new Intent(Scores.this , MainActivity.class);
		                startActivity(intent);
		            }  
		        });            
		        dialogo.show();
				
			}
		});
	}
	
	public void escribir()
	{
		try
		{       	
        	PrintWriter OSW = new PrintWriter(new OutputStreamWriter(openFileOutput("puntuaciones.txt", MODE_PRIVATE)));
        	for (int i = 0; i < scores.size(); i++) 
        	{
        			String nombre = scores.get(i).getNombre();
        			String dificultad = scores.get(i).getDificultad();
        			String tiempo = scores.get(i).getTiempo();	
    			    OSW.println(nombre);
    			    OSW.println(dificultad);
    			    OSW.println(tiempo);
			}
        	OSW.flush();
		    OSW.close();
		}
		catch (FileNotFoundException e) 
		{
		    Log.e("ERROR", "No ha sido posible crear el archivo" + e.toString());
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent  event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
        	
        	escribir();
        	
        	Intent i = new Intent(Scores.this, MainActivity.class);
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
	        	Intent intent = new Intent(Scores.this,New_Game.class);
	        	startActivity(intent);
	        break;
	             
	        case R.id.it_scores:
	           	 Intent intent1 = new Intent(Scores.this,Scores.class);
	           	Bundle b = new Bundle();
				b.putString("TIEMPO", "-1");
				intent1.putExtras(b);
	        	startActivity(intent1);
	        break;
	            
	        case R.id.it_about:
	        	Intent intent2 = new Intent(Scores.this,About.class);
	           	startActivity(intent2);
		    break;
		    
	        case R.id.it_configuration:
	        	Intent intent3 = new Intent(Scores.this,Preferencias.class);
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
