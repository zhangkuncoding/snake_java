package test;
import org.junit.Test;

import worm.Cell;
import worm.Worm;
import worm.WormStage;


/**
 * 测试案例
 */
public class TestCase {
	//@Test
	public void testWormInit(){
		System.out.println("测试Worm构造器");
		Worm worm = new Worm();
		System.out.println(worm);
	}
	//@Test
	public void testWormContains(){
		System.out.println("测试Worm包含算法");
		Worm worm = new Worm();
		System.out.println(worm.contains(2, 0));
		System.out.println(worm.contains(2, 5));
	}
	//@Test
	public void testWormStage(){
		System.out.println("创建舞台实例");
		WormStage stage = new WormStage();
		System.out.println(stage);
	}
	//@Test
	public void testCreep(){
		System.out.println("爬行测试");
		Worm worm = new Worm();
		System.out.println(worm);
		worm.creep();
		System.out.println(worm);
	}
	//@Test
	public void testCreepForFood(){
		System.out.println("检查食物的爬行");
		Worm worm = new Worm();
		Cell food = new Cell(1,2);
		System.out.println(worm);//打印蛇的位置
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.RIGHT,food));
		System.out.println(worm);
	}
	@Test
	public void testHit(){
		System.out.println("撞击测试");
		Worm worm = new Worm();
		Cell food = new Cell(10,10);
		System.out.println(worm);
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.hit(Worm.LEFT));
		System.out.println(worm.hit(Worm.RIGHT));
		System.out.println(worm.creep(Worm.RIGHT,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.RIGHT,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.UP,food));
		System.out.println(worm);
		System.out.println(worm.hit(Worm.UP));
	}
}