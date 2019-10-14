# 用java做出贪吃蛇-学习笔记
## 1.首先创建一个类，然后在这个类中引入JFrame对象，然后再设置网格大小和网格出现的位置,按退出键退出, 然后添加另一个类,让游戏窗口出现`.具体代码如下：
	JFrame frame = new JFrame();
	frame.setBounds(200,50,900,800);  //设置网格大小
    frame.setResizable(true);   //禁止改变大小
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //按退出键会怎样    frame.add(new MP());  //添加工具
    frame.setVisible(true);  //使窗口出现
 ## 2.第二步就是再创建一个类，然后再这个类中实现贪吃蛇的具体功能。第一步中添加这个类。具体代码如下：
     public class MP extends JPanel implements KeyListener, ActionListener{
    ImageIcon st = new ImageIcon("st.png");
    ImageIcon s = new ImageIcon("s.png");
    ImageIcon x = new ImageIcon("x.png");
    ImageIcon z = new ImageIcon("z.png");
    ImageIcon y = new ImageIcon("y.png");
    ImageIcon f = new ImageIcon("f.png");
    //开始定义变量
    int len =3;  //定义蛇的长度
    int[] snakex = new int[1000];
    int[] snakey = new int[1000];  //蛇的坐标的数组
     String fx ="Y";  //上下左右
    boolean sfstart = false;  //是否开始游戏
    boolean sffail = false;  //是否结束游戏
    Timer time = new Timer(100,this);  //时钟
    int foodx,foody;  //食物坐标
    int score= 0 ;  //分数
    Random r =new Random();  //设置随机值
	public MP() {
		initSnake(); //初始化
		this.setFocusable(true);  //设置焦点
		this.addKeyListener(this);  //添加键盘监听器
		time.start();  //启动时间
		
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
		snakex[0] = 50;  //蛇头
		snakey[2] = 50;
		snakey[1] = 50;
		snakey[0] = 50;	 //蛇头
		foodx = 0+25*r.nextInt(35);
		foody = 50+25*r.nextInt(28);
		fx = "Y"; //初始化方向
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
			repaint();  //改写显示
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
		// TODO 自动生成的方法存根
		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO 自动生成的方法存根
		}
	//时间重置
	@Override
	public void actionPerformed(ActionEvent e) {
		//  这里用的和视频不一样的方法
		 
		if(sfstart && !sffail) {
		for(int i=len-1;i>0;i--) {
			snakex[i] = snakex[i-1];
			snakey[i] = snakey[i-1];
		                          }	
        
            if(fx == "Y") { //右转
             snakex[0] = snakex[0]+25;  //蛇头的移动
                if(snakex[0] > 850) snakex[0] = 0;  //超出窗格后返回最左边
            }else if(fx == "X") { //下转
             snakey[0] = snakey[0]+25;
             if(snakey[0] > 800) snakey[0] = 50;
            }else if(fx == "Z") { //左转
                snakex[0] = snakex[0]-25;
                if(snakex[0] < 0) snakex[0] = 850;
            }else if(fx == "S") { //上转
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
## 3.学习到的有用的代码
* 1 .Timer time = new Timer(100,this); //引入时钟对象
* 2 . Random r =new Random();  //设置随机值
这里食物的出现就用到了这个，
	`foodx = 0+25*r.nextInt(35)；
	 foody = 50+25*r.nextInt(28)；` 这里`r.nextInt(35)`表示从0到35中随机选取一个数，达到食物出现的位置是随机的效果。
* 3 .initSnake(); //初始化
初始化内容： 

        public void initSnake() {
		snakex[2] = 0;
		snakex[1] = 25;
		snakex[0] = 50;  //蛇头横坐标
		snakey[2] = 50;
		snakey[1] = 50;
		snakey[0] = 50;	 //蛇头纵坐标
		foodx = 0+25*r.nextInt(35); //食物横坐标
		foody = 50+25*r.nextInt(28); //食物纵坐标
		fx = "Y"; //初始化方向
		len = 3; ？//蛇的初始长度
		score = 0; //初始分数
* 4 .this.addKeyListener(this);  //添加键盘监听器

       public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode == KeyEvent.VK_SPACE) {
			if(sffail) {
				sffail = false;  //是否失败
				initSnake();
			}else{
			sfstart = !sfstart; }
			repaint();  //改写显示
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
	