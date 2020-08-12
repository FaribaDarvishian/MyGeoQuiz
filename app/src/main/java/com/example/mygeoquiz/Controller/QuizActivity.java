package com.example.mygeoquiz.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygeoquiz.Model.Question;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.mygeoquiz.R;

import java.io.Serializable;

public class QuizActivity extends AppCompatActivity implements Serializable {

    public static final String EXTRA_QUESTION_ANSWER = "com.example.MyGeoQuiz2.questionAnswer";
    public static final String CURRENT_INDEX = "Current_Index";
    public static final String NUMBER_OF_ANSWERED = "Number_Of_Answered";
    public static final String QUESTION_BANK = "Question_Bank";
    public static final int REQUEST_CODE_CHEAT = 0;
    public static final int REQUEST_CODE_SETTING = 1;
    public static final String CURRENT_SCORE = "CURRENT_SCORE";
    public static final String EXTRA_TEXT_CURRENT_SIZE = "text_current_size";
    public static final String EXTRA_CURRENT_COLOR_BACKGROUND = "current_color_background";

    private LinearLayout mMainLayout;
    private TextView mTextViewQuestion;
    private Button mButtonTrue;
    private Button mButtonFalse;
    private Button mButtonCheat;
    private ImageButton mImageButtonNext;
    private ImageButton mImageButtonPrev;
    private ImageButton mImageButtonLast;
    private ImageButton mImageButtonFirst;
    private TextView mTextViewScore;
    private LinearLayout mScoreLayout;
    private TextView mTextViewFinalScore;
    private ImageButton mImageButtonReset;
    private ImageButton mImageButtonSetting;

