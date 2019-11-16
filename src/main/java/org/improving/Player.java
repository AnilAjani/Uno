package org.improving;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player implements iPlayer {
    LinkedList<Card> hand;
    private String name;
    private Game game;


    public Player(String name, Deck deck) {
        this.hand = new LinkedList<>();
        this.name = name;
        dealHand(deck);
    }

    public void dealHand(Deck deck) {
        for (int i = 0; i < 7; i++) {
            hand.add(deck.draw());
        }
    }

    public List<Card> getHand() {
        return hand;
    }
    @Override
    public void takeTurn(Game game) {
        for (var card : hand) {
            if (game.isPlayable(card)) {
                hand.remove(card);
                game.playCard(card);
                System.out.println(card + " was played ");
                return;
            }
        }
        hand.add(game.getDeck().draw());
        System.out.println("Drew card");
    }

    public String getName() {
        return name;
    }

    @Override
    public int handSize() {
        return hand.size();
    }

    @Override
    public Card draw(Game game) {
        return game.draw();
    }
}

