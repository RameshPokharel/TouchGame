package com.csit.ramesh.game;

import android.content.Intent;
import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView box,orange,pink,black,li,plane,item;
    TextView scoreLabel,startLabel,show,live;
    public  int Boxy,Boxx;
TextView tap;
    private Handler handler= new Handler();
    private Timer timer=new Timer();

    private  SoundPlayer sound;
//status check
private  boolean action_flag= false;
    private  boolean start_flag= false;
    //size
     private  int frameheight;
    private  int framewidth;
    private  int boxHeight;
    private  int boxWidth;
    private  int screenWidth;
    private  int screenHeight;

    private  int planex=2000;
    private  int planey=300;
    private  int lix=300;
    private  int liy=400;
    private  int showx=350;
    private  int showy=200;
    private  int orangeX=600;
    private  int orangeY=32;
    private  int pinkX=489;
    private  int pinkY=300;
    private  int blackX;
    private  int blackY;
    private  int live_text;
    private  int score=0;
    float a,b;
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plane= (ImageView) findViewById(R.id.plan);
        box= (ImageView) findViewById(R.id.box_image);
        orange= (ImageView) findViewById(R.id.orange);
        pink= (ImageView) findViewById(R.id.pink);
        black= (ImageView) findViewById(R.id.black);
        scoreLabel= (TextView) findViewById(R.id.t1);
        startLabel= (TextView) findViewById(R.id.t2);
        tap= (TextView) findViewById(R.id.tap);
        li= (ImageView) findViewById(R.id.li);
        item= (ImageView) findViewById(R.id.item);
        scoreLabel.setText("score : 0");
        sound = new SoundPlayer(this);



        //getScreenSize
        WindowManager wm= getWindowManager();
        Display disp=wm.getDefaultDisplay();
        Point size= new Point();
        disp.getSize(size);
        screenWidth=size.x;
        screenHeight=size.y;
        show= (TextView) findViewById(R.id.show);

        live= (TextView) findViewById(R.id.live);
        li.setVisibility(View.INVISIBLE);
        live.setVisibility(View.INVISIBLE);
        show.setVisibility(View.INVISIBLE);
        orange.setX(20);
        orange.setY(33);
        li.setX(30);
        li.setY(76);
        pink.setX(78);
        pink.setY(24);
        box.setX(400);
        box.setY(500);
        plane.setVisibility(View.INVISIBLE);
        item.setVisibility(View.INVISIBLE);
        box.setVisibility(View.INVISIBLE);
        orange.setVisibility(View.INVISIBLE);
        pink.setVisibility(View.INVISIBLE);
        black.setVisibility(View.INVISIBLE);

    }

     public  void changePos()
    {


        hitCheck();
        //orange


       check(plane);

        showx-=1;
        if(show.getX()+show.getWidth()<0)
        {
            showx=screenWidth+200;
            showy=(int)(Math.floor(frameheight/2));
        }
        show.setY(showy);
        show.setX(showx);
        lix-=7;
        if(lix<0)
        {
            lix=screenWidth+800;
            liy=(int)(Math.random()*(frameheight-li.getHeight()));
        }
        li.setY(liy);
        li.setX(lix);
        orangeX-=17;
        if(orangeX<0)
        {
            orangeX=screenWidth+20;
            orangeY=(int)(Math.random()*(frameheight-orange.getHeight()));
        }
        orange.setY(orangeY);
        orange.setX(orangeX);
        blackX-=16;
        if(blackX<0)
        {
            blackX=screenWidth+60;
            blackY=(int)(Math.random()*(frameheight-black.getHeight()));
        }
        black.setY(blackY);
        black.setX(blackX);

        pinkX-=12;
        if(pinkX<0)
        {
            pinkX=screenWidth+15;
            pinkY=(int)(Math.random()*(frameheight-pink.getHeight()));
        }
        pink.setY(pinkY);
        pink.setX(pinkX);

        if(action_flag==true)
        {
            Boxy+=20;
            Log.d("y",""+Boxy);
        }
        else
        {
            //release
            Boxy-=20;
        }
        Log.d("y",""+Boxy);
        Boxx-=0.05;
        if(Boxy<0)
            Boxy=0;
        if(Boxx<0)
        Boxx=0;
        if(Boxy>frameheight- boxHeight)
            Boxy=frameheight- boxHeight;
        if(Boxx>framewidth- boxWidth)
            Boxx=framewidth-boxWidth;
        box.setY(Boxy);
        box.setX(Boxx);

     //  0 box.setX(Boxx);
        scoreLabel.setText("score : "+score);
        live.setText("Live :"+live_text);
    }



    public  void hitCheck()
    {
        int ocenterx=orangeX+orange.getWidth()/2;
        int ocentery=orangeY+orange.getHeight()/2;
        if(0<=ocenterx && ocenterx<= box.getX()+boxWidth && orangeX>=box.getX() && (int) box.getY()<=ocentery
                && ocentery<=Boxy+ boxHeight)
        {
            score+=10;
            sound.playHitSound();
//            orangeX  =-10;
            orangeX=screenWidth+20;
            orangeY=(int)Math.floor(Math.random()*(frameheight-orange.getHeight()));

        }
        int pinkx=pinkX+pink.getWidth()/2;
        int pinky=pinkY+pink.getHeight()/2;
        if((0<=pinkx && pinkx<=box.getX()+boxWidth&& pinkX>=box.getX()
                && (int) box.getY()<=pinky && pinky<=(int)box.getY()+ box.getHeight()))
        {
            score+=30;
            sound.playHitSound();
//            pinkX  =-10;
            pinkX=screenWidth+15;
            pinkY=(int)Math.floor(Math.random()*(frameheight-pink.getHeight()));
        }
        int blakx=blackX+black.getWidth()/2;
        int blaky=blackY+black.getHeight()/2;
        if(0<=blakx && blakx<=box.getX()+boxWidth&& blackX>=box.getX()
                && (int) box.getY()<=blaky && blaky<=(int)box.getY()+ box.getHeight())
        {
            blackX=screenWidth+60;
            blackY=(int)Math.floor(Math.random()*(frameheight-black.getHeight()));

            sound.playOverSound();
            black.setY(blackY);
            black.setX(blackX);

            live_text-=1;

            if(live_text==0)
            {
                blackX=screenWidth+17;
                timer.cancel();
                timer=null;

                Intent intent= new Intent(MainActivity.this,result.class);
                intent.putExtra("SCORE",score);
                startActivity(intent);
                finish();
            }
        }
        int px=planex+plane.getWidth()/2;
        int py=planey+plane.getHeight()/2;
        if(0<=px && px<=box.getX()+boxWidth&& planex>=box.getX()
                && (int) box.getY()<=py && py<=(int)box.getY()+ box.getHeight())
        {    timer.cancel();
                timer=null;

                Intent intent= new Intent(MainActivity.this,result.class);
                intent.putExtra("SCORE",score);
                startActivity(intent);
                finish();

        }
        int lx=lix+li.getWidth()/2;
        int ly=liy+li.getHeight()/2;

        if(0<=lx && lx<=box.getX()+boxWidth&& lix>=box.getX()
                && (int) box.getY()<=ly && ly<=(int)box.getY()+ li.getHeight()) {
            lix = screenWidth + 1600;
            liy = (int) Math.floor(Math.random() * (frameheight - li.getHeight()));

            li.setY(lix);
            li.setX(liy);

            if(live_text<3)
            {
                sound.playNoiseSound();
                live_text += 1;
            }



        }
        }
    public  boolean onTouchEvent(MotionEvent me)
    {
        box.setVisibility(View.VISIBLE);

        if(start_flag==false)
        {
            start_flag=true;
            final Button boxMove= (Button) findViewById(R.id.boxMove);
            FrameLayout frameLayout= (FrameLayout) findViewById(R.id.frame);
            box.setY(782);
            box.setX(0);
            frameheight=frameLayout.getHeight();
            framewidth=frameLayout.getWidth();
            boxMove.setVisibility(View.VISIBLE);
            boxMove.setX(frameLayout.getWidth()-boxMove.getWidth());
            boxMove.setY(frameLayout.getHeight()-boxMove.getHeight());
            boxMove.setText("MoveBox");

            Boxy = (int) box.getY();
           Boxx = (int) box.getX();
            Log.d("Y value",""+Boxy);
            Log.d("X value",""+Boxx);
            box.setX(Boxx);


            plane.setX(screenWidth+50);
            plane.setY((int)Math.floor(Math.random()*(frameheight-plane.getHeight())));
            plane.setVisibility(View.VISIBLE);

            a=plane.getX();
            b=plane.getY();
            item.setX(a);
            item.setY(b);
            item.setVisibility(View.VISIBLE);


            boxHeight =box.getHeight();
            boxWidth =box.getWidth();
            li.setVisibility(View.VISIBLE);
            black.setVisibility(View.VISIBLE);
            pink.setVisibility(View.VISIBLE);
            orange.setVisibility(View.VISIBLE);
           // Toast.makeText(MainActivity.this,""+boxHeight,Toast.LENGTH_SHORT).show();
            startLabel.setVisibility(View.GONE);
            tap.setVisibility(View.INVISIBLE);
            boxMove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Boxx +=40;
                    box.setX(Boxx);

                }
            });
            box.setY(boxHeight);

            live_text=3;
            live.setVisibility(View.VISIBLE);
            live.setText("Live: "+live_text);
            show.setVisibility(View.VISIBLE);
            show.setText("Developed By:" +
                    "Ramesh Pokhrel");
            show.setTextSize(15);
            showx=screenWidth+50;
            showy=(int)Math.floor(Math.floor(frameheight/2));
        show.setY(showy);
        show.setX(showx);


        timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            changePos();
                        }
                    });
                }
            },3,40);
        }else {
            if(me.getAction()==MotionEvent.ACTION_DOWN)
            {
                action_flag=true;
            }else if(me.getAction()==MotionEvent.ACTION_UP)
            {
                action_flag=false;
            }
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        timer.cancel();
        timer=null;
        finish();
    }
    public void check(View plane)
    {
        planex-=2;

        if(plane.getX()>0&&plane.getX()<screenWidth)
        {
            sound.playOverSound();
            double angle= Math.atan2(((box.getY()+boxHeight/2)-(plane.getY())+plane.getHeight()/2),
                    ((box.getX()+boxWidth/2)-(plane.getX()+plane.getWidth()/2)));
            double xvel=10*Math.cos(angle);
            double yvel=10*Math.sin(angle);

            a+=xvel;
            b+=yvel;
            if(a<=0)
            {
                a= (int) plane.getX();
                b= (int) plane.getY();
            }

            float cx=item.getX()+item.getWidth()/2;
            float cy=item.getY()+item.getHeight()/2;
            if(0<=cx && cx<= box.getX()+boxWidth && item.getX()>=box.getX() && (int) box.getY()<=cy
                    && cy<=Boxy+ boxHeight) {

               a=plane.getX();
                b=plane.getY();
                 score -= 10;
                sound.playHitSound();
            }
            item.setX(a);
            item.setY(b);


        }
        if(plane.getX()+plane.getWidth()<0)
        {
            planex=screenWidth+1000;
            planey=(int)Math.floor(Math.random()*(frameheight-plane.getHeight()));

        }
        plane.setX(planex);
        plane.setY(planey);


    }
}
