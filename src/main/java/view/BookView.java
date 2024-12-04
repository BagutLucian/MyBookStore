package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.model.BookDTO;

import java.util.List;

public class BookView {

    private TableView<BookDTO> bookTableView;
    private final ObservableList<BookDTO> booksObservableList;
    private TextField authorTextField;
    private TextField titleTextField;
    private TextField stockTextField;
    private TextField priceTextField;
    private Label authorLabel;
    private Label titleLabel;
    private Label stockLabel;
    private Label priceLabel;
    private Button saveButton;
    private Button deleteButton;
    private Button orderButton;
    private Button backButton;

    public BookView(Stage primaryStage, List<BookDTO> books) {
        primaryStage.setTitle("Library");
        GridPane gridPane = new GridPane();
        initializeGridPane(gridPane);

        Scene scene = new Scene(gridPane, 1100, 600);
        primaryStage.setScene(scene);

        booksObservableList = FXCollections.observableArrayList(books);

        initBackButton(gridPane);
        initTableView(gridPane);
        initSaveOptions(gridPane);

        primaryStage.show();
    }

    private void initializeGridPane(GridPane gridPane) {
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 50, 25, 50));
        gridPane.setStyle("-fx-background-color: #f5f5f5;");  // Optional, for background color
    }

    private void initBackButton(GridPane gridPane) {
        VBox backButtonContainer = new VBox();
        backButtonContainer.setAlignment(Pos.BOTTOM_LEFT); // Poziționează conținutul jos, stânga
        backButtonContainer.setPadding(new Insets(0, 0, 10, 0)); // Adaugă puțin spațiu jos

        backButton = new Button("Back");
        backButtonContainer.getChildren().add(backButton);

        //gridPane.add(backButtonContainer, 0, 1); // Adăugăm containerul în coloana 0, rândul 1
        GridPane.setValignment(backButtonContainer, javafx.geometry.VPos.BOTTOM); // Aliniere verticală jos
    }

    private void initTableView(GridPane gridPane) {
        bookTableView = new TableView<>();
        bookTableView.setPlaceholder(new Label("No books to display"));

        TableColumn<BookDTO, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        TableColumn<BookDTO, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        TableColumn<BookDTO, String> stockColumn = new TableColumn<>("Stock");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        TableColumn<BookDTO, String> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        bookTableView.getColumns().addAll(titleColumn, authorColumn, stockColumn, priceColumn);
        bookTableView.setItems(booksObservableList);

        gridPane.add(bookTableView, 1, 0, 1, 5); // Add table to column 1, spanning 5 rows
    }

    private void initSaveOptions(GridPane gridPane) {
        VBox formContainer = new VBox(10); // Vertical container for labels and textfields
        formContainer.setAlignment(Pos.TOP_LEFT);

        titleLabel = new Label("Title");
        titleTextField = new TextField();

        authorLabel = new Label("Author");
        authorTextField = new TextField();

        stockLabel = new Label("Stock");
        stockTextField = new TextField();

        priceLabel = new Label("Price");
        priceTextField = new TextField();

        saveButton = new Button("Save");
        deleteButton = new Button("Delete");
        orderButton = new Button("Order");

        formContainer.getChildren().addAll(titleLabel, titleTextField, authorLabel, authorTextField, stockLabel, stockTextField, priceLabel, priceTextField, saveButton, deleteButton, orderButton);

        gridPane.add(formContainer, 2, 0); // Add form container to column 2, spanning 5 rows
    }

    public void reloadBooks(List<BookDTO> books) {
        booksObservableList.clear();
        booksObservableList.addAll(books);
    }

    public void addBackButtonListener(EventHandler<ActionEvent> backButtonListener) {
        backButton.setOnAction(backButtonListener);
    }

    public void addSaveButtonListener(EventHandler<ActionEvent> saveButtonListener) {
        saveButton.setOnAction(saveButtonListener);
    }

    public void addDeleteButtonListener(EventHandler<ActionEvent> deleteButtonListener) {
        deleteButton.setOnAction(deleteButtonListener);
    }

    public void addOrderButtonListener(EventHandler<ActionEvent> orderButtonListener) {
        orderButton.setOnAction(orderButtonListener);
    }

    public void addDisplayAlertMessage(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    public String getTitle() {
        return titleTextField.getText();
    }

    public String getAuthor() {
        return authorTextField.getText();
    }

    public Integer getStock() {
        return Integer.parseInt(stockTextField.getText());
    }

    public Integer getPrice() {
        return Integer.parseInt(priceTextField.getText());
    }

    public void removeBookFromObservableList(BookDTO bookDTO) {
        this.booksObservableList.remove(bookDTO);
    }

    public TableView<BookDTO> getBookTableView() {
        return bookTableView;
    }
}
