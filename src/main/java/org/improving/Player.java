package org.improving;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Player {
    LinkedList<Card> hand;
    private String name;

    public Player(String name, Deck deck) {
        this.hand = new LinkedList<>();
        this.name = name;
        dealHand(deck);
    }

    public void dealHand(Deck deck) {
        deck.getDrawPile();
        for (int i = 0; i < 7; i++) {
            hand.add(deck.draw());
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public void takeTurn(Deck deck, Game game) {
        for (var card:hand) {
            if(game.isPlayable(card)){
                hand.remove(card);
                game.playCard(card);
                return;
            }
        }
        hand.add(game.getDeck().draw());
    }

    public String getName() {
        return name;
    }
}

