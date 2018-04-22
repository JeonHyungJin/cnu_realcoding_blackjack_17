package com.cnu.blackjack;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;
    private int dealerScore = 0;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
        game_set();
    }

    private void game_set() {
        dealerScore = dealer.getDealerScore();
        if (dealerScore == 21) {
            playerMap.forEach((name, player) -> {
                player.setState(false);
            });
        }
    }

    public void start() {

        playerMap.forEach((name, player) -> {
            if (player.getState()) {
                List<Card> CardList = player.getHand().getCardList();
                int total_card_count = player.getHand().gettotalscore();

                if (total_card_count <= 16) {
                    player.hitCard();
                } else if (total_card_count == 21 && CardList.size() == 2) {
                    System.out.println(name + "BlackJack으로 승리");
                    player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                    player.setState(false);
                } else {
                    player.setState(false);
                }
            }
        });
    }

    public void result() {   //경기 결과 비교
        playerMap.forEach((name, player) -> {

            if (dealerScore == 21) {    //딜러가 21
                System.out.println(name + "패배");
                System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
            } else if (dealerScore < 21) {  //딜러가 21이하인 경우
                if (player.getHand().gettotalscore() == 21) {
                    if (player.getHand().getCardList().size() != 2) {
                        player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                        System.out.println(name + "승리");
                        System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                    }

                } else if (player.getHand().gettotalscore() > 21) {
                    System.out.println(name + "패배");
                    System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                } else {
                    if (dealerScore > player.getHand().gettotalscore()) {
                        System.out.println(name + "패배");
                        System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                    } else if (dealerScore > player.getHand().gettotalscore()) {
                        System.out.println(name + "비김");
                        player.setBalance(player.getCurrentBet() + player.getBalance());
                        System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                    } else {
                        player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                        System.out.println(name + "승리");
                        System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                    }
                }
            } else if (dealerScore > 21) {
                if (player.getHand().gettotalscore() == 21) {
                    if (player.getHand().getCardList().size() != 2) {
                        player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                        System.out.println(name + "승리");
                        System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                    }

                } else if (player.getHand().gettotalscore() > 21) {
                    System.out.println(name + "비김");
                    player.setBalance(player.getCurrentBet() + player.getBalance());
                    System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                } else {
                    player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                    System.out.println(name + "승리");
                    System.out.println(name + "의 점수는 : " + player.getHand().gettotalscore());
                }
            }

/*
            if (dealer.getDealerScore() > 21 && player.getHand().gettotalscore() <= 21) {   //딜러가 파산, 플레이어가 21이하로 승리 경우
                player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                System.out.println(name + "승리");
                System.out.println(player.getHand().gettotalscore());
            } else if (dealer.getDealerScore() < 21 && player.getHand().gettotalscore() <= 21) {   //둘다 21이하
                if (player.getHand().gettotalscore() > dealer.getDealerScore()) {   //플레이어 승리
                    player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
                    System.out.println(name + "승리");
                    System.out.println(player.getHand().gettotalscore());
                } else {  //플레이어 패배
                    System.out.println(name + "패배");
                    System.out.println(player.getHand().gettotalscore());
                }
            }*/

        });

    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
