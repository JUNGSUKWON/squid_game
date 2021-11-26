package main;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import game.BridgeGame;
import game.HoljjackGame;

public class MainClass {

	public static void main(String[] args)  {
//		long sss = (long) Math.pow(2, 18);
//		System.out.println(sss);
		
		boolean isNext = false;
		
		//Ȧ¦ ��ü����(�ν��Ͻ�ȭ)
		HoljjackGame hg = new HoljjackGame(10,10);//���� �������� ������ ������ ���⼭ ���Ѵ� ������ ���ϳ�
		
		//��Ʈ�� ������
		hg.intro();
		
		//������ �ϰ�
		//���� ����
		hg.gameLoop();
		
		//���� ����� ����
		// ���� ���������� �̵����� ���ӿ����� ������?
		isNext = hg.getIsSucess();
		
		if (isNext) {
			// ���� ��������
//			System.out.println("������������");
			BridgeGame bg = new BridgeGame();
			bg.setPlayer();//�������� ����� ������ ���� 16�� �̳������Ѵ�.
			bg.setup();//�ٸ��� ��ȭ���� ���θ� ���Ѵ�.
			bg.play();
			bg.successMsg();
			isNext = bg.getIsSuccess();
		}
		
		if (isNext) {//�ٸ������� ���
			// ���� ��������
			System.out.println("������������ ���� �˾Ҵµ� ������ ���̻� ����");
			System.out.println("��!!");
		}
		
		if (!isNext) {
			// ���� ����
			System.out.println("���� ����");
		}

	}
}