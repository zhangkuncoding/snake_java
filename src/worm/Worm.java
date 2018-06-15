package worm;

import java.util.Arrays;
/**
 * 贪吃蛇
 */
public class Worm {
	public static final int DEFAULT_LENGTH = 12;
	private Cell cells[];
	
	public static final int UP = 1;
	public static final int DOWN = -1;
	public static final int LEFT = 2;
	public static final int RIGHT = -2;
	
	/**蛇当前运行方向*/
	private int currentDirection;
	/**蛇  构造器*/
	
	public Worm(){//
		cells = new Cell[DEFAULT_LENGTH];
		for(int i=0;i<cells.length;i++){//创建初始默认蛇的位子
			cells[i] = new Cell(i,0);
		//等价于 cells = new Cell[]{new Cell(0,0),new Cell(1,0),.....}
		}
		currentDirection = DOWN;//默认向下跑
	}
	/**蛇身上是否包含食物的算法*/
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
	 * 吃到食物与未吃到食物的蛇的移动情况
	   1) 计算currentDirection与direction的和，
	                如果是0表示反向了，就结束方法返回，不进行任何动作
	   2)currentDirection = direction 改变当前的方向，
	             作为下次运行的方向
	   3)判断当前头节点的坐标与食物对象的坐标一致
	             如果一致说明是吃到食物了:
	   4)如果吃到食物，就将cel1s数组进行扩容
	             将cells数组内容的每个元素向后移动.
	   5)将新头节点插入的头位置cells[0]=newHead
	   6)返回是否吃到食物
	*/
	public boolean creep(int direction,Cell food){
		if(currentDirection + direction == 0){
			return false;//反向了，不进行任何动作
		}
		currentDirection = direction;//把当前方向改为给定的方向
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
	/**检查是否撞*/
	public boolean hit(int direction){//撞墙
		//System.out.println("方向(2):"+direction);
		Cell head = createHead(direction);
		//System.out.println(head);
		if(head.getX()<0 || head.getX()>=WormStage.COLS ||
		   head.getY()<0 || head.getY()>=WormStage.ROWS){
			return true;
		}
		for(int i=0;i<cells.length-1;i++){//撞自己
			Cell node = cells[i];
			if(node.getX()==head.getX() && 
			   node.getY()==head.getY()){
				return true;
			}
		}
		return false;
	}
	
	public boolean creep(Cell food){//重载一份
		return creep(currentDirection,food);
	}
	
	/**爬行算法*/
	public void creep(){
		for(int i=cells.length-1;i>=1;i--){
			cells[i] = cells[i-1];
		}
		cells[0] = createHead(currentDirection);
	}
	/**节点构造器      创建一个头节点，按照某个方向*/
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
