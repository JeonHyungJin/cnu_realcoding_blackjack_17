package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private Deck deck;
    private List<Card> cardList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public int gettotalscore() {
        int total_card_count = 0;
        for (int i = 0; i < cardList.size(); i++) {
            total_card_count += cardList.get(i).getRank();
        }
        return total_card_count;
    }

    public List<Card> getCardList() {
        return cardList;
    }
}
