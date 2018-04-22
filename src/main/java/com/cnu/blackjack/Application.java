package com.cnu.blackjack;

import java.util.Map;
import java.util.Scanner;

public class Application {

    private Map<String, Player> playerMap;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("게임을 시작 하시겠습니까? (1:시작, 2:종료)");

        int start = scanner.nextInt();
        while (start == 1) {

            Game game = new Game(new Deck(3));

            System.out.println("몇 명?");
            int people_num = scanner.nextInt();
            for (int i = 0; i < people_num; i++) {
                game.addPlayer(String.valueOf((char) ('A' + i)), 10000);
                System.out.println(String.valueOf((char) ('A' + i)) + "배팅금액 얼마?");
                game.placeBet(String.valueOf((char) ('A' + i)), scanner.nextInt());
            }

            game.start();   //배팅 금액 0원인지 확인 -> 0원이면 오류

            Evaluator evaluator = new Evaluator(game.getPlayerList());  //카드 나눠주고 Map에 넣어주기..

            while (people_num != game.ongame_people_num()) {
                //게임이 진행되는 중...
                evaluator.start();  //사용해서 턴 돌리기..
            }
            //결과 돈...
            evaluator.result();

            game.getPlayerList().forEach((name, player) -> {
                System.out.println(name + "의 돈은" + player.getBalance() + "입니다.");
            });
            System.out.println("게임을 다시 시작 하시겠습니까? (1:시작, 2:종료)");
            start = scanner.nextInt();

        }
        System.out.println("게임 끝!");

    }
}
