package com.app.zhaobaocheng.sudoku;

/**
 * Created by ZhaoBaocheng on 2016/7/11.
 */
public class Game {
    private final String str="360000000004230850010054200"+
            "070460003820000014500013020"+
            "001900000007048300000000045";

    private int sudoku[]=new int[9*9];
    //用于存储每个单元格已经不可用的数据
    private int used[][][]=new int[9][9][];

    public Game(){
        sudoku=fromPuzzleString(str);
        calculateAllUsedTitles();
    }

    //用于计算所有单元格对应的不可用数据
    private void calculateAllUsedTitles() {
        for (int x=0;x<9;x++){
            for (int y=0;y<9;y++){
                used[x][y]=calculateUsedTitles(x,y);
            }
        }
    }

    //根据坐标得到不可用的数据
    //取出某一单元格中已经不可用的数据
    public int[] getUsedTitlesByCoor(int x,int y){
        return used[x][y];
    }

    //计算某一单元格当中已经不可用的数据
    public int[] calculateUsedTitles(int x,int y) {
        int c[]=new int[9];
        for (int i=0;i<9;i++){
            if (i==y){
                continue;
            }
            int t=getTitle(x,i);
            if (t!=0){
                c[t-1]=t;
            }
        }
        for (int i=0;i<9;i++){
            if (i==x){
                continue;
            }
            int t=getTitle(i,y);
            if (t!=0){
                c[t-1]=t;
            }
        }

        int startx=(x/3)*3;
        int starty=(y/3)*3;
        for (int i=startx;i<startx+3;i++){
            for (int j=starty;j<starty+3;j++){
                if (i==x&&j==y){
                    continue;
                }
                int t=getTitle(i,j);
                if (t!=0){
                    c[t-1]=t;
                }
            }
        }

        int used=0;
        for (int t:c) {
            if (t!=0){
                used++;
            }
        }
        int c1[]=new int[used];
        used=0;
        for (int t:c) {
            if (t!=0)
                c1[used++]=t;
        }
        return c1;
    }

    protected boolean setTitleIfValid(int x,int y,int value){
        int titles[]=getUsedTitles(x,y);
        if (value!=0){
            for (int title:titles) {
                if (title==value){
                    return false;
                }
            }
        }
        setTitle(x,y,value);
        calculateAllUsedTitles();
        return true;
    }

    protected int [] getUsedTitles(int x,int y){
        return used[x][y];
    }

    private void setTitle(int x,int y,int value){
        sudoku[y*9+x]=value;
    }

    private int getTitle(int x,int y){
        return sudoku[y*9+x];
    }
    public String getTitleString(int x,int y){
        int v=getTitle(x,y);
        if (v==0){
            return "";
        }
        return String.valueOf(v);
    }

    private int[] fromPuzzleString(String str) {
        int sudo[]=new int[str.length()];
        for (int i=0;i<sudo.length;i++){
            sudo[i]=str.charAt(i)-'0'; //转化成整形数组
        }
        return sudo;
    }
}

