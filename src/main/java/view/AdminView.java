package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Book;
import view.model.BookDTO;


import java.awt.*;
import java.util.*;
import java.util.List;

public class AdminView {


    private Button viewBooksButton;
    private Button viewUsersButton;


    public AdminView(Stage primaryStage)
    {
        primaryStage.setTitle("Admin");
        GridPane gridPane= new GridPane();
        initializeGridPage(gridPane);

        Scene scene = new Scene(gridPane,1100,600);
        primaryStage.setScene(scene);

        initButtonsOptions(gridPane);
        primaryStage.show();
    }



    private void initializeGridPage(GridPane gridPane)
    {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));
    }


    public void initButtonsOptions(GridPane gridPane)
    {

        viewBooksButton=new Button("View books");
        gridPane.add(viewBooksButton,10,1);

        viewUsersButton=new Button("View users");
        gridPane.add(viewUsersButton,9,1);

    }

    public void addViewBooksButtonListener(EventHandler<ActionEvent> viewBooksButtonListener)
    {
        viewBooksButton.setOnAction(viewBooksButtonListener);
    }
    public void addViewUsersButtonListener(EventHandler<ActionEvent> viewUsersButtonListener)
    {
        viewUsersButton.setOnAction(viewUsersButtonListener);
    }




}
