package com.csit.ramesh.game;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Ramesh on 3/12/2017.
 */

public class SoundPlayer {



    private  static SoundPool soundPool;
    private  static  int hitSound;
    private static  int overSound;
    private static  int noiseSound;

    public  SoundPlayer(Context context)
    {
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC,2);
        hitSound =soundPool.load(context,R.raw.cross,1);
        overSound =soundPool.load(context,R.raw.crash,1);
        noiseSound =soundPool.load(context,R.raw.tuktuk,1);



    }

    public  void  playHitSound()
    {
        soundPool.play(hitSound,1.0f,1.0f,1,0,1.0f);

    }
    public  void  playOverSound()
    {
        soundPool.play(overSound,1.0f,1.0f,1,0,1.0f);

    }
    public  void  playNoiseSound()
    {
        soundPool.play(noiseSound,1.0f,1.0f,1,0,1.0f);

    }
}
