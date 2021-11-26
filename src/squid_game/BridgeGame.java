package squid_game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


//앞선사람의 수만큼 유리가 강화유리인지 아닌지 정해진다.
//플레이어으 번호부터 시작한다.


public class BridgeGame {
	// 변수
	private ArrayList<Integer> bList;

	// 다리를 건너야될 스텝의 갯수 설정 변수
	private int step=18;

	//플레이어 번호
	private int playerNumber;
	// 끝까지 성공인 아닌지를 체크하기 위한 임시 변수
	private boolean isSuccess = true;

	Random rd = new Random();
	Scanner sc =new Scanner(System.in);

	// 하지만 내가 맘대로 사용해보자
	public BridgeGame() {

	}

	// 메소드
	// 다리를 건너야될 스텝의 값을 셋팅 하는 메소드
	public void setPlayer() {
		System.out.println("1번 부터 16번중 마음에 드는 숫자를 하나 정해주시기 바랍니다.");
		System.out.println("몇번으로 하시겠습니까?");
		playerNumber = sc.nextInt();
	}

	// 다리만들기
	public void setup() {
		// 오징어 게임의 다리 건너기
		// 건너야 될 다리가 18개가 있다
		// 18개의 다리를 만들자(1, 2) 강화유리인지 망한 유리인지 정해놓자
		bList = new ArrayList<Integer>();
		Random rnd = new Random();
		for (int i = 0; i < step; i++) {
			bList.add(rnd.nextInt(2) + 1);
		}
	}

	// 게임 플레이

	public void play() {
		//플레이어 넘버부터 시작한다 
		System.out.println("내가"+playerNumber+"번 이니깐 " +(step-playerNumber)+"칸 만 건너면 되는구나 ");
		for(int i=playerNumber;i<bList.size();i++) {
			System.out.println("[마음의소리]왼쪽으로 갈까 오른쪽으로 갈까..? 왼쪽(1) 오른쪽(2): ");
			int choice = sc.nextInt();
			if(choice!=bList.get(i)) {
				System.out.println("악!~~~");
				isSuccess=false;
				break;
			}
			else {
				System.out.println("휴 다행이다");
			}
		}
	}

	// 성공이냐 실패냐 확인 메소드
	public boolean getIsSuccess() {
		return isSuccess;
	}
	// 완료 메세지
	public void successMsg() {
		// 강화 유리를 선택을 안하면 생명 -1(생명의 갯수를 설정)
		// ... 마지막 18개 다리를 다 건너면 산다
		// 만약에 isSuccess 가 true 이면 산거고 아니면 죽은거다
		if (isSuccess) {
			System.out.println("휴~ 다리를 다 건넜네 다음 스테이지");
			isSuccess = true;
		} else {
			System.out.println("게임오버");
			isSuccess = false;
		}
	}
}