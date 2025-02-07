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
import nemo.NemoException;
import nemo.command.AddDeadlineCommand;
import nemo.command.AddEventCommand;
import nemo.command.AddTodoCommand;
import nemo.command.DeleteCommand;
import nemo.command.ExitCommand;
import nemo.command.FindCommand;
import nemo.command.ListCommand;
import nemo.command.MarkCommand;
import nemo.command.UnmarkCommand;

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

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getNemoDialog(String text, Image img, String commandStr) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandStr);
        return db;
    }

    private void changeDialogStyle(String commandStr) {
        switch(commandStr) {
        case "LIST":
            dialog.getStyleClass().add("reply-label");
            break;
        case "BYE":
            dialog.getStyleClass().add("bye-label");
            break;
        case "MARK":
            dialog.getStyleClass().add("mark-label");
            break;
        case "UNMARK":
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
