package com.improving;

import java.util.Collections;
import java.util.LinkedList;

public class Deck implements IDeck {

    private LinkedList<Card> drawPile = new LinkedList<>();
    private LinkedList<Card> discardPile = new LinkedList<>();

    public Deck(){
        for (var color : Colors.values()){
            for (var faces : Faces.values()){
                if (faces.getValue() == 50) {
                    drawPile.add(new Card(null, faces));
                } else {
                    drawPile.add(new Card(color, faces));
                    drawPile.add(new Card(color, faces));
                }
            }
        }
        Collections.shuffle(drawPile);
    }
    public Card draw(){
        replenishDeck();
        var firstCard = drawPile.getLast();
        drawPile.remove(firstCard);
        return firstCard;
        // determine if floating card is playable or keep in player hand
    }

    private void replenishDeck() {
        if(drawPile.isEmpty()){
            // getDiscardPile()-getDiscardTopCard();
            var tempCard = discardPile.getLast();
            discardPile.remove(tempCard);
            drawPile.addAll(discardPile);
            discardPile.clear();
            discardPile.add(tempCard);
            Collections.shuffle(drawPile);

        }
    }

    public LinkedList<Card> getDrawPile() {
        return drawPile;
    }
    @Override
    public LinkedList<Card> getDiscardPile(){
        return discardPile;
    }

    public Card getDiscardTopCard(){
        return discardPile.getLast();
    }

    @Override
    public int getDrawPileSize() {
        return drawPile.size();
    }
}
