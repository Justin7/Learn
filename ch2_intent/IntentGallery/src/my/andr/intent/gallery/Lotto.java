package my.andr.intent.gallery;

import java.util.Random;

public class Lotto {
	private int[] su = new int[6];

	// 임의의 값 6개 저장
	public void input() {
		for (int i = 0; i < su.length; i++) {
			su[i] = uniqueValue(i);
		}
	}

	// 배열 출력
	void print() {
		for (int s : su) {
			System.out.println(s);
		}
	}

	public String getValue() {
		input();
		StringBuilder sb = new StringBuilder();
		for (int s : su) {
			sb.append(s + " ");
		}
		return sb.toString();
	}

	// main()....
	public static void main(String[] args) {
		Lotto lo = new Lotto();
		lo.input();
		lo.print();
	}

	// 중복되지 않는 값 찾아서 반환
	public int uniqueValue(int index) {
		int imsi;
		boolean eq; // 중복된 값 여부
		Random ran = new Random();
		do {
			imsi = ran.nextInt(45) + 1;
			eq = false;
			for (int j = 0; j < index; j++) {
				if (imsi == su[j]) {
					eq = true;
					break;
				}
			}
		} while (eq);
		return imsi;
	}
}// end class
