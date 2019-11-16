package org.improving;

import javafx.scene.control.ColorPicker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.LinkedList;
import java.util.Random;

public class Deck {

    private LinkedList<Card> drawPile = new LinkedList<>();
    private LinkedList<Card> discardPile = new LinkedList<>();

    public Deck(){
        for (var color : Color.values()){
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
    public LinkedList<Card> getDiscardPile(){
        return discardPile;
    }

    public Card getDiscardTopCard(){
        return discardPile.getLast();
    }
}
