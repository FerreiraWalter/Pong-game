import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Paint extends JFrame implements ActionListener {
    // CRIANDO VARIÁVEIS
    Font big = new Font("Digiface", Font.BOLD, 70);
    Grafico gr;
    Timer time;
    int bx = 50, by = 50, velx = 8, vely = 8, dir = 1;
    int px = 10, py = 100, iax = 750, iay = 100, plScore = 0, iaScore = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        jogar();
    }

    public void jogar() {
        bx += velx;
        by += vely;

        if(bx > 705) {
            velx *= -1;
        }
        if(bx < 60 && bx > 50 && by > py && by < py + 100) {
            velx *= -1;
        }
        //BATER NO TETO OU NO CHÃO  
        if(by > 500 || by <= 40) {
            vely *= -1;
        }
        //DEIXOU A BOLINHA PASSAR
        if(bx < -40) {
            bx = 300; 
            velx = 8;
            iaScore++;
        }
        //FUNCIONAMENTO DA IA
        if(bx > 400) {
            if(by > iay + 50 && iay < 420) {
                iay += 8;
            }
            if(by < iay + 50 && iay > 40) {
                iay -= 8;
            }
        }

        gr.repaint();

        if(dir == 1 && py > 40) {
            py -= 7;
        }
        else if(dir == 2 && py < 420) {
            py += 7;
        }

    }   

    class Grafico extends JPanel {

        private static final long serialVersionUID = 1L;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);

            // CRIANCO NOSSOS OBJETOS
            Graphics2D bar0 = (Graphics2D) g;
            Graphics2D bar1 = (Graphics2D) g;
            Graphics2D bar2 = (Graphics2D) g;
            Graphics2D play = (Graphics2D) g;
            Graphics2D iyah = (Graphics2D) g;
            Graphics2D ball = (Graphics2D) g;
            Graphics2D scor = (Graphics2D) g;
            scor.setFont(big);

            bar0.setColor(Color.WHITE);
            bar1.setColor(Color.WHITE);
            bar2.setColor(Color.WHITE);
            play.setColor(Color.WHITE);
            iyah.setColor(Color.WHITE);
            ball.setColor(Color.WHITE);

            scor.drawString(plScore + "     " + iaScore, 310, 100);
            // POSIÇÕES FIXAS DOS OBJETOS
            bar0.fill(new Rectangle2D.Double(40, 20, 700, 20));
            bar1.fill(new Rectangle2D.Double(40, 520, 700, 20));
            bar2.fill(new Rectangle2D.Double(400, 20, 5, 500));
            play.fill(new Rectangle2D.Double(40, py, 20, 100));
            iyah.fill(new Rectangle2D.Double(720, iay, 20, 100));
            ball.fill(new Rectangle2D.Double(bx, by, 15, 15));
        }
    }

    private static final long serialVersionUID = 1L;

    //FUNÇÃO PARA CRIAÇÃO DA JANELA
    public void Janela() {
        setTitle("Pong");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        gr = new Grafico();
        add(gr);
        time = new Timer(2, this);
        time.start();
    }

    public void control() {
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { 
                //    
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == 38 && py > 40) {
                    dir = 1;
                } else if(e.getKeyCode() == 40 && py < 420) {
                    dir = 2;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                dir = 0;
            }
        });
    }

    public static void main(String[] main) {
        
        Paint paint = new Paint();
        paint.Janela();
        paint.control();
    }
}
