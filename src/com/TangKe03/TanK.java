package com.TangKe03;

public class TanK {
    private int x;//横坐标
    private int y;
    private int direct;
    private int speed;



    public int getSpeed() {
        return speed;
    }


    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void move(int direct){
        if (direct == 0)
            y -= speed;
        else if(direct == 1)
            x += speed;
        else if(direct == 2)
            y += speed;
        else if(direct == 3)
            x -= speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public TanK(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
