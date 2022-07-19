package com.TangKe03;



import javax.swing.*;

public class TanKGame01 extends JFrame {

    MyPanel mp = null;

    public static void main(String[] args) {
        TanKGame01 tanKGame01 = new TanKGame01();
    }

    public TanKGame01(){
        mp = new MyPanel();

        new Thread(mp).start();

        this.add(mp);

        this.setSize(1000,750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }



}
