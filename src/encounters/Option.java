package encounters;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Option {

    private Controller c;
    private Button b;
    private int optionNumber;
    private Encounter myEncounter;
    private String youTalk = null;

    Option(Encounter myEncounter, Controller c, String optionText, String youTalk, int optionNumber) {
        this(myEncounter, c, optionText, optionNumber);
        this.youTalk = youTalk;
    }

    Option(Encounter myEncounter, Controller c, String optionText, int optionNumber) {
        this.c = c;
        this.optionNumber = optionNumber;
        this.myEncounter = myEncounter;
        this.b = new Button(optionText);
        b.setId("option" + optionNumber);
        b.setWrapText(true);
        this.b.setOnAction((ActionEvent e) -> {
            if (this.youTalk != null) {
                this.c.youTalk(this.youTalk);
            } else {
                this.c.youTalk(b.getText());
            }
            myEncounter.optionChosen(this);
        });
    }

    Encounter getMyEncounter(){
        return myEncounter;
    }

    void display() {
        c.boxButtons.getChildren().add(b);
    }

    Button getButton() {
        return b;
    }

    int getOptionNumber() {
        return optionNumber;
    }
}
