import java.awt.BorderLayout;
import java.awt.Point;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.SwingDispatchService;
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
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
		stage.setTitle("Script Creator");
		
		HBox layout = new HBox();
		Button butt1 = new Button("Record");
		Button butt2 = new Button("Code");
		Button butt3 = new Button("Load");
		butt1.setOnAction(e -> {stage.setScene(record);});
		butt2.setOnAction(e -> {stage.setScene(code);});
		butt3.setOnAction(e -> {stage.setScene(load);});
		layout.getChildren().addAll(butt1, butt2, butt3);
		
		main = new Scene(layout, 300, 250);
		stage.setScene(main);
		
		record = new Scene(new HBox(new Button("record")));
		code = new Scene(new HBox(new Button("code")));
		load = new Scene(new HBox(new Button("load")));
		
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
		say("pressed: " + e.getPoint());
	}

	@Override
	public void nativeMouseReleased(NativeMouseEvent e) {
		say("released: " + e.getPoint());
	}

	@Override
	public void nativeMouseMoved(NativeMouseEvent e) {
		//say("moved");
	}

	@Override
	public void nativeMouseDragged(NativeMouseEvent e) {
		say("drag");
	}

	@Override
	public void nativeMouseWheelMoved(NativeMouseWheelEvent e) {
		say("Scrolled: " + e.getWheelRotation());
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}


	

}
