package org.improving;

public class Card {
  private Color color;
  private Faces faces;

    public Card(Color color, Faces faces) {
        this.color = color;
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "" + (color == null ?  "" : color.toString()) + " " + faces.toString();
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Faces getFace() {
        return faces;
    }

    public void setFace(Faces face) {
        face = face;
    }
}
