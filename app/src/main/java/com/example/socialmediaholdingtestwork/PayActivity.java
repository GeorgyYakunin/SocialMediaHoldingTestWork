package com.example.socialmediaholdingtestwork;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_activity);
        TypefaceUtil.overrideFont(getApplicationContext(), "SERIF", "fonts/Raleway-Bold.ttf");


    }
}
