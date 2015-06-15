package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Account_Creation extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account__creation);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_account__creation, menu);
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

    public void valider(View view){

        EditText login = (EditText)findViewById(R.id.Login);
        EditText password = (EditText)findViewById(R.id.pass);


        RegisterDAO registerDAO = new RegisterDAO(this);
        registerDAO.open();

        String log= login.getText().toString();
        String pass= password.getText().toString();

        Register registerUser= new Register(log,pass);

        registerDAO.insert(registerUser);

        registerDAO.close();

        Context context = getApplicationContext();
        CharSequence text = "Compte validé";
        int duration = Toast.LENGTH_SHORT;

        Toast.makeText(context, text, duration).show();

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void back(View view){
            Intent intent = new Intent(this,Login.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
    }

}
