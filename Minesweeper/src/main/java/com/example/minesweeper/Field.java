package com.example.minesweeper;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Field extends StackPane {

  /*
  Variablen
   */
  private int x, y;
  public boolean bomb;
  private int count = 0;
  private String text;
  public Text bombCount;
  private Rectangle fieldNode;
  private boolean openedField;

  /*
  ArrayLists fuer Speichern vom Feld
   */
  private ArrayList<Integer> arrXBomb = new ArrayList<>();
  private ArrayList<Integer> arrYBomb = new ArrayList<>();


  public ArrayList<Integer> getArrXBomb() {
    return arrXBomb;
  }

  public ArrayList<Integer> getArrYBomb() {
    return arrYBomb;
  }

  /*
  Getter, Setter, Konstruktor
   */
  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void setBombCount(String bombCount) {
    this.bombCount.setText(bombCount);
  }

  public Field(int x, int y, boolean bomb) {
    this.x = x;
    this.y = y;
    this.bomb = bomb;
    this.openedField = false;
    openedField = false;
    if (bomb) {
      text = "X";
      arrXBomb.add(x);
      arrYBomb.add(y);
      //System.out.println(arrXBomb + " | " + arrYBomb);
    }

    /*
    Feld Gestaltung
     */

    fieldNode = new Rectangle(MinesweeperApplication.FIELD_SIZE - 2, MinesweeperApplication.FIELD_SIZE - 2);
    fieldNode.setFill(Color.LIGHTBLUE);
    fieldNode.setStroke(Color.LIGHTCYAN);
    fieldNode.setVisible(true);

    bombCount = new Text();
    bombCount.setVisible(false);
    bombCount.setText(this.bomb ? "X" : "");
    bombCount.setStroke(Color.RED);
    setOnMouseClicked(e -> onFieldClick(e));

    getChildren().addAll(fieldNode, bombCount);
    setTranslateX(x * MinesweeperApplication.FIELD_SIZE);
    setTranslateY(y * MinesweeperApplication.FIELD_SIZE);
    setOnMouseClicked(e -> onFieldClick(e));

  }
  /*
  Auf Mausklick reagieren, Rechtsklick, Linksklick
   */

  private void onFieldClick(MouseEvent e) {
    if (e.getButton() == MouseButton.PRIMARY) {
      open();
    } else if (e.getButton() == MouseButton.SECONDARY) {
      fieldNode.setFill(Color.RED);
      bombCount.setVisible(false);
    } else if (e.getButton() == MouseButton.PRIMARY && bomb == true) {
      System.exit(0);
    }
  }

  /*
  alle anliegenden Freien Felder werden geoeffnet
   */

  public void open(){
    if (this.openedField) return;
    this.openedField = true;
    bombCount.setVisible(true);
    fieldNode.setFill(Color.LIGHTGRAY);
    if (bombCount.getText().isEmpty()) {
      MinesweeperApplication.getNeighbours(this).forEach(Field::open);
    }

    /*if (!this.openedField && !this.bomb) {
      fieldNode.setFill(Color.LIGHTGRAY);
      bombCount.setVisible(true);
      if (bombCount.getText().isEmpty()) {
        MinesweeperApplication.getNeighbours(this).forEach(Field::open);
      }
    }*/
  }
}
