package com.example.heinenj.heinenjocelinas;

import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by heinenj on 29/05/15.
 */
public class Jeu extends Object{

    private Integer score;

    public Jeu() {
    score=0;
    }

    public void verifMulti(int nb1, int nb2, int res1) {
        if ((nb2 * nb1) == res1) {
            score++;
        }
    }

    public void verifAdd(int nb1, int nb2, int res1) {
        if ((nb2 + nb1) == res1) {
            score++;
        }
    }
    public void verifQ(int rep1, int repNbrand) {
        if (repNbrand==rep1) {
            score++;
        }
    }
        public Integer getScore(){
        return score;
    }
}

