package encounters;

import javafx.scene.Node;
import javafx.scene.control.Label;

public class Person implements Encounterable {

    private String name;
    private Node node;
    Controller c;
    private Encounter firstEncounter;

    Person(Controller c, String name, Encounter firstEncounter){
        this(c, name);
        this.firstEncounter = firstEncounter;
    }

    Person(Controller c, String name) {
        this.c = c;
        setName(name);
    }

    void setName(String name) {
        this.name = name;
        c.lblTheirName.setText(name);
    }


    void setFirstEncounter(Encounter firstEncounter) {
        this.firstEncounter = firstEncounter;
    }

    public Node getNode() {
        return node;
    }

    public void encounter(){
        firstEncounter.begin();
    }

    public void talk(String words) {
        if (words != null) {
            c.themTalk(words);
        }
    }

    public void handleResponse(Option option) {

    }
}
