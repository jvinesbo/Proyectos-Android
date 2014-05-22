package com.simarro.memory;

import java.util.Random;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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

public class Difficult extends Activity implements OnClickListener {

	private ImageButton btnPlay;
	private ImageButton btnPause;
	private Chronometer crono;
	private ImageView cero, uno, dos, tres, cuatro, cinco, seis, siete, ocho,
			nueve, diez, once, doce, trece, catorce, quince, dieziseis,
			diezisiete, dieziocho, dieznueve, veinte, veinteuno, veintedos,
			veintetres, veintecuatro, veintecinco, veinteseis, veintesiete,
			veinteocho, veintenueve, treinta, treintauno, treintados,
			treintatres, treintacuatro, treintacinco, treintaseis,
			treintasiete, treintaocho, treintanueve, cuarenta, cuarentauno,
			cuarentados, cuarentatres, cuarentacuatro, cuarentacinco,
			cuarentaseis, cuarentasiete, cuarentaocho, cuarentanueve,
			cincuenta, cincuentauno, cincuentados, cincuentatres;
	private ImageView[] imagenes = new ImageView[54];
	private Drawable[] drawable = new Drawable[54];
	private int[] tablero = { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7,
			8, 8, 9, 9, 10, 10, 11, 11, 12, 12, 13, 13, 14, 14, 15, 15, 16, 16,
			17, 17, 18, 18, 19, 19, 20, 20, 21, 21, 22, 22, 23, 23, 24, 24, 25,
			25, 26, 26 };
	private int posicion;
	private boolean comprobar = true;
	private boolean auxiliar = false;
	private String tiempo = "00:00";
	private int contador = 0;
	final Context context = this;
	private MediaPlayer musica;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_difficult);

		musica = MediaPlayer.create(this, R.raw.cancion);
		musica.setLooping(true);
		musica.setVolume(100, 100);
		
		btnPause = (ImageButton) findViewById(R.id.btnPause);
		btnPlay = (ImageButton) findViewById(R.id.btnPlay);
		crono = (Chronometer) findViewById(R.id.crono);

		cero = (ImageView) findViewById(R.id.cero);
		uno = (ImageView) findViewById(R.id.uno);
		dos = (ImageView) findViewById(R.id.dos);
		tres = (ImageView) findViewById(R.id.tres);
		cuatro = (ImageView) findViewById(R.id.cuatro);
		cinco = (ImageView) findViewById(R.id.cinco);
		seis = (ImageView) findViewById(R.id.seis);
		siete = (ImageView) findViewById(R.id.siete);
		ocho = (ImageView) findViewById(R.id.ocho);
		nueve = (ImageView) findViewById(R.id.nueve);
		diez = (ImageView) findViewById(R.id.diez);
		once = (ImageView) findViewById(R.id.once);
		doce = (ImageView) findViewById(R.id.doce);
		trece = (ImageView) findViewById(R.id.trece);
		catorce = (ImageView) findViewById(R.id.catorce);
		quince = (ImageView) findViewById(R.id.quince);
		dieziseis = (ImageView) findViewById(R.id.dieziseis);
		diezisiete = (ImageView) findViewById(R.id.diezisiete);
		dieziocho = (ImageView) findViewById(R.id.dieziocho);
		dieznueve = (ImageView) findViewById(R.id.diezinueve);
		veinte = (ImageView) findViewById(R.id.veinte);
		veinteuno = (ImageView) findViewById(R.id.veinteuno);
		veintedos = (ImageView) findViewById(R.id.veintedos);
		veintetres = (ImageView) findViewById(R.id.veintetres);
		veintecuatro = (ImageView) findViewById(R.id.veintecuatro);
		veintecinco = (ImageView) findViewById(R.id.veintecinco);
		veinteseis = (ImageView) findViewById(R.id.veinteseis);
		veintesiete = (ImageView) findViewById(R.id.veintesiete);
		veinteocho = (ImageView) findViewById(R.id.veinteocho);
		veintenueve = (ImageView) findViewById(R.id.veintenueve);
		treinta = (ImageView) findViewById(R.id.treinta);
		treintauno = (ImageView) findViewById(R.id.treintauno);
		treintados = (ImageView) findViewById(R.id.treintados);
		treintatres = (ImageView) findViewById(R.id.treintatres);
		treintacuatro = (ImageView) findViewById(R.id.treintacuatro);
		treintacinco = (ImageView) findViewById(R.id.treintacinco);
		treintaseis = (ImageView) findViewById(R.id.treintaseis);
		treintasiete = (ImageView) findViewById(R.id.treintasiete);
		treintaocho = (ImageView) findViewById(R.id.treintaocho);
		treintanueve = (ImageView) findViewById(R.id.treintanueve);
		cuarenta = (ImageView) findViewById(R.id.cuarenta);
		cuarentauno = (ImageView) findViewById(R.id.cuarentauno);
		cuarentados = (ImageView) findViewById(R.id.cuarentados);
		cuarentatres = (ImageView) findViewById(R.id.cuarentatres);
		cuarentacuatro = (ImageView) findViewById(R.id.cuarentacuatro);
		cuarentacinco = (ImageView) findViewById(R.id.cuarentacinco);
		cuarentaseis = (ImageView) findViewById(R.id.cuarentaseis);
		cuarentasiete = (ImageView) findViewById(R.id.cuarentasiete);
		cuarentaocho = (ImageView) findViewById(R.id.cuarentaocho);
		cuarentanueve = (ImageView) findViewById(R.id.cuarentanueve);
		cincuenta = (ImageView) findViewById(R.id.cincuenta);
		cincuentauno = (ImageView) findViewById(R.id.cincuentauno);
		cincuentados = (ImageView) findViewById(R.id.cincuentados);
		cincuentatres = (ImageView) findViewById(R.id.cincuentatres);

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
		imagenes[16] = dieziseis;
		imagenes[17] = diezisiete;
		imagenes[18] = dieziocho;
		imagenes[19] = dieznueve;
		imagenes[20] = veinte;
		imagenes[21] = veinteuno;
		imagenes[22] = veintedos;
		imagenes[23] = veintetres;
		imagenes[24] = veintecuatro;
		imagenes[25] = veintecinco;
		imagenes[26] = veinteseis;
		imagenes[27] = veintesiete;
		imagenes[28] = veinteocho;
		imagenes[29] = veintenueve;
		imagenes[30] = treinta;
		imagenes[31] = treintauno;
		imagenes[32] = treintados;
		imagenes[33] = treintatres;
		imagenes[34] = treintacuatro;
		imagenes[35] = treintacinco;
		imagenes[36] = treintaseis;
		imagenes[37] = treintasiete;
		imagenes[38] = treintaocho;
		imagenes[39] = treintanueve;
		imagenes[40] = cuarenta;
		imagenes[41] = cuarentauno;
		imagenes[42] = cuarentados;
		imagenes[43] = cuarentatres;
		imagenes[44] = cuarentacuatro;
		imagenes[45] = cuarentacinco;
		imagenes[46] = cuarentaseis;
		imagenes[47] = cuarentasiete;
		imagenes[48] = cuarentaocho;
		imagenes[49] = cuarentanueve;
		imagenes[50] = cincuenta;
		imagenes[51] = cincuentauno;
		imagenes[52] = cincuentados;
		imagenes[53] = cincuentatres;

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
		drawable[14] = getResources().getDrawable(R.drawable.dani);
		drawable[15] = getResources().getDrawable(R.drawable.dani);
		drawable[16] = getResources().getDrawable(R.drawable.herrera);
		drawable[17] = getResources().getDrawable(R.drawable.herrera);
		drawable[18] = getResources().getDrawable(R.drawable.colau);
		drawable[19] = getResources().getDrawable(R.drawable.colau);
		drawable[20] = getResources().getDrawable(R.drawable.jesus);
		drawable[21] = getResources().getDrawable(R.drawable.jesus);
		drawable[22] = getResources().getDrawable(R.drawable.leon);
		drawable[23] = getResources().getDrawable(R.drawable.leon);
		drawable[24] = getResources().getDrawable(R.drawable.miguel);
		drawable[25] = getResources().getDrawable(R.drawable.miguel);
		drawable[26] = getResources().getDrawable(R.drawable.pedrito);
		drawable[27] = getResources().getDrawable(R.drawable.pedrito);
		drawable[28] = getResources().getDrawable(R.drawable.soro);
		drawable[29] = getResources().getDrawable(R.drawable.soro);
		drawable[30] = getResources().getDrawable(R.drawable.raul);
		drawable[31] = getResources().getDrawable(R.drawable.raul);
		drawable[32] = getResources().getDrawable(R.drawable.nacho);
		drawable[33] = getResources().getDrawable(R.drawable.nacho);
		drawable[34] = getResources().getDrawable(R.drawable.puchol);
		drawable[35] = getResources().getDrawable(R.drawable.puchol);
		drawable[36] = getResources().getDrawable(R.drawable.astorgano);
		drawable[37] = getResources().getDrawable(R.drawable.astorgano);
		drawable[38] = getResources().getDrawable(R.drawable.santi);
		drawable[39] = getResources().getDrawable(R.drawable.santi);
		drawable[40] = getResources().getDrawable(R.drawable.pedro);
		drawable[41] = getResources().getDrawable(R.drawable.pedro);
		drawable[42] = getResources().getDrawable(R.drawable.victor);
		drawable[43] = getResources().getDrawable(R.drawable.victor);
		drawable[44] = getResources().getDrawable(R.drawable.tomas);
		drawable[45] = getResources().getDrawable(R.drawable.tomas);
		drawable[46] = getResources().getDrawable(R.drawable.javi);
		drawable[47] = getResources().getDrawable(R.drawable.javi);
		drawable[48] = getResources().getDrawable(R.drawable.hector);
		drawable[49] = getResources().getDrawable(R.drawable.hector);
		drawable[50] = getResources().getDrawable(R.drawable.pere);
		drawable[51] = getResources().getDrawable(R.drawable.pere);
		drawable[52] = getResources().getDrawable(R.drawable.felix);
		drawable[53] = getResources().getDrawable(R.drawable.felix);

		Random r = new Random();
		int aux;
		int[] vectorAuxiliar = new int[tablero.length];
		Drawable auxDra;

		for (int i = 0; i < tablero.length; i++) {
			int randomPosicion = r.nextInt(tablero.length);

			if (vectorAuxiliar[randomPosicion] != -1) {
				auxDra = drawable[i];
				drawable[i] = drawable[randomPosicion];
				drawable[randomPosicion] = auxDra;

				aux = tablero[i];
				tablero[i] = tablero[randomPosicion];
				tablero[randomPosicion] = aux;
				vectorAuxiliar[randomPosicion] = -1;
			} else {
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
		dieziseis.setOnClickListener(this);
		diezisiete.setOnClickListener(this);
		dieziocho.setOnClickListener(this);
		dieznueve.setOnClickListener(this);
		veinte.setOnClickListener(this);
		veinteuno.setOnClickListener(this);
		veintedos.setOnClickListener(this);
		veintetres.setOnClickListener(this);
		veintecuatro.setOnClickListener(this);
		veintecinco.setOnClickListener(this);
		veinteseis.setOnClickListener(this);
		veintesiete.setOnClickListener(this);
		veinteocho.setOnClickListener(this);
		veintenueve.setOnClickListener(this);
		treinta.setOnClickListener(this);
		treintauno.setOnClickListener(this);
		treintados.setOnClickListener(this);
		treintatres.setOnClickListener(this);
		treintacuatro.setOnClickListener(this);
		treintacinco.setOnClickListener(this);
		treintaseis.setOnClickListener(this);
		treintasiete.setOnClickListener(this);
		treintaocho.setOnClickListener(this);
		treintanueve.setOnClickListener(this);
		cuarenta.setOnClickListener(this);
		cuarentauno.setOnClickListener(this);
		cuarentados.setOnClickListener(this);
		cuarentatres.setOnClickListener(this);
		cuarentacuatro.setOnClickListener(this);
		cuarentacinco.setOnClickListener(this);
		cuarentaseis.setOnClickListener(this);
		cuarentasiete.setOnClickListener(this);
		cuarentaocho.setOnClickListener(this);
		cuarentanueve.setOnClickListener(this);
		cincuenta.setOnClickListener(this);
		cincuentauno.setOnClickListener(this);
		cincuentados.setOnClickListener(this);
		cincuentatres.setOnClickListener(this);

		btnPlay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				boolean autoStartValue = prefs.getBoolean("musica", false);
				
				if (autoStartValue) 
				{
					musica.start();
				}
				
				int stoppedMilliseconds = 0;
				String chronoText = crono.getText().toString();
				String array[] = chronoText.split(":");

				if (array.length == 2) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60
							* 500 + Integer.parseInt(array[1]) * 500;
				} else if (array.length == 3) {
					stoppedMilliseconds = Integer.parseInt(array[0]) * 60 * 60
							* 500 + Integer.parseInt(array[1]) * 60 * 500
							+ Integer.parseInt(array[2]) * 500;
				}
				crono.setBase(SystemClock.elapsedRealtime()
						- stoppedMilliseconds);
				crono.start();
			}
		});

		btnPause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				musica.pause();
				crono.stop();
				tiempo = crono.getText().toString();
			}
		});
	}

	@Override
	public void onClick(View v) {
		if (crono.getText().equals(tiempo)) {
			Toast.makeText(getApplicationContext(), "Pulsa el botón play",
					Toast.LENGTH_SHORT).show();
		} else {
			Handler handler = new Handler();
			switch (v.getId()) {
			case R.id.cero:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[0].setImageDrawable(drawable[0]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 0);
						}
					}, 500);
				}
				break;

			case R.id.uno:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[1].setImageDrawable(drawable[1]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 1);
						}
					}, 500);
				}
				break;

			case R.id.dos:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[2].setImageDrawable(drawable[2]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 2);
						}
					}, 500);
				}
				break;

			case R.id.tres:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[3].setImageDrawable(drawable[3]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 3);
						}
					}, 500);
				}
				break;

			case R.id.cuatro:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[4].setImageDrawable(drawable[4]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 4);
						}
					}, 500);
				}
				break;
			case R.id.cinco:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[5].setImageDrawable(drawable[5]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 5);
						}
					}, 500);
				}
				break;

			case R.id.seis:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[6].setImageDrawable(drawable[6]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 6);
						}
					}, 500);
				}
				break;

			case R.id.siete:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[7].setImageDrawable(drawable[7]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 7);
						}
					}, 500);
				}
				break;

			case R.id.ocho:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[8].setImageDrawable(drawable[8]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 8);
						}
					}, 500);
				}
				break;

			case R.id.nueve:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[9].setImageDrawable(drawable[9]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 9);
						}
					}, 500);
				}
				break;

			case R.id.diez:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[10].setImageDrawable(drawable[10]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 10);
						}
					}, 500);
				}
				break;
			case R.id.once:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[11].setImageDrawable(drawable[11]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 11);
						}
					}, 500);
				}
				break;

			case R.id.doce:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[12].setImageDrawable(drawable[12]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 12);
						}
					}, 500);
				}
				break;

			case R.id.trece:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[13].setImageDrawable(drawable[13]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 13);
						}
					}, 500);
				}
				break;

			case R.id.catorce:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[14].setImageDrawable(drawable[14]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 14);
						}
					}, 500);
				}
				break;

			case R.id.quince:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[15].setImageDrawable(drawable[15]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 15);
						}
					}, 500);
				}
				break;

			case R.id.dieziseis:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[16].setImageDrawable(drawable[16]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 16);
						}
					}, 500);
				}
				break;

			case R.id.diezisiete:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[17].setImageDrawable(drawable[17]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 17);
						}
					}, 500);
				}
				break;

			case R.id.dieziocho:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[18].setImageDrawable(drawable[18]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 18);
						}
					}, 500);
				}
				break;

			case R.id.diezinueve:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[19].setImageDrawable(drawable[19]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 19);
						}
					}, 500);
				}
				break;

			case R.id.veinte:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[20].setImageDrawable(drawable[20]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 20);
						}
					}, 500);
				}
				break;

			case R.id.veinteuno:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[21].setImageDrawable(drawable[21]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 21);
						}
					}, 500);
				}
				break;

			case R.id.veintedos:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[22].setImageDrawable(drawable[22]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 22);
						}
					}, 500);
				}
				break;
			case R.id.veintetres:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[23].setImageDrawable(drawable[23]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 23);
						}
					}, 500);
				}
				break;

			case R.id.veintecuatro:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[24].setImageDrawable(drawable[24]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 24);
						}
					}, 500);
				}
				break;

			case R.id.veintecinco:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[25].setImageDrawable(drawable[25]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 25);
						}
					}, 500);
				}
				break;

			case R.id.veinteseis:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[26].setImageDrawable(drawable[26]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 26);
						}
					}, 500);
				}
				break;

			case R.id.veintesiete:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[27].setImageDrawable(drawable[27]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 27);
						}
					}, 500);
				}
				break;

			case R.id.veinteocho:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[28].setImageDrawable(drawable[28]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 28);
						}
					}, 500);
				}
				break;

			case R.id.veintenueve:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[29].setImageDrawable(drawable[29]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 29);
						}
					}, 500);
				}
				break;

			case R.id.treinta:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[30].setImageDrawable(drawable[30]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 30);
						}
					}, 500);
				}
				break;

			case R.id.treintauno:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[31].setImageDrawable(drawable[31]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 31);
						}
					}, 500);
				}
				break;

			case R.id.treintados:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[32].setImageDrawable(drawable[32]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 32);
						}
					}, 500);
				}
				break;

			case R.id.treintatres:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[33].setImageDrawable(drawable[33]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 33);
						}
					}, 500);
				}
				break;

			case R.id.treintacuatro:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[34].setImageDrawable(drawable[34]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 34);
						}
					}, 500);
				}
				break;

			case R.id.treintacinco:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[35].setImageDrawable(drawable[35]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 35);
						}
					}, 500);
				}
				break;

			case R.id.treintaseis:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[36].setImageDrawable(drawable[36]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 36);
						}
					}, 500);
				}
				break;

			case R.id.treintasiete:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[37].setImageDrawable(drawable[37]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 37);
						}
					}, 500);
				}
				break;

			case R.id.treintaocho:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[38].setImageDrawable(drawable[38]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 38);
						}
					}, 500);
				}
				break;

			case R.id.treintanueve:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[39].setImageDrawable(drawable[39]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 39);
						}
					}, 500);
				}
				break;

			case R.id.cuarenta:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[40].setImageDrawable(drawable[40]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 40);
						}
					}, 500);
				}
				break;

			case R.id.cuarentauno:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[41].setImageDrawable(drawable[41]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 41);
						}
					}, 500);
				}
				break;

			case R.id.cuarentados:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[42].setImageDrawable(drawable[42]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 42);
						}
					}, 500);
				}
				break;

			case R.id.cuarentatres:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[43].setImageDrawable(drawable[43]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 43);
						}
					}, 500);
				}
				break;

			case R.id.cuarentacuatro:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[44].setImageDrawable(drawable[44]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 44);
						}
					}, 500);
				}
				break;

			case R.id.cuarentacinco:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[45].setImageDrawable(drawable[45]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 45);
						}
					}, 500);
				}
				break;

			case R.id.cuarentaseis:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[46].setImageDrawable(drawable[46]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 46);
						}
					}, 500);
				}
				break;

			case R.id.cuarentasiete:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[47].setImageDrawable(drawable[47]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 47);
						}
					}, 500);
				}
				break;

			case R.id.cuarentaocho:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[48].setImageDrawable(drawable[48]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 48);
						}
					}, 500);
				}
				break;

			case R.id.cuarentanueve:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[49].setImageDrawable(drawable[49]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 49);
						}
					}, 500);
				}
				break;

			case R.id.cincuenta:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[50].setImageDrawable(drawable[50]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 50);
						}
					}, 500);
				}
				break;

			case R.id.cincuentauno:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[51].setImageDrawable(drawable[51]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 51);
						}
					}, 500);
				}
				break;

			case R.id.cincuentados:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[52].setImageDrawable(drawable[52]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 52);
						}
					}, 500);
				}
				break;

			case R.id.cincuentatres:
				contador++;
				if (contador > 2) {
					contador = 0;
				} else {
					imagenes[53].setImageDrawable(drawable[53]);
					handler.postDelayed(new Runnable() {
						public void run() {
							jugada(posicion, auxiliar, 53);
						}
					}, 500);
				}
				break;
			}
		}
	}

	public boolean finalJuego() {
		boolean aux = true;

		for (int i = 0; i < imagenes.length; i++) {
			if (imagenes[i] != null) {
				aux = false;
			}
		}

		return aux;
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
									Intent i = new Intent(Difficult.this, MainActivity.class);
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

	public void jugada(int seleccionado, boolean aux, int posicionCase) {
		if (aux) {
			if (tablero[posicionCase] == tablero[seleccionado]) {
				imagenes[posicionCase].setEnabled(false);
				imagenes[seleccionado].setEnabled(false);
				imagenes[seleccionado] = null;
				imagenes[posicionCase] = null;
				contador = 0;
				auxiliar = false;

				comprobar = finalJuego();

				if (comprobar) {
					Toast.makeText(getApplicationContext(), "Juego Terminado",
							Toast.LENGTH_SHORT).show();
					crono.stop();
					btnPause.setEnabled(false);
					btnPlay.setEnabled(false);

					tiempo = crono.getText().toString();
					musica.stop();
					
					Intent intent = new Intent(Difficult.this, Insertar.class);
					Bundle bundle = new Bundle();
					bundle.putString("VALOR", tiempo);
					bundle.putString("DIFICULTAD", "Difícil");
					intent.putExtras(bundle);

					startActivity(intent);
				}
			} else {
				imagenes[seleccionado]
						.setImageResource(R.drawable.interrogante);
				imagenes[seleccionado].setEnabled(true);
				imagenes[posicionCase]
						.setImageResource(R.drawable.interrogante);
				imagenes[posicionCase].setEnabled(true);
				contador = 0;
				auxiliar = false;
			}
		} else {
			imagenes[posicionCase].setImageDrawable(drawable[posicionCase]);
			auxiliar = true;
			imagenes[posicionCase].setEnabled(false);
			posicion = posicionCase;
		}
	}
}
