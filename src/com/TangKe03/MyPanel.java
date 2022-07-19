package com.TangKe03;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

public class MyPanel extends JPanel implements KeyListener,Runnable {

    Hero hero = null;
    Graphics g = null;
//    敌人坦克
    Vector<EnemyTanK> enemyTanKS = new Vector<>();

    int enemyTankSize = 3;

    public MyPanel(){
        hero = new Hero(100,100);
//        设置坦克速度
        hero.setSpeed(6);

//        敌方坦克
        for (int i = 0; i <enemyTankSize; i++){

            enemyTanKS.add(new EnemyTanK(100 * (i + 1),0, 2));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.g = g;
//        g.setColor(Color.red);
        g.fillRect(0,0,StaticS.Screen_x,StaticS.Screen_y);    //填矩形

//        画出子弹
        if(hero.shot != null && hero.shot.isLive){
            System.out.println("子弹开始");
            Color color = g.getColor();
            g.setColor(Color.CYAN);
            g.draw3DRect(hero.shot.x, hero.shot.y, 4, 4, false);
            g.setColor(color);
        }


//        画坦克-方法
        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 0);

        for (int i = 0; i < enemyTanKS.size(); i++){
            EnemyTanK enemyTanK = enemyTanKS.get(i);
            drawTank(enemyTanK.getX(), enemyTanK.getY(), g, enemyTanK.getDirect(),1);
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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            hero.move(0);
        }

        else if(e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            hero.move(1);
        }
        else if(e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            hero.move(2);
        }
        else if(e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            hero.move(3);
        }

        else if(e.getKeyCode() == KeyEvent.VK_J){
            System.out.println(11111);
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
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
