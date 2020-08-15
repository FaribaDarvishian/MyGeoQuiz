package com.example.mygeoquiz.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygeoquiz.Controller.QuizActivity;
import com.example.mygeoquiz.Fragment.CheatFragment;
import com.example.mygeoquiz.Fragment.QuizFragment;
import com.example.mygeoquiz.Model.Question;
import com.example.mygeoquiz.R;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEAT = "com.example.MyGeoQuiz2.isCheat";
    public static final String ARG_ANSWER_TRUE = "argAnswerTrue";

    private boolean mIsAnswerTrue;

/*    public static Intent newIntent(Context src, boolean isAnswerTrue){
        Intent intent = new Intent(src, CheatActivity.class);
        intent.putExtra(EXTRA_IS_CHEAT, isAnswerTrue );

        return intent;
    }*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);



        //check if fragment exists in container (configuration changes save the fragments)
        Intent intent= getIntent();
        boolean ans= intent.getBooleanExtra(QuizFragment.EXTRA_QUESTION_ANSWER,false);


        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        //create an add fragment transaction for CrimeDetailFragment
        if (fragment == null) {
            fragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_container,CheatFragment.newInstance(ans) )
                    .commit();
        }
    }
}