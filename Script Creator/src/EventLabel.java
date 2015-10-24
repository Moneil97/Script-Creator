import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class EventLabel extends BorderPane{

	public EventLabel(String label) {
		
		this.getStyleClass().add("simpleBorder");
		getStylesheets().add(getClass().getResource("Creator.css").toExternalForm());
		
		this.setPrefSize(200, 30);
		
		setCenter(new Label(label));
		Button delete = new Button("Del");
		delete.setOnAction(e -> ScriptCreator.events.getChildren().remove(this));
		setRight(delete);
		
	}

}

class EditableLabel extends TextField{
	
	public EditableLabel() {
		setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER)){
				String text = getText().trim().toLowerCase();
				if (text.startsWith("press")){
					text = text.substring("press".length()).trim();
					char c = text.charAt(0);
					ScriptCreator.events.getChildren().add(new EventLabel("Pressed: " + c));
				}
				
				ScriptCreator.events.getChildren().remove(this);
			}
					
				
		});
	}
	
}
