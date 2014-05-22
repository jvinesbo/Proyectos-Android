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
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Compra extends Activity {

	private String nombre;
	private Button btnCompra;
	private EditText txtPrecioCompra, txtEmpresaCompra, txtNumAccionesCompra;
	private String ultimo;
	private boolean aux = false;

	private ProgressDialog pDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String LOGIN_URL_COMPRA = "http://10.20.104.31/bolsa/consultaPrecioActual.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_NAME = "nombreEmpresa";
	private static final String TAG_DATOS = "resultado";
	private static final String TAG_ULTIMO = "ultimo";

	private static final String COMPRA_URL = "http://10.20.104.31/bolsa/insert.php";
	private static final String BEFORE_INSERT_URL = "http://10.20.104.31/bolsa/beforeInsert.php";

	private JSONArray mAcciones = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compra);

		Bundle bundle = getIntent().getExtras();
		nombre = bundle.getString("VALOR").toString();

		btnCompra = (Button) findViewById(R.id.btnCompra);
		txtPrecioCompra = (EditText) findViewById(R.id.txtPrecioCompra);
		txtEmpresaCompra = (EditText) findViewById(R.id.txtEmpresaCompra);
		txtNumAccionesCompra = (EditText) findViewById(R.id.txtNumAccionesCompra);

		txtEmpresaCompra.setText(nombre);

		txtEmpresaCompra.setKeyListener(null);
		txtPrecioCompra.setKeyListener(null);

		new ConsultasCompra().execute();

		btnCompra.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new ConsultaBeforeInsert().execute();
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Intent intent = new Intent(Compra.this,
					Activity_Configuracion.class);
			startActivity(intent);
			finish();
		}
		return true;
	}

	class ConsultasCompra extends AsyncTask<String, String, String> {
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Compra.this);
			pDialog.setMessage("Comprobando...");
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

				Log.d("request! compra", "starting");
				Log.d("NOMBRE", nombre);

				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL_COMPRA,
						"POST", params);

				Log.d("Login attempt compra", json.toString());

				success = json.getInt(TAG_SUCCESS);

				Log.d("Success: ", success + "");

				if (success == 1) {
					Log.d("Login Successful!", json.toString());

					mAcciones = json.getJSONArray(TAG_DATOS);

					for (int i = 0; i < mAcciones.length(); i++) {
						JSONObject c = mAcciones.getJSONObject(i);

						ultimo = "" + c.getDouble(TAG_ULTIMO);

						Log.d("ULTIMO: ", ultimo);
					}

					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (file_url != null) {
				txtPrecioCompra.setText(ultimo);
			}

		}

	}

	class ConsultaBeforeInsert extends AsyncTask<String, String, String> {
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Compra.this);
			pDialog.setMessage("Comprobando...");
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
				params.add(new BasicNameValuePair("precioCompra", ultimo));

				Log.d("request! compra", "starting");
				Log.d("NOMBRE", nombre);

				JSONObject json = jsonParser.makeHttpRequest(BEFORE_INSERT_URL,
						"POST", params);

				Log.d("Login attempt compra", json.toString());

				success = json.getInt(TAG_SUCCESS);

				Log.d("Success: ", success + "");

				if (success == 1) {
					Log.d("RESULTADO", "SIIIIIIII");
					aux = true;
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("RESULTADO", "NOOOO");
					
					return json.getString(TAG_MESSAGE);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}

		protected void onPostExecute(String file_url) {
			pDialog.dismiss();
			if (file_url != null) {
				if(aux){
					new Update().execute();
				}else{
					new Insert().execute();
				}
			}

		}

	}

	// Clase AsyncTask
		class Update extends AsyncTask<String, String, String> {
			private ProgressDialog pDialog;
			final String UPDATE_URL = "http://10.20.104.31/bolsa/updateCompra.php";
			JSONParser jsonParser = new JSONParser();

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				pDialog = new ProgressDialog(Compra.this);
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
							txtNumAccionesCompra.getText().toString()));

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
					Intent intent = new Intent(Compra.this,Activity_Configuracion.class);
					startActivity(intent);
					Toast.makeText(Compra.this, file_url, Toast.LENGTH_LONG)
							.show();
				}

			}

		}
	
	class Insert extends AsyncTask<String, String, String> {
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Compra.this);
			pDialog.setMessage("Insertando acciones...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// Check for success tag
			int success;

			try {
				String precioCompra = txtPrecioCompra.getText().toString();
				String numeroAcciones = txtNumAccionesCompra.getText()
						.toString();

				// creamos params username y password
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nombreEmpresa", nombre));
				params.add(new BasicNameValuePair("numeroAcciones",
						numeroAcciones));
				params.add(new BasicNameValuePair("precioCompra", precioCompra));

				Log.d("request!", "starting");

				// creamos objeto JSON
				// ojo esta vez es
				JSONObject json = jsonParser.makeHttpRequest(COMPRA_URL,
						"POST", params);

				// full json response
				Log.d("Login attempt", json.toString());

				// json success element
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Message Created!", json.toString());
					finish();
					return json.getString(TAG_MESSAGE);
				} else {
					Log.d("Message Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once product deleted
			pDialog.dismiss();
			if (file_url != null) {
				Toast.makeText(Compra.this, file_url, Toast.LENGTH_LONG).show();

				Intent intent = new Intent(Compra.this,
						Activity_Configuracion.class);
				startActivity(intent);
				finish();
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.compra, menu);
		return true;
	}

}
