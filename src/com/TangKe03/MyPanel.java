package com.TangKe03;


import jdk.nashorn.internal.runtime.Version;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {

//    我的坦克
    Hero hero = null;

//    敌人坦克
    Vector<EnemyTanK> enemyTanKS = new Vector<>();

//    存放炸弹
    Vector<Bomb> bombs = new Vector<>();

//    存放我方发射的子弹
    Vector<Shot> shots = new Vector<>();

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    int enemyTankSize = 3;

    public MyPanel(){
        hero = new Hero(500,100);
//        设置坦克速度
        hero.setSpeed(6);

//        敌方坦克
        for (int i = 0; i <enemyTankSize; i++){

            EnemyTanK enemyTanK = new EnemyTanK(100 * (i + 1), 0, 2);
            enemyTanK.setSpeed(10);
//            启动敌人坦克
            new Thread(enemyTanK).start();
            Shot shot = new Shot(enemyTanK.getX() + 20, enemyTanK.getY() + 60, enemyTanK.getDirect());
            enemyTanK.shots.add(shot);
            new Thread(shot).start();
            enemyTanKS.add(enemyTanK);
        }


        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/bomb_3.png"));

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
//        g.setColor(Color.red);
        g.fillRect(0,0,StaticS.Screen_x,StaticS.Screen_y);    //填矩形

        for(int i = 0; i < hero.shots.size(); i++){
            Shot shot = hero.shots.get(i);
            if(shot.isLive){
                Color color = g.getColor();
                g.setColor(Color.CYAN);
                g.draw3DRect(shot.x, shot.y, 4, 4, false);
                g.setColor(color);
            }
        }
        
//        画爆炸
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if(bomb.life > 6){
                g.drawImage(image1, bomb.x, bomb.y, 60 ,60, this);
            }if(bomb.life >3){
                g.drawImage(image2, bomb.x, bomb.y, 60 ,60, this);
            }else {
                g.drawImage(image3, bomb.x, bomb.y, 60 ,60, this);
            }

            bomb.lifeDown();
            if(bomb.life == 0){
                bombs.remove(bomb);
            }
        }


//        画坦克-方法
        if(hero.isLive) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);
        }

        for (int i = 0; i < enemyTanKS.size(); i++) {
            EnemyTanK enemyTanK = enemyTanKS.get(i);
//            敌人坦克存活才画
            if (enemyTanK.isLive) {
                drawTank(enemyTanK.getX(), enemyTanK.getY(), g, enemyTanK.getDirect(), 1);

//            画出子弹
                for (int j = 0; j < enemyTanK.shots.size(); j++) {
                    Shot shot = enemyTanK.shots.get(j);
                    if (shot.isLive) {
                        Color color = g.getColor();
                        g.setColor(Color.yellow);
                        g.draw3DRect(shot.x, shot.y, 4, 4, false);
                        g.setColor(color);
                    } else {
//                    删除移除的坦克
                        enemyTanK.shots.remove(shot);
                    }
                }

            }
        }

    }


