package com.improving;

public class Card {
  private Colors color;
  private Faces faces;

    public Card(Colors color, Faces faces) {
        this.color = color;
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "" + (color == null ?  "" : color.toString()) + " " + faces.toString();
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }

    public Faces getFace() {
        return faces;
    }
}
