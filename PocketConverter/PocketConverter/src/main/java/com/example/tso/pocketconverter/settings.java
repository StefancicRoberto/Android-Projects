package com.example.tso.pocketconverter;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;

public class settings extends Activity {

    private Button btnSave;
    private EditText editFrom;
    private EditText editTo;
    private EditText editFormula;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        actionBar=getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnConvertClick(v);
            }
        });

        editFrom = (EditText)findViewById(R.id.editFrom);
        editTo = (EditText)findViewById(R.id.editTo);
        editFormula = (EditText)findViewById(R.id.editFormula);

    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch (menuItem.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
               return super.onOptionsItemSelected(menuItem);
        }
    }

    public void btnConvertClick(View v)
    {
        if(!(TextUtils.isEmpty(editFrom.getText().toString()) && TextUtils.isEmpty(editTo.getText().toString()) && TextUtils.isEmpty(editFormula.getText().toString())))
        {
            try {
                Double.parseDouble(editFormula.getText().toString());
                putPref("From", editFrom.getText().toString(), getApplicationContext());
                putPref("To", editTo.getText().toString(), getApplicationContext());
                putPref("Formula", editFormula.getText().toString(), getApplicationContext());
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
            }
            catch (Exception ex) {
                Toast.makeText(getApplicationContext(),"Error: Formula is not a numeric value!", Toast.LENGTH_LONG).show();
            }
        }
        else
            Toast.makeText(getApplicationContext(),"Please fill all fields", Toast.LENGTH_LONG).show();
    }

    public static void putPref(String key, String value, Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPref(String key, Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }
}
