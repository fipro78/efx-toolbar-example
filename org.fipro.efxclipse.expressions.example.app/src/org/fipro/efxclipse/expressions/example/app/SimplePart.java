package org.fipro.efxclipse.expressions.example.app;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.workbench.UIEvents;

public class SimplePart {
	
	@Inject
	IEventBroker eventBroker;
	
	Button debugOn;
	Button debugOff;
	
	@PostConstruct
	public void postConstruct(BorderPane parent, final IEclipseContext context) {
		
		debugOn = new Button();
		debugOn.setText("Debug On");
		
		debugOff = new Button();
		debugOff.setText("Debug Off");

		String enabled = context.get(ToggleMenuItem.KEY).toString();
		Boolean debugEnabled = ToggleMenuItem.ENABLED.equals(enabled);
		debugOn.setDisable(debugEnabled);
		debugOff.setDisable(!debugEnabled);
		
		debugOn.setOnAction(e -> {
			context.modify(ToggleMenuItem.KEY, ToggleMenuItem.ENABLED);
			
			debugOn.setDisable(true);
			debugOff.setDisable(false);
			
			// we changed the value that is used for the core expression
			// trigger updates via IEventBroker
			eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
					UIEvents.ALL_ELEMENT_ID);
		});
		
		
		debugOff.setOnAction(e -> {
			context.modify(ToggleMenuItem.KEY, ToggleMenuItem.DISABLED);
			
			debugOn.setDisable(false);
			debugOff.setDisable(true);
			
			// we changed the value that is used for the core expression
			// trigger updates via IEventBroker
			eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
					UIEvents.ALL_ELEMENT_ID);
		});
		
		HBox box = new HBox(debugOn, debugOff);
		
		parent.setCenter(box);
	}
	
	@Inject
	@Optional
	private void subscribeToEnablement(
			@UIEventTopic(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC) String test, 
			@Named(ToggleMenuItem.KEY) String enabled) {
		
		Boolean debugEnabled = ToggleMenuItem.ENABLED.equals(enabled);
		
		debugOn.setDisable(debugEnabled);
		debugOff.setDisable(!debugEnabled);
	}
}