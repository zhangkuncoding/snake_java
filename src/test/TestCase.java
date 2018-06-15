package test;
import org.junit.Test;

import worm.Cell;
import worm.Worm;
import worm.WormStage;


/**
 * ���԰���
 */
public class TestCase {
	//@Test
	public void testWormInit(){
		System.out.println("����Worm������");
		Worm worm = new Worm();
		System.out.println(worm);
	}
	//@Test
	public void testWormContains(){
		System.out.println("����Worm�����㷨");
		Worm worm = new Worm();
		System.out.println(worm.contains(2, 0));
		System.out.println(worm.contains(2, 5));
	}
	//@Test
	public void testWormStage(){
		System.out.println("������̨ʵ��");
		WormStage stage = new WormStage();
		System.out.println(stage);
	}
	//@Test
	public void testCreep(){
		System.out.println("���в���");
		Worm worm = new Worm();
		System.out.println(worm);
		worm.creep();
		System.out.println(worm);
	}
	//@Test
	public void testCreepForFood(){
		System.out.println("���ʳ�������");
		Worm worm = new Worm();
		Cell food = new Cell(1,2);
		System.out.println(worm);//��ӡ�ߵ�λ��
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.DOWN,food));
		System.out.println(worm);
		System.out.println(worm.creep(Worm.RIGHT,food));
		System.out.println(worm);
	}
	@Test
	public void testHit(){
		System.out.println("ײ������");
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