package com.example.mygeoquiz.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mygeoquiz.R;

public class SettingActivity extends AppCompatActivity {

    public static final String TEXT_SIZE = "text_size";
    public static final String EXTRA_COLOR_BACKGROUND = "extraColorBackground";
    public static final String EXTRA_TEXT_SIZE = "ExtraTextSize";
    public static final String CURRENT_COLOR_BACKGROUND = "CURRENT_COLORBACGROUND";

    private RadioButton mSmallSize;
    private RadioButton mMediumSize;
    private RadioButton mLargeSize;
    private RadioButton mLightBlue;
    private RadioButton mLightRed;
    private RadioButton mLightGreen;
    private RadioButton mWight;
    private Button mSaveChange;
    private Button mDiscard;



    private Boolean mIsSaved=false;
    private int mTextSize;
    private int mColorBackground;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViews();
        Intent intent=new Intent();
        mTextSize=intent.getIntExtra(QuizActivity.EXTRA_TEXT_CURRENT_SIZE,16);
        mColorBackground=intent.getIntExtra(EXTRA_COLOR_BACKGROUND,R.color.colorWight);
        if (savedInstanceState !=null){
            mTextSize=savedInstanceState.getInt(TEXT_SIZE);
            mColorBackground=savedInstanceState.getInt(CURRENT_COLOR_BACKGROUND);
        }

        setListeners();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(TEXT_SIZE,mTextSize);
        outState.putInt(CURRENT_COLOR_BACKGROUND,mColorBackground);

    }

    private void findViews() {
        mSmallSize = findViewById(R.id.radio_btn_small);
        mMediumSize = findViewById(R.id.radio_btn_medium);
        mLargeSize = findViewById(R.id.radio_btn_large);
        mLightBlue=findViewById(R.id.radio_btn_light_blue);
        mLightRed=findViewById(R.id.radio_byn_light_red);
        mLightGreen=findViewById(R.id.radio_btn_light_green);
        mWight=findViewById(R.id.radio_btn_wight);
        mSaveChange=findViewById(R.id.btn_save);
        mDiscard=findViewById(R.id.btn_discard);
    }

    private void setListeners() {
        mSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              mIsSaved=true;
              setChange(mTextSize,mColorBackground);
            }
        });
        mDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingActivity.this.finish();
            }
        });

        mSmallSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    mTextSize=16;
                }

            }
        });
        mMediumSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    mTextSize=18;
                }

            }
        });
        mLargeSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean checked = ((RadioButton) view).isChecked();
                // Check which radiobutton was pressed
                if (checked){
                    mTextSize=24;

                }

            }
        });
        mLightBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mColorBackground=R.color.colorLightBlue;
            }
        });
        mLightRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mColorBackground=R.color.colorLightRed;
            }
        });
        mLightGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mColorBackground=R.color.colorLightGreen;
            }
        });
        mWight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mColorBackground=R.color.colorWight;
            }
        });

    }
    private  void setChange(int size,int colorBackground) {
        if (mIsSaved) {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_TEXT_SIZE, size);
            intent.putExtra(EXTRA_COLOR_BACKGROUND,colorBackground);
            setResult(RESULT_OK, intent);
        }
    }

}