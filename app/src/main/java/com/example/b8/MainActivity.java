package com.example.b8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.preference.SwitchPreference;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String sharedPrefFile = "com.example.b8";
    Button btnCount;
    TextView txtViewResult;
    Button btnBlack;
    Button btnRed;
    Button btnBlue;
    Button btnGreen;
    Boolean saveChoice;
    int c;
    private int mCount = 0;

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        saveChoice = sharedPreferences.getBoolean("remember_count", false);
        System.out.println("save choice main: " + saveChoice);
        if(saveChoice == true) {
            SharedPreferences mPreferences  = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putInt("count", mCount);
            preferencesEditor.putInt("color", c);
            preferencesEditor.clear();
            preferencesEditor.apply();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(getApplicationContext(), PreferenceActivity.class);
        switch (item.getItemId()) {
            case R.id.item_preferences:
                intent.putExtra("settings", "settings");
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        txtViewResult = findViewById(R.id.txtViewResult);
        mCount = Integer.parseInt(txtViewResult.getText().toString());
        btnCount = findViewById(R.id.btnCount);
        btnBlack = findViewById(R.id.btnBlack);
        btnRed = findViewById(R.id.btnRed);
        btnBlue = findViewById(R.id.btnBlue);
        btnGreen = findViewById(R.id.btnGreen);

        SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
        if(savedInstanceState == null) {
            mCount = mPreferences.getInt("count", mCount);
            txtViewResult.setText(String.valueOf(mCount));
            txtViewResult.setBackgroundColor(mPreferences.getInt("color", c));
        }

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCount++;
                txtViewResult.setText(String.valueOf(mCount));
            }
        });
        btnBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewResult.setBackgroundColor(Color.BLACK);
                c = Color.BLACK;
            }
        });
        btnRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewResult.setBackgroundColor(Color.RED);
                c = Color.RED;
            }

        });
        btnBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewResult.setBackgroundColor(Color.BLUE);
                c = Color.BLUE;
            }
        });
        btnGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtViewResult.setBackgroundColor(Color.GREEN);
                c = Color.GREEN;
            }
        });
    }
}