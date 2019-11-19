package com.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeckTest {

    Deck deck;
    Game game;

    @BeforeEach
    void arrange(){
       deck = new Deck();
       game = new Game();
    }

    @Test
    public void Deck_Should_Return_112_Cards(){
        //Arrange

        //Act
        System.out.println(deck.getDrawPile().size());

        //Assert
        assertTrue(deck.getDrawPile().size() == 112);

    }

    @Test
    public void Draw_Card_Should_Remove_From_drawPile(){
        //Arrange

        //Act
        deck.draw();
        var result = deck.getDrawPile().size();

        //Assert
        assertEquals(111, result);
    }
    @Test
    public void Draw_Card_Should_Return_A_Card(){
        //Arrange

        //Act
        var result =  deck.draw().getClass();

        //Assert
        assertEquals(Card.class, result);
    }
}



