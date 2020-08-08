package com.example.mygeoquiz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mygeoquiz.R;

public class SettingActivity extends AppCompatActivity {
    public static final String RESULT_OK = "RESULT_OK";
    public static final String EXTRA_IS_SETTING = "com.example.MyGeoQuiz2.isSetting";
    public static final String EXTRA_SETTING = "EXTRA_SETTING";
    private RadioButton mSmallSize;
    private RadioButton mMediumSize;
    private RadioButton mLargeSize;
    private int mTextSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        setListeners();
    }

    private void findViews() {
        mSmallSize = findViewById(R.id.rbtn_small);
        mMediumSize = findViewById(R.id.rbtn_mediom);
        mLargeSize = findViewById(R.id.rbtn_large);
    }

    private void setListeners() {


        mSmallSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextSize = 14;
               setTextSize(mTextSize);
            }
        });

        mMediumSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextSize = 18;
                setTextSize(mTextSize);


            }
        });
        mLargeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextSize = 26;
                setTextSize(mTextSize);


            }
        });
    }
    private  void setTextSize(int size){
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_SETTING, size);
        setResult(Integer.parseInt(RESULT_OK), intent);
    }
}