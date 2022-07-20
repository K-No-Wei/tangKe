package com.TangKe03;

import java.util.Vector;

@SuppressWarnings({"all"})
public class Hero extends TanK{
//    创建SHot对象
    Shot shot = null;

    Vector<Shot> shots = new Vector<>();

    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        switch (getDirect()){
            case 0:
                shots.add(new Shot(getX() + 20, getY(), getDirect()));
                break;
            case 1:
                shots.add(new Shot(getX() + 60, getY() + 20, getDirect()));
                break;
            case 2:
                shots.add(new Shot(getX() + 20, getY() + 60, getDirect()));
                break;
            case 3:
                shots.add(new Shot(getX(), getY() + 20, getDirect()));
                break;
        }

        for (Shot shot : shots) {
            new Thread(shot).start();
        }
    }
}
