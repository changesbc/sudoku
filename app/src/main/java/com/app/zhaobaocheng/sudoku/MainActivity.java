package com.app.zhaobaocheng.sudoku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(1); //隐藏标题栏
        //setContentView(R.layout.activity_main);
        setContentView(new ShuduView(this));
    }
}
