package com.example.mygeoquiz.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mygeoquiz.Controller.QuizActivity;
import com.example.mygeoquiz.Model.Question;
import com.example.mygeoquiz.R;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEAT = "com.example.MyGeoQuiz2.isCheat";


    public static final String TEXT_VIEW_ANSWER = "TextViewAnswer";
    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;

    private boolean mIsAnswerTrue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState != null) {
            mTextViewAnswer.setText( savedInstanceState.getString(TEXT_VIEW_ANSWER));

        }

        mIsAnswerTrue = getIntent().getBooleanExtra(QuizActivity.EXTRA_QUESTION_ANSWER, false);

        findViews();
        setListeners();

        //1.ResultCode: OK | Cancel (default: Cancel)
        //2.ResultIntent: null (default: null)
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(TEXT_VIEW_ANSWER, (String) mTextViewAnswer.getText());
    }

    @SuppressLint("WrongViewCast")
    private void findViews() {
        mTextViewAnswer = findViewById(R.id.txt_true_answer);
        mButtonShowAnswer = findViewById(R.id.btn_I_sure);
    }

    private void setListeners() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsAnswerTrue)
                    mTextViewAnswer.setText(R.string.button_true);
                else
                    mTextViewAnswer.setText(R.string.button_false);

                setShownAnswerResult(true);
            }
        });
    }

    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);

        setResult(RESULT_OK, intent);
    }
}