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
    void play_Should_Draw_And_Add_To_DiscardPile() {
        //Arrange
        game.getDeck().getDrawPile().clear();
        for (var player:game.getPlayers()) {
            player.getHand().clear();
        }
        game.getPlayers().get(0).getHand().add(new Card(Color.Red, Faces.Two));
        game.getDeck().getDrawPile().add(new Card(Color.Red, Faces.Two));
        //Act
        game.play();
        var result = game.getDeck().getDiscardPile().size();
        //Assert
        assertEquals(2, result);
    }
    @Test
    void play_Should_Prompt_User_To_Take_Turn() {
        //Arrange
        game.getDeck().getDiscardPile().clear();
        game.getDeck().getDrawPile().add(new Card(Color.Red, Faces.Seven));
        for(var player:game.getPlayers()) {
            player.getHand().clear();
            player.getHand().addAll(Arrays.asList(
                    new Card(Color.Red, Faces.Seven)
            ));
        }
        //Act
        game.play();
        var result = game.getDeck().getDiscardPile().size();
        //Assert
        assertEquals(2, result);
    }
    @Test
    void playCard_Should_Add_Card_To_DisCardPile(){
        //Arrange
        game.getPlayers().get(0).getHand().add(new Card(Color.Green, Faces.Seven));
        game.getDeck().getDiscardPile().clear();
        game.getDeck().getDiscardPile().add(new Card(Color.Green, Faces.Seven));
        //Act
        game.playCard(new Card(Color.Red, Faces.Five));
        var result = game.getDeck().getDiscardPile().size();
        //Assert
        assertEquals(2, result);
    }
    @Test
    void playCard_Should_Set_Null_To_Color(){
        //Arrange

        //Act
        game.playCard(new Card(null, Faces.Wild));
        var result = game.getDeck().getDiscardPile().getLast().getColor().getClass();
        //Assert
        assertEquals(Color.class, result);
    }
    @Test
    void play_Should_Not_executeSpecial_When_Player_Does_Not_Play(){
        //Arrange
        for(var player:game.getPlayers()){
            player.getHand().clear();
            player.getHand().add(new Card(Color.Blue, Faces.Five));
        }
        game.getPlayers().get(0).getHand().clear();
        game.getPlayers().get(0).getHand().add(new Card(Color.Yellow, Faces.WildDrawFour));
        game.getPlayers().get(0).getHand().add(new Card(Color.Green, Faces.Wild));
        //Act
        game.play();
        var playerTwoHandSize = game.getPlayers().get(1).getHand().size();
        var playerThreeHandSize = game.getPlayers().get(2).getHand().size();
        var playerFourHandSize = game.getPlayers().get(3).getHand().size();
        //Assert
        assertEquals(5, playerTwoHandSize);
        assertEquals(2, playerThreeHandSize);
        assertEquals(2, playerFourHandSize);
    }
    @Test
    void play_Should_executeSpecial_For_First_Card_Dealt(){

    }

}