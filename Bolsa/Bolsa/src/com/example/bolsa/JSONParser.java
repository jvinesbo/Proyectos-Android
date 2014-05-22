package com.example.bolsa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;
 
public class JSONParser {
 
    static InputStream is = null; //vble recoger datos url
    static JSONObject jObj = null; //objeto JSON
    static String json = ""; //recoger información de json
 
    // constructor
    public JSONParser() {
 
    }
    
    
    // metodo obtengo JSON de URL
    public JSONObject getJSONFromUrl(final String url) {

        // solicitud HTTP request para conectar y obtener datos de url
        try {
            // Creo el cliente y el http request.
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(url);

            
            //Ejecuto el POST y guardo en httpResponse
            HttpResponse httpResponse = httpClient.execute(httpPost);
     
            //Extraemos datos del httpResponse (de la respuesta)
            HttpEntity httpEntity = httpResponse.getEntity();
     
            //abro un inputStream con el contenido de dato
            is = httpEntity.getContent();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
        
        //leo datos recibidos y obtengo un string que guardo en variable json
        try {
        	//creo un BufferReader para parsear lo que recibo por el inputStream
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            
           	//Declaro a string builder para ayudar en el parsing
            StringBuilder sb = new StringBuilder();
   
            //Declaro un string para almacenar el objeto JSON en string
            String line = null;
            
            //leo linea a linea y añado a sb mientras la linea no sea nula
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n"); //introduzco salto de linea despues de guardarme linea
            }
            
            // cierro el input stream.
            is.close();
    
            //convierto el (string builder) sb a un string
            json = sb.toString();
        } catch (Exception e) { //capturo excepcion
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

 
   
        //Try para parsear el string a un objeto JSON
        try {
            jObj = new JSONObject(json); //creación objeto JSON
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Return the JSON Object.
        return jObj;

    }//fin metodo  getJSONFromUrl ----------------------------------------
    
 
    
    //Método obtiene objeto JSON pero haciendo  POST  o GET (envio datos)  
     public JSONObject makeHttpRequest(String url, String method,List<NameValuePair> params) {
         // try HTTP request
        try {
 
        	//compruebo método
            if(method == "POST"){
                // Si el metodo es POST
                // defaultHttpClient
                DefaultHttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(url);
                //codigico en la url los params
                httpPost.setEntity(new UrlEncodedFormEntity(params));
 
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                //recibo contenido en input stream is
                is = httpEntity.getContent();
                
            //si el metodo es get
            }else if(method == "GET"){
                // request method is GET
                DefaultHttpClient httpClient = new DefaultHttpClient();
                //codifico params en utf-8 para añadirlos a url
                String paramString = URLEncodedUtils.format(params, "utf-8");
                //monto url
                url += "?" + paramString;
                //creo objeto HttpGet
                HttpGet httpGet = new HttpGet(url);
 
                HttpResponse httpResponse = httpClient.execute(httpGet);
                HttpEntity httpEntity = httpResponse.getEntity();
                //Recibo contenido en input stream
                is = httpEntity.getContent();
            }           
 
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
      //leo datos recibidos y obtengo un string que guardo en variable json
        try {
        	//creo un BufferReader para parsear lo que recibo por el inputStream
        	BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            
           	//Declaro a string builder para ayudar en el parsing
            StringBuilder sb = new StringBuilder();
   
            //Declaro un string para almacenar el objeto JSON en string
            String line = null;
            
            //leo linea a linea y añado a sb mientras la linea no sea nula
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n"); //introduzco salto de linea despues de guardarme linea
            }
            
            // cierro el input stream.
            is.close();
    
            //convierto el (string builder) sb a un string
            json = sb.toString();
        } catch (Exception e) { //capturo excepcion
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

   
        //Try para parsear el string a un objeto JSON
        try {
            jObj = new JSONObject(json); //creación objeto JSON
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        // Return the JSON Object.
        return jObj;
 
    }//FIN metodo makeHttpRequest ---------------------------------------------
}//fin clase---------------------------------------
