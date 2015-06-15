package com.example.heinenj.heinenjocelinas;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.heinenj.heinenjocelinas.QuestionDAO;
import com.example.heinenj.heinenjocelinas.Question;

import java.util.Random;

public class Questions extends Activity {
    public Jeu game;
    public Integer i;
    public int id;
    public int repNbrand;
    public RadioButton premBut;
    public RadioButton secBut;
    public RadioButton thirdBut;
    public int radioId;

    public RadioGroup radioGroup;


    public final static String LOGUSER = "logUser";
    public final static String PASS = "password";
    public final static String SCOREQ = "scoreq";

    public String log;
    public String pass;
    public String catquest;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        log = getIntent().getStringExtra(Category.LOGUSER);
        pass = getIntent().getStringExtra(Category.PASS);
        catquest = getIntent().getStringExtra(Category.CATQUEST);
        radioGroup = (RadioGroup) findViewById(R.id.blockQ);



        game = new Jeu();
        i=1;

        newQ();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_questions, menu);
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

    public void newQ(){
        QuestionDAO questionDAO = new QuestionDAO(this);
        questionDAO.open();

        TextView questionView = (TextView)findViewById(R.id.quetionText);

        premBut = (RadioButton)findViewById(R.id.bonneRep);
        secBut = (RadioButton)findViewById(R.id.bad1);
        thirdBut = (RadioButton)findViewById(R.id.bad2);

        Question question = questionDAO.getQuestionCat(catquest);

        questionDAO.close();

        Random randScen = new Random();
        repNbrand = randScen.nextInt(3);
        if (i < 11) {

            questionView.setText(question.getQuestion());
            if (repNbrand==0){
                    premBut.setText(question.getBonneReponse());
                    secBut.setText(question.getMauvaiseReponse1());
                    thirdBut.setText(question.getMauvaiseReponse2());
            }else if (repNbrand==1) {
                    premBut.setText(question.getMauvaiseReponse1());
                    secBut.setText(question.getBonneReponse());
                    thirdBut.setText(question.getMauvaiseReponse2());
            }else{
                    premBut.setText(question.getMauvaiseReponse2());
                    secBut.setText(question.getMauvaiseReponse1());
                    thirdBut.setText(question.getBonneReponse());
            }

            i++;

        } else {
            Intent intent = new Intent(this, Resultat.class);
            intent.putExtra(SCOREQ, game.getScore());
            intent.putExtra(LOGUSER, log);
            intent.putExtra(PASS, pass);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    public void next(View view) {

        //recup et verif reponse
        id = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton)radioGroup.findViewById(id);
        radioId = radioGroup.indexOfChild(radioButton);
        game.verifQ(radioId,repNbrand);
        newQ();
    }
}
