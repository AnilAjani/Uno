package org.improving;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void arrange() {
        game = new Game();

    }
    @Test
    void startGame_Should_Draw_And_Add_To_DiscardPile() {
        //Arrange
        //Act
        game.startGame(game.getDeck().draw());
        var result = game.getDeck().getDiscardPile().size();
        //Assert
        assertEquals(1, result);
    }
    @Test
    void startTurns_Should_Prompt_User_To_Take_Turn() {
        //Arrange
        game.getPlayer1().getHand().getHand().clear();
        game.getPlayer1().getHand().getHand().addAll(Arrays.asList(
                new Card(Color.Red, Faces.Seven),
                new Card(Color.Red, Faces.Five),
                new Card(Color.Green, Faces.Six),
                new Card(Color.Yellow, Faces.Zero),
                new Card(Color.Blue, Faces.Two),
                new Card(Color.Red, Faces.Three),
                new Card(Color.Green, Faces.One)
        ));
        //Act
        game.startGame(new Card(Color.Red, Faces.Seven));
        game.startTurns();
        var result = game.getDeck().getDiscardPile().size();
        //Assert
        assertEquals(2, result);
    }
}