package com.example.b8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

public class MainPreference extends PreferenceFragmentCompat {
    Context c;
    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
        SwitchPreference saveChoice = (SwitchPreference) findPreference("remember_count");
        saveChoice.setOnPreferenceChangeListener(
                new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                        saveChoice.setChecked((Boolean)newValue);
                        System.out.println("Saved choice change: " + (Boolean)newValue);
                        return true;
                    }
                }
        );
    }
}

