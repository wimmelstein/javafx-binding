package nl.inholland;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    Label userLabel, passwordLabel, visiblePassword;
    TextField userInput;
    PasswordField passwordField;
    Button loginButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage window) throws Exception { // IMO window is a better name than stage
        window.setHeight(200);
        window.setWidth(350);
        window.setTitle("Login screen");

        VBox container = new VBox();
        userLabel = new Label("Username");
        passwordLabel = new Label("Password");
        visiblePassword = new Label();
        visiblePassword.setPadding(new Insets(10));

        userInput = new TextField();
        userInput.setPromptText("Username");

        passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        StringProperty passwordFieldProperty = passwordField.textProperty();
        visiblePassword.textProperty().bind(passwordFieldProperty);
        passwordFieldProperty.addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                loginButton.setVisible(isValidPassword(newValue));
            }
        });

        loginButton = new Button("Login");
        loginButton.setVisible(false);
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // Logic goes here
            }
        });
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(8);

        GridPane.setConstraints(userLabel, 0, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(userInput, 1,0);
        GridPane.setConstraints(passwordField, 1,1);
        GridPane.setConstraints(loginButton, 0,2);

        gridPane.getChildren().addAll(userLabel, passwordLabel, userInput, passwordField, loginButton);
        container.getChildren().addAll(gridPane, visiblePassword);

        Scene scene = new Scene(container);
        window.setScene(scene);
        window.show();

    }

    private Boolean isValidPassword(String password) {
        boolean hasLetters = false;
        boolean hasDigits = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigits = true;
            } else if (Character.isLetter(c)) {
                hasLetters = true;
            } else {
                hasSpecial = true;
            }
        }
        return password.length() > 7 && (hasLetters && hasDigits && hasSpecial);
    }
}
