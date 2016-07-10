package com.app.zhaobaocheng.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ZhaoBaocheng on 2016/7/10.
 */
public class ShuduView extends View {
    //单元格的宽度和高度
    private float width;
    private float height;
    private Game game=new Game();
    private int selectedX;
    private int selectedY;

    public ShuduView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //计算当前单元格的宽度和高度
        this.width=w/9f;
        this.height=h/9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        //生成用于绘制背景色的画笔
        Paint backgroundPaint=new Paint();
        //backgroundPaint.setColor(getResources().getColor(R.color.shudubackground));
        //设置画笔的颜色
        backgroundPaint.setColor(getResources().getColor(R.color.shudu_background));

        //绘制背景色
        canvas.drawRect(0,0,getWidth(),getHeight(),backgroundPaint);

        Paint darkPaint=new Paint();
        darkPaint.setColor(getResources().getColor(R.color.shudu_dark));

        Paint hilitePaint=new Paint();
        hilitePaint.setColor(getResources().getColor(R.color.shudu_hilite));

        Paint lightPaint=new Paint();
        lightPaint.setColor(getResources().getColor(R.color.shudu_light));

        for (int i=0;i<9;i++){
            //绘制横向单元格线
            canvas.drawLine(0,i*height,getWidth(),i*height,lightPaint);
            canvas.drawLine(0,i*height+1,getWidth(),i*height+1,hilitePaint);
            //绘制纵向单元格线
            canvas.drawLine(i*width,0,i*width,getHeight(),lightPaint);
            canvas.drawLine(i*width+1,0,i*width+1,getHeight(),hilitePaint);
        }

        for (int i=0;i<9;i++){
            if (i%3!=0){
                continue;
            }
            canvas.drawLine(0,i*height,getWidth(),i*height,darkPaint);
            canvas.drawLine(0,i*height+1,getWidth(),i*height+1,hilitePaint);
            canvas.drawLine(i*width,0,i*width,getHeight(),darkPaint);
            canvas.drawLine(i*width+1,0,i*width+1,getHeight(),hilitePaint);
        }
        Paint numaberPaint=new Paint();
        numaberPaint.setColor(Color.BLACK);
        numaberPaint.setStyle(Paint.Style.STROKE);
        numaberPaint.setTextSize(height*0.75f);
        numaberPaint.setTextAlign(Paint.Align.CENTER);

        Paint.FontMetrics fm=numaberPaint.getFontMetrics();
        float x=width/2;
        float y=height/2-(fm.ascent+fm.descent)/2;
        //canvas.drawText("1",3*width+x,y,numaberPaint);
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                canvas.drawText(game.getTitleString(i,j),i*width+x,j*height+y,numaberPaint);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction()!=MotionEvent.ACTION_DOWN){
            return super.onTouchEvent(event);
        }

        selectedX= (int) (event.getX()/width);
        selectedY= (int) (event.getY()/height);
        int used[]=game.getUsedTitlesByCoor(selectedX,selectedY);
      /*  for (int i=0;i<used.length;i++){
            Toast.makeText(getContext(),"已经使用的数据"+used[i],Toast.LENGTH_SHORT).show();
        }*/
        StringBuffer sb=new StringBuffer();
        for (int i=0;i<used.length;i++){
            sb.append(used[i]);
        }

        KeyDialog keyDialog=new KeyDialog(getContext(),used,this);
        keyDialog.show();
        return true;
    }

    public void setSelectedTitle(int t){
        if (game.setTitleIfValid(selectedX,selectedY,t)){
            invalidate();
        }
    }
}
