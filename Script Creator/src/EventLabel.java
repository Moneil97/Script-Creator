import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;

public class EventLabel extends BorderPane{

	public EventLabel(String label) {
		
		this.getStyleClass().add("simpleBorder");
		getStylesheets().add(getClass().getResource("Creator.css").toExternalForm());
		
		this.setPrefSize(200, 30);
		
		setCenter(new Label(label));
		setRight(new Button("Del"));
		
		
	}

}
