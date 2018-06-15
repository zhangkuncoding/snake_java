
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
 * ��̨
 */
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//import com.tarena.test.Stage;

public class WormStage extends JPanel{
	/**����*/
	public static final int ROWS = 35;
	/**����*/
	public static final int COLS = 35;
	/**���Ӵ�С  10������*/
	public static final int CELL_SIZE = 10;
		
	private Worm worm;
	private Cell food;
	
	public WormStage(){//������ ����ʳ����ߵ�ʵ��
		worm = new Worm();
		food = createFood();
	}
	/**����һ��ʳ��
	 * 1.���������x,y
	 * 2.������Ƿ����x,y
	 * 	 2.1 ������������� 1
	 * 3.����ʳ��ڵ�
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
		//��䱳��ɫ
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		//����ʳ��
		g.setColor(Color.RED);
		g.fill3DRect(CELL_SIZE*food.getX(), CELL_SIZE*food.getY(),
				     CELL_SIZE, CELL_SIZE, true);
		//������
		g.setColor(Color.GREEN);
		Cell cells[] = worm.getCells();
		for(int i=0;i<cells.length;i++){
			Cell node = cells[i];
			g.fill3DRect(CELL_SIZE*node.getX(), CELL_SIZE*node.getY(),
				         CELL_SIZE, CELL_SIZE, true);
		}
	}
	
	public static void main(String args[]){
		//�������  WormStage.java
		JFrame frame = new JFrame("̰����");
		WormStage pane = new WormStage();//���
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ��֣�ȡ���Զ�����
		frame.add(pane);//������������
		pane.setSize(CELL_SIZE*COLS,CELL_SIZE*ROWS);//����С
		pane.setLocation(50, 50);//���λ��
		frame.setSize(10*45,10*48);//���ô��ڴ�С
		pane.setBorder(new LineBorder(Color.black));//��ӱ߿�
		frame.setLocationRelativeTo(null);//ʹframe����
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�ر����
		pane.action();//�����ߵ�����
	}
	
	public void action(){
		//worm.creep(food);//��һ��
		//repaint();//swing JPanel �������ķ������ᾡ�������������ػ湦��
		                                    //���������paint(g)����
		Timer timer = new Timer();
		timer.schedule(new TimerTask(){
			public void run(){
				//���п����߼�
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
		}, 0,1000/7);//7��֮һ��
		//this ���ǵ�ǰ��̨������
		this.requestFocus();
		this.addKeyListener(new KeyAdapter(){//KeyListener ��һ���ӿ�
			//�ڰ������µ�ʱ��ִ�еķ���
			public void keyPressed(KeyEvent e) {
				//key �����ĸ�����������
				int key = e.getKeyCode();
				switch(key){
				case KeyEvent.VK_UP://�ϼ�ͷ���£�
					creepTo(Worm.UP);
					break;
				case KeyEvent.VK_DOWN://�¼�ͷ���£�
					creepTo(Worm.DOWN);
					break;
				case KeyEvent.VK_LEFT://���ͷ���£�
					creepTo(Worm.LEFT);
					break;
				case KeyEvent.VK_RIGHT://�Ҽ�ͷ���£�
					creepTo(Worm.RIGHT);
					break;
				}
			}
		} );//addKeyListener	
	}//action()
	/**���п��Ʒ������ڰ�������ʱ�����*/
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