package org.improving;

import java.util.List;

public class Player {
    private Hand hand;

    public Player(Hand hand) {
        this.hand = hand;
    }

    public boolean isPlayable(Deck deck, Card card) {
        return deck.getDiscardTopCard().getFace() == card.getFace() ||
                deck.getDiscardTopCard().getColor() == card.getColor() ||
                card.getFace().getValue() == 50;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void takeTurn() {

    }
}