    private boolean mIsCheater = false;
    private int mCurrentIndex = 0;
    private int mCurrentScore=0;
    private int mNumOfAnswered=0;
    private int mTextSize=18;//Medium size
    private int mColorBackground=R.color.colorWight;
    private Question[] mQuestionBank = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };


    /**
     * This method is used to crete ui for activity.
     * @param savedInstanceState
     */
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //this method will create the layout
        //inflate: creating object of xml layout
        setContentView(R.layout.activity_quiz);

        findViews();
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX);
            mNumOfAnswered=savedInstanceState.getInt(NUMBER_OF_ANSWERED);
            mQuestionBank=(Question[]) savedInstanceState.getSerializable(QUESTION_BANK);
            mCurrentScore=savedInstanceState.getInt(CURRENT_SCORE);
            mTextViewScore.setText(" : امتیاز "+ mCurrentScore);
        }

        //if we want to change logic we must first find the view objects (it must have "id")

        setListeners();
        updateQuestion();
        checkGameOver();


    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_INDEX,mCurrentIndex);
        outState.putInt(NUMBER_OF_ANSWERED,mNumOfAnswered);
        outState.putSerializable(QUESTION_BANK,mQuestionBank);
        outState.putInt(CURRENT_SCORE,mCurrentScore);



    }
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        //this means if the result if backed from CheatActivity
        if (requestCode == REQUEST_CODE_CHEAT) {
            mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_IS_CHEAT, false);
            mQuestionBank[mCurrentIndex].setIsCheating(mIsCheater);
            // if we on score page this method check and set visibility
            checkGameOver();
        }
        if(requestCode==REQUEST_CODE_SETTING){
            mTextSize =data.getIntExtra(SettingActivity.EXTRA_TEXT_SIZE,mTextSize);
            mColorBackground=data.getIntExtra(SettingActivity.EXTRA_COLOR_BACKGROUND,mColorBackground);
            mMainLayout.setBackgroundColor(getResources().getColor(mColorBackground));
            updateQuestion();


        }

    }



    private void findViews() {
        mTextViewQuestion = findViewById(R.id.txt_view_question_text);
        mTextViewScore=findViewById(R.id.txt_view_score_text);
        mButtonTrue = findViewById(R.id.btn_true);
        mButtonFalse = findViewById(R.id.btn_false);
        mButtonCheat=findViewById(R.id.btn_cheat);
        mImageButtonNext= findViewById(R.id.im_btn_next);
        mImageButtonPrev = findViewById(R.id.im_btn_prev);
        mImageButtonLast = findViewById(R.id.im_btn_last);
        mImageButtonFirst= findViewById(R.id.im_btn_first);
        mImageButtonSetting=findViewById(R.id.im_btn_setting);
        mTextViewFinalScore=findViewById(R.id.txt_final_score);
        mImageButtonReset=findViewById(R.id.im_btn_reset);
        mScoreLayout=findViewById(R.id.score);
        mMainLayout=findViewById(R.id.main);

    }

    private void setListeners() {
        mButtonTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mNumOfAnswered;
                mButtonTrue.setEnabled(false);
                mButtonFalse.setEnabled(false);
                mQuestionBank[mCurrentIndex].setIsAnswered(true);
                checkAnswer(true);

            }
        });

        mButtonFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ++mNumOfAnswered;
                mButtonTrue.setEnabled(false);
                mButtonFalse.setEnabled(false);
                mQuestionBank[mCurrentIndex].setIsAnswered(true);
                checkAnswer(false);

            }
        });

        mImageButtonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();



            }
        });

        mImageButtonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex - 1 + mQuestionBank.length) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mImageButtonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=0;
                updateQuestion();
            }
        });
        mImageButtonLast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex=mQuestionBank.length -1;
                updateQuestion();
            }
        });
        mImageButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mScoreLayout.setVisibility(View.GONE);
                resetGame();
            }
        });
        mButtonCheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_ANSWER, mQuestionBank[mCurrentIndex].isAnswerTrue());

                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });
        mImageButtonSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QuizActivity.this, SettingActivity.class);
                intent.putExtra(EXTRA_TEXT_CURRENT_SIZE,mTextSize);
                intent.putExtra(EXTRA_CURRENT_COLOR_BACKGROUND,mColorBackground);

                startActivityForResult(intent, REQUEST_CODE_SETTING);
            }
        });

    }
    private void resetGame(){
        mCurrentScore=0;
        mCurrentIndex=0;
        mTextViewScore.setText("Score :" + mCurrentScore);
        mButtonTrue.setEnabled(true);
        mButtonFalse.setEnabled(true);
        mNumOfAnswered=0;

        for (Question element:mQuestionBank) {
            element.setIsAnswered(false);

        }
        mMainLayout.setVisibility(View.VISIBLE);

    }

    private void updateQuestion() {
        int questionTextResId = mQuestionBank[mCurrentIndex].getQuestionTextResId();
        mTextViewQuestion.setText(questionTextResId);
        mTextViewQuestion.setTextSize(mTextSize);
        if(!mQuestionBank[mCurrentIndex].isIsAnswered())
        {
            mButtonTrue.setEnabled(true);
            mButtonFalse.setEnabled(true);

        }
        else {
            mButtonTrue.setEnabled(false);
            mButtonFalse.setEnabled(false);
        }
        checkGameOver();
    }

    private void updateScore(){
        mTextViewScore.setText(" : امتیاز " + ++mCurrentScore);
    }

    @SuppressLint("WrongConstant")
    private void checkAnswer(boolean userPressed) {
        if (mQuestionBank[mCurrentIndex].isIsCheating()) {
            Toast.makeText(this, R.string.toast_judgment, Toast.LENGTH_LONG).show();
            mIsCheater=false;
        }
        else {
            if (mQuestionBank[mCurrentIndex].isAnswerTrue() == userPressed) {
                Toast.makeText(QuizActivity.this, R.string.toast_correct, Toast.LENGTH_SHORT)
                        .show();
                updateScore();

            } else {
                Toast.makeText(QuizActivity.this, R.string.toast_incorrect, Toast.LENGTH_SHORT)
                        .show();

            }
        }



        }
        private void showFinalScore () {
            mTextViewFinalScore.setText( " : امتیاز شما در بازی  " + mCurrentScore);
            mTextViewFinalScore.setTextSize(30);

        }
        private void checkGameOver () {
            if (mNumOfAnswered == mQuestionBank.length) {
                mMainLayout.setVisibility(View.GONE);
                mScoreLayout.setVisibility(View.VISIBLE);
                showFinalScore();


            }

        }
    }