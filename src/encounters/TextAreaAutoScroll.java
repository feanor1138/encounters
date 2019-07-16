package encounters;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public class TextAreaAutoScroll extends TextArea {
    public TextAreaAutoScroll() {
        super();
        this.textProperty().addListener((ObservableValue<?> observable, Object oldValue, Object newValue) ->
                this.setScrollTop(Double.MAX_VALUE));
    }
}
