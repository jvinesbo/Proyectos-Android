package com.simarro.memory;

import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.CheckBoxPreference;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Easy extends Activity implements OnClickListener
{
	private ImageButton btnPlay;
	private ImageButton btnPause;
	private Chronometer crono;
	private ImageView cero,uno,dos,tres,cuatro,cinco,seis,siete,ocho,nueve,diez,once,doce,trece,catorce,quince;
	private ImageView[] imagenes = new ImageView[16];
	private Drawable[] drawable = new Drawable[16];
	private int[] tablero = {0,0,1,1,2,2,3,3,4,4,5,5,6,6,7,7};
	private int posicion;
	private boolean comprobar = true;
	private boolean auxiliar = false;
	private String tiempo = "00:00";
	final Context context = this;
	private int contador = 0;
	private MediaPlayer musica;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_easy);
		
		musica = MediaPlayer.create(this, R.raw.cancion);
		musica.setLooping(true);
		musica.setVolume(100, 100);
		
		btnPlay = (ImageButton)findViewById(R.id.btnPlay);
		btnPause = (ImageButton)findViewById(R.id.btnPause);
		crono = (Chronometer)findViewById(R.id.crono);
		
		cero = (ImageView)findViewById(R.id.cero);
		uno = (ImageView)findViewById(R.id.uno);
		dos = (ImageView)findViewById(R.id.dos);
		tres = (ImageView)findViewById(R.id.tres);
		cuatro = (ImageView)findViewById(R.id.cuatro);
		cinco = (ImageView)findViewById(R.id.cinco);
		seis = (ImageView)findViewById(R.id.seis);
		siete = (ImageView)findViewById(R.id.siete);
		ocho = (ImageView)findViewById(R.id.ocho);
		nueve = (ImageView)findViewById(R.id.nueve);
		diez = (ImageView)findViewById(R.id.diez);
		once = (ImageView)findViewById(R.id.once);
		doce = (ImageView)findViewById(R.id.doce);
		trece = (ImageView)findViewById(R.id.trece);
		catorce = (ImageView)findViewById(R.id.catorce);
		quince = (ImageView)findViewById(R.id.quince);
		
		imagenes[0] = cero;
		imagenes[1] = uno;
		imagenes[2] = dos;
		imagenes[3] = tres;
		imagenes[4] = cuatro;
		imagenes[5] = cinco;
		imagenes[6] = seis;
		imagenes[7] = siete;
		imagenes[8] = ocho;
		imagenes[9] = nueve;
		imagenes[10] = diez;
		imagenes[11] = once;
		imagenes[12] = doce;
		imagenes[13] = trece;
		imagenes[14] = catorce;
		imagenes[15] = quince;
		
		drawable[0] = getResources().getDrawable(R.drawable.carlos);
		drawable[1] = getResources().getDrawable(R.drawable.carlos);
		drawable[2] = getResources().getDrawable(R.drawable.badana);
		drawable[3] = getResources().getDrawable(R.drawable.badana);
		drawable[4] = getResources().getDrawable(R.drawable.fronton);
		drawable[5] = getResources().getDrawable(R.drawable.fronton);
		drawable[6] = getResources().getDrawable(R.drawable.genoves);
		drawable[7] = getResources().getDrawable(R.drawable.genoves);
		drawable[8] = getResources().getDrawable(R.drawable.vaqueta);
		drawable[9] = getResources().getDrawable(R.drawable.vaqueta);
		drawable[10] = getResources().getDrawable(R.drawable.panete);
		drawable[11] = getResources().getDrawable(R.drawable.panete);
		drawable[12] = getResources().getDrawable(R.drawable.guants);
		drawable[13] = getResources().getDrawable(R.drawable.guants);
		drawable[14] = getResources().getDrawable(R.drawable.leon);
		drawable[15] = getResources().getDrawable(R.drawable.leon);
		
		Random r = new Random();
        int aux;
        int[] vectorAuxiliar = new int[tablero.length];
        Drawable auxDra;
        
        for (int i = 0; i < tablero.length; i++) 
        {
            int randomPosicion = r.nextInt(tablero.length);
            
            if (vectorAuxiliar[randomPosicion] != -1) 
            {
            	auxDra = drawable[i];
            	drawable[i] = drawable[randomPosicion];
            	drawable[randomPosicion] = auxDra;
            	
                aux = tablero[i];
                tablero[i] = tablero[randomPosicion];
                tablero[randomPosicion] = aux;
                vectorAuxiliar[randomPosicion] = -1;
            }
            else
            {
                i--;
            }
        }
		
		cero.setOnClickListener(this);
		uno.setOnClickListener(this);
		dos.setOnClickListener(this);
		tres.setOnClickListener(this);
		cuatro.setOnClickListener(this);
		cinco.setOnClickListener(this);
		seis.setOnClickListener(this);
		siete.setOnClickListener(this);
		ocho.setOnClickListener(this);
		nueve.setOnClickListener(this);
		diez.setOnClickListener(this);
		once.setOnClickListener(this);
		doce.setOnClickListener(this);
		trece.setOnClickListener(this);
		catorce.setOnClickListener(this);
		quince.setOnClickListener(this);
		
		btnPlay.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{
			
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				boolean autoStartValue = prefs.getBoolean("musica", false);
				
				if (autoStartValue) 
				{
					musica.start();
				}
				
				int stoppedMilliseconds = 0;
		        String chronoText = crono.getText().toString();
		        String array[] = chronoText.split(":");
		        
		        if (array.length == 2) 
		        {
		            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 500 + Integer.parseInt(array[1]) * 500;
		        } 
		        else if (array.length == 3) 
		        {
		            stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60 * 500 
		                + Integer.parseInt(array[1]) * 60 * 500
		                + Integer.parseInt(array[2]) * 500;
		        }
		          crono.setBase(SystemClock.elapsedRealtime() - stoppedMilliseconds);
		          crono.start();
			}
		});
		
		btnPause.setOnClickListener(new OnClickListener() 
		{	
			@Override
			public void onClick(View v) 
			{	
				musica.pause();
				crono.stop();
				tiempo = crono.getText().toString();
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

			alertDialogBuilder.setTitle("Alerta!!");

			alertDialogBuilder
					.setMessage("¿Seguro que quiere salir?")
					.setPositiveButton("Si",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									musica.stop();
									Intent i = new Intent(Easy.this, MainActivity.class);
									startActivity(i);
								}
							})
					.setNegativeButton("No",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							})
					.setNeutralButton("Cancelar",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});

			AlertDialog alertDialog = alertDialogBuilder.create();
			
			alertDialog.show();
		}
		return true;
	}
	
	@Override
	public void onClick(View v) 
	{
		if(crono.getText().equals(tiempo))
		{
			Toast.makeText(getApplicationContext(), "Pulsa el botón play", Toast.LENGTH_SHORT).show();
		}
		else
		{
			Handler handler = new Handler();
			switch (v.getId())
			{
				case R.id.cero:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[0].setImageDrawable(drawable[0]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 0 );
		                     }  
		                }, 500);
					}
					break;
			
				case R.id.uno:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[1].setImageDrawable(drawable[1]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 1 );
		                     }  
		                }, 500);
					}
					break;
					
				case R.id.dos:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[2].setImageDrawable(drawable[2]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 2 );
		                     }  
		                }, 500);
					}
					break;
					
				case R.id.tres:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[3].setImageDrawable(drawable[3]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 3 );
		                     }  
		                }, 500);
					}
					break;
					
				case R.id.cuatro:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[4].setImageDrawable(drawable[4]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 4 );
		                     }  
		                }, 500);
					}
					break;
				case R.id.cinco:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[5].setImageDrawable(drawable[5]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 5 );
		                     }  
		                }, 500);
					}
					break;
				
				case R.id.seis:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[6].setImageDrawable(drawable[6]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 6 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.siete:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[7].setImageDrawable(drawable[7]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 7 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.ocho:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[8].setImageDrawable(drawable[8]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 8 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.nueve:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[9].setImageDrawable(drawable[9]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 9 );
		                     }  
		                }, 500);
					}
					break;
			
				case R.id.diez:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[10].setImageDrawable(drawable[10]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 10 );
		                     }  
		                }, 500);
					}
					break;
				case R.id.once:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[11].setImageDrawable(drawable[11]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 11 );
		                     }  
		                }, 500);
					}
					break;
				
				case R.id.doce:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[12].setImageDrawable(drawable[12]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 12 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.trece:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[13].setImageDrawable(drawable[13]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 13 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.catorce:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[14].setImageDrawable(drawable[14]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 14 );
		                     }  
		                }, 500);
					}
					break;
						
				case R.id.quince:
					contador++;
					if (contador > 2) 
					{
						contador = 0;
					}
					else
					{
						imagenes[15].setImageDrawable(drawable[15]);
						handler.postDelayed(new Runnable() {  
		                     public void run() {  
		                    	 jugada(posicion, auxiliar, 15 );
		                     }  
		                }, 500);
					}
					break;
			}
		}
	}
	
	public boolean finalJuego()
	{
		boolean aux = true;
		
		for (int i = 0; i < imagenes.length; i++) 
		{
			if (imagenes[i] != null) 
			{
				aux = false;
			}
		}
		
		return aux;
	}
	
	public void jugada(int seleccionado, boolean aux, int posicionCase)
	{
		if (aux) 
		{		
			if(tablero[posicionCase] == tablero[seleccionado])
			{	
				imagenes[posicionCase].setEnabled(false);
				imagenes[seleccionado].setEnabled(false);
				imagenes[seleccionado] = null;
				imagenes[posicionCase] = null;
				contador = 0;
				auxiliar = false;
				
				comprobar = finalJuego();
				
				if (comprobar) 
				{
					Toast.makeText(getApplicationContext(), "Juego Terminado", Toast.LENGTH_SHORT).show();
					crono.stop();
					btnPause.setEnabled(false);
					btnPlay.setEnabled(false);
					
					tiempo = crono.getText().toString();
					
					Intent intent = new Intent(Easy.this,Insertar.class);
					Bundle bundle = new Bundle();
					bundle.putString("VALOR", tiempo);
					bundle.putString("DIFICULTAD", "Fácil");
					intent.putExtras(bundle);
					musica.stop();
					startActivity(intent);
				} 
			}
			else
			{   
				imagenes[seleccionado].setImageResource(R.drawable.interrogante);
				imagenes[seleccionado].setEnabled(true);
				imagenes[posicionCase].setImageResource(R.drawable.interrogante);
				imagenes[posicionCase].setEnabled(true);
				contador = 0;
				auxiliar = false;
			}
		} 
		else 
		{
			imagenes[posicionCase].setImageDrawable(drawable[posicionCase]);
			auxiliar = true;
			imagenes[posicionCase].setEnabled(false);
			posicion = posicionCase;
		}
	}
}
