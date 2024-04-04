package com.eduardo.spacewar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Handler;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class SpaceShooter extends View {
    Context context;
    Bitmap background, lifeImage;
    Handler handler;
    long UPDATE_MILLIS = 30;
    static int screenWidth, screenHeight;
    int points = 0, life = 5;
    Paint scorePaint;
    int textSize = 80;
    boolean paused = false;
    PlayerSpaceship playerSpaceship;
    EnemySpaceship enemySpaceship;
    Random random;
    ArrayList<Shot> enemyShots,playerShots;
    ArrayList<Explosion> explosions;
    Boolean enemyExplosion = false;
    Explosion explosion;
    boolean enemyShotAction = false;

    final Runnable runnable = new Runnable() {
        @Override
        public void run() {
         invalidate();
        }
    };

    public SpaceShooter(Context context){
        super(context);
        this.context = context;
        random = new Random();
        enemyShots = new ArrayList<>();
        playerShots = new ArrayList<>();
        explosions = new ArrayList<>();
        Display display =((Activity) getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;
        playerSpaceship = new PlayerSpaceship(context);
        enemySpaceship = new EnemySpaceship(context);
        background = BitmapFactory.decodeResource(context.getResources(), R.drawable.bg);
        lifeImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.life);
        handler = new Handler();
        scorePaint = new Paint();
        scorePaint.setTextSize(textSize);
        scorePaint.setTextAlign(Paint.Align.LEFT);

    }

    @Override
    protected  void onDraw(Canvas cv){
        cv.drawBitmap(background, 0, 0, null);
        cv.drawText("Points: "+points, 0, textSize, scorePaint);
        for(int i = 0; i < life; i--){
            cv.drawBitmap(lifeImage, screenWidth -lifeImage.getWidth() * i, 0, null);
        }
        if(life == 0){
           paused = true;
           handler = null;
           Intent intent = new Intent(context, GameOver.class);
           intent.putExtra("points", points);
           context.startActivity(intent);
            ((Activity) context).finish();
        }

        enemySpaceship.ex += enemySpaceship.enemyVelocity;
        if(enemySpaceship.ex + enemySpaceship.getEnemySpaceshipWidth() >= screenWidth){
            enemySpaceship.enemyVelocity *= -1;
        }
        if(enemySpaceship.ex <= 0){
            enemySpaceship.enemyVelocity *= -1;
        }
        if(enemyShotAction == false && enemySpaceship.ex >= 200 + random.nextInt(400)){
            Shot enemyShot = new Shot(context, enemySpaceship.ex + enemySpaceship.getEnemySpaceshipWidth() / 2, enemySpaceship.ey + enemySpaceship.getEnemySpaceshipHeight());
            enemyShots.add(enemyShot);
        }
    }

}







