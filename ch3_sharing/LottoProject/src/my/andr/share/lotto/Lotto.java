package my.andr.share.lotto;

import java.util.Random;

public class Lotto {
	private int[] values = new int[6]; // 6개의 값을 저장하기 위한 배열
	private Random ran = new Random(); // 임의의 값을 구해주는 클래스

	public void setValues() { // 중복되지 않은 임의의 값 6개를 구하여 저장.
		for (int i = 0; i < values.length; i++) {
			values[i] = uniqueValue(i);
		}
	}

	// 임의의 값 6개를 구하여 리턴한다.
	public StringBuilder getValues() {
		setValues(); // inputValues()를 호출하여 임의의 값 6개를 구한다.
		// 구해진 6개의 값을 StringBuilder에 담아서 결과값을 리턴
		StringBuilder sb = new StringBuilder();
		// values 배열에서 하나의 데이터를 꺼내어 s에 대입(Java5.0에서 추가)
		for (int s : values) { // values의 갯수만큼 반복처리 한다.
			sb.append(s + " ");
		}
		return sb;
	}

	// 중복되지 않은 임의의 값을 찾아서 리턴한다
	private int uniqueValue(int index) { // 현재 구하고자 하는 위치를 식별
		int imsi = 0;
		boolean eq = false; // 중복된 값 여부
		do {
			imsi = ran.nextInt(45) + 1; // 임의의 값을 구하여
			eq = false;
			// 존재하는 값과 중복되지 않는지를 비교
			for (int j = 0; j < index; j++) {
				if (imsi == values[j]) {
					eq = true;
					break;
				}
			}
		} while (eq); // 중복 되었다면 다시 do loop 수행
		return imsi; // 중복되지 않았다면 값 리턴
	}

}
