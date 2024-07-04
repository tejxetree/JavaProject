package com.example.projectjava;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProjectManagementTool extends Application {
    private ObservableList<Task> tasks;

    @Override
    public void start(Stage primaryStage) {
        tasks = FXCollections.observableArrayList();

        primaryStage.setTitle("Project Management Tool");

        // Menu
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem addTaskMenuItem = new MenuItem("Add Task");
        MenuItem listTasksMenuItem = new MenuItem("List Tasks");
        menu.getItems().addAll(addTaskMenuItem, listTasksMenuItem);
        menuBar.getMenus().add(menu);

        VBox vbox = new VBox(menuBar);
        Scene scene = new Scene(vbox, 800, 600);

        addTaskMenuItem.setOnAction(e -> showAddTaskDialog());
        listTasksMenuItem.setOnAction(e -> showListTasksPanel(vbox));

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showAddTaskDialog() {
        Stage dialog = new Stage();
        dialog.setTitle("Add Task");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();
        Label descriptionLabel = new Label("Description:");
        TextField descriptionField = new TextField();
        Label assigneeLabel = new Label("Assignee:");
        TextField assigneeField = new TextField();
        Label statusLabel = new Label("Status:");
        TextField statusField = new TextField();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Task task = new Task(titleField.getText(), descriptionField.getText(), assigneeField.getText(), statusField.getText());
            tasks.add(task);
            dialog.close();
        });

        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(descriptionLabel, 0, 1);
        grid.add(descriptionField, 1, 1);
        grid.add(assigneeLabel, 0, 2);
        grid.add(assigneeField, 1, 2);
        grid.add(statusLabel, 0, 3);
        grid.add(statusField, 1, 3);
        grid.add(addButton, 1, 4);

        Scene scene = new Scene(grid, 400, 300);
        dialog.setScene(scene);
        dialog.show();
    }

    private void showListTasksPanel(VBox vbox) {
        TableView<Task> table = new TableView<>();
        table.setItems(tasks);

        TableColumn<Task, Integer> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());

        TableColumn<Task, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitle()));

        TableColumn<Task, String> descriptionColumn = new TableColumn<>("Description");
        descriptionColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescription()));

        TableColumn<Task, String> assigneeColumn = new TableColumn<>("Assignee");
        assigneeColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAssignee()));

        TableColumn<Task, String> statusColumn = new TableColumn<>("Status");
        statusColumn.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStatus()));

        table.getColumns().addAll(idColumn, titleColumn, descriptionColumn, assigneeColumn, statusColumn);

        vbox.getChildren().clear();
        vbox.getChildren().add(table);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
