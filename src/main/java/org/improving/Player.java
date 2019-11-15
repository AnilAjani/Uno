package org.improving;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Player {
    LinkedList<Card> hand;
    private String name;
    private Game game;


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
        for (var card : hand) {
            if (game.isPlayable(card)) {
                hand.remove(card);
                game.playCard(card);
                System.out.println(deck.getDiscardPile().getLast() + " was played ");
                return;
            }
        }
        hand.add(game.getDeck().draw());
        System.out.println("Drew card");
    }

    public String getName() {
        return name;
    }

    public void draw(Game game) {
        this.game = game;
        // TODO: replenish deck from discard pile.
        if (game.getDeck().getDrawPile().isEmpty()) {
            // getDiscardPile()-getDiscardTopCard();
            var tempCard = game.getDeck().getDiscardPile().getLast();
            game.getDeck().getDiscardPile().remove(tempCard);
            game.getDeck().getDrawPile().addAll(game.getDeck().getDiscardPile());
            game.getDeck().getDiscardPile().clear();
            game.getDeck().getDiscardPile().add(tempCard);
            Collections.shuffle(game.getDeck().getDrawPile());

        }
    }
}

