package com.example.bolsa;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;


public class MainActivity extends Activity {

	private Button btnEntrar;
	private EditText txtNombre;
	private EditText txtContrasenya;
	
	private ProgressDialog pDialog;
    JSONParser jsonParser = new JSONParser();
    private static final String LOGIN_URL = "http://10.20.104.31/bolsa/login.php";
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnEntrar = (Button) findViewById(R.id.btnEntrar);
		txtNombre = (EditText) findViewById(R.id.txtNombre);
		txtContrasenya = (EditText) findViewById(R.id.txtContrasenya);

		btnEntrar.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				new AttemptLogin().execute();
			}
		});
	}

	// Clase AttempLogin de tipo AsyncTask
	class AttemptLogin extends AsyncTask<String, String, String> {

		// vble controlar error
		boolean failure = false;

		@Override
		// antes de empezar mostramos Progressdialogo
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(MainActivity.this);
			pDialog.setMessage("Comprobando...");
			pDialog.setIndeterminate(false); // no sabe el tiempo que va a durar
												// es indeterminado
			pDialog.setCancelable(true); // no cancelable
			pDialog.show(); // muestra process dialog
		}

		@Override
		protected String doInBackground(String... args) {
			// vble para comprobar si ha tenido exito
			int success;
			String username = txtNombre.getText().toString();
			String password = txtContrasenya.getText().toString();

			try {
				// Construimos params ("username"+contenido username,
				// "password"+contenido password)
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("nombreLogin", username));
				params.add(new BasicNameValuePair("contrasenya", password));

				Log.d("request!", "starting");

				// llamo a la clase jsonParser y hago peticion con los
				// parametros
				// devolvera objeto json
				JSONObject json = jsonParser.makeHttpRequest(LOGIN_URL, "POST",
						params);

				// check your log for json response
				Log.d("Login attempt", json.toString());

				// guardo en success contenido del TAG_SUCCESS
				success = json.getInt(TAG_SUCCESS);
				// si es 1 hemos tenido exito
				if (success == 1) {
					Log.d("Login Successful!", json.toString());
					// envio a activitiy muestra comentarios
					Intent i = new Intent(MainActivity.this, Activity_Configuracion.class);
					// finalizo activity e inicializo la otra
					finish();
					startActivity(i);
					// devuelvo contenido json
					return json.getString(TAG_MESSAGE);
				} else {
					// sino devuelvo mensaje json error
					Log.d("Login Failure!", json.getString(TAG_MESSAGE));
					return json.getString(TAG_MESSAGE);

				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return null;
		}// fin doinbackground
			// Despues de completar tarea en background desaparece process
			// dialogg

		protected void onPostExecute(String file_url) {
			// elimina el process dialogo
			pDialog.dismiss();
			// si hay error muestra toast
			if (file_url != null) {
				Toast.makeText(MainActivity.this, file_url, Toast.LENGTH_LONG)
						.show();
			}

		}

	}

}
