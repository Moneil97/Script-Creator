import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;
import org.jnativehook.mouse.NativeMouseMotionListener;
import org.jnativehook.mouse.NativeMouseWheelEvent;
import org.jnativehook.mouse.NativeMouseWheelListener;

public class ScriptCreator extends Application implements NativeMouseInputListener, NativeMouseMotionListener, NativeMouseWheelListener{

	/** Logging */
	private static final Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
	
	Scene main, record, code, load;
	
	@Override
	public void init() throws Exception {
		super.init();
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Add listeners
		GlobalScreen.addNativeMouseListener(this);
		GlobalScreen.addNativeMouseMotionListener(this);
		GlobalScreen.addNativeMouseWheelListener(this);
		
		// Disable parent logger and set the desired level.
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.ALL);
		
	}
		
	
	@Override
	public void stop() throws Exception {
		super.stop();
		System.exit(0);
	}
	
	VBox events;
	ScrollPane eventsScrollPane;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Script Creator");
		
		eventsScrollPane = new ScrollPane();
		eventsScrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		events = new VBox();
		//events.getChildren().add(new Label("hi"));
		events.getChildren().add(new EventLabel("Start"));
		eventsScrollPane.setContent(events);
		main = new Scene(eventsScrollPane, 300, 250);
		stage.setScene(main);
		
		stage.show();
	}

	protected void say(Object o) {
		System.out.println(o);
	}

	@Override
	public void nativeMouseClicked(NativeMouseEvent e) {
		//say(e.getPoint());
	}

	@Override
	public void nativeMousePressed(NativeMouseEvent e) {
		//say("pressed: " + e.getPoint());
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		//say("released: " + e.getPoint());
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		//say("moved");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		//say("drag");
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		//say("Scrolled: " + e.getWheelRotation());
		Platform.runLater( () -> {
			events.getChildren().add(new EventLabel("Scrolled: " + e.getWheelRotation()));
			eventsScrollPane.setVvalue(1.0);
		});
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
