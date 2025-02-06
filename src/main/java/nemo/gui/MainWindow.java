package nemo.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nemo.Nemo;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Nemo nemo;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Dory.png"));
    private Image nemoImage = new Image(this.getClass().getResourceAsStream("/images/Nemo.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Nemo instance */
    public void setNemo(Nemo d) {
        nemo = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nemo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = nemo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNemoDialog(response, nemoImage)
        );
        userInput.clear();
    }
}
