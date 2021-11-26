package squid_game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//�ռ������ ����ŭ ������ ��ȭ�������� �ƴ��� ��������.
//�÷��̾��� ��ȣ���� �����Ѵ�.


public class BridgeGame {
	// ����
	private ArrayList<Integer> bList;

	// �ٸ��� �ǳʾߵ� ������ ���� ���� ����
	private int step=18;

	//�÷��̾� ��ȣ
	private int playerNumber;
	// ������ ������ �ƴ����� üũ�ϱ� ���� �ӽ� ����
	private boolean isSuccess = true;

	Random rd = new Random();
	Scanner sc =new Scanner(System.in);

	// ������ ���� ����� ����غ���
	public BridgeGame() {

	}

	// �޼ҵ�
	// �ٸ��� �ǳʾߵ� ������ ���� ���� �ϴ� �޼ҵ�
	public void setPlayer() {
		System.out.println("1�� ���� 16���� ������ ��� ���ڸ� �ϳ� �����ֽñ� �ٶ��ϴ�.");
		System.out.println("������� �Ͻðڽ��ϱ�?");
		playerNumber = sc.nextInt();
	}

	// �ٸ������
	public void setup() {
		// ��¡�� ������ �ٸ� �ǳʱ�
		// �ǳʾ� �� �ٸ��� 18���� �ִ�
		// 18���� �ٸ��� ������(1, 2) ��ȭ�������� ���� �������� ���س���
		bList = new ArrayList<Integer>();
		Random rnd = new Random();
		for (int i = 0; i < step; i++) {
			bList.add(rnd.nextInt(2) + 1);
		}
	}

	// ���� �÷���

	public void play() {
		//�÷��̾� �ѹ����� �����Ѵ� 
		System.out.println("����"+playerNumber+"�� �̴ϱ� " +(step-playerNumber)+"ĭ �� �ǳʸ� �Ǵ±��� ");
		for(int i=playerNumber;i<bList.size();i++) {
			System.out.println("[�����ǼҸ�]�������� ���� ���������� ����..? ����(1) ������(2): ");
			int choice = sc.nextInt();
			if(choice!=bList.get(i)) {
				System.out.println("��!~~~");
				isSuccess=false;
				break;
			}
			else {
				System.out.println("�� �����̴�");
			}
		}
	}

	// �����̳� ���г� Ȯ�� �޼ҵ�
	public boolean getIsSuccess() {
		return isSuccess;
	}
	// �Ϸ� �޼���
	public void successMsg() {
		// ��ȭ ������ ������ ���ϸ� ���� -1(������ ������ ����)
		// ... ������ 18�� �ٸ��� �� �ǳʸ� ���
		// ���࿡ isSuccess �� true �̸� ��Ű� �ƴϸ� �����Ŵ�
		if (isSuccess) {
			System.out.println("��~ �ٸ��� �� �ǳԳ� ���� ��������");
			isSuccess = true;
		} else {
			System.out.println("���ӿ���");
			isSuccess = false;
		}
	}
}