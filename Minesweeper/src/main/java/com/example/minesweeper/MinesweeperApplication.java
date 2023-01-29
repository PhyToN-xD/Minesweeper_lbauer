package com.example.minesweeper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MinesweeperApplication extends Application {
  private static final int X_FIELD = 16;
  private static final int Y_FIELD = 16;
  public static final int FIELD_SIZE = 20;
  private static Field[][] grid = new Field[X_FIELD][Y_FIELD];
  public static int countBomb = 0;




  private Parent createContent () {
    Pane root = new Pane();

    root.setPrefSize(320, 326);

    /*
     Spielfeld mit Zufallsbomben wird generiert
     */
    for (int y = 0; y < Y_FIELD; y++) {
      for (int x = 0; x < X_FIELD; x++) {
        Field field = new Field(x, y, Math.random() < 0.13);
        grid[x][y] = field;
        root.getChildren().add(field);
      }
    }

    for (int y = 0; y < Y_FIELD; y++) {
      for (int x = 0; x < X_FIELD; x++) {
        Field field = grid[x][y];
        if (!field.bomb){
          int count = 0;
          ArrayList<Field> fields = getNeighbours(grid[x][y]);

          for (Field f:fields) {
            if (f.bomb) count++;
          }
          if (count > 0) {
            field.setBombCount(Integer.toString(count));
          }
        }
      }
    }

    return root;
  }

  /*
  Herausfinden von den anliegenden Feldern
   */

  static ArrayList<Field> getNeighbours (Field field) {
    ArrayList<Field> neighbours = new ArrayList<>();

    int[] points = new int[] {-1,-1, -1,0, -1,1, 0,1, 1,1, 1,0, 1,-1, 0,-1};

    for (int i = 0; i<points.length; i++) {
      int dx = points[i];
      int dy = points[i+1];

      int newX = field.getX() + dx;
      int newY = field.getY() + dy;

      if (newX >= 0 && newX < X_FIELD && newY >= 0 && newY < Y_FIELD) {
        neighbours.add(grid[newX][newY]);
      }
      i++;
    }

    return neighbours;
  }



  @Override
  public void start(Stage stage) throws IOException {
    //FXMLLoader fxmlLoader = new FXMLLoader(MineSweeperApplication.class.getResource("hello-view.fxml"));

    Scene scene = new Scene(createContent());

    stage.setTitle("MineSweeper");
    stage.setScene(scene);
    stage.show();


  }

  public static void main(String[] args) {
    launch();
  }


}