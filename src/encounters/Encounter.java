package encounters;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.HashMap;

class Encounter {
    ArrayList<Option> options = new ArrayList<>();
    private Controller c;
    private Encounterable encounterable;
    private HashMap<String,String> hashData = new HashMap<>();
    private int index;
    private String openingStoryText;
    private String openingThemText;
    private String openingYouText;

    Encounter(int index, Encounterable encounterable, Controller c, String storyText, String themText) {
        this(index, encounterable, c);
        this.openingStoryText = storyText;
        this.openingThemText = themText;
    }

    Encounter(int index, Encounterable encounterable, Controller c, String storyText) {
        this(index, encounterable, c);
        this.openingStoryText = storyText;
    }

    Encounter(int index, Encounterable encounterable, Controller c) {
        this.c = c;
        this.index = index;
        this.encounterable = encounterable;
    }

    void setOpeningStoryText(String storyText) {
        this.openingStoryText = storyText;
    }

    void setOpeningThemText(String themText) {
        this.openingThemText = themText;
    }

    void setOpeningYouText(String youText) {
        this.openingYouText = youText;
    }

    Option addOption(String text) {
        Option o = new Option(this, c, text, options.size()+1);
        this.options.add(o);
        return o;
    }

    void addOption(String text, String youText) {
        Option o = new Option(this, c, text, youText, options.size()+1);
        this.options.add(o);
    }

    int getIndex() {
        return this.index;
    }

    void begin() {
        c.tellStory(openingStoryText);
        encounterable.talk(openingThemText);
        c.youTalk(openingYouText);
        c.boxButtons.getChildren().clear();
        for (Option o : options) {
            o.display();
        }
    }

    private void addData(String key, String value) {
        hashData.put(key, value);
    }

    String getData(String key) {
        return hashData.get(key);
    }

    void optionChosen(Option o) {
        //check if there are other inputs, if so, send that data through
        ObservableList<Node> children = this.c.boxButtons.getChildren();
        for (Node child : children) {
            if (child instanceof TextField) {
                if (child.getId() != null) {
                    addData(child.getId(), ((TextField) child).getText());
                }
            }
        }
        encounterable.handleResponse(o);
    }
}
