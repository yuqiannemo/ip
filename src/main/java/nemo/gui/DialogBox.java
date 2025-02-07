package nemo.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates and returns a user dialog box with the specified text and image.
     * This method is used to create a dialog box for the user.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed alongside the text.
     * @return A DialogBox object containing the user's dialog with the specified text and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a Nemo dialog box with the specified text, image, and command string.
     * This method creates a dialog box for Nemo and applies a style based on the given command.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed alongside the text.
     * @param commandStr The command string that influences the dialog box's style.
     * @return A DialogBox object containing Nemo's dialog with the specified text, image, and style.
     */
    public static DialogBox getNemoDialog(String text, Image img, String commandStr) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandStr);
        return db;
    }

    /**
     * Changes the style of the dialog box based on the given command string.
     * The dialog box will be styled according to the command type (e.g., BYE, MARK, DELETE, etc.).
     *
     * @param commandStr The command string that determines the style of the dialog box.
     */
    private void changeDialogStyle(String commandStr) {
        switch(commandStr) {
        case "BYE":
            dialog.getStyleClass().add("bye-label");
            break;
        case "MARK", "UNMARK":
            dialog.getStyleClass().add("mark-label");
            break;
        case "DELETE":
            dialog.getStyleClass().add("delete-label");
            break;
        case "FIND":
            dialog.getStyleClass().add("find-label");
            break;
        default:
            dialog.getStyleClass().add("reply-label");
        }
    }
}
