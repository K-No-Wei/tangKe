package com.TangKe03;

public class Bomb {
    int x, y;
    int life = 40;
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }


//    减少生命值
    public void lifeDown(){
        if (life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
