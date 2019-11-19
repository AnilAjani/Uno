package com.improving;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Player implements IPlayer {
    LinkedList<Card> hand;
    private String name;
    private List<Optional> chooseColor;


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
    public void takeTurn(IGame game) {
        for (var card : hand) {
            if (game.isPlayable(card)) {
                hand.remove(card);

                game.playCard(card, Optional.of(Colors.Red));
                System.out.println(card + " was played ");
                return;
            }
        }
        hand.add(draw(game));
        System.out.println("Drew card");
    }

    public void playHighestValue(IGame igame) {
        for (var cards : hand) {
            if(cards.getFace().getValue() == 50){
                takeTurn(igame);
            }
        }
    }


    public String getName() {
        return name;
    }

    @Override
    public int handSize() {
        return hand.size();
    }

    @Override
    public Card draw(IGame game) {
        return game.draw();
    }
}

