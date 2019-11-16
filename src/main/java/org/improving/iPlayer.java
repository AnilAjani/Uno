package org.improving;

public interface iPlayer {
    //method signature
    //return type, identifier, parameter
    void takeTurn(Game game);
    int handSize();
    Card draw(Game geme);
}
