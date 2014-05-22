package com.example.bolsa;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NombreAdapter extends ArrayAdapter<Nombres>  {
	  protected Activity context;
	  protected ArrayList<Nombres> datos;
	              
	  //Constructor
	  NombreAdapter(Activity context, ArrayList<Nombres> datos) {
	    	 super(context, R.layout.single_name, datos);
	         this.context = context;
	         this.datos=datos;
	         
	     }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
		    View item=convertView;
		          
		    if(convertView == null) {
		      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		      item = inflater.inflate(R.layout.single_name, null);
		    }
		        
		    Nombres nombre = datos.get(position);
		     		          
		    TextView name = (TextView) item.findViewById(R.id.name);
		    name.setText(nombre.getName());
		 
		    return item;
		  }

}
