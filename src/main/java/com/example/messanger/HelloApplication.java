package com.example.messanger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {
  @Override
  public void start(Stage stage) throws IOException {
    Parent root = FXMLLoader.load
            (HelloApplication.class.getResource("hello-view.fxml"));
    stage.setScene(new Scene(root, 380, 400));
    stage.setTitle("[Client]");
    stage.show();
    GridPane roo = new GridPane();
    roo.setHgap(10);
    roo.setVgap(5);
    for(int i = 0; i < 4; i++) {
      for(int j = 0; j < 2; j++) {
        roo.add(new TextField(),j,i);
      }
      roo.setPadding(new Insets(5));
    }
  }

  public static void main(String[] args) {
    launch();
  }
}
