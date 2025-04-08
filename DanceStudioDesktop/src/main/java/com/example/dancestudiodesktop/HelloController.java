package com.example.dancestudiodesktop;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private DBHelper dbHelper;
    private List<Course> courses = new ArrayList<>();

    @FXML
    private TableColumn<Course, String> instructor;
    @FXML
    private TableColumn<Course, String> name;
    @FXML
    private TableColumn<Course, Integer> length;
    @FXML
    private TableColumn<Course, String> type;
    @FXML
    private TableView<Course> TableView;

    @FXML
    private void initialize() {
        instructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        length.setCellValueFactory(new PropertyValueFactory<>("length"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));

        try{
            dbHelper = new DBHelper();
            getAllCourses();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Nem sikerült az adabázishoz csatlakozni!");
            alert.showAndWait()
                    .filter(response -> response == ButtonType.OK)
                    .ifPresent(response -> {
                        Platform.exit();
                    });
        }

    }

    private void getAllCourses() throws SQLException {
        List<Course> courseList = dbHelper.getCourses();
        TableView.getItems().clear();
        courses.clear();
        TableView.getItems().addAll(courseList);
        courseList.addAll(courseList);
    }

    @FXML
    public void onDeleteButtonClick(ActionEvent actionEvent) throws SQLException {
        Course selectedCourse = TableView.getSelectionModel().getSelectedItem();
        if(selectedCourse != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Biztosan törölni szeretné?");
            var finished = alert.showAndWait();
            if(finished.isEmpty() || finished.get() != ButtonType.OK && finished.get() != ButtonType.YES ){
                return;
            }
            try{
                dbHelper.DeleteCourse(selectedCourse.getId());
                courses.remove(selectedCourse);
                Alert yesAlert = new Alert(Alert.AlertType.INFORMATION, "Sikeresen törölve lett!");
                yesAlert.showAndWait();
                return;
            }
            catch(Exception e){
                Alert errorAlert = new Alert(Alert.AlertType.WARNING, e.getMessage());
                errorAlert.showAndWait();
                return;
            }

        }
        Alert alert = new Alert(Alert.AlertType.WARNING, "Törléshez először válasszon ki egy kurzust!");
        alert.showAndWait();
    }
}