package com.example.bolsa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Visualizar extends Activity {

	private EditText txtNombreEmpresa, txtNumAcciones, txtPrecio;
	private Button btnCompraVenta, btnCompraVisualizar;
	private String nombre = "", precioCompra = "", numAcciones = "";

	final String TAG_SUCCESS = "success";
	final String TAG_MESSAGE = "message";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualizar);

		txtNombreEmpresa = (EditText) findViewById(R.id.txtNombreEmpresa);
		txtNumAcciones = (EditText) findViewById(R.id.txtNumAcciones);
		txtPrecio = (EditText) findViewById(R.id.txtPrecio);
		
		btnCompraVenta = (Button) findViewById(R.id.btnCompraVenta);
		btnCompraVisualizar = (Button) findViewById(R.id.btnCompraVisualizar);

		txtNombreEmpresa.setKeyListener(null);
		txtPrecio.setKeyListener(null);

		Bundle bundle = getIntent().getExtras();
		nombre = bundle.getString("VALOR").toString();
		precioCompra = bundle.getString("PRECIO").toString();
		numAcciones = bundle.getString("NUM").toString();

		txtNombreEmpresa.setText(nombre);
		txtNumAcciones.setText(numAcciones);
		txtPrecio.setText(precioCompra);

		btnCompraVenta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (txtNumAcciones.getText().toString().length() <= 0) {
					new Delete().execute();
				} else {
					if (Integer.parseInt(txtNumAcciones.getText().toString()) <= 0) {
						new Delete().execute();
					} else {
						int numAccNuevas = Integer.parseInt(txtNumAcciones.getText().toString());
						int numAccViejas = Integer.parseInt(numAcciones);
						
						if(numAccNuevas > numAccViejas){
							Toast.makeText(getApplicationContext(), "En este apartado solo se pueden realizar ventas.\nEl número de acciones " +
									"inicial debe ser inferior al final", Toast.LENGTH_SHORT).show();
						}else{
							new Update().execute();
						}		
					}
				}
			}
		});
		
		btnCompraVisualizar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Visualizar.this, Compra.class);
				Bundle bundle = new Bundle();
				bundle.putString("VALOR", nombre);
				intent.putExtras(bundle);
				startActivity(intent);	
				finish();
			}
		});
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(Visualizar.this, Activity_Configuracion.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.visualizar, menu);
		return true;
	}

	// Clase AsyncTask
	class Update extends AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		final String UPDATE_URL = "http://10.20.104.31/bolsa/update.php";
		JSONParser jsonParser = new JSONParser();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Visualizar.this);
			pDialog.setMessage("Actualizando acciones...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			int success;

			try { 
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nombreEmpresa", nombre));
				params.add(new BasicNameValuePair("numeroAcciones",
						txtNumAcciones.getText().toString()));

				Log.d("request!", "starting");

				JSONObject json = jsonParser.makeHttpRequest(UPDATE_URL,
						"POST", params);

				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("city update!", json.toString());
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("city update  Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				finish();
				Intent intent = new Intent(Visualizar.this,
						Activity_Configuracion.class);
				startActivity(intent);
				Toast.makeText(Visualizar.this, file_url, Toast.LENGTH_LONG)
						.show();
			}

		}

	}

	class Delete extends AsyncTask<String, String, String> {
		private ProgressDialog pDialog;
		final String DELETE_URL = "http://10.20.104.31/bolsa/delete.php";
		JSONParser jsonParser = new JSONParser();

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Visualizar.this);
			pDialog.setMessage("Vendiendo acciones...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			int success;

			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nombreEmpresa", nombre));
				params.add(new BasicNameValuePair("precioCompra", precioCompra));

				Log.d("request!", "starting");

				// Realizao el http con los parámetros de username,título y
				// post_message
				JSONObject json = jsonParser.makeHttpRequest(DELETE_URL,
						"POST", params);

				// acciones en función si ha funcionado insertar comentario o no
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("city update!", json.toString());

					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("city update  Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				Toast.makeText(Visualizar.this, file_url, Toast.LENGTH_LONG)
						.show();

				finish();
				Intent intent = new Intent(Visualizar.this,
						Activity_Configuracion.class);
				startActivity(intent);
			}

		}

	}
}
