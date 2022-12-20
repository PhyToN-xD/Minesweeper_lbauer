package com.example.minesweeper;

import javafx.scene.layout.Pane;

import java.lang.reflect.Field;

public class Fields {
  int x;
  int y;
  int rand;


  Pane root = new Pane();
  root.setPrefSize(320, 320);

  for (int i = 0; i < Y_FIELD; i++) {
    for (int j = 0; j < X_FIELD; j++) {
      Field field = new Field(x, y, Math.random() <= 0.2);
      root.getChildren().add(field);
    }
  }
}
