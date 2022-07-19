package com.TangKe03;

public class Shot implements Runnable{

    public int x;
    public int y;
    public int speed = 10;
    public int direct = 0;
    boolean isLive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true){

            if (!(x >= 0 && x <= StaticS.Screen_x && y >= 0 && y <= StaticS.Screen_y)) {
                isLive = false;
                break;
            }



            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (direct){
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
            }
            System.out.println("子弹" + x + " " + y);
        }
    }
}
