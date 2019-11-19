package com.improving;

public interface IPlayer extends IPlayerInfo{
    void takeTurn(IGame igame);
    int handSize();
    Card draw(IGame igame);
}
