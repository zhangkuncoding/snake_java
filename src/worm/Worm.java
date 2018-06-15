package worm;

import java.util.Arrays;
/**
 * ̰����
 */
public class Worm {
	public static final int DEFAULT_LENGTH = 12;
	private Cell cells[];
	
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	/**�ߵ�ǰ���з���*/
	private int currentDirection;
	/**��  ������*/
	
	public Worm(){//
		cells = new Cell[DEFAULT_LENGTH];
		for(int i=0;i<cells.length;i++){//������ʼĬ���ߵ�λ��
			cells[i] = new Cell(i,0);
		//�ȼ��� cells = new Cell[]{new Cell(0,0),new Cell(1,0),.....}
		}
		currentDirection = DOWN;//Ĭ��������
	}
	/**�������Ƿ����ʳ����㷨*/
	public boolean contains(int x,int y){
		for(int i=0;i<cells.length;i++){
			Cell node = cells[i];
			if(node.getX()==x && node.getY()==y){
				return true;
			}
		}
		return false;
	}
	/**
	 * �Ե�ʳ����δ�Ե�ʳ����ߵ��ƶ����
	   1) ����currentDirection��direction�ĺͣ�
	                �����0��ʾ�����ˣ��ͽ����������أ��������κζ���
	   2)currentDirection = direction �ı䵱ǰ�ķ���
	             ��Ϊ�´����еķ���
	   3)�жϵ�ǰͷ�ڵ��������ʳ����������һ��
	             ���һ��˵���ǳԵ�ʳ����:
	   4)����Ե�ʳ��ͽ�cel1s�����������
	             ��cells�������ݵ�ÿ��Ԫ������ƶ�.
	   5)����ͷ�ڵ�����ͷλ��cells[0]=newHead
	   6)�����Ƿ�Ե�ʳ��
	*/
	public boolean creep(int direction,Cell food){
		if(currentDirection + direction == 0){
			return false;//�����ˣ��������κζ���
		}
		currentDirection = direction;//�ѵ�ǰ�����Ϊ�����ķ���
		Cell head = createHead(direction);
		boolean eat = head.getX()==food.getX()&&
				      head.getY()==food.getY();
	    // boolean eat = false;
	    //if(head.getX()==food.getX()&&head.getY()==food.getY()){
	    //	eat = true;
	    //}
		if(eat){
			cells = Arrays.copyOf(cells,cells.length+1);
		}
		for(int i=cells.length-1;i>=1;i--){
			cells[i] = cells[i-1];
		}
		cells[0] = head;
		return eat;
	}
	public boolean hit(){
		return hit(currentDirection);
	}
	/**����Ƿ�ײ*/
	public boolean hit(int direction){//ײǽ
		//System.out.println("����(2):"+direction);
		Cell head = createHead(direction);
		//System.out.println(head);
		if(head.getX()<0 || head.getX()>=WormStage.COLS ||
		   head.getY()<0 || head.getY()>=WormStage.ROWS){
			return true;
		}
		for(int i=0;i<cells.length-1;i++){//ײ�Լ�
			Cell node = cells[i];
			if(node.getX()==head.getX() && 
			   node.getY()==head.getY()){
				return true;
			}
		}
		return false;
	}
	
	public boolean creep(Cell food){//����һ��
		return creep(currentDirection,food);
	}
	
	/**�����㷨*/
	public void creep(){
		for(int i=cells.length-1;i>=1;i--){
			cells[i] = cells[i-1];
		}
		cells[0] = createHead(currentDirection);
	}
	/**�ڵ㹹����      ����һ��ͷ�ڵ㣬����ĳ������*/
	private Cell createHead(int direction){
		int x = cells[0].getX();
		int y = cells[0].getY();
		switch(direction){ 
		case DOWN:  y++; break;
		case UP:    y--; break;
		case LEFT:  x--; break;
		case RIGHT: x++; break;
		}
		return new Cell(x,y);
	}
	
	public Cell[] getCells(){
		return cells;
	}
	
	public String toString(){
		return Arrays.toString(cells);
	}
}
