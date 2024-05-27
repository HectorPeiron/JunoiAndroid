package com.tfgjunio.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static final String PREF_NAME = "CrianzaPref";
    private static final String KEY_CRIANZA_ID = "crianza_id";
    private static final String KEY_FECHA_INICIO = "fecha_inicio";
    private SharedPreferences sharedPreferences;

    public PreferencesHelper(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveCrianzaId(long crianzaId) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(KEY_CRIANZA_ID, crianzaId);
        editor.apply();
    }

    public long getCrianzaId() {
        return sharedPreferences.getLong(KEY_CRIANZA_ID, -1);
    }

    public void clearCrianzaId() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_CRIANZA_ID);
        editor.apply();
    }

    public void saveCrianzaFechaInicio(String fechaInicio) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_FECHA_INICIO, fechaInicio);
        editor.apply();
    }

    public String getCrianzaFechaInicio() {
        return sharedPreferences.getString(KEY_FECHA_INICIO, null);
    }

    public void clearCrianzaFechaInicio() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_FECHA_INICIO);
        editor.apply();
    }
}
