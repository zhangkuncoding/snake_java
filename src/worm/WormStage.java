
package worm;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


import java.awt.event.KeyListener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;import javax.swing.JFrame;
/**
 * 舞台
 */
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//import com.tarena.test.Stage;

public class WormStage extends JPanel{
	/**行数*/
	public static final int ROWS = 35;
	/**列数*/
	public static final int COLS = 35;
	/**格子大小  10个像素*/
	public static final int CELL_SIZE = 10;
		
	private Worm worm;
	private Cell food;
	
	public WormStage(){//构造器 创建食物和蛇的实例
		worm = new Worm();
		food = createFood();
	}
	/**生成一个食物
	 * 1.生成随机数x,y
	 * 2.检查蛇是否包含x,y
	 * 	 2.1 如果包含，返回 1
	 * 3.创建食物节点
	 * */
	private Cell createFood(){
		int x;
		int y;
		Random r = new Random();
		do{
			x = r.nextInt(COLS);
			y = r.nextInt(ROWS);
		}while(worm.contains(x,y));
		return new Cell(x,y);
	}
	
	public String toString(){
		return "worm:"+worm+"\nfood"+food;
	}
	
	public void paint(Graphics g){
		//填充背景色
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		//绘制食物
		g.setColor(Color.RED);
		g.fill3DRect(CELL_SIZE*food.getX(), CELL_SIZE*food.getY(),
				     CELL_SIZE, CELL_SIZE, true);
		//绘制蛇
		g.setColor(Color.GREEN);
		Cell cells[] = worm.getCells();
		for(int i=0;i<cells.length;i++){
			Cell node = cells[i];
			g.fill3DRect(CELL_SIZE*node.getX(), CELL_SIZE*node.getY(),
				         CELL_SIZE, CELL_SIZE, true);
		}
	}
	
	public static void main(String args[]){
		//启动软件  WormStage.java
		JFrame frame = new JFrame("贪吃蛇");
		WormStage pane = new WormStage();//面板
		frame.setLayout(null);//取消窗口的默认布局，取消自动充满
		frame.add(pane);//窗口里添加面板
		pane.setSize(CELL_SIZE*COLS,CELL_SIZE*ROWS);//面板大小
		pane.setLocation(50, 50);//面板位置
		frame.setSize(10*45,10*48);//设置窗口大小
		pane.setBorder(new LineBorder(Color.black));//添加边框
		frame.setLocationRelativeTo(null);//使frame居中
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭软件
		pane.action();//启动蛇的运行
	}
	
	public void action(){
		//worm.creep(food);//爬一步
		//repaint();//swing JPanel 中声明的方法，会尽快的启动界面的重绘功能
		                                    //即尽快调用paint(g)方法
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				//爬行控制逻辑
				if(worm.hit()){
					worm = new Worm();
					food = createFood();
				}else{
					boolean eat = worm.creep(food);
					if(eat){
						food = createFood();
					}
				}
				repaint();
			}
		}, 0,1000/7);//7分之一秒
		//this 就是当前舞台面板对象
		this.requestFocus();
		this.addKeyListener(new KeyAdapter(){//KeyListener 是一个接口
			//在按键按下的时候执行的方法
			public void keyPressed(KeyEvent e) {
				//key 代表哪个按键被按下
				int key = e.getKeyCode();
				switch(key){
				case KeyEvent.VK_UP://上箭头按下！
					creepTo(Worm.UP);
					break;
				case KeyEvent.VK_DOWN://下箭头按下！
					creepTo(Worm.DOWN);
					break;
				case KeyEvent.VK_LEFT://左箭头按下！
					creepTo(Worm.LEFT);
					break;
				case KeyEvent.VK_RIGHT://右箭头按下！
					creepTo(Worm.RIGHT);
					break;
				}
			}
		} );//addKeyListener	
	}//action()
	/**爬行控制方法，在按键按下时候调用*/
	private void creepTo(int direction){
		if(worm.hit(direction)){
			worm = new Worm();
			food = createFood();
		}else{
			boolean eat = worm.creep(direction, food);
			if(eat){
				food = createFood();
			}
		}
		repaint();
	}
}//WormStage