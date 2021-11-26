package game;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import exception.FoolException;

public class HoljjackGame {

	private boolean isSuccess;//다음스테이지로 넘어갈지말지
	private int usermarble ;//성기훈 구슬
	private int bet;//성기훈 베팅 변수
	private int oilbet;//오일남의 배팅수
	private int cpumarble;//오일남 구슬
	private int totalmarble;//총 구슬의 갯수
	
	//홀인지 짝인지 답 정하기
	private String userDab;
	private String cpuDab;

	//입력받기 변수
	Scanner sc ;
	Random rd = new Random();

	//몇개를 쓸지 처음에 정한다.
	public HoljjackGame(int usermarble,int cpumarble) {

		this.usermarble=usermarble;
		this.cpumarble=cpumarble;
		this.totalmarble=usermarble+cpumarble;
		sc = new Scanner(System.in);//인스턴스를 만들때 스캐너도 생성
	}

	public void intro() {
		System.out.println("오징어 게임에 오신것을 환영합니다.");
		System.out.println("구슬게임을 진행하겠습니다.");
		// 오징어 게임의 구슬게임(홀짝)
		// 유저가 구슬을 10개를 가지고 게임시작
		// 유저가 베팅을 하고 홀인지 짝인지
		// 맞추면 구슬을 얻고 틀리면 구슬을 잃는다
		// 10개를 다 잃으면 죽고
		// 상대방의 구슬 10개를 다 따면 다음 스테이지
		System.out.println(usermarble+ "개의 구슬로 시작합니다.");
		System.out.println("상대방의 구슬을 다 획득하면 살고");
		System.out.println("당신의 구슬을 다 잃으면 팡!");
	}

	//베팅 메소드
	public void betting() throws Exception{//배팅루프
		//		boolean isRet = true;
		System.out.println("[오일남]자네 몇개를 걸겠나?: ");//오일남
		//		System.out.println("[성기훈]내가 가진 구슬은 "+usermarble+ "개 이구나..");
		bet=0;
		bet = sc.nextInt();


		if(usermarble<bet) {
			throw new FoolException();//내가 에러처리 (예외 발생시킴)
		}

	}
	public void betLoop() {
		//배팅을 하고
		while(true) {
			try {
				betting();
				break;
			}catch(InputMismatchException e) {
				System.out.println("숫자만 입력이 가능합니다.");
				sc.nextLine();
			} catch(FoolException e) {
				System.out.println("당신이 가진 구슬이 부족하다. 다시 입력해라");
			} catch (Exception e) {
				// InputMismatchException->숫자를 입력해야 되는데 문자가 들어와서 오류
				// FoolException->우리가 만든 오류(베팅 구슬보다 가진 구슬이 적다)
				e.printStackTrace();
			}
		}
	}

	//게임 반복
	public void gameLoop() {//게임을 하기 위한 루프

		while(true) {
			//성기훈이 맞추는 차례
			sung();
			if(cpumarble>=totalmarble) {
				System.out.println("빵@@");
				isSuccess = false;
				break;
			}

			if(usermarble>=totalmarble) {
				System.out.println("다음 스테이지로");
				isSuccess=true;
				break;
			}

			//오일남이 맞추는차례
			oil();
			if(cpumarble>=totalmarble) {
				System.out.println("빵@@");
				isSuccess = false;
				break;
			}

			if(usermarble>=totalmarble) {
				System.out.println("다음 스테이지로");
				isSuccess=true;
				break;
			}
		}
	}

	private void sung() {
		betLoop();
		System.out.println("[오일남]자... 내 구슬이 홀일까 짝일까?");//오일남
		userDab = sc.next();
		if((!userDab.equals("홀"))&&(!userDab.equals("짝"))){
			System.out.println("홀 짝 중 하나를 말하게!");
			sung();
		}
		System.out.println("[성기훈] "+userDab+"... 이지요..?");

		cpuDab ="홀";
		// 컴퓨터가 홀인지 짝인지를 먼저 문제를 만들어야
		int cpu=rd.nextInt(2)+1;
		if(cpu==1) {//1인경우 홀수
			cpuDab="홀";

		}else if(cpu==2){
			cpuDab="짝";
		}
		if(cpuDab.equals(userDab)) {
			System.out.println("[오일남]맞췄어 여기 내 구슬이야..");//오일남
			if(cpumarble>=bet) {//배팅한갯수보다 상대방 구슬이 많은경우 배팅한것 전부빼도 상관없는경우
				usermarble+= bet;
				cpumarble-=bet;
			}
			else {
				usermarble+= cpumarble;
				cpumarble=0;

			}
		}else{//배팅한사람은 따질필요없다.
			System.out.println("[오일남]흐흐흐 내가이겼군 자네 구슬 주개나..");//오일남
			usermarble-= bet;
			cpumarble+=bet;

		}
		System.out.println("오일남의 남은 구슬갯수는 "+" "+cpumarble);
		System.out.println("성기훈 남은 구슬의 갯수는.."+ usermarble);
	}


	private void oil() {//오일남이 맞히는 차례
		System.out.print("[오일남]자 이제 자네가 구슬을 움켜쥐어보게나..");
		int sung = rd.nextInt(2)+1;//성기훈이 움켜쥘 구슬

		//성기훈이 몇개를 움켜 쥘것인지
		int sungInput =0;
		try {
			sungInput =sc.nextInt();
		}
		catch(Exception e) {
			System.out.println("숫자만 입력이 가능합니다.");
			sc.nextLine();
			oil();
		}
		if(sungInput>usermarble) {
			System.out.println("구슬이 부족합니다 다시 정하세요!");
			oil();
		}

		if(sungInput%2==1) {//홀수일경우
			userDab="홀";

		}else {
			userDab="짝";
		}
		int cpuChoice = rd.nextInt(2)+1;
		if(cpuChoice==1) {//1인경우 홀수
			cpuDab="홀";

		}else if(cpuChoice==2){
			cpuDab="짝";
		}

		oilbet=rd.nextInt(cpumarble)+1;
		System.out.println("[오일남]나는 "+oilbet+"개를 걸겠네..");
		System.out.println("[오일남]자네 구슬은 "+cpuDab+" 이야..");

		if(userDab.equals(cpuDab)) {
			System.out.println("[성기훈]맞추셨어요..여기 제 구슬입니다..");//성기훈이 졌다
			if(usermarble>=oilbet) {

				cpumarble+=oilbet;
				usermarble-= oilbet;
			}
			else {
				cpumarble+=usermarble;
				usermarble=0;
			}
		}else{
			System.out.println("[성기훈]틀렸습니다. 영감님 구슬 주시겠어요?");//성기훈
			usermarble+= oilbet;
			cpumarble-=oilbet;
		}
		
		System.out.println("오일남의 남은 구슬갯수는 "+" "+cpumarble);
		System.out.println("성기훈의 남은 구슬의 갯수는.."+ usermarble);
	}


	public boolean getIsSucess() {

		return isSuccess;
	}
}

