package com.eduardo.spacewar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class PlayerSpaceship {
    Context context;
    Bitmap playerSpaceship;
    int px, py;
    int playerVelocity;
    Random random = new Random();
    boolean moveLeft, moveRight, isAlive = true;


    public PlayerSpaceship(Context context){
        this.context = context;
        playerSpaceship = BitmapFactory.decodeResource(context.getResources(), R.drawable.nave);
        random = new Random();
       resetPlayerSpaceship();

    }

    public Bitmap getPlayerSpaceship(){
        return playerSpaceship;
    }

    public int getPlayerSpaceshipWidth(){
        return playerSpaceship.getWidth();
    }

    public int getPlayerSpaceshipHeight(){
        return playerSpaceship.getHeight();
    }

    private void resetPlayerSpaceship() {
        px = random.nextInt(SpaceShooter.screenWidth);
        py = SpaceShooter.screenHeight - playerSpaceship.getHeight();
        playerVelocity = 10 + random.nextInt(6);

    }

}
