package week1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class baseball {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 0~9까지의 숫자를 리스트에 추가
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numbers.add(i);
        }

        // 리스트를 섞어 무작위로 만듦
        Collections.shuffle(numbers);

        // 처음 3개의 숫자를 선택
        int num1 = numbers.get(0);
        int num2 = numbers.get(1);
        int num3 = numbers.get(2);

        // 선택된 숫자를 리스트로 저장
        ArrayList<Integer> target = new ArrayList<>();
        target.add(num1);
        target.add(num2);
        target.add(num3);

        // 게임 시작
        System.out.println("숫자 야구 시작!");
        boolean isCorrect = false;
        int count = 0;

        while (!isCorrect) {
            System.out.print("숫자 3개 입력(예: 123) : ");
            String input = sc.nextLine();

            // 입력된 숫자의 길이를 확인
            if (input.length() != 3) {
                System.out.println("잘못된 입력입니다. 숫자 3개를 정확히 입력해주세요.");
                continue; // 입력이 잘못되었으면 다시 입력받도록 함
            }

            // 숫자인지 확인
            boolean isValidInput = true;
            ArrayList<Integer> guess = new ArrayList<>();  // 사용자가 입력한 숫자를 저장할 리스트 선언
            HashSet<Integer> uniqueCheck = new HashSet<>();  // 입력된 숫자의 중복을 확인할 HashSet 선언

            for (int i = 0; i < 3; i++) {
                char ch = input.charAt(i);
                if (ch < '0' || ch > '9') {  // 각 문자가 0~9 사이에 있는지 확인
                    isValidInput = false;
                    break;
                }
                int num = Character.getNumericValue(ch);
                guess.add(num);  // 숫자인 경우, 해당 문자를 숫자로 변환하여 guess 리스트에 추가
                uniqueCheck.add(num);  // 중복 확인을 위해 HashSet에 숫자 추가
            }

            if (!isValidInput) {  // isValidInput가 false인 경우(=입력 문자열에 숫자가 아닌 문자가 포함된 경우)
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요.");
                continue; // 입력이 잘못되었으면 다시 입력받도록 함
            }

            // 입력 숫자 중복확인
            if (uniqueCheck.size() != 3) {  // HashSet의 크기가 3이 아닌 경우, 중복된 숫자가 있다는 의미
                System.out.println("잘못된 입력입니다. 중복되지 않은 숫자 3개를 입력해주세요.");
                continue; // 입력이 잘못되었으면 다시 입력받기
            }

            int strikes = 0;
            int balls = 0;

            // 스트라이크와 볼 계산
            for (int i = 0; i < 3; i++) {
                if (guess.get(i).equals(target.get(i))) {  // guess 리스트의 i번째 값과 target 리스트의 i번째 값이 같은지 확인
                    strikes++;
                } else if (target.contains(guess.get(i))) {  // target 리스트에 guess 리스트의 i번째 값이 존재하는지 확인
                    balls++;
                }
            }

            // 결과 출력
            System.out.println(strikes + " 스트라이크, " + balls + " 볼");

            count++;

            // 정답 확인
            if (strikes == 3) {
                isCorrect = true;
                System.out.println("정답!");
                System.out.println(count + "번 만에 맞히셨습니다!");
            }
        }
        sc.close();
    }
}
