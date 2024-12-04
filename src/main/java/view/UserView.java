package view;

import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import view.model.BookDTO;
import view.model.RoleDTO;
import view.model.UserDTO;


import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
public class UserView {

    private TableView<UserDTO> userTableView;
    private final ObservableList<UserDTO> usersObservableList;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label usernameLabel;
    private Label passwordLabel;
    private Label rolesLabel;
    private VBox rolesCheckboxContainer;
    private Button addUserButton;
    private Button deleteButton;
    private Button pdfButton;
    private Button backButton;
    private List<RoleDTO> roles;

    public UserView(Stage primaryStage, List<UserDTO> users, List<RoleDTO> roles) {
        primaryStage.setTitle("Users");
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 1100, 600);
        primaryStage.setScene(scene);

        usersObservableList = FXCollections.observableArrayList(users);

        initBackButton(gridPane);
        initTableView(gridPane);
        initRightPanel(gridPane, roles);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 50, 25, 50));
    }

    private void initBackButton(GridPane gridPane) {
        VBox backButtonContainer = new VBox();
        backButtonContainer.setAlignment(Pos.BOTTOM_LEFT); // Poziționează conținutul jos, stânga
        backButtonContainer.setPadding(new Insets(0, 0, 10, 0)); // Adaugă puțin spațiu jos

        backButton = new Button("Back");
        backButtonContainer.getChildren().add(backButton);

        gridPane.add(backButtonContainer, 0, 1); // Adăugăm containerul în coloana 0, rândul 1
        GridPane.setValignment(backButtonContainer, javafx.geometry.VPos.BOTTOM); // Aliniere verticală jos
    }


    private void initTableView(GridPane gridPane) {
        userTableView = new TableView<>();
        userTableView.setPlaceholder(new Label("No users to display"));

        TableColumn<UserDTO, String> usernameColumn = new TableColumn<>("User name");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        TableColumn<UserDTO, String> rolesColumn = new TableColumn<>("Roles");

        rolesColumn.setCellValueFactory(data -> new SimpleStringProperty(
                data.getValue().getRoleNames().stream().collect(Collectors.joining(", "))
        ));
        rolesColumn.setPrefWidth(300);
        userTableView.getColumns().addAll(usernameColumn, rolesColumn);
        userTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        userTableView.setItems(usersObservableList);

        gridPane.add(userTableView, 1, 0); // Adăugăm tabelul în coloana din mijloc
    }

    private void initRightPanel(GridPane gridPane, List<RoleDTO> roles) {
        this.roles = roles;
        GridPane rightPane = new GridPane();
        rightPane.setAlignment(Pos.TOP_LEFT);
        rightPane.setHgap(5);
        rightPane.setVgap(10);

        // Username
        usernameLabel = new Label("Name:");
        rightPane.add(usernameLabel, 0, 0);
        usernameTextField = new TextField();
        rightPane.add(usernameTextField, 1, 0);

        // Password
        passwordLabel = new Label("Password:");
        rightPane.add(passwordLabel, 0, 1);
        passwordTextField = new TextField();
        rightPane.add(passwordTextField, 1, 1);

        // Roles
        rolesLabel = new Label("Roles:");
        rightPane.add(rolesLabel, 0, 2);

        rolesCheckboxContainer = new VBox();
        rolesCheckboxContainer.setSpacing(5);
        rolesCheckboxContainer.setPadding(new Insets(0, 0, 0, 20));

        for (RoleDTO role : roles) {
            CheckBox roleCheckBox = new CheckBox(role.getRole());
            roleCheckBox.setUserData(role);
            rolesCheckboxContainer.getChildren().add(roleCheckBox);
        }
        rightPane.add(rolesCheckboxContainer, 0, 3, 2, 1);

        // Add User Button
        addUserButton = new Button("Add User");
        rightPane.add(addUserButton, 0, 4);

        // Separator între butoane
        Separator separator = new Separator();
        separator.setPrefWidth(150); // Lungimea separatorului
        rightPane.add(separator, 0, 5, 2, 1); // Extindem separatorul pe 2 coloane

        // Delete Button
        deleteButton = new Button("Delete Selected User");
        rightPane.add(deleteButton, 0, 6);

        // PDF Button
        pdfButton = new Button("Generate PDF");
        rightPane.add(pdfButton, 0, 7);

        gridPane.add(rightPane, 2, 0); // Adăugăm panelul în coloana dreaptă
    }
    public void reloadUsers(List<UserDTO> users) {
        usersObservableList.clear();
        usersObservableList.addAll(users);
    }

    public void addBackButtonListener(EventHandler<ActionEvent> backButtonListener) {
        backButton.setOnAction(backButtonListener);
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener) {
        addUserButton.setOnAction(saveButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addPdfButtonListener(EventHandler<ActionEvent> pdfButtonListener) {
        pdfButton.setOnAction(pdfButtonListener);
    }

    public void displayAlertMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public String getUsername() {
        return usernameTextField.getText();
    }

    public String getPassword() {
        return passwordTextField.getText();
    }

    public List<RoleDTO> getSelectedRoles() {
        return rolesCheckboxContainer.getChildren().stream()
                .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                .map(node -> (RoleDTO) ((CheckBox) node).getUserData())  // Obținem obiectul RoleDTO asociat cu CheckBox
                .collect(Collectors.toList());
    }
    public TableView getUserTableView()
    {
        return userTableView;
    }

    public void addDisplayAlertMessage(String title,String header,String content)
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
    public void removeUserFromObservableList(UserDTO userDTO)
    {
        this.usersObservableList.remove(userDTO);
    }

}
