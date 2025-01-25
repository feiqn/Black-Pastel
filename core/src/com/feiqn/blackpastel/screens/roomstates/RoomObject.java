package com.feiqn.blackpastel.screens.roomstates;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.feiqn.blackpastel.dialog.DialogScript;

public class RoomObject extends Actor {

    public DialogScript associatedScript;
    public Rectangle clickable;

    public RoomObject(float x, float y, float width, float height) {
        clickable = new Rectangle(x,y,width,height);
    }

}
