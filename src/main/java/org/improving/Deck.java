package org.improving;

import javafx.scene.control.ColorPicker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> drawPile = new ArrayList<>();
    private List<Card> discardPile = new ArrayList<>();

    public Deck(){
        for (var color : Color.values()){
            for (var faces : Faces.values()){
                if (color.toString().equalsIgnoreCase("wild")) {
                    if (faces.getValue() == 50) {
                        drawPile.add(new Card(color, faces));
                        drawPile.add(new Card(color, faces));
                        drawPile.add(new Card(color, faces));
                        drawPile.add(new Card(color, faces));
                    }
                }else if (!faces.toString().equalsIgnoreCase("Wild") &&
                        !faces.toString().equalsIgnoreCase("WildDrawFour")){
                    drawPile.add(new Card(color, faces));
                    drawPile.add(new Card(color, faces));
                }
            }
        }
    }
    public Card draw(){
    var random = new Random().nextInt(drawPile.size());
    var card = drawPile.get(random-1);
    drawPile.remove(card);
    return card;
    }

    public List<Card> getDrawPile() {
        return drawPile;
    }
    public List<Card> getDiscardPile(){
        return discardPile;
    }

    public Card getDiscardTopCard(){
        return discardPile.get(this.discardPile.size()-1);
    }
}
