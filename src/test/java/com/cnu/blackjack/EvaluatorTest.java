package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NotTwoCardException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class EvaluatorTest {
    Map<String, Player> playerMap = new HashMap<>();

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Deck deck = new Deck(2);
                Player player1 = new Player(1000, new Hand(deck));
                Player player2 = new Player(2000, new Hand(deck));
                playerMap.put("miji", player1);
                playerMap.put("dodo", player2);
                Evaluator evaluator = new Evaluator(playerMap);

                playerMap.forEach((name, player) -> {
                        int count = player.getHand().getCardList().size();
                        assertThat(count, is(2));
                    });

    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
        Deck deck = new Deck(2);
                Player player1 = new Player(1000, new Hand(deck));
                playerMap.put("miji", player1);
                Evaluator evaluator = new Evaluator(playerMap);

                player1.getHand().getCardList().clear();
                player1.getHand().getCardList().add(new Card(3, Suit.SPADES));
                player1.getHand().getCardList().add(new Card(5, Suit.SPADES));
                evaluator.start();
                int cur_card_count = player1.getHand().getCardList().size();
                assertTrue(cur_card_count == 3);

    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        Deck deck = new Deck(2);
                Player player1 = new Player(1000, new Hand(deck));
                playerMap.put("miji", player1);
                Evaluator evaluator = new Evaluator(playerMap);

                        player1.placeBet(1000);

                player1.getHand().getCardList().clear();
                player1.getHand().getCardList().add(new Card(11, Suit.SPADES));
                player1.getHand().getCardList().add(new Card(10, Suit.SPADES));
                evaluator.start();
                int cur_card_count = player1.getHand().getCardList().size();
                assertThat(cur_card_count,is(2));
                assertThat(player1.getBalance(),is(2000));

    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {
        Deck deck = new Deck(2);
                Player player1 = new Player(1000, new Hand(deck));
                playerMap.put("miji", player1);
                Evaluator evaluator = new Evaluator(playerMap);

                player1.getHand().getCardList().clear();
                player1.getHand().getCardList().add(new Card(10, Suit.SPADES));
                player1.getHand().getCardList().add(new Card(8, Suit.SPADES));
                evaluator.start();
                int cur_card_count = player1.getHand().getCardList().size();
                assertTrue(cur_card_count == 2);
    }
}
