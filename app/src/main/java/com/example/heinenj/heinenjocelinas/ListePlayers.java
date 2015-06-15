package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;


public class ListePlayers extends Activity {

    public int i = 0;
    public ListView listPlayers;
    public final static String LOGUSER = "logUser";
    public final static String PASS = "password";

    public EditText password;
    public EditText loginU;
    public Intent logfast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_players);

        ListView lp = (ListView) findViewById(R.id.listView);
        RegisterDAO registerDAO = new RegisterDAO(this);
        registerDAO.open();

        List<Register> listPlayers = registerDAO.selectAll();


        final ArrayAdapter<Register> adapter = new ArrayAdapter<Register>(ListePlayers.this, android.R.layout.simple_list_item_1, listPlayers);
        lp.setAdapter(adapter);
        logfast = new Intent(this, Hub_choix.class);

        lp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Register register= adapter.getItem(i);
                    logfast = new Intent(ListePlayers.this,Hub_choix.class);
                    logfast.putExtra(LOGUSER, register.getLogin());
                    logfast.putExtra(PASS, register.getPassword());
                startActivity(logfast);
            }

        });
        registerDAO.close();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liste_players, menu);
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


    public void back(View view) {
        Intent intent = new Intent(this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    public void login(View view) {


        loginU = (EditText) findViewById(R.id.login);
        password = (EditText) findViewById(R.id.password);

        String log = loginU.getText().toString();
        String pass = password.getText().toString();

        RegisterDAO registerDAO = new RegisterDAO(this);
        registerDAO.open();

        Register exist = registerDAO.checkUser(log, pass);


        if (exist != null) {

            //recuperer un objet Register avec le login dans le check

            String logTest = exist.getLogin();
            pass = exist.getPassword();
            Integer idu = exist.getIdu();


            Intent intent = new Intent(this, Hub_choix.class);
            intent.putExtra(LOGUSER, logTest);
            intent.putExtra(PASS, pass);


            startActivity(intent);

        }
    }
}