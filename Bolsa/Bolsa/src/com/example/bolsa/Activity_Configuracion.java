package com.example.bolsa;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Activity_Configuracion extends Activity {

	// Progress Dialog
	private ProgressDialog pDialog;

	// testing on Emulator:
	private static final String READ_NOMBRES_URL = "http://10.20.104.31/bolsa/spinner.php";

	// JSON IDS. Etiquetas JSON
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "nombreEmpresa";
	private static final String TAG_NOMBRES = "nombreEmpresas";
	private JSONArray mNombres = null;
	ArrayList<Nombres> nombresAvaliable = new ArrayList<Nombres>();
	
	private static final String LOGIN_URL = "http://10.20.104.31/bolsa/consultas.php";
	private static final String TAG_DATOS = "acciones";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_NUM_ACCIONES = "numeroAcciones";
	private static final String TAG_PRECIO_ACCIONES = "precioCompra";
	JSONParser jsonParser = new JSONParser();
	
	private String name, numAcciones, precioCompra;
	
	private JSONArray mAcciones = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_activity__configuracion);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity__configuracion, menu);
		return true;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		// loading the cities via AsyncTask
		new Listas().execute();
	}
	
	public void updateJSONdata() {

		// creo objeto JONParser
		JSONParser jParser = new JSONParser();

		// hago request a url y obtengo JSON que guardare en objeto json
		JSONObject json = jParser.getJSONFromUrl(READ_NOMBRES_URL);

		try {

			// recupero el contenido del array que contiene la etiqueta cities)
			// en estructura json todos cuelgan tras la etiqueta cities
			mNombres = json.getJSONArray(TAG_NOMBRES);
			
			// recorremos
			for (int i = 0; i < mNombres.length(); i++) {
				JSONObject c = mNombres.getJSONObject(i);

				// recupero contenido de cada etiqueta
				int id = c.getInt(TAG_ID);
				name = c.getString(TAG_NAME);
				Log.d("NAME", name);
				// Creamos el objeto City
				Nombres nombre = new Nombres(id, name);

				nombresAvaliable.add(nombre);
				Log.d("Parser!", "3");

			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateList() {
		ListView listview_cities = (ListView) findViewById(R.id.list);

		// Creamos el objeto CityAdapter y lo asignamos al ListView
		NombreAdapter cityAdapter = new NombreAdapter(this,
				nombresAvaliable);
		listview_cities.setAdapter(cityAdapter);
		
		listview_cities.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				name = ((Nombres) parent.getAdapter().getItem(position)).getName();
				new Consultas().execute();
			}
		});
	}

	public class Listas extends AsyncTask<Void, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Configuracion.this);
			pDialog.setMessage("Cargando empresas...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected Boolean doInBackground(Void... arg0) {
			// recupera los datos por request y los pasa de JSON a lista
			updateJSONdata();
			return null;

		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			pDialog.dismiss();
			// carga el listview
			updateList();
		}
	}
	
	class Consultas extends AsyncTask<String, String, String> {
		boolean failure = false;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Activity_Configuracion.this);
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
				params.add(new BasicNameValuePair("nombreEmpresa", name));

				Log.d("request!", "starting");

				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",params);

				Log.d("Login attempt", json.toString());

				success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					Log.d("Login Successful!", json.toString());
					
					mAcciones = json.getJSONArray(TAG_DATOS);
					
					for (int i = 0; i < mAcciones.length(); i++) {
						JSONObject c = mAcciones.getJSONObject(i);
						
						precioCompra = "" + c.getDouble(TAG_PRECIO_ACCIONES);
						numAcciones = "" + c.getInt(TAG_NUM_ACCIONES);

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
				if (file_url.equals("No tienes acciones de esta empresa!")) {
					Intent intent = new Intent(Activity_Configuracion.this, Compra.class);
					Bundle bundle = new Bundle();
					bundle.putString("VALOR", name);
					intent.putExtras(bundle);
					startActivity(intent);	
					finish();
				}else{
					Intent intent = new Intent(Activity_Configuracion.this, Visualizar.class);
					Bundle bundle = new Bundle();
					bundle.putString("VALOR", name);
					bundle.putString("NUM", numAcciones);
					bundle.putString("PRECIO", precioCompra);
					intent.putExtras(bundle);
					startActivity(intent);
					finish();
				}
			}

		}
	}
}
