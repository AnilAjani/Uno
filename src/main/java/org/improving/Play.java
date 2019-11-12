package org.improving;

import java.util.List;

public class Play {

    Game game = new Game();

    public void startGame (Deck deck, Hand hand, Card card){
        var topCard = deck.getDrawPile().remove(0);
        deck.getDiscardPile().add(0, topCard);
        if(topCard.getFace() == Faces.Wild);
    }
}

