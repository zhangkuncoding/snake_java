package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;//Frame ���  ���
import javax.swing.JPanel;//Panel ���
import javax.swing.border.LineBorder;//�߿�
/**
 * ��ʾ�������ͼ
 * 
 */
public class JFrameDemo {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("����");//����һ��JFrameʵ��
		Stage pane = new Stage();//���
		frame.setLayout(null);//ȡ�����ڵ�Ĭ�ϲ��֣�ȡ���Զ�����
		frame.add(pane);//������������
		pane.setSize(10*35,10*35);//����С
		pane.setLocation(50, 50);//���λ��
		frame.setSize(10*45,10*48);//���ÿ�ܴ�С
		pane.setBorder(new LineBorder(Color.black));//�������߿���ɫ
		frame.setLocationRelativeTo(null);//ʹframe����
		frame.setVisible(true);//Ĭ��Ϊ���ɼ�����������Ϊ�ɼ�
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�˳�Ӧ�ó�����Ĭ�ϴ��ڹرղ���
		
		//��Swing�����´������ʵ�ֶԼ����¼��ļ���
		//Ҳ���ǻ�õ����ĸ����̰�����������
		pane.requestFocus();//��ȡ���롰���㡱
		//Ҳ������pane��ɼ��������Ŀ��
		//�̳�KeyAdapter��ʵ��KeyListener ���Ӽ��
		//��pane����Ӽ����¼��ļ�������õ����ĸ����̰�����������
		pane.addKeyListener(new KeyAdapter(){//KeyListener ��һ���ӿ�
			//�ڰ������µ�ʱ��ִ�еķ���
			public void keyPressed(KeyEvent e) {
				System.out.println("HI"+e.getKeyCode());
			}
			
		/**
			pane.addKeyListener(new KeyListener(){//KeyListener ��һ���ӿ�
			
			//����ĳ����ʱ���ô˷���
			public void keyPressed(KeyEvent e) {
				System.out.println("HI"+e.getKeyCode());
			}
			
			//�ͷ�ĳ����ʱ���ô˷���
			public void keyReleased(KeyEvent arg0) {
			}
			
			//����ĳ����ʱ���ô˷���
			public void keyTyped(KeyEvent arg0) {
			}
			
			}
		*/
		} );
	}
}
class Stage extends JPanel{
	/**��д��    Ĭ�ϻ�ͼ����*/
	public void paint(Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fill3DRect(50, 50, 30, 20, true);//��x=50,y=50��ʼ��30Ϊx����20Ϊy��
		
	}
}