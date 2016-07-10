package com.app.zhaobaocheng.sudoku;

/**
 * Created by ZhaoBaocheng on 2016/7/11.
 */

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

/**
 * 实现自定义对话框功能
 */
public class KeyDialog extends Dialog {
    private final View keys[]=new View[9]; //用来存放代表对话框当中按钮的对象
    private final int used[];
    private ShuduView shuduView;

    public KeyDialog(Context context, int[] used, ShuduView shuduView) {
        super(context);
        this.used = used;       //保存当前单元格中已经用过的数字
        this.shuduView=shuduView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("KeyDialog");
        setContentView(R.layout.keypad);
        findViews();
        //遍历整个used数组
        for (int i=0;i<used.length;i++){
            if (used[i]!=0){
                keys[used[i]-1].setVisibility(View.INVISIBLE);
            }
        }

        //为对话框中的所有按钮设置监听器
        setListeners();

    }

    private void setListeners() {
        //遍历整个keys数组
        for (int i=0;i<keys.length;i++){
            final int t=i+1;
            keys[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    returnResult(t);
                }
            });
        }
    }

    private void returnResult(int t) {
        shuduView.setSelectedTitle(t);
        //取消对话框显示
        dismiss();
    }

    private void findViews() {
        keys[0]=findViewById(R.id.keypad1);
        keys[1]=findViewById(R.id.keypad2);
        keys[2]=findViewById(R.id.keypad3);
        keys[3]=findViewById(R.id.keypad4);
        keys[4]=findViewById(R.id.keypad5);
        keys[5]=findViewById(R.id.keypad6);
        keys[6]=findViewById(R.id.keypad7);
        keys[7]=findViewById(R.id.keypad8);
        keys[8]=findViewById(R.id.keypad9);
    }
}