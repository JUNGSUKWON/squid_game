package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import exception.FoolException;

public class HoljjackGame {

	private boolean isSuccess;//�������������� �Ѿ������
	private int usermarble ;//������ ����
	private int bet;//������ ���� ����
	private int oilbet;//���ϳ��� ���ü�
	private int cpumarble;//���ϳ� ����
	private int totalmarble;//�� ������ ����
	
	//Ȧ���� ¦���� �� ���ϱ�
	private String userDab;
	private String cpuDab;

	//�Է¹ޱ� ����
	Scanner sc ;
	Random rd = new Random();

	//��� ���� ó���� ���Ѵ�.
	public HoljjackGame(int usermarble,int cpumarble) {

		this.usermarble=usermarble;
		this.cpumarble=cpumarble;
		this.totalmarble=usermarble+cpumarble;
		sc = new Scanner(System.in);//�ν��Ͻ��� ���鶧 ��ĳ�ʵ� ����
	}

	public void intro() {
		System.out.println("��¡�� ���ӿ� ���Ű��� ȯ���մϴ�.");
		System.out.println("���������� �����ϰڽ��ϴ�.");
		// ��¡�� ������ ��������(Ȧ¦)
		// ������ ������ 10���� ������ ���ӽ���
		// ������ ������ �ϰ� Ȧ���� ¦����
		// ���߸� ������ ��� Ʋ���� ������ �Ҵ´�
		// 10���� �� ������ �װ�
		// ������ ���� 10���� �� ���� ���� ��������
		System.out.println(usermarble+ "���� ������ �����մϴ�.");
		System.out.println("������ ������ �� ȹ���ϸ� ���");
		System.out.println("����� ������ �� ������ ��!");
	}

	//���� �޼ҵ�
	public void betting() throws Exception{//���÷���
		//		boolean isRet = true;
		System.out.println("[���ϳ�]�ڳ� ��� �ɰڳ�?: ");//���ϳ�
		//		System.out.println("[������]���� ���� ������ "+usermarble+ "�� �̱���..");
		bet=0;
		bet = sc.nextInt();


		if(usermarble<bet) {
			throw new FoolException();//���� ����ó�� (���� �߻���Ŵ)
		}

	}
	public void betLoop() {
		//������ �ϰ�
		while(true) {
			try {
				betting();
				break;
			}catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է��� �����մϴ�.");
				sc.nextLine();
			} catch(FoolException e) {
				System.out.println("����� ���� ������ �����ϴ�. �ٽ� �Է��ض�");
			} catch (Exception e) {
				// InputMismatchException->���ڸ� �Է��ؾ� �Ǵµ� ���ڰ� ���ͼ� ����
				// FoolException->�츮�� ���� ����(���� �������� ���� ������ ����)
				e.printStackTrace();
			}
		}
	}

	//���� �ݺ�
	public void gameLoop() {//������ �ϱ� ���� ����

		while(true) {
			//�������� ���ߴ� ����
			sung();
			if(cpumarble>=totalmarble) {
				System.out.println("��@@");
				isSuccess = false;
				break;
			}

			if(usermarble>=totalmarble) {
				System.out.println("���� ����������");
				isSuccess=true;
				break;
			}

			//���ϳ��� ���ߴ�����
			oil();
			if(cpumarble>=totalmarble) {
				System.out.println("��@@");
				isSuccess = false;
				break;
			}

			if(usermarble>=totalmarble) {
				System.out.println("���� ����������");
				isSuccess=true;
				break;
			}
		}
	}

	private void sung() {
		betLoop();
		System.out.println("[���ϳ�]��... �� ������ Ȧ�ϱ� ¦�ϱ�?");//���ϳ�
		userDab = sc.next();
		if((!userDab.equals("Ȧ"))&&(!userDab.equals("¦"))){
			System.out.println("Ȧ ¦ �� �ϳ��� ���ϰ�!");
			sung();
		}
		System.out.println("[������] "+userDab+"... ������..?");

		cpuDab ="Ȧ";
		// ��ǻ�Ͱ� Ȧ���� ¦������ ���� ������ ������
		int cpu=rd.nextInt(2)+1;
		if(cpu==1) {//1�ΰ�� Ȧ��
			cpuDab="Ȧ";

		}else if(cpu==2){
			cpuDab="¦";
		}
		if(cpuDab.equals(userDab)) {
			System.out.println("[���ϳ�]����� ���� �� �����̾�..");//���ϳ�
			if(cpumarble>=bet) {//�����Ѱ������� ���� ������ ������� �����Ѱ� ���λ��� ������°��
				usermarble+= bet;
				cpumarble-=bet;
			}
			else {
				usermarble+= cpumarble;
				cpumarble=0;

			}
		}else{//�����ѻ���� �����ʿ����.
			System.out.println("[���ϳ�]������ �����̰屺 �ڳ� ���� �ְ���..");//���ϳ�
			usermarble-= bet;
			cpumarble+=bet;

		}
		System.out.println("���ϳ��� ���� ���������� "+" "+cpumarble);
		System.out.println("������ ���� ������ ������.."+ usermarble);
	}


	private void oil() {//���ϳ��� ������ ����
		System.out.print("[���ϳ�]�� ���� �ڳװ� ������ �������Գ�..");
		int sung = rd.nextInt(2)+1;//�������� ������ ����

		//�������� ��� ���� �������
		int sungInput =0;
		try {
			sungInput =sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("���ڸ� �Է��� �����մϴ�.");
			sc.nextLine();
			oil();
		}
		if(sungInput>usermarble) {
			System.out.println("������ �����մϴ� �ٽ� ���ϼ���!");
			oil();
		}

		if(sungInput%2==1) {//Ȧ���ϰ��
			userDab="Ȧ";

		}else {
			userDab="¦";
		}
		int cpuChoice = rd.nextInt(2)+1;
		if(cpuChoice==1) {//1�ΰ�� Ȧ��
			cpuDab="Ȧ";

		}else if(cpuChoice==2){
			cpuDab="¦";
		}

		oilbet=rd.nextInt(cpumarble)+1;
		System.out.println("[���ϳ�]���� "+oilbet+"���� �ɰڳ�..");
		System.out.println("[���ϳ�]�ڳ� ������ "+cpuDab+" �̾�..");

		if(userDab.equals(cpuDab)) {
			System.out.println("[������]���߼̾��..���� �� �����Դϴ�..");//�������� ����
			if(usermarble>=oilbet) {

				cpumarble+=oilbet;
				usermarble-= oilbet;
			}
			else {
				cpumarble+=usermarble;
				usermarble=0;
			}
		}else{
			System.out.println("[������]Ʋ�Ƚ��ϴ�. ������ ���� �ֽðھ��?");//������
			usermarble+= oilbet;
			cpumarble-=oilbet;
		}
		
		System.out.println("���ϳ��� ���� ���������� "+" "+cpumarble);
		System.out.println("�������� ���� ������ ������.."+ usermarble);
	}


	public boolean getIsSucess() {

		return isSuccess;
	}
}

