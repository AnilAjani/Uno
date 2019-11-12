package org.improving;


import java.util.*;

public class Hand {
    private Deck deck;
    List<Card> hand = new ArrayList<>();

    public Hand(Deck deck) {
        deck.getDrawPile();
        for (int i = 0; i < 7; i++) {
            hand.add(deck.draw());
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Card> drawCard() {
        deck.getDrawPile();
        hand.add(deck.draw());
        return hand;
    }

    public List<Card> drawTwo(Deck deck) {
        var drawTwo = deck.draw();
        hand.add(drawTwo);
        hand.add(drawTwo);
        return hand;
    }
    public List<Card> drawFour(Deck deck) {
        var drawFour = deck.draw();
        hand.add(drawFour);
        hand.add(drawFour);
        return hand;
    }

    public void playCard(Card card){

    }

    }


