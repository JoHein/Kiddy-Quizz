package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Math extends Activity {
    public String log;
    public final static String LOGUSER = "logUser";
    public final static String PASS = "password";
    public final static String OPERCALC = "plusmult";

    public String pass;
    public String plusmult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        log = getIntent().getStringExtra(Hub_choix.LOGUSER);
        pass = getIntent().getStringExtra(Hub_choix.PASS);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_math, menu);
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

    public void add (View view){
        Intent intent = new Intent(this,Addition.class);
        intent.putExtra(LOGUSER, log);
        intent.putExtra(PASS, pass);
        plusmult=" + ";
        intent.putExtra(OPERCALC, plusmult);

        startActivity(intent);
    }
    public void multi (View view){
        Intent intent = new Intent(this,Addition.class);
        intent.putExtra(LOGUSER, log);
        intent.putExtra(PASS, pass);
        plusmult=" X ";
        intent.putExtra(OPERCALC, plusmult);


        startActivity(intent);
    }

    public void back (View view){
        Intent intent = new Intent(this,Hub_choix.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