//

    /**
     *
     * @param x x坐标
     * @param y y坐标
     * @param g 画笔
     * @param direct 方向
     * @param type  类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        switch (type){
            case 0://乙方坦克
                g.setColor(Color.cyan);break;
            case 1://敌方
                g.setColor(Color.yellow);break;
        }

//        方向
        switch (direct){
            case 0:
                g.fill3DRect(x, y, 10,60,false);
                g.fill3DRect(x + 30, y, 10,60,false);
                g.fill3DRect(x + 10, y +10, 20, 40,false);
                g.drawOval(x + 10,y + 20,20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1:
                g.fill3DRect(x, y, 60,10,false);
                g.fill3DRect(x, y + 30, 60,10,false);
                g.fill3DRect(x + 10, y +10, 40, 20,false);
                g.drawOval(x + 20,y + 10,20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10,60,false);
                g.fill3DRect(x + 30, y, 10,60,false);
                g.fill3DRect(x + 10, y +10, 20, 40,false);
                g.drawOval(x + 10,y + 20,20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60,10,false);
                g.fill3DRect(x, y + 30, 60,10,false);
                g.fill3DRect(x + 10, y +10, 40, 20,false);
                g.drawOval(x + 20,y + 10,20, 20);
                g.drawLine(x , y + 20, x + 30, y + 20);
                break;
            default:
                System.out.println("无");
        }
    }


//    判断子弹击中
    public void hitTank(Shot s, EnemyTanK enemyTanK){
//        防止区域内子弹路过消失的坦克区域是时消失
        if (enemyTanK.isLive) {
            switch (enemyTanK.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemyTanK.getX() && s.x < enemyTanK.getX() + 40
                            && s.y > enemyTanK.getY() && s.y < enemyTanK.getY() + 60) {
                        s.isLive = false;
                        enemyTanK.isLive = false;
                        hero.shots.remove(s);
//                        创建Bomb
                        Bomb bomb = new Bomb(enemyTanK.getX(), enemyTanK.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemyTanK.getX() && s.x < enemyTanK.getX() + 60
                            && s.y > enemyTanK.getY() && s.x < enemyTanK.getY() + 40) {
                        s.isLive = false;
                        enemyTanK.isLive = false;
                        hero.shots.remove(s);
                        //                        创建Bomb
                        Bomb bomb = new Bomb(enemyTanK.getX(), enemyTanK.getY());
                        bombs.add(bomb);
                    }
                    break;
            }
        }
    }
    public void hitTank(Shot s, TanK enemyTanK){
//        防止区域内子弹路过消失的坦克区域是时消失
        if (enemyTanK.isLive) {
            switch (enemyTanK.getDirect()) {
                case 0:
                case 2:
                    if (s.x > enemyTanK.getX() && s.x < enemyTanK.getX() + 40
                            && s.y > enemyTanK.getY() && s.y < enemyTanK.getY() + 60) {
                        s.isLive = false;
                        enemyTanK.isLive = false;
                        hero.shots.remove(s);
//                        创建Bomb
                        Bomb bomb = new Bomb(enemyTanK.getX(), enemyTanK.getY());
                        bombs.add(bomb);
                    }
                    break;
                case 1:
                case 3:
                    if (s.x > enemyTanK.getX() && s.x < enemyTanK.getX() + 60
                            && s.y > enemyTanK.getY() && s.x < enemyTanK.getY() + 40) {
                        s.isLive = false;
                        enemyTanK.isLive = false;
                        hero.shots.remove(s);
                        //                        创建Bomb
                        Bomb bomb = new Bomb(enemyTanK.getX(), enemyTanK.getY());
                        bombs.add(bomb);
                    }
                    break;
            }
        }
    }

//    我方坦克被击中
    public void hitHero(){
        for(int i = 0; i < enemyTanKS.size(); i++){
            EnemyTanK enemyTanK = enemyTanKS.get(i);
            for(int j = 0; j < enemyTanK.shots.size(); j++){
                Shot shot = enemyTanK.shots.get(j);
                if(hero.isLive){
                    hitTank(shot, hero);
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            if(hero.getY() > 0)
                hero.move(0);
        }

        else if(e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if(hero.getX() + 60 <StaticS.Screen_x)
                hero.move(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if(hero.getY() + 60 < StaticS.Screen_y)
                hero.move(2);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if(hero.getX() > 0)
                hero.move(3);
        }

        else if(e.getKeyCode() == KeyEvent.VK_J && hero.isLive){
            hero.shotEnemyTank();
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            判断是否击中敌人
//            循环己方坦克的子弹
            for(int i = 0; i < hero.shots.size(); i++){
                Shot shot = hero.shots.get(i);
                if(shot.isLive){
                    for(int j = 0; j <enemyTanKS.size(); j++){
                        EnemyTanK enemyTanK = enemyTanKS.get(j);
                        if(enemyTanK.isLive){
                            hitTank(shot,enemyTanK);
                        }else {
                            enemyTanKS.remove(enemyTanK);
                        }
                    }
                }
            }
//            敌方击中我方
            hitHero();

//            if(hero.shot != null&& hero.shot.isLive){
//                for(int i = 0; i < enemyTanKS.size(); i++){
//                    EnemyTanK enemyTanK = enemyTanKS.get(i);
//                    //        防止区域内子弹路过消失的坦克区域是时消失
//                    if(enemyTanK.isLive)
//                        hitTank(hero.shot, enemyTanK);
//                    else {
//                        enemyTanKS.remove(enemyTanK);
//                    }
//                }
//            }

            this.repaint();
        }
    }
}
