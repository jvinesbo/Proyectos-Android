package com.simarro.memory;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class Opciones extends PreferenceFragment 
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferencias);
	}
}
