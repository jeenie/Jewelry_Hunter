import java.util.Random;

public  class Rock extends GameObject{
	//���� ������ ����
	public static final int RockNum=2;
	public static int x=200,y=200,power;
	//���� �̸��� ����
	public static String RockName[]={"��1","��2"};
	//���� �μ��� ��� ��� �ɷ�ġ���� ����
	public static int RockPower =100;
	
	public Rock(){
	}
	public Rock(String name){
		super.name = name;
		
	}
	
	public Rock(String name, int power){
		super.name=name;
		super.power=power;
		Random r = new Random();
//		this.x=200;
//		this.y=200;
//		this.x = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
//		this.y = (r.nextInt(GameMap.WIDTH-this.WIDTH/10*10));
	}
	
}
