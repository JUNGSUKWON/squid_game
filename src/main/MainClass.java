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
		
		//홀짝 객체생성(인스턴스화)
		HoljjackGame hg = new HoljjackGame(10,10);//게임 참가자의 구슬의 갯수를 여기서 정한다 성기훈 오일남
		
		//인트로 나오게
		hg.intro();
		
		//배팅을 하고
		//게임 시작
		hg.gameLoop();
		
		//위의 결과에 따라
		// 다음 스테이지로 이동할지 게임오버가 될지는?
		isNext = hg.getIsSucess();
		
		if (isNext) {
			// 다음 스테이지
//			System.out.println("다음스테이지");
			BridgeGame bg = new BridgeGame();
			bg.setPlayer();//성기훈이 몇번을 정할지 결정 16번 이내여야한다.
			bg.setup();//다리의 강화유리 여부를 정한다.
			bg.play();
			bg.successMsg();
			isNext = bg.getIsSuccess();
		}
		
		if (isNext) {//다리게임의 결과
			// 다음 스테이지
			System.out.println("다음스테이지 인줄 알았는데 게임이 더이상 없네");
			System.out.println("끝!!");
		}
		
		if (!isNext) {
			// 게임 오버
			System.out.println("게임 오버");
		}

	}
}