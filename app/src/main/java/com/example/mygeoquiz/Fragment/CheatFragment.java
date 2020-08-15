package com.example.mygeoquiz.Fragment;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mygeoquiz.Controller.CheatActivity;
import com.example.mygeoquiz.Controller.QuizActivity;
import com.example.mygeoquiz.Model.Question;

import com.example.mygeoquiz.R;

import static android.app.Activity.RESULT_OK;

public class CheatFragment extends Fragment {

    public static final String EXTRA_IS_CHEAT = "com.example.MyGeoQuiz2.isCheat";
    public static final String TEXT_VIEW_ANSWER = "TextViewAnswer";
    public static final String M_IS_CHEATED = "mIsCheated";
    public static final String M_IS_ANSWER_TRUE = "mIsAnswerTrue";
    public static final String ANSWER_TRUE_REC = "AnswerTrueRec";
    private TextView mTextViewAnswer;
    private Button mButtonShowAnswer;

    private boolean mIsAnswerTrue;
    private boolean mIsCheated;

/*    public static CheatFragment newInstance(boolean answerTrue) {


        Bundle args = new Bundle();
        args.putBoolean(ANSWER_TRUE_REC,answerTrue);
        CheatFragment fragment = new CheatFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

public static CheatFragment newInstance(boolean isAnswerTrue){
    Bundle args = new Bundle();
    args.putBoolean("ars_is_answer_true", isAnswerTrue);

    CheatFragment fragment = new CheatFragment();
    fragment.setArguments(args);
    return fragment;
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            mIsAnswerTrue= getArguments().getBoolean(ANSWER_TRUE_REC);
//        Toast.makeText(getActivity(),"args"+mIsAnswerTrue,Toast.LENGTH_LONG).show();
        if (savedInstanceState != null) {

            mTextViewAnswer.setText(savedInstanceState.getString(TEXT_VIEW_ANSWER));
        }
    }

        @Override
        public View onCreateView (LayoutInflater inflater, ViewGroup container,
                                  Bundle savedInstanceState){
            // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_cheat, container, false);

            findViews(view);

            setListeners();

            return view;
        }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_VIEW_ANSWER, mTextViewAnswer.getText().toString());
        outState.putBoolean(M_IS_CHEATED, mIsCheated);
        outState.putBoolean(M_IS_ANSWER_TRUE, mIsAnswerTrue);
    }

    @SuppressLint("WrongViewCast")
    private void findViews(View view) {
        mTextViewAnswer = view.findViewById(R.id.txt_true_answer);
        mButtonShowAnswer = view.findViewById(R.id.btn_I_sure);
    }

    private void setListeners() {
        mButtonShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mIsAnswerTrue) {
                    mTextViewAnswer.setText(R.string.button_true);
                } else {
                    mTextViewAnswer.setText(R.string.button_false);
                }


                mIsCheated = true;
            }
        });



        setShownAnswerResult(true);
    }


    private void setShownAnswerResult(boolean isCheat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, isCheat);
        getActivity().setResult(RESULT_OK, intent);

    }
}