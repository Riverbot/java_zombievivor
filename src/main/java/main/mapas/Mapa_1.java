package main.mapas;

import java.awt.Graphics;
import main.interfaces.IDesenhavel;

public class Mapa_1 implements IDesenhavel{
    public Mapa_1() {
        super();
    }

    public void desenha(Graphics g){
        g.fillRect(0,0,400,400);
    } 
}