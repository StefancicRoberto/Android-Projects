package com.example.tso.pocketconverter;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainWindow extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ListView lstFrom;
    private ListView lstTo;
    private ArrayList<String> stringArray;
    private ArrayAdapter<String> adapter;
    private EditText editIntput;
    private EditText editOuput;
    private Button btnConvert;

    private int from=-1;
    private int to=-1;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent sendIntent = new Intent(Intent.ACTION_VIEW);
                sendIntent.setData(Uri.parse("sms:"));
                sendIntent.putExtra("body", 0);
                startActivity(sendIntent);
            }
        });
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnConvert=(Button)findViewById(R.id.btnConvert);
        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnConvertClick(v);
            }
        });


        lstFrom=(ListView)findViewById(R.id.lstFrom);

        lstFrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                from=position;

            }
        });


        lstTo=(ListView)findViewById(R.id.lstTo);

        lstTo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                to = position;
            }
        });

        stringArray=new ArrayList<>();
        stringArray.add("Decimal");
        stringArray.add("Hexadecimal");
        stringArray.add("Binary");
        adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
        lstFrom.setAdapter(adapter);
        lstTo.setAdapter(adapter);
        id=R.id.nav_number;

        editIntput=(EditText)findViewById(R.id.editInput);
        editOuput=(EditText)findViewById(R.id.editOutput);
        //editOuput.setEnabled(false);

    }

    public void btnConvertClick(View view) {
        if(!TextUtils.isEmpty(editIntput.getText().toString())) {
            if((from!=-1)&&(to!=-1))
                switch (id) {
                    case R.id.nav_number: {
                        Toast.makeText(getApplicationContext(), "Converting number in progress...", Toast.LENGTH_SHORT).show();
                        ConvertNumbers();
                    }
                    break;
                    case R.id.nav_weight: {
                        Toast.makeText(getApplicationContext(), "Converting weight in progress...", Toast.LENGTH_SHORT).show();
                        ConvertWeight();
                    }
                    break;
                    case R.id.nav_length: {
                        Toast.makeText(getApplicationContext(), "Converting length in progress...", Toast.LENGTH_SHORT).show();
                        ConvertLength();
                    }
                    break;
                    case R.id.nav_speed: {
                        Toast.makeText(getApplicationContext(), "Converting speed in progress...", Toast.LENGTH_SHORT).show();
                        ConvertSpeed();
                    }
                    break;
                    case R.id.nav_custom: {
                        Toast.makeText(getApplicationContext(), "Converting custom in progress...", Toast.LENGTH_SHORT).show();
                        ConvertCustom();
                    }
                    break;
                    default:
                        Toast.makeText(getApplicationContext(), "No conversion selected...", Toast.LENGTH_LONG).show();
                        break;
                }
            else
                Toast.makeText(getApplicationContext(), "No conversion input or output selected!", Toast.LENGTH_LONG).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Error, no input!", Toast.LENGTH_LONG).show();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_window, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        id = item.getItemId();

        if (id == R.id.nav_number) {
            stringArray=new ArrayList<>();
            stringArray.add("Decimal");
            stringArray.add("Hexadecimal");
            stringArray.add("Binary");
            adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
            lstFrom.setAdapter(adapter);
            lstTo.setAdapter(adapter);
        } else if (id == R.id.nav_weight) {
            stringArray=new ArrayList<>();
            stringArray.add("Kilogram");
            stringArray.add("Pound");
            stringArray.add("Ounces");
            adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
            //lstFrom=(ListView)findViewById(R.id.lstFrom);
            lstFrom.setAdapter(adapter);
            //lstTo=(ListView)findViewById(R.id.lstTo);
            lstTo.setAdapter(adapter);
        } else if (id == R.id.nav_length) {
            stringArray=new ArrayList<>();
            stringArray.add("Meters");
            stringArray.add("Centimeters");
            stringArray.add("Miles");
            stringArray.add("Feets");
            stringArray.add("Yards");
            stringArray.add("Inches");
            adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
            //lstFrom=(ListView)findViewById(R.id.lstFrom);
            lstFrom.setAdapter(adapter);
            //lstTo=(ListView)findViewById(R.id.lstTo);
            lstTo.setAdapter(adapter);
        } else if (id == R.id.nav_speed) {
            stringArray=new ArrayList<>();
            stringArray.add("Kilometers/Hour");
            stringArray.add("Miles/Hour");
            stringArray.add("Knots");
            stringArray.add("Feets/Second");
            stringArray.add("Meters/Second");
            adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
            //lstFrom=(ListView)findViewById(R.id.lstFrom);
            lstFrom.setAdapter(adapter);
            //lstTo=(ListView)findViewById(R.id.lstTo);
            lstTo.setAdapter(adapter);
        } else if(id==R.id.nav_custom){
            stringArray=new ArrayList<>();
            stringArray.add(settings.getPref("From", getApplicationContext()));
            stringArray.add(settings.getPref("To", getApplicationContext()));
            adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stringArray);
            lstFrom.setAdapter(adapter);
            lstTo.setAdapter(adapter);
        }
        else if (id == R.id.nav_settings) {
            Intent intent = new Intent(this, settings.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void ConvertNumbers()
    {
        switch (from) {

            case 0: //converting decimal
                switch (to) {
                    case 0: //to decimal
                        editOuput.setText(editIntput.getText());
                        break;
                    case 1://to hexadecimal
                        try {
                            editOuput.setText(Long.toHexString(Long.parseLong(editIntput.getText().toString())));
                        }
                        catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2://to binary
                        try {
                            editOuput.setText(Long.toBinaryString(Long.parseLong(editIntput.getText().toString())));
                        }
                        catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;
            case 1: //converting hexadecimal
                switch (to) {
                    case 0: //to decimal
                        try {
                            editOuput.setText(Long.toString(Long.parseLong(editIntput.getText().toString(), 16)));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to hexadecimal
                        editOuput.setText(editIntput.getText());
                        break;
                    case 2: //to binary
                        try
                        {
                            editOuput.setText(Long.toBinaryString(Long.parseLong(editIntput.getText().toString())));
                        }
                        catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;
            case 2: //converting binary
                switch (to) {
                    case 0: //to decimal
                        try {
                            editOuput.setText(Long.toString(Long.parseLong(editIntput.getText().toString(), 2)));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to hexadecimal
                        try {
                            long decimal=Long.parseLong(editIntput.getText().toString(), 2);
                            editOuput.setText(Long.toString(decimal, 16));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to binary
                        editOuput.setText(editIntput.getText());
                        break;
                }
                break;
        }
    }

    private void ConvertWeight()
    {
        switch(from) {

            case 0: //converting kilogram
                switch(to) {
                    case 0: //to kilogram
                        editOuput.setText(editIntput.getText());
                        break;
                    case 1: //to pound
                        try {
                            editOuput.setText(Double.toString(2.2 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to ounce
                        try {
                            editOuput.setText(Double.toString(35.27 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 1: //converting pound
                switch(to) {
                    case 0: //to kilogram
                        try {
                            editOuput.setText(Double.toString(0.45 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to pound
                        editOuput.setText(editIntput.getText());
                        break;
                    case 2: //to ounce
                        try {
                            editOuput.setText(Double.toString(16 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 2: //converting ounces
                switch(to) {
                    case 0: //to kilogram
                        try {
                            editOuput.setText(Double.toString(0.028 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to pound
                        try {
                            editOuput.setText(Double.toString(0.062 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to ounce
                        editOuput.setText(editIntput.getText());
                        break;
                }
                break;
        }
    }

    private void ConvertLength()
    {
        switch(from) {

            case 0: //converting meters
                switch(to) {
                    case 0: //to meters
                        editOuput.setText(editIntput.getText());
                        break;
                    case 1: //to centimeters
                        try {
                            editOuput.setText(Double.toString(100 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to miles
                        try {
                            editOuput.setText(Double.toString(0.000621 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to feets
                        try {
                            editOuput.setText(Double.toString(3.28 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to yards
                        try {
                            editOuput.setText(Double.toString(1.093 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5: //to inches
                        try {
                            editOuput.setText(Double.toString(39.37 * Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 1: //converting centimeters
                switch(to) {
                    case 0: //to meters
                        try {
                            editOuput.setText(Double.toString(Double.parseDouble(editIntput.getText().toString())/100));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to centimeters
                        editOuput.setText(editIntput.getText());
                        break;
                    case 2: //to miles
                        try {
                            editOuput.setText(Double.toString(6.21* Double.parseDouble(editIntput.getText().toString())/1000000));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to feets
                        try {
                            editOuput.setText(Double.toString(0.0328* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to yards
                        try {
                            editOuput.setText(Double.toString(0.0109* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5: //to inches
                        try {
                            editOuput.setText(Double.toString(0.0109* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 2: //converting miles
                switch(to) {
                    case 0: //to meters
                        try {
                            editOuput.setText(Double.toString(1609.34* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to centimeters
                        try {
                            editOuput.setText(Double.toString(160934.4* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to miles
                        editOuput.setText(editIntput.getText());
                        break;
                    case 3: //to feets
                        try {
                            editOuput.setText(Double.toString(5280* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to yards
                        try {
                            editOuput.setText(Double.toString(1760* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5: //to inches
                        try {
                            editOuput.setText(Double.toString(63360* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 3: //converting feets
                switch(to) {
                    case 0: //to meters
                        try {
                            editOuput.setText(Double.toString(0.3048* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to centimeters
                        try {
                            editOuput.setText(Double.toString(30.48* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to miles
                        try {
                            editOuput.setText(Double.toString(0.000189* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to feets
                        editOuput.setText(editIntput.getText());
                        break;
                    case 4: //to yards
                        try {
                            editOuput.setText(Double.toString(0.333* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5: //to inches
                        try {
                            editOuput.setText(Double.toString(12* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 4: //converting yards
                switch(to) {
                    case 0: //to meters
                        try {
                            editOuput.setText(Double.toString(0.9144* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to centimeters
                        try {
                            editOuput.setText(Double.toString(91.44* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to miles
                        try {
                            editOuput.setText(Double.toString(0.00056818* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to feets
                        try {
                            editOuput.setText(Double.toString(3* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to yards
                        editOuput.setText(editIntput.getText());
                        break;
                    case 5: //to inches
                        try {
                            editOuput.setText(Double.toString(36* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 5: //converting inches
                switch(to) {
                    case 0: //to meters
                        try {
                            editOuput.setText(Double.toString(0.0254* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to centimeters
                        try {
                            editOuput.setText(Double.toString(2.54* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to miles
                        try {
                            editOuput.setText(Double.toString(1.578* Double.parseDouble(editIntput.getText().toString())/100000));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to feets
                        try {
                            editOuput.setText(Double.toString(0.0833* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to yards
                        try {
                            editOuput.setText(Double.toString(0.0277* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 5: //to inches
                        editOuput.setText(editIntput.getText());
                        break;
                }
                break;
        }
    }

    private void ConvertSpeed()
    {
        switch(from) {

            case 0: //converting Km/h
                switch (to) {
                    case 0: //to Km/h
                        editOuput.setText(editIntput.getText());
                        break;
                    case 1: //to Miles/h
                        try {
                            editOuput.setText(Double.toString(0.621* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to Knots
                        try {
                            editOuput.setText(Double.toString(0.539* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to Feets/s
                        try {
                            editOuput.setText(Double.toString(0.911* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to Meters/s
                        try {
                            editOuput.setText(Double.toString(0.277* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 1: //converting Miles/h
                switch (to) {
                    case 0: //to Km/h
                        try {
                            editOuput.setText(Double.toString(1.609* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to Miles/h
                        editOuput.setText(editIntput.getText());
                        break;
                    case 2: //to Knots
                        try {
                            editOuput.setText(Double.toString(0.868* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to Feets/s
                        try {
                            editOuput.setText(Double.toString(1.466* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to Meters/s
                        try {
                            editOuput.setText(Double.toString(0.447* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 2: //converting Knots
                switch (to) {
                    case 0: //to Km/h
                        try {
                            editOuput.setText(Double.toString(1.852* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to Miles/h
                        try {
                            editOuput.setText(Double.toString(1.1507* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to Knots
                        editOuput.setText(editIntput.getText());
                        break;
                    case 3: //to Feets/s
                        try {
                            editOuput.setText(Double.toString(1.687* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to Meters/s
                        try {
                            editOuput.setText(Double.toString(0.514* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 3: //converting Feets/s
                switch (to) {
                    case 0: //to Km/h
                        try {
                            editOuput.setText(Double.toString(1.0972* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to Miles/h
                        try {
                            editOuput.setText(Double.toString(0.681* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to Knots
                        try {
                            editOuput.setText(Double.toString(0.592* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to Feets/s
                        editOuput.setText(editIntput.getText());
                        break;
                    case 4: //to Meters/s
                        try {
                            editOuput.setText(Double.toString(0.304* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
                break;

            case 4: //converting Meters/s
                switch (to) {
                    case 0: //to Km/h
                        try {
                            editOuput.setText(Double.toString(3.6* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1: //to Miles/h
                        try {
                            editOuput.setText(Double.toString(2.236* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 2: //to Knots
                        try {
                            editOuput.setText(Double.toString(1.943* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 3: //to Feets/s
                        try {
                            editOuput.setText(Double.toString(3.2808* Double.parseDouble(editIntput.getText().toString())));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 4: //to Meters/s
                        editOuput.setText(editIntput.getText());
                        break;
                }
                break;
        }
    }

    private void ConvertCustom()
    {
        switch (from)
        {
            case 0:
            {
                switch (to)
                {
                    case 0:
                        editOuput.setText(editIntput.getText());
                        break;
                    case 1:
                        try{
                            Double formula = Double.parseDouble(settings.getPref("Formula", getApplicationContext()));
                            editOuput.setText(Double.toString(formula * Double.parseDouble((editIntput.getText().toString()))));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                }
            }
            break;

            case 1:
            {
                switch (to)
                {
                    case 0:
                        try{
                            Double formula = 1/Double.parseDouble(settings.getPref("Formula", getApplicationContext()));
                            editOuput.setText(Double.toString(formula * Double.parseDouble((editIntput.getText().toString()))));
                        }
                        catch (Exception ex){
                            Toast.makeText(getApplicationContext(), "Error converting! Bad input...", Toast.LENGTH_LONG).show();
                        }
                        break;
                    case 1:
                        editOuput.setText(editIntput.getText());
                        break;
                }
            }
            break;
        }
    }
}
