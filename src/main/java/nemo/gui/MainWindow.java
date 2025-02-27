package nemo.gui;

import java.util.Map;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import nemo.Nemo;
import nemo.Ui;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    public static final String FAREWELL_MESSAGE = "Bye Bye, see you soon!\n";
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

    /**
     * Injects the Nemo instance
     */
    public void setNemo(Nemo d) {
        nemo = d;
        Ui ui = new Ui();
        String welcomeMessage = ui.getWelcomeMessage();
        dialogContainer.getChildren().add(DialogBox.getNemoDialog(welcomeMessage, nemoImage, ""));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Nemo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Map.Entry<String, String> result = nemo.getResponse(input);
        assert result != null : "User input should not be null";
        String response = result.getKey();
        String command = result.getValue();
        if (response.equals(FAREWELL_MESSAGE)) {
            ReminderScheduler.stopScheduler();
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1),
                    event -> Platform.exit()));
            timeline.setCycleCount(1);
            timeline.play();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNemoDialog(response, nemoImage, command)
        );
        userInput.clear();
    }
}
