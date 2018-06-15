package test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;//Frame 框架  相框
import javax.swing.JPanel;//Panel 面板
import javax.swing.border.LineBorder;//边框
/**
 * 显示窗口与绘图
 * 
 */
public class JFrameDemo {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame("窗口");//创建一个JFrame实例
		Stage pane = new Stage();//面板
		frame.setLayout(null);//取消窗口的默认布局，取消自动充满
		frame.add(pane);//窗口里添加面板
		pane.setSize(10*35,10*35);//面板大小
		pane.setLocation(50, 50);//面板位置
		frame.setSize(10*45,10*48);//设置框架大小
		pane.setBorder(new LineBorder(Color.black));//设置面板边框颜色
		frame.setLocationRelativeTo(null);//使frame居中
		frame.setVisible(true);//默认为不可见，这里设置为可见
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//退出应用程序后的默认窗口关闭操作
		
		//在Swing中如下代码可以实现对键盘事件的监听
		//也就是获得到底哪个键盘按键被按下了
		pane.requestFocus();//获取输入“焦点”
		//也就是是pane变成键盘输入的目标
		//继承KeyAdapter比实现KeyListener 更加简洁
		//在pane上添加键盘事件的监听，获得到底哪个键盘按键被按下了
		pane.addKeyListener(new KeyAdapter(){//KeyListener 是一个接口
			//在按键按下的时候执行的方法
			public void keyPressed(KeyEvent e) {
				System.out.println("HI"+e.getKeyCode());
			}
			
		/**
			pane.addKeyListener(new KeyListener(){//KeyListener 是一个接口
			
			//按下某个键时调用此方法
			public void keyPressed(KeyEvent e) {
				System.out.println("HI"+e.getKeyCode());
			}
			
			//释放某个键时调用此方法
			public void keyReleased(KeyEvent arg0) {
			}
			
			//键入某个键时调用此方法
			public void keyTyped(KeyEvent arg0) {
			}
			
			}
		*/
		} );
	}
}
class Stage extends JPanel{
	/**重写了    默认绘图方法*/
	public void paint(Graphics g){
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		g.fill3DRect(50, 50, 30, 20, true);//从x=50,y=50开始，30为x长，20为y长
		
	}
}