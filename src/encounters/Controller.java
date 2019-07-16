package encounters;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Controller {
    @FXML
    VBox boxThem;

    @FXML
    Label lblYourName;

    @FXML
    Label lblLocation;

    @FXML
    Label lblYourSpeech;

    @FXML
    Label lblTheirName;

    @FXML
    Label lblTheirSpeech;

    @FXML
    TextAreaAutoScroll txtStory;

    @FXML
    VBox boxButtons;

    public void initialize(){
        Innkeeper innkeeper = new Innkeeper(this, "The Innkeeper");
        innkeeper.encounter();
    }

    void youTalk(String talk) {
        if (talk == null) {
            return;
        }
        lblYourSpeech.setText(talk);
        tellStory("\"" + getDialog(talk) + "\" you say.");
    }

    String getDialog(String text) {
        if (text.endsWith(".")) {
            text = text.substring(0,text.length()-1);
        } else if (text.endsWith("?") || text.endsWith("!")) {
            return text;
        }
        return text + ",";
    }

    void themTalk(String talk) {
        lblTheirSpeech.setText(talk);
        tellStory("\"" + getDialog(talk) + "\" says " + lblTheirName.getText() + ".");
    }

    void tellStory(String story) {
        if (story.equals("")) {
            return;
        }
        txtStory.appendText(story + "\n");
    }

    void showCharacterChooser() {
        boxButtons.getChildren().clear();
        //add a button for each character
        boxButtons.getChildren().addAll(new Button());
    }

    boolean hasSavedCharacters() {
        //check for saved characters
        return false;
    }
}
