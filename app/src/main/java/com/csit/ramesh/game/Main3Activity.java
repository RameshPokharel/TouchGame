package com.csit.ramesh.game;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Main3Activity extends AppCompatActivity {

    TextView textView;
    Button text,button,button1;
    private  boolean action_flag= false;
    private  boolean start_flag= false;
    private  int textX;
    private  int textY;
    private  int b1x;
    private  int b1y;
    private  int buttonX;
    private  int buttonY;
    private Handler handler= new Handler();
    private Timer timer=new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);


        textView= (TextView) findViewById(R.id.textView);
        text= (Button) findViewById(R.id.text);
        button= (Button) findViewById(R.id.button);
        button1= (Button) findViewById(R.id.button1);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {


        if(start_flag==false)
        {
            start_flag=true;

            textX=(int)text.getX();
            textY=(int)text.getY();

            button.setX(600);
            buttonX= (int) button.getX();
            buttonY= (int) button.getY();
            button1.setX(buttonX);
            button1.setY(buttonY);
            b1x=buttonX;
            b1y=buttonY;


            float deltax=textX-buttonX;
            float deltay= textY-buttonY;
            float angle= (float) Math.atan2(deltay,deltax);

timer.schedule(new TimerTask() {
    @Override
    public void run() {
handler.post(new Runnable() {
    @Override
    public void run() {

        moveItem();
    }

});
    }
},33,45);
        }
        else {
            if(event.getAction()==MotionEvent.ACTION_DOWN)
            {
                action_flag=true;
            }else if(event.getAction()==MotionEvent.ACTION_UP)
            {
                action_flag=false;
            }
        }
        return  true;}

    private void moveItem() {
            if(action_flag==true)
        {
            textY-=2;
        }
        else
        {
            //release
            textY+=2;
        }


        buttonX-=1;
        text.setY(textY);
        double angle= Math.atan2((text.getY()-button.getY()),(text.getX()-button.getX()));
        double xvel=Math.cos(angle);
       double yvel=Math.sin(angle);
/*
        double xvel=text.getY()-button.getY();
        double yvel=text.getX()-button.getX();
        double mag = Math.sqrt(xvel*xvel+yvel*yvel);
        xvel=xvel*2/mag;
        yvel=yvel*2/mag;*/

        b1x+=2*xvel;
        b1y+=2*yvel;
      /*  int a= (int) text.getX();
        int b= (int) text.getY();
        buttonY=(int)button.getY();
        if(buttonY>b&&a<buttonX)
        {
            b1x-=10;
            b1y-=10;
        }
        else if(buttonX==a)
        {
            b1x-=10;
        }
        else
        {
            b1x-=10;
            b1y+=10;
        }
*/
if(b1x<=0)
{
    b1x= (int) button.getX();
    b1y= (int) button.getY();
}
        button1.setX(b1x);
        button1.setY(b1y);
if(buttonX<0)
{
    buttonX+=400;
}
button.setX(buttonX);
    }
}
