package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import main.interfaces.IDesenhavel;

public class App {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();

        List<IDesenhavel> ds = new ArrayList<IDesenhavel>();
        
        Quadrado q = new Quadrado();
        Circulo c = new Circulo(340, 340);
        Mapa m = new Mapa();

        ds.add(m);
        ds.add(c);

        //futuro switch case para outras telas
        Janela_Principal principal = new Janela_Principal(ds);

        frame.add(principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //In Game Loop
        while (true){

            //Controla eventos
            q.setPos(principal.getMousePos());            
            c.segue(principal.getMousePos());

            //Desenha na Tela
            principal.repaint();
            Thread.sleep(10);
        }
    }
}

class Quadrado implements IDesenhavel{
    int x;
    int y;
    Point pos = new Point();

    public Quadrado() {
        this.x = 0;
        this.y = 0;
    }

    public void setPos(Point p){
        this.x = p.x - 10;
        this.y = p.y - 10;

        this.pos.x = this.x;
        this.pos.y = this.y;
    }

    public void desenha(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, 20, 20);
    }

}

class Circulo implements IDesenhavel{
    Point pos = new Point();
    int x2 = 0;
    int y2 = 0;

    public Circulo(int x, int y) {
        this.pos.setLocation(x, y);
    }

    public void desenha(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(this.pos.x-10, this.pos.y-10, 20, 20);
        g.setColor(Color.GREEN);
		g.drawLine(this.pos.x-10, this.pos.y-10, x2, y2);
    }

	public void segue(Point p) {
        this.x2 = p.x;
        this.y2 = p.y;

        double dx = p.x - pos.x;
        double dy = p.y - pos.y;
        double mag = Math.sqrt(dx*dx + dy*dy);

        dx = dx*5/mag;
        dy = dy*5/mag;

        if (mag < 3){
            pos.x = pos.x;
            pos.y = pos.y;
        }
        else{
            pos.x += dx;
            pos.y += dy;
        }

        System.out.println(mag);
	}
}

class Mapa implements IDesenhavel{
    public void desenha(Graphics g){
        g.fillRect(0,0,400,400);
    } 
}