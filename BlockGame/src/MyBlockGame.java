
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


class GameOption{
	static int gameBoard = 1;
	static int num_stage = 1;
	static int ball_speed = 300;
	static int highScore = 0;
	static int score = 0;
	static boolean Failed = false;
	
	static Color bkColor1 = new Color(255,191,0);
	static Color bkColor2 = new Color(255,255,210);
	
	static Color textColor1 = new Color(50,200,50);
	static Color textColor2 = new Color(200,50,50);
	
	static Color sphere_color = Color.magenta;
	
	final static int WIDTH = 800;
	final static int HEIGHT = 800;
	final static int BAR_WIDTH = 20;
	
}

class TitlePanel extends JPanel implements KeyListener
{
	MyBlockGame jFrame;
	Clip clip;
	
	int width = 800;
	int height = 1200;
	
	TitlePanel(MyBlockGame jFrame){
		this.jFrame=jFrame;
		loadAudio("resources/Intro.wav");
		clip.setFramePosition(0);
		clip.start();
		
		setLayout(null);
		
		
		
		JLabel title1 = new JLabel("Java Programming");
		int title1_w = 800;
		int title1_h = 100;
		title1.setFont(new Font("Serif", 10, 60));

		title1.setBounds((width-title1_w)/2,100,title1_w,title1_h) ;
		title1.setForeground(GameOption.textColor1);
		title1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title1_shadow = new JLabel("Java Programming");
		title1_shadow.setFont(new Font("Serif", 10, 60));
		title1_shadow.setBounds((width-title1_w)/2+2,100+2,title1_w,title1_h) ;
		title1_shadow.setForeground(Color.black);
		title1_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
		
		JLabel title2 = new JLabel("Homework #5");
		int title2_w = 800;
		int title2_h = 100;
		title2.setFont(new Font("Serif", 10, 60));
		title2.setBounds((width-title2_w)/2,180,title2_w,title2_h) ;
		title2.setForeground(GameOption.textColor2);
		title2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title2_shadow = new JLabel("Homework #5");
		title2_shadow.setFont(new Font("Serif", 10, 60));
		title2_shadow.setBounds((width-title2_w)/2+2,180+2,title2_w,title2_h) ;
		title2_shadow.setForeground(Color.black);
		title2_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
		JLabel title3 = new JLabel("Block Breaker");
		int title3_w = 800;
		int title3_h = 100;
		title3.setFont(new Font("Stencil", 10, 100));
		title3.setBounds((width-title3_w)/2,340,title3_w,title3_h) ;
		title3.setForeground(Color.black);
		title3.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title3_shadow = new JLabel("Block Breaker");
		title3_shadow.setFont(new Font("Stencil", 10, 100));
		title3_shadow.setBounds((width-title3_w)/2+2,340+2,title3_w,title3_h) ;
		title3_shadow.setForeground(new Color(100,0,0));
		title3_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		PressLabel press = new PressLabel(
				"Press Spacebar to play!", 160);
		press.setBounds(250,550,title3_w,80) ;
		press.setHorizontalAlignment(JLabel.CENTER);
		
		add(title1);
		add(title1_shadow);
		
		add(title2);
		add(title2_shadow);

		add(title3);
		add(title3_shadow);
		
		add(press);
				
		addKeyListener(this);
		setFocusable(true);
		requestFocus();		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0,0,GameOption.bkColor1,
				0,jFrame.getHeight(),GameOption.bkColor2);
		g2.setPaint(gp);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
	}
	public void keyTyped(KeyEvent e) {	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			clip.close();
			System.out.println("keyPressed!");
			GameOption.gameBoard=2;		//생성자 자체는 한번만 실행되므로, 별개의 함수 호출 필요
			jFrame.switchScreen();
		}
	}
	public void keyReleased(KeyEvent e) {	}

	
	
	
	
	
	class PressLabel extends JLabel implements Runnable{
		Color color;
		int interval;
		PressLabel(String n, int inv){
			super(n);
			interval = inv;
			Thread t = new Thread(this);
			t.start();
		
		}
		@Override
		protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setFont(new Font("Stencil", 10, 25));
	        g2.setColor(Color.black); 
	        g2.drawString(getText(), 2, getHeight() - 10); // 음영 부분
	        g2.setColor(Color.red);
	        g2.drawString(getText(), 0, getHeight() - 12); // 실제 텍스트 부분
	        g2.dispose();
	    }
		
		
		public void run() {
			boolean bb = true;
			while(true) {
				if(bb)
					this.setVisible(false);
				else
					this.setVisible(true);
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					return;
				}
				bb = !bb;
			}		
		}
	}

	void loadAudio(String pathName) {	//어차피 패널에서 하나만 관리할 거 그냥 함수로 만들자(굳이 클래스로 X)
		try
		{
			clip = AudioSystem.getClip();	
			
			URL url = getClass().getClassLoader().getResource(pathName);
			//클래스패스 기준 : 클래스패스란 src폴더 내부를 의미함.
			System.out.println(pathName);
			System.out.println(url);
			//FILE대신 URL객체 활용
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			
			
			clip.open(audioStream);
		}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
		//지원파일 형식 체크
		catch (IOException e) {e.printStackTrace();}	
		//얘네 3개는 알아서 위에 try구문에서 빨간밑줄 쳐지면서 catch문 추가할 수 있음
	}

}

class PlayingPanel extends JPanel implements Runnable
{
	MyBlockGame jFrame;
	LinkedList<MyObject> objs;
	LinkedList<MyBlock> blocks;
	
	Bar bar;
	int bar_w = 200;
	int bar_h = 20;
	boolean moveLeft=false;
	boolean moveRight=false;
	
	int left_x,left_y;
	int right_x, right_y;
	int side_w,side_h;
	
	int top_x, top_y;
	int top_w,top_h;
	
	Color wall_color;
	
	Thread t;
	Clip clip;
	
	PlayingPanel(MyBlockGame jFrame, int num_stage){
		this.jFrame=jFrame;
		setLayout(null);
		
		
		GameOption.score = 0;
		side_w = 20;
		side_h = jFrame.getHeight();
		top_w = jFrame.getWidth();
		top_h =side_w;
		
		left_x = 0;
		left_y = side_w+2;
		
		right_x=jFrame.getWidth()-side_w;
		right_y=side_w+2;
		
		top_x = 2;
		top_y = 2;
		
		wall_color = Color.lightGray;
		
		objs = new LinkedList<>();	//벽 추가
		objs.add(new MyWall(top_x,top_y,top_w-3,top_h,wall_color));
		objs.add(new MyWall(left_x+1,left_y+3,side_w,side_h,wall_color));
		objs.add(new MyWall(right_x-1,right_y+3,side_w,side_h,wall_color));
		
		objs.add(new MySphere(300,jFrame.getHeight()/2+100, 5, 1/3.0));	//공 추가
		MySphere.ball_cnt=1;

		
		
		//발판 추가
		bar = new Bar((jFrame.getWidth()-bar_w)/2,jFrame.getHeight()-100);
		//벽돌 추가
		blocks=new LinkedList<>();
		int interval=6;
		int bX,bY;
		int bW = (GameOption.WIDTH-GameOption.BAR_WIDTH * 2 -
				
				interval*(3*num_stage+1)) / (3*num_stage);
		
		
		int bH = (400 -
				interval*(3*num_stage))/
				(3*num_stage);
		
		int sumW= 3*num_stage*bW + 
				(3*num_stage-1)*interval;
		int startP = (GameOption.WIDTH-sumW)/2;
		
		Color bC;
		
		int bT = 0;
		
		for(int y =0 ;y<3*num_stage;y++)
		{
			for(int x=0;x<3*num_stage;x++)
			{
				bX =  startP + 
						x*bW+
						x*interval;
				bY = startP +
						y*bH+
						y*interval;
				
				if(num_stage>=5 && (x+y)%(int)(Math.random()*13+23)==0)
					bT = 2;
				else if((x+y)%(int)(Math.random()*13+3)==0 ||
						(num_stage == 1 && (x+y)%3==0))
					bT = 1;
				else
					bT = 0;
				
				blocks.add(new MyBlock(bX,bY,bW,bH,bT));
			}
		}
		
		
		
		t = new Thread(this);
		t.start();
				
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0,0,GameOption.bkColor1,
				0,jFrame.getHeight(),GameOption.bkColor2);
		g2.setPaint(gp);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		for(var o : objs)
			o.draw(g);
		
		for(var b:blocks)
			b.draw(g);
		
		bar.draw(g);
	}
	
	@Override
	public void run() {
		String str = "STAGE"+GameOption.num_stage;		//클리어 문구 띄우기
		MyBanner label = new MyBanner(str, Color.white);
		label.setBounds(0,500,800,200);
		
		String str_ = "STAGE"+GameOption.num_stage;		//클리어 문구 띄우기
		MyBanner label_ = new MyBanner(str_, Color.orange);
		label_.setBounds(0,500,802,202);
		this.add(label);
		this.add(label_);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			return;
		}
		this.remove(label);
		this.remove(label_);
		
		while(true) {
			
			if(moveLeft)
			{
				if(bar.x-5>GameOption.BAR_WIDTH)
					bar.x-=10;
				repaint();
			}
			if(moveRight)
			{
				if(bar.x+bar.w+5<GameOption.WIDTH-GameOption.BAR_WIDTH)
					bar.x+=10;
				repaint();
			}
			
			// 1. update
			for(var o : objs)
				o.update(0.016);
			
			
			
			// 2. resolve
			for(var o1: objs) 		//벽과의 충돌검사
			{
				for(var o2: objs) 
				{
					if(o1 == o2) continue;
					o1.resolve1(o2);
				}
			}
			
			
			int triple_flag = 0;	//블록과의 충돌검사 후 갱신
			MySphere triple_sphere=null;
			
			var it_obj = objs.iterator();
			while(it_obj.hasNext()) 	
			{
				var o1=it_obj.next();
				if(o1 instanceof MySphere) 
				{
					MySphere o1_sphere = (MySphere)o1;
					var it = blocks.iterator();
					while(it.hasNext()) 
					{
						var b = it.next();
						if(b.isIn(o1_sphere)) 
						{
							loadAudio("resources/Break.wav");
							clip.setFramePosition(0);
							clip.start();
							
							o1_sphere.resolve2(b);
							
							if(b.triple!=0)
							{
								triple_flag = b.triple;
								triple_sphere=o1_sphere;
							}
							System.out.println(b);
							
							GameOption.score+=100;
							
							it.remove();
							repaint();
						}
					}
					
					if(o1_sphere.isDead()) 
					{			//공이 떨어졌을 경우
						MySphere.ball_cnt--;
						it_obj.remove();
						System.out.println(
								"ball_cnt : " + MySphere.ball_cnt +
								"\nScore : " + GameOption.score);
						
					}
				}
			}
			
			if(triple_flag==1)	//증식블록에 충돌했을 시
			{
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5,
						triple_sphere.angle + 1/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle - 1/12.0));	//공 추가
				MySphere.ball_cnt+=2;
				System.out.println(MySphere.ball_cnt);
			}
			if(triple_flag==2)	//증식블록에 충돌했을 시
			{
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5,
						triple_sphere.angle + 4/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle + 3/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle + 2/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle + 1/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle - 1/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle - 2/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle - 3/12.0));	//공 추가
				objs.add(new MySphere(triple_sphere.x, 
						triple_sphere.y, 
						5, 
						triple_sphere.angle - 4/12.0));	//공 추가
				MySphere.ball_cnt+=8;
				System.out.println(MySphere.ball_cnt);
			}
			
		
			
			for(var o1: objs) 		//발판과의 충돌검사
					o1.resolve3(bar);
			
			
			
			// 3. render
			repaint();
			
			
			
			
			if(MySphere.ball_cnt==0)	//게임 실패시
			{
				loadAudio("resources/Dead.wav");
				clip.setFramePosition(0);
				clip.start();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					return;
				}
				GameOption.Failed=true;
				System.out.println("Game Over ;(");
				
				if(GameOption.score>GameOption.highScore)
					GameOption.highScore=GameOption.score;
				GameOption.gameBoard = 3;
				GameOption.num_stage = 1;
				blocks.clear();
				objs.clear();
				
				
				jFrame.switchScreen();	//실패화면으로
				return;
			}
			
			synchronized(this)
			{
				if(blocks.size() == 0)
				{
					
					loadAudio("resources/Clear.wav");
					clip.setFramePosition(0);
					clip.start();
					
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						return;
					}
					
					System.out.println("You did it!");
					GameOption.num_stage++;
					blocks.clear();
					objs.clear();
					
					
					
					jFrame.switchScreen();	//다음 스테이지로
					return;
				}
			}
			
			
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				return;
			}
		}		
	}
	
	
	
	
	
	
	
	
	abstract class MyObject{
		MyObject(){
		}
		abstract public void draw(Graphics g);
		public void update(double dt) {};
		public void resolve1(MyObject o) {};
		public void resolve2(MyBlock b) {};
		public void resolve3(Bar B) {};
		public boolean isDead() { return false;}
	}

	class MyBanner extends JLabel{
		Color color;
		
		MyBanner(String str, Color color){
			super(str);
			this.color = color;
			setFont(new Font("Arial",10,120));
			this.setForeground(color);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	class MyScore extends JLabel{
		Color color;
		
		MyScore(String str, Color color){
			super(str);
			this.color = color;
			setFont(new Font("Arial",10,30));
			this.setForeground(color);
			setHorizontalAlignment(SwingConstants.CENTER);
		}
	}
	
	class MyWall extends MyObject{
		int x;
		int y;
		int width;
		int height;
		Color color;
		MyWall(int _x, int _y, int _w, int _h, Color c){
			x = _x;
			y = _y;
			width = _w;
			height = _h;
			color = c;
		}
		@Override
		public void draw(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			GradientPaint gp = new GradientPaint(0,0,Color.white,0,height,new Color(120,120,120));
			
			g.setColor(color);
			g.fillRect(x, y, width, height);
			
			g2.setStroke(new BasicStroke(3));
			g2.setPaint(gp);
			g.drawRect(x, y, width, height);
		}
		public boolean isIn(MySphere o) {
			double xmin = x - o.r;
			double xmax = x+width + o.r;
			double ymin = y - o.r;
			double ymax = y+height + o.r;
			
			
			if(o.x>xmin && o.x <xmax && o.y>ymin && o.y<ymax)
				return true;
			return false;
		}
		
	}

	class MyBlock extends MyObject{
		int x,y;
		int w,h;
		int triple;
		int alpha;
		Color color;
		Thread t;
		
		MyBlock(int x, int y, int w, int h, int triple)
		{	
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.triple= triple;
			alpha=255;
			
			
		}
		public void draw(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			GradientPaint gp;
			
			if(triple == 2)
				color=new Color(240,200,0,alpha);
			else if(triple==1)
				color=new Color(240,50,50,alpha);
			else
				color=new Color(0,200,100,alpha);
			
			
			if(triple==2)
				gp = new GradientPaint(x,y,new Color(255,255,255,alpha),
						x,y+h,new Color(150,100,0,alpha));
			else if(triple==1)
				gp = new GradientPaint(x,y,new Color(255,255,255,alpha),
						x,y+h,new Color(150,0,0,alpha));
			else
				gp = new GradientPaint(x,y,new Color(255,255,255,alpha)
						,x,y+h,new Color(0,255,0,alpha));
			g2.setPaint(gp);
			g2.setStroke(new BasicStroke(4));
			g2.drawRect(x, y, w, h);
			
			g.setColor(color);
			g.fillRect(x, y, w, h);
		}
		
		public boolean isIn(MySphere o) {
			double xmin = x - o.r;
			double xmax = x+w + o.r;
			double ymin = y - o.r;
			double ymax = y+h + o.r;
			
			if(o.x>xmin && o.x <xmax && o.y>ymin && o.y<ymax)
				return true;
			return false;
		}
		
	}

	class MySphere extends MyObject{
		double x;
		double y;
		double prevX;
		double prevY;
		double angle;
		double vx;
		double vy;
		double r;
		double age;
		static int ball_cnt=0;
		
		Color color;
		MySphere(double _x, double _y, double _r, double _a){
			age = 0;
			x = _x;
			y = _y;
			r = _r;
			
			prevX = x;
			prevY = y;
			
			angle = Math.PI*_a;
			
			vx = Math.cos(angle)*GameOption.ball_speed;
			vy = Math.sin(angle)*GameOption.ball_speed;
			
			double dv_x = GameOption.ball_speed*(GameOption.num_stage)*0.2;
			double dv_y = GameOption.ball_speed*(GameOption.num_stage)*0.2;
			
			if(vx<0)
				dv_x=-dv_x;
			if(vy<0)
				dv_y=-dv_y;
			
			vx+=dv_x;
			vy+=dv_y;
			
			
			System.out.println("vx! : " + vx);
			
			color = GameOption.sphere_color;
		}
		@Override
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillOval((int)(x-r), (int)(y-r), (int)(2*r), (int)(2*r));
		}
		@Override
		public void update(double dt) {
			prevX = x;
			prevY = y;
			x = x + vx * dt;
			y = y + vy * dt;
		}
		@Override
		public void resolve1(MyObject o) {	//벽과의 충돌
			if(o instanceof MyWall != true) return;
			
			MyWall w = (MyWall) o;
			if(w.isIn(this) == false) return;
			
			loadAudio("resources/Collision.wav");
			clip.setFramePosition(0);
			clip.start();
			
			double xmin = w.x - r;
			double xmax = w.x+w.width + r;
			double ymin = w.y - r;
			double ymax = w.y+w.height + r;
			
			if(prevX < xmin) {vx = -vx; x = xmin;}
			if(prevX > xmax) {vx = -vx; x = xmax;}
			if(prevY < ymin) {vy = -vy; y = ymin;}
			if(prevY > ymax) {vy = -vy; y = ymax;}		
		}
		
		public void resolve2(MyBlock b) {	//블록과의 충돌

			if(b.isIn(this) == false) return;
			
			
			double xmin = b.x - r;
			double xmax = b.x+b.w + r;
			double ymin = b.y - r;
			double ymax = b.y+b.h + r;
			
			if(prevX < xmin) {vx = -vx; x = xmin;}
			if(prevX > xmax) {vx = -vx; x = xmax;}
			if(prevY < ymin) {vy = -vy; y = ymin;}
			if(prevY > ymax) {vy = -vy; y = ymax;}
			
		}
		
		public void resolve3(Bar B) {	//발판과의 충돌

			if(B.isIn(this) == false) return;
			
			loadAudio("resources/Collision.wav");
			clip.setFramePosition(0);
			clip.start();
			
			double xmin = B.x - r;
			double xmax = B.x+B.w + r;
			double ymin = B.y - r;
			double ymax = B.y+B.h + r;
			
			if(prevX < xmin) {vx = -vx; x = xmin;}
			if(prevX > xmax) {vx = -vx; x = xmax;}
			if(prevY < ymin) {vy = -vy; y = ymin;}
			if(prevY > ymax) {vy = -vy; y = ymax;}		
		}
		@Override 
		public boolean isDead() {	//공이 바닥 아래로 떨어질 경우
			if(this.y>GameOption.HEIGHT)
				{
				System.out.println("Falled!");
				return true;
				}
			return false;
		}
	}
	
	class Bar extends MyObject implements KeyListener, Runnable{
		int x, y;
		int w, h;
		int center;
			
		Bar(int x, int y){
			this.x =x;
			this.y=y;
			
			this.w=bar_w;
			this.h=bar_h;
			
			center = x + bar_w/2;
			
			Thread t_bar = new Thread(this);
			t_bar.start();
			
			addKeyListener(this);
			setFocusable(true);
			requestFocus();	
		}
		public void draw(Graphics g)
		{
			Graphics2D g2 = (Graphics2D) g;
			GradientPaint gp = new GradientPaint(x,y,new Color(150,150,0),
					x, y + h,new Color(100,100,0));
			
			g.setColor(Color.orange);
			g.fillRect(x, y, bar_w, bar_h);
			
			g2.setPaint(gp);
			g2.setStroke(new BasicStroke(3));
			g2.drawRect(x,y,bar_w,bar_h);
			
		}
		
		
		@Override
		public void run() 
		{
			while(true) {
				
			}
		}
		
		public void keyTyped(KeyEvent e){}
		public void keyPressed(KeyEvent e) 
		{
			if (e.getKeyCode() == KeyEvent.VK_LEFT) 
				moveLeft = true;
			    

		    if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		        moveRight = true;
			    
			if(e.getKeyCode() == KeyEvent.VK_SPACE) {
				t.interrupt();
				GameOption.gameBoard=3;
				loadAudio("resources/Dead.wav");
				clip.setFramePosition(0);
				clip.start();
				
				if(GameOption.score>GameOption.highScore)
					GameOption.highScore=GameOption.score;
				GameOption.gameBoard = 3;
				GameOption.num_stage = 1;
				blocks.clear();
				objs.clear();
				
				jFrame.switchScreen();
				repaint();
			}
		}
		public void keyReleased(KeyEvent e)
		{
			if (e.getKeyCode() == KeyEvent.VK_LEFT) 
				moveLeft = false;
			    

		    if (e.getKeyCode() == KeyEvent.VK_RIGHT) 
		        moveRight = false;
			    
		}
		
		public boolean isIn(MySphere o) {
			double xmin = x - o.r;
			double xmax = x+w + o.r;
			double ymin = y - o.r;
			double ymax = y+h + o.r;
			
			
			if(o.x>xmin && o.x <xmax && o.y>ymin && o.y<ymax)
				return true;
			return false;
		}
	}
	
	void loadAudio(String pathName) {	//어차피 패널에서 하나만 관리할 거 그냥 함수로 만들자(굳이 클래스로 X)
		try
		{
			clip = AudioSystem.getClip();	
			
			URL url = getClass().getClassLoader().getResource(pathName);
			//클래스패스 기준 : 클래스패스란 src폴더 내부를 의미함.
			System.out.println(pathName);
			System.out.println(url);
			//FILE대신 URL객체 활용
			
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
			
			
			clip.open(audioStream);
		}
		catch (LineUnavailableException e) {e.printStackTrace();}
		catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
		//지원파일 형식 체크
		catch (IOException e) {e.printStackTrace();}	
		//얘네 3개는 알아서 위에 try구문에서 빨간밑줄 쳐지면서 catch문 추가할 수 있음
	}
	
}


class OverPanel extends JPanel implements KeyListener
{
	MyBlockGame jFrame;

	int width = 800;
	int height = 1200;
	
	OverPanel(MyBlockGame jFrame){
		this.jFrame=jFrame;
		
		setLayout(null);
		
		
		
		JLabel title1 = new JLabel("Game Over");
		int title1_w = 800;
		int title1_h = 100;
		int title1_x = (width-title1_w)/2;
		int title1_y = 240;
		
		title1.setFont(new Font("Stencil", 10, 120));

		title1.setBounds(title1_x,title1_y,title1_w,title1_h) ;
		title1.setForeground(Color.white);
		title1.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title1_shadow = new JLabel("Game Over");
		title1_shadow.setFont(new Font("Stencil", 10, 120));
		title1_shadow.setBounds(title1_x+2, title1_y+2,title1_w,title1_h) ;
		title1_shadow.setForeground(Color.black);
		title1_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		
		
		JLabel title2 = new JLabel("High Score: " + GameOption.highScore);
		int title2_w = 800;
		int title2_h = 50;
		int title2_x = (width-title2_w)/2;
		int title2_y = 420;
		title2.setFont(new Font("Stencil", 10, 40));
		title2.setBounds(title2_x, title2_y, title2_w,title2_h) ;
		title2.setForeground(Color.gray);
		title2.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title2_shadow = new JLabel("High Score: " + GameOption.highScore);
		title2_shadow.setFont(new Font("Stencil", 10, 40));
		title2_shadow.setBounds(title2_x+2, title2_y+2,title2_w,title2_h) ;
		title2_shadow.setForeground(Color.black);
		title2_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		JLabel title3 = new JLabel("Your Score: " + GameOption.score);
		int title3_w = 800;
		int title3_h = 50;
		int title3_x = (width-title3_w)/2;
		int title3_y = title2_y+title2_h;
		title3.setFont(new Font("Stencil", 10, 40));
		title3.setBounds(title3_x,title3_y,title3_w,title3_h) ;
		title3.setForeground(Color.gray);
		title3.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel title3_shadow = new JLabel("Your Score: " + GameOption.score);
		title3_shadow.setFont(new Font("Stencil", 10, 40));
		title3_shadow.setBounds(title3_x+2,title3_y+2,title3_w,title3_h) ;
		title3_shadow.setForeground(Color.black);
		title3_shadow.setHorizontalAlignment(JLabel.CENTER);
		
		
		
		PressLabel press = new PressLabel(
				"Press Spacebar!", 100);
		press.setBounds(250,title3_y+100,title3_w,80) ;
		press.setHorizontalAlignment(JLabel.CENTER);
		
		add(title1);
		add(title1_shadow);
		
		add(title2);
		add(title2_shadow);

		add(title3);
		add(title3_shadow);
		
		add(press);
				
		addKeyListener(this);
		setFocusable(true);
		requestFocus();		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0,0,GameOption.bkColor1,
				0,jFrame.getHeight(),GameOption.bkColor2);
		g2.setPaint(gp);
		g2.fillRect(0, 0, getWidth(), getHeight());
		
	}
	public void keyTyped(KeyEvent e) {	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
		{
			System.out.println("keyPressed!");
			GameOption.gameBoard=1;		//생성자 자체는 한번만 실행되므로, 별개의 함수 호출 필요
			jFrame.switchScreen();
		}
	}
	public void keyReleased(KeyEvent e) {	}

	
	
	
	
	
	class PressLabel extends JLabel implements Runnable{
		Color color;
		int interval;
		PressLabel(String n, int inv){
			super(n);
			interval = inv;
			Thread t = new Thread(this);
			t.start();
		
		}
		@Override
		protected void paintComponent(Graphics g) {
	        Graphics2D g2 = (Graphics2D) g.create();
	        g2.setFont(new Font("Stencil", 10, 40));
	        g2.setColor(Color.black); 
	        g2.drawString(getText(), 2, getHeight() - 10); // 음영 부분
	        g2.setColor(Color.red);
	        g2.drawString(getText(), 0, getHeight() - 12); // 실제 텍스트 부분
	        g2.dispose();
	    }
		
		
		public void run() {
			boolean bb = true;
			while(true) {
				if(bb)
					this.setVisible(false);
				else
					this.setVisible(true);
				
				try {
					Thread.sleep(interval);
				} catch (InterruptedException e) {
					return;
				}
				bb = !bb;
			}		
		}
	}

}









public class MyBlockGame extends JFrame{
	TitlePanel titlePanel;
	PlayingPanel playingPanel;
	OverPanel overPanel;
	
	MyBlockGame(){
		setSize(800,800);
		setTitle("Moving Balls");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		switchScreen();	//게임 시작
		
		setVisible(true);
		
			
	}
	
	public static void main(String[] args) {
		new MyBlockGame();
	}

	
	
	  public void switchScreen() 
	  {
	        switch(GameOption.gameBoard) {
	            case 1: //화면이 몇 개 안 되서 그냥 add-remove로 구현
	            	if(overPanel!=null)
	            		remove(overPanel);
	                
	                titlePanel = new TitlePanel(this);
	                add(titlePanel);		
	                //add와 remove는 꼭 생성자 안에서만 이루어지라는 법 없음.
	                //즉, 화면전환처럼 계속해서 뭔가 변화를 주고싶었다면 
	                //그냥 '클래스 내에서' 함수호출을 통해 변화를 줬으면 될 일임
	                titlePanel.requestFocusInWindow();
	                break;
	                
	            case 2:
	            	titlePanel.clip.close();
	                remove(titlePanel);
	                if(GameOption.num_stage!=1)
	                	remove(playingPanel);
	                
	                playingPanel = new PlayingPanel(this,GameOption.num_stage);
	                add(playingPanel);
	                playingPanel.requestFocusInWindow();
	                break;
	                
	            case 3:
	                remove(playingPanel);
	                
	                overPanel = new OverPanel(this);
	                add(overPanel);
	                overPanel.requestFocusInWindow();
	                break;
	                
	            default:
	                break;
	        }
	        revalidate();
	  }

}

