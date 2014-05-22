package com.simarro.memory;

import java.util.ArrayList;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ScoresAdapter extends ArrayAdapter<ScoresMemory> 
{
	Activity context;
	ArrayList<ScoresMemory> datos;
	
	public ScoresAdapter(Activity context,ArrayList<ScoresMemory>  datos) 
	{
		super(context, R.layout.itemscores,datos);
		this.context = context;
		this.datos = datos;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		LayoutInflater inflater = context.getLayoutInflater();
		View item = inflater.inflate(R.layout.itemscores, null);
		
		ImageView ImgIcon =(ImageView)item.findViewById(R.id.Icon);
		switch (Math.round((float)Math.random()*3)) {
		case 0:
			ImgIcon.setImageResource(R.drawable.badana);
			break;
		case 1:
			ImgIcon.setImageResource(R.drawable.vaqueta);
			break;
		default:
			ImgIcon.setImageResource(R.drawable.fronton);
			break;
		}
		
		TextView txtTitulo = (TextView)item.findViewById(R.id.lbl);
		txtTitulo.setText(datos.get(position).getNombre());
		
		TextView txtDificultad = (TextView)item.findViewById(R.id.lblDificultad);
		txtDificultad.setText(datos.get(position).getDificultad());
		
		TextView txtSubTitulo = (TextView)item.findViewById(R.id.txtEquipo);
		txtSubTitulo.setText("" +datos.get(position).getTiempo());
		
		return (item);
	}
}
