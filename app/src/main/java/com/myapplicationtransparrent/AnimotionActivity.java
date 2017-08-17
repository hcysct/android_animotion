package com.myapplicationtransparrent;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;


/**
 * Created by xox on 2017/8/17.
 */

public class AnimotionActivity extends AppCompatActivity {
    private ImageView image1,image2,image3,image4;
    private RelativeLayout.LayoutParams layoutParams1,layoutParams2,layoutParams3,layoutParams4;
    private Point p;
    private int randomX,randomY;
    private Bitmap bitmap1,bitmap2,bitmap3,bitmap4;
    private static final String TAG = "AnimotionActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout innerLayout = (RelativeLayout) findViewById(R.id.relative_layout);
        //获取屏幕宽p.x 获取屏幕高p.y
        p = new Point();
        this.getWindowManager().getDefaultDisplay().getSize(p);
        image1 = new ImageView(this);
        image2 = new ImageView(this);
        image3 = new ImageView(this);
        image4 = new ImageView(this);
        bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.dami_1);
        image1.setImageBitmap(bitmap1);
        bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.dami_2);
        image2.setImageBitmap(bitmap2);
        bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.dami_3);
        image3.setImageBitmap(bitmap3);
        bitmap4 = BitmapFactory.decodeResource(getResources(), R.drawable.dami_4);
        image4.setImageBitmap(bitmap4);
        layoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        randomX = new Random().nextInt((int)(p.x*0.15));
        randomY = (int) (new Random().nextInt((int) (p.y*0.25))+bitmap1.getHeight()*1.3);
        Toast.makeText(this,randomY+"   randomY",Toast.LENGTH_SHORT).show();
        layoutParams1.setMargins(randomX,-randomY,0,0);
        innerLayout.addView(image1,layoutParams1);
        startAnimation(image1,0,0);

        layoutParams2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        randomX = new Random().nextInt((int)(p.x*0.15))+(int)(p.x*0.15);
        randomY = (int) (new Random().nextInt((int) (p.y*0.3))+bitmap2.getHeight()*1.3);
        layoutParams2.setMargins(randomX,-randomY,0,0);
        innerLayout.addView(image2,layoutParams2);
        startAnimation(image2,0,0);

        layoutParams3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        randomX = new Random().nextInt((int)(p.x*0.15))+(int)(p.x*0.4);
        randomY = (int) (new Random().nextInt((int) (p.y*0.2))+bitmap3.getHeight()*1.3);
        layoutParams3.setMargins(randomX,-randomY,0,0);
        innerLayout.addView(image3,layoutParams3);
        startAnimation(image3,0,0);

        layoutParams4 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,RelativeLayout.LayoutParams.WRAP_CONTENT);
        randomX = new Random().nextInt((int)(p.x*0.15))+(int)(p.x*0.6);
        randomY = (int) (new Random().nextInt((int) (p.y*0.4))+bitmap4.getHeight()*1.3);
        layoutParams4.setMargins(randomX,-randomY,0,0);
        innerLayout.addView(image4,layoutParams4);
        startAnimation(image4,0,0);
    }

    public void startAnimation(View imageView, float X, float Y){
        ObjectAnimator xAnimator;
        int sharkAmplitude = new Random().nextInt(150) + 50;
//        Log.d(TAG,sharkAmplitude+"    sharkAmplitude");
        if(sharkAmplitude%2 == 0){
            xAnimator = new ObjectAnimator().ofFloat(imageView, "translationX", X, sharkAmplitude, X, -sharkAmplitude,X);
        }else{
            xAnimator = new ObjectAnimator().ofFloat(imageView, "translationX", X, -sharkAmplitude, X, sharkAmplitude,X);
        }
        ObjectAnimator yAnimator = new ObjectAnimator().ofFloat(imageView, "translationY", Y, p.y*1.2f);
        ObjectAnimator alphaAnimator = new ObjectAnimator().ofFloat(imageView, "alpha",0.7f,1f,0f);
        Interpolator YInterpolator = new LinearInterpolator();
        yAnimator.setInterpolator(YInterpolator);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(xAnimator).with(yAnimator).with(alphaAnimator);
        animatorSet.setDuration(5000);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
