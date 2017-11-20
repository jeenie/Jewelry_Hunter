
import java.util.Arrays;
import java.util.Scanner;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 

public class GameMain extends JFrame { 
	public JPanel GameGround; 
	public JPanel GameMessage; 


	//������Ʈ ��ü ���� 
	public Player User;
	public Rock[] Rocks; 

	//GUI�� ���� JLabel���� 
	public 	JLabel UserLabel, RockLabel[]; 
	public JLabel UserInfo, RockInfo; 
	public JTextField UserLocation; 
	public JButton exit; 


	GameMain(){ 
		//Frame ���� 
		Scanner s = new Scanner(System.in);
		setTitle(GameMap.strGameTitle); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null); 

		GameGround = new JPanel(); 
		GameMessage = new JPanel(); 
		GameGround.setLayout(null); 
		GameMessage.setLayout(null); 

		GameGround.setBounds(0,0,GameMap.MAX_WIDTH,GameMap.HEIGHT); 
		System.out.printf("GameGround: %d, %d, \n", GameGround.getWidth(), GameGround.getHeight()); 

		GameMessage.setBounds(0,GameMap.HEIGHT,GameMap.MAX_HEIGHT,80); 
		System.out.printf("GameMessage: %d, %d, \n", GameMessage.getWidth(), GameMessage.getHeight()); 

		GameGround.setBackground(Color.WHITE); 
		GameMessage.setBackground(Color.GRAY); 

		setSize(GameMap.MAX_WIDTH,GameMap.MAX_HEIGHT); 

		//���ΰ� ��ü ���� 
		User= new Player("�÷��̾�",0,0); 
		System.out.printf("%s�� �ʱ� ��ġ�� (%d, %d) �Դϴ�. \n", User.name, User.getX(), User.getY()); 
		//	System.out.printf("���� ����: ", User.Score);

		//���ΰ� JLabel ��ü ���� �� Frame�� Add 
		UserLabel= new JLabel(User.name); 
		UserLabel.setLocation(User.getX(),User.getY()); 
		UserLabel.setSize(GameObject.WIDTH,GameObject.HEIGHT); 
		UserLabel.setForeground(Color.BLUE); 
		GameGround.add(UserLabel); 

		//������ġ�� TextBox�� ��� 
		UserInfo= new JLabel("���� ��ġ: (0, 0)"); 
		UserInfo.setLocation(10,20); 
		UserInfo.setSize(150,20); 
		GameMessage.add(UserInfo); 

		//Rock ��ü ������ Console�� ��� ���
		Rocks = new Rock[Rock.RockNum];
		for(int i=0; i<Rock.RockNum; i++){
			Rocks[i] = new Rock(Rock.RockName[i],Rock.power);
			System.out.printf("%s�̻����Ǿ���, ��ġ��(%d, %d)�Դϴ�. \n",Rocks[i].name,
					//��ġ�H 200���� �׳� ��������
					(200+100*i)/100,(200+100*i)/100); 
		}

		//Rock JLable ��ü ���� �� Frame�� Add
		RockLabel = new JLabel[Rock.RockNum];

		for(int i=0; i<Rock.RockNum; i++){
			RockLabel[i] = new JLabel(Rocks[i].name);
			RockLabel[i].setLocation(200+100*i, 200+100*i);
			RockLabel[i].setSize(GameObject.WIDTH,GameObject.HEIGHT);
			RockLabel[i].setForeground(Color.GREEN);
			GameGround.add(RockLabel[i]);
		}


		//�����ư 
		exit = new JButton("����"); 
		exit.setLocation(400,15); 
		exit.setSize(80,30);  
		GameMessage.add(exit); 

		GameGround.addKeyListener(new GameKeyListener());	 
		exit.addActionListener(new GameActionListener()); 

		add(GameMessage); 
		add(GameGround); 

		setResizable(false); 
		setVisible(true); 
		GameGround.requestFocus(); 
	} 


	//KeyEvent 
	class GameKeyListener extends KeyAdapter{ 
		public void keyPressed(KeyEvent e){ 
			int keyCode = e.getKeyCode(); 
			switch(keyCode){ 
			case KeyEvent.VK_UP: User.move(0, -User.MOVING_UNIT); break; 
			case KeyEvent.VK_DOWN: User.move(0, +User.MOVING_UNIT); break; 
			case KeyEvent.VK_LEFT: User.move(-User.MOVING_UNIT, 0); break; 
			case KeyEvent.VK_RIGHT: User.move(+User.MOVING_UNIT, 0); break; 
			case KeyEvent.VK_TAB: User.power(+50);break;
			default: return;  
			} 
			UserLabel.setLocation(User.getX(),User.getY()); 
			for(int i =0; i<Rock.RockNum; ++i ){
				if((User.getX() == (200+100*i)) &&(User.getY() == (200+100*i))){
					System.out.println("������ ������ �� �����ϴ�.");
					break;
				}else{
					System.out.printf("%s�� (%d,%d)�� �̵��߽��ϴ�. \n", User.name, (User.getX()/100), (User.getY()/100)); 
					UserInfo.setText("���� ��ġ: (" + (User.getX()/100) +", " + (User.getY()/100) + ")"); 
				}
			}
			//�� �μ���
			for(int i=0; i<Rock.RockNum;i++){
				if(Rocks[i]!=null)
					if((User.getX() == (200+100*i))&&(User.getY() == (200+100*i))){
						int power = User.getPower() - Rocks[i].getPower();
						System.out.println(User .getPower());
						User.setPower(power);
						UserInfo.setText("����Ŀ�: "+User.getPower());
						System.out.println("���� �μ�����.");

						Rocks[i]=null;
						GameGround.remove(RockLabel[i]);
					}
			} 
		}	 
	}
	class GameActionListener implements ActionListener{ 
		public void actionPerformed(ActionEvent e){ 
			JButton b = (JButton)e.getSource(); 
			if(b.getText().equals("����")) 
				System.exit(0); 
		} 
	} 

	public static void main(String args[]){ 
		new GameMain(); 
	} 
} 


