package com.TangKe03;
import java.util.Vector;

public class EnemyTanK extends TanK implements Runnable{

    Vector<Shot> shots = new Vector<>();
    boolean isLive = true;

    public EnemyTanK(int x, int y, int direct) {
        super(x, y);
        super.setDirect(direct);
    }


    @Override
    public void run() {
        while (true){
//            根据方向移动坦克
            for(int i = 0; i < (int)((Math.random()*(100-30))+ 30); i++){
                switch (getDirect()){
                    case 0:
                        if(getY() > 0){
                            move(getDirect());
                        }
                        break;
                    case 1:
                        if(getX() + 60 <StaticS.Screen_x){
                            move(getDirect());
                        }
                        break;
                    case 2:
                        if (getY() + 60 < StaticS.Screen_y){
                            move(getDirect());
                        }
                        break;
                    case 3:
                        if(getX() > 0){
                            move(getDirect());
                        }
                        break;
                }
                try {
                    Thread.sleep(70);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            switch (getDirect()){
//                case 0:
//                    for(int i = 0; i < (int)((Math.random()*(100-30))+ 30); i++){
//                        move(getDirect());
//                        try {
//                            Thread.sleep(70);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                case 1:
//                    for(int i = 0; i < (int)((Math.random()*(100-30))+ 30); i++){
//                        move(getDirect());
//                        try {
//                            Thread.sleep(70);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                case 2:
//                    for(int i = 0; i < (int)((Math.random()*(100-30))+ 30); i++){
//                        move(getDirect());
//                        try {
//                            Thread.sleep(70);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//                case 3:
//                    for(int i = 0; i < (int)((Math.random()*(100-30))+ 30); i++){
//                        move(getDirect());
//                        try {
//                            Thread.sleep(70);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    break;
//            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            setDirect((int)(Math.random() * 4));

            if(isLive == false){
                break;
            }
        }
    }
}
