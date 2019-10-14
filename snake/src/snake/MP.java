package snake;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class MP extends JPanel implements KeyListener, ActionListener{
   ImageIcon st = new ImageIcon("st.png");
   ImageIcon s = new ImageIcon("s.png");
   ImageIcon x = new ImageIcon("x.png");
   ImageIcon z = new ImageIcon("z.png");
   ImageIcon y = new ImageIcon("y.png");
   ImageIcon f = new ImageIcon("f.png");
   //��ʼ�������
   int len =3;  //�����ߵĳ���
   int[] snakex = new int[1000];
   int[] snakey = new int[1000];
    String fx ="Y";  //��������
    boolean sfstart = false;  //�Ƿ�ʼ��Ϸ
    boolean sffail = false;  //�Ƿ������Ϸ
    Timer time = new Timer(100,this);  //ʱ��
    int foodx,foody;  //ʳ������
    int score= 0 ;  //����
    Random r =new Random();  //�������ֵ
	public MP() {
		initSnake(); //��ʼ��
		this.setFocusable(true);  //���ý���
		this.addKeyListener(this);  //��Ӽ��̼�����
		time.start();  //����ʱ��
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		g.fillRect(0, 50, 900, 900);  //
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("score="+score, 750, 40);
		f.paintIcon(this, g,foodx, foody);
		if(fx == "Y"){
			y.paintIcon(this, g,snakex[0], snakey[0]);
		}else if(fx == "S") {
			s.paintIcon(this, g,snakex[0], snakey[0]);
		}else if(fx == "X") {
			x.paintIcon(this, g,snakex[0], snakey[0]);
		}else if(fx == "Z") {
			z.paintIcon(this, g,snakex[0], snakey[0]);
		}
		
		for(int i=len-1;i>0;i--)
		{
	    st.paintIcon(this, g, snakex[i], snakey[i]);	
		}
		if(sfstart == false) {
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,40));
		g.drawString("Press space to start", 250, 350);
		}
		if(sffail == true) {
			g.setColor(Color.red);
			g.setFont(new Font("arial",Font.BOLD,40));
			g.drawString("Wasted!Press space to restart", 175, 350);
			}
	}
	public void initSnake() {
		snakex[2] = 0;
		snakex[1] = 25;
		snakex[0] = 50;
		snakey[2] = 50;
		snakey[1] = 50;
		snakey[0] = 50;	
		foodx = 0+25*r.nextInt(35);
		foody = 50+25*r.nextInt(28);
		fx = "Y"; //��ʼ������
		len = 3;
		score = 0;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(sffail) {
				sffail = false;
				initSnake();
			}else{
			sfstart = !sfstart; }
			repaint();  //��д��ʾ
		}else if (keyCode == KeyEvent.VK_LEFT) {
			fx = "Z";
		}else if (keyCode == KeyEvent.VK_RIGHT) {
			fx = "Y";
		}else if (keyCode == KeyEvent.VK_UP) {
			fx = "S";
		}else if (keyCode == KeyEvent.VK_DOWN) {
			fx = "X";
		}
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO �Զ����ɵķ������
		}
	//ʱ������
	@Override
	public void actionPerformed(ActionEvent e) {
		//  �����õĺ���Ƶ��һ���ķ���
		 
		if(sfstart && !sffail) {
		for(int i=len-1;i>0;i--) {
			snakex[i] = snakex[i-1];
			snakey[i] = snakey[i-1];
		                          }	
        
            if(fx == "Y") { //��ת
             snakex[0] = snakex[0]+25;  //��ͷ���ƶ�
                if(snakex[0] > 850) snakex[0] = 0;  //��������󷵻������
            }else if(fx == "X") { //��ת
             snakey[0] = snakey[0]+25;
             if(snakey[0] > 800) snakey[0] = 50;
            }else if(fx == "Z") { //��ת
                snakex[0] = snakex[0]-25;
                if(snakex[0] < 0) snakex[0] = 850;
            }else if(fx == "S") { //��ת
                snakey[0] = snakey[0]-25;
                if(snakey[0] < 50) snakey[0] = 800;
            }
            if(snakex[0] == foodx && snakey[0] == foody) {
            	len++;
            	score = score+1;
            	foodx = 0+25*r.nextInt(35);
        		foody = 50+25*r.nextInt(29);
            }
            for(int i=len-1;i>0;i--) {
            if(snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
            	sffail = !sffail;
            }
            }
	repaint();
		}
	time.start();                                
	}
	}
