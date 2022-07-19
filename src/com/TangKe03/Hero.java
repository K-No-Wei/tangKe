package com.TangKe03;

@SuppressWarnings({"all"})
public class Hero extends TanK{
//    创建SHot对象
    Shot shot = null;
    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank() {
        switch (getDirect()){
            case 0:
                shot =  new Shot(getX() + 20, getY(), getDirect());
                break;
            case 1:
                shot =  new Shot(getX() + 60, getY() + 20, getDirect());
                break;
            case 2:
                shot =  new Shot(getX() + 20, getY() + 60, getDirect());
                break;
            case 3:
                shot =  new Shot(getX(), getY() + 20, getDirect());
                break;
        }

        new Thread(shot).start();
    }
}
