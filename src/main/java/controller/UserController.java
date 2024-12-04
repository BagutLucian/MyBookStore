package controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import launcher.AdminComponentFactory;
import launcher.LoginComponentFactory;
import mapper.UserMapper;
import model.EmployeeSales;
import model.User;
import service.order.OrderService;
import service.user.UserService;
import view.UserView;
import view.model.RoleDTO;
import view.model.UserDTO;
import view.model.builder.UserDTOBuilder;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.pdf.PdfPTable;


public class UserController {
    private final UserView userView;
    private final UserService userService;
    private final OrderService orderService;
    public UserController(UserView userView, UserService userService, OrderService orderService)
    {
        this.userView=userView;
        this.userService=userService;
        this.orderService=orderService;
        this.userView.addSaveButtonListener(new SaveButtonListener());
        this.userView.addDeleteButtonListener(new DeleteButtonListener());
        this.userView.addPdfButtonListener(new PdfButtonListener());
        this.userView.addBackButtonListener(new BackButtonListener());
    }
    private class SaveButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            String name = userView.getUsername();
            String password = userView.getPassword();
            List<RoleDTO> selRoles = userView.getSelectedRoles();
            ObservableList<RoleDTO> roles = FXCollections.observableArrayList(selRoles);


            if(name.isEmpty() || password.isEmpty())
            {
                userView.addDisplayAlertMessage("Add Error","Problem at Username or Password fields","Can not have an empty Username or Password field.");
            } else{
                UserDTO userDTO = new UserDTOBuilder()
                        .setUsername(name)
                        .setPassword(password)
                        .setRoles(roles)
                        .build();
                boolean savedUser = userService.save(UserMapper.convertUserDTOToUser(userDTO));

                if(savedUser)
                {
                    List<User> newUsers = userService.findAll();
                    // reload list
                    userView.reloadUsers(UserMapper.convertUserListToUserDTOList(newUsers));
                    userView.addDisplayAlertMessage("Add Successful","User Added","User was successfully added to the database.");


                } else{
                    userView.addDisplayAlertMessage("Add Error","Problem at adding user","There was a problem at adding the user to the database. Please try again.");
                }
            }
        }
    }
    private class DeleteButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            UserDTO userDTO = (UserDTO) userView.getUserTableView().getSelectionModel().getSelectedItem();
            if(userDTO != null)
            {
                boolean deletionSuccessful = userService.deleteById(userDTO.getId());
                if(deletionSuccessful)
                {
                    userView.addDisplayAlertMessage("Delete Successful","User Deleted","User was successfully deleted from the database.");
                    userView.removeUserFromObservableList(userDTO);
                } else
                {
                    userView.addDisplayAlertMessage("Delete Error","Problem at deleting the user","There was a problem with the database. Please try again.");
                }
            } else {
                userView.addDisplayAlertMessage("Delete Error","Problem at deleting user","You must select a user before pressing the delete button.");
            }
        }
    }
    private class PdfButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            List<EmployeeSales> employeeSalesList = orderService.getEmployeeSalesReport();
            generatePdf(employeeSalesList);
        }
    }
    private class BackButtonListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent event)
        {
            AdminComponentFactory.resetInstance();
            AdminComponentFactory.getInstance(LoginComponentFactory.getComponentsForTests(), LoginComponentFactory.getStage());
        }
    }




    public void generatePdf(List<EmployeeSales> employeeSalesList) {
        try {
            String filePath = System.getProperty("user.home") + "/Desktop/employee_sales_report.pdf";

            // Creăm un document PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Setăm fontul
            BaseFont helveticaBold = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            Font font = new Font(helveticaBold, 16);

            // Titlu
            Paragraph title = new Paragraph("Employee Sales Report", font);
            document.add(title);

            // Crearea unui tabel cu 3 coloane: Nume angajat, Vânzări, Total vânzări
            PdfPTable table = new PdfPTable(3); // 3 coloane

            // Adăugăm antetul tabelului
            table.addCell("Employee Name");
            table.addCell("Books Sold");
            table.addCell("Total Sales");

            // Adăugăm datele despre angajați și vânzările lor
            for (EmployeeSales employeeSales : employeeSalesList) {
                table.addCell(employeeSales.getEmployeeName());
                table.addCell(String.valueOf(employeeSales.getBooksSold()));
                table.addCell(String.format("%.2f", employeeSales.getTotalSales()));
            }

            // Adăugăm tabelul la document
            document.add(table);

            // Închidem documentul PDF
            document.close();

            // Afișăm un mesaj de succes
            showAlert("PDF Generated", "The sales report has been generated successfully.", "Check the generated file.");

        } catch (Exception e) {
            showAlert("Error", "An error occurred while generating the report.", e.getMessage());
            e.printStackTrace();
        }
    }


    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}



