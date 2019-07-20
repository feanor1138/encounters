package encounters;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.regex.Pattern;

public class Innkeeper extends Person {
    private static final String intro = "You enter The Prancing Pony Inn and sit down at the bar. You encounter the Innkeeper, who smiles and begins speaking to you.";

    @Override
    public void encounter() {
        super.encounter();
    }

    Innkeeper(Controller c, String name) {
        super(c, name);

        //maybe instead of having the options call the next encounter,
        //on the button click, the option would call a method on the encounterable that owns the encounter,
        //and pass back the selected response.
        //then the encounterable would have a logic structure that would handle the response.

        Encounter e1 = new Encounter(EncounterName.INNKEEPER_START, this, c, intro,
                "Hi, there. Come around here much?");
        e1.addOption("Yes, you fool. Don't you remember me?");
        e1.addOption("Never. What's good?");
        setFirstEncounter(e1);
    }

    private void startCharacterCreate(String openingText) {
        Encounter characterCreate1 = new Encounter(EncounterName.INNKEEPER_NAME,this, c,
                "You continue your conversation with the Innkeeper, and find as you answer the questions " +
                        "he asks you about yourself, your understanding of yourself becomes clearer.",
                openingText);
        Option o = characterCreate1.addOption("My name is...");
        Button b = o.getButton();
        b.setDisable(true);
        characterCreate1.begin();
        TextField txtName = new TextField();
        txtName.setId("txtYourName");
        txtName.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 50) {
                txtName.setText(oldValue);
            }
            b.setDisable(!validName(newValue));
            b.setText("My name is " + newValue + ".");
        });
        c.boxButtons.getChildren().add(txtName);
    }

    private boolean validName(String name) {
        if (name.trim().equals("")) {
            return false;
        }
        return Pattern.matches("^[a-zA-Z0-9_\\s]*$", name);
    }

    public void handleResponse(Option option) {
        Encounter encounter = option.getMyEncounter();
        switch (encounter.getName()) {
            case INNKEEPER_START:
                switch (option.getOptionNumber()) {
                    case 1:
                        //yes, don't you remember?
                        if (c.hasSavedCharacters()) {
                            //c.showCharacterChooser();
                            //create a new encounter with each character as an option
                        } else {
                            startCharacterCreate("Nope, sorry, you don't look familiar. " +
                                    "What's your name again?");
                        }
                        break;
                    case 2:
                        //never, what's good
                        if (c.hasSavedCharacters()) {
                            //new encounter
                            //innkeeper says, "You sure? You look familiar."
                            //options are, "I'm sure," which kicks off character creation encounters
                            //and remaining options are, "Just kidding you. I'm <character name>."
                        } else {
                            startCharacterCreate("Everything! What's your name, stranger?");
                        }
                        break;
                    default:
                }
                break;
            case INNKEEPER_NAME:
                c.lblYourName.setText(encounter.getData("txtYourName"));
                Encounter characterCreate2 = new Encounter(EncounterName.INNKEEPER_CLASS, this, c, "",
                        "Ah, good to meet you. I'm Frum, this here's my place." +
                                " And what's your profession then?");
                characterCreate2.addOption("Thief", "I'm a liberator, actually. I liberate rich fools from their money.");
                characterCreate2.addOption("Bard", "I, good sir, am an artist, and a lover of beauty.");
                characterCreate2.addOption("Mercenary", "I kill things. For money. It's a living.");
                characterCreate2.addOption("Wizard", "I am a wielder of the great mystery, a practitioner of the great art of change. In short, my friend, I know a few spells.");
                //below is throwing off display, too long. How to fix??
                setName("Frum the Innkeeper");
                characterCreate2.begin();
                break;
            case INNKEEPER_CLASS:
                //class selection
                switch (option.getOptionNumber()) {
                    case 1:
                        //thief
                        //strength: stealth
                        break;
                    case 2:
                        //bard
                        //strength: charm
                        break;
                    case 3:
                        //mercenary
                        //strength: fight
                        break;
                    case 4:
                        //wizard
                        //strength: magic
                        break;
                    default:
                }
                break;
            default:
        }
    }
}
