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

import com.example.heinenj.heinenjocelinas.RegisterDAO;

public class Login extends Activity {
    public final static String LOGUSER = "logUser";
    public final static String IDUSER = "iduser";
    public final static String SCOREQ = "scoreq";
    public final static String PASS = "password";

    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    public void anonyme (View view){
        Intent intent = new Intent(this,Hub_choix.class);
        startActivity(intent);
    }
/*
    public void startReg (View view){
        Intent intent = new Intent(this,Register.class);
        startActivity(intent);
    }
*/

    public void startReg(View view){
        Intent intent = new Intent(this,Account_Creation.class);
        startActivity(intent);

    }

    public void listePlayers(View view){
        Intent intent = new Intent(this,ListePlayers.class);
        startActivity(intent);
    }


    public void login(View view){


        EditText login = (EditText)findViewById(R.id.login);
         password = (EditText)findViewById(R.id.password);

        String log=login.getText().toString();
        String pass=password.getText().toString();

        RegisterDAO registerDAO = new RegisterDAO(this);
        registerDAO.open();

        Register exist= registerDAO.checkUser(log,pass);


        if(exist!=null) {

        //recuperer un objet Register avec le login dans le check

        String logTest = exist.getLogin();
        pass = exist.getPassword();
        Integer idu = exist.getIdu();


        Intent intent = new Intent(this, Hub_choix.class);
        intent.putExtra(LOGUSER, logTest);
        intent.putExtra(IDUSER, idu);
        intent.putExtra(PASS, pass);


            startActivity(intent);

     }else{
        Context context = getApplicationContext();
        CharSequence text = "Wrong Login or Password";
        int duration = Toast.LENGTH_SHORT;
            password.setText(null);

            Toast.makeText(context, text, duration).show();
    }

    }
}
