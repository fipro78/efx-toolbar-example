package org.fipro.efxclipse.expressions.example.app;

import javax.inject.Inject;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.internal.events.EventBroker;
import org.eclipse.e4.ui.workbench.UIEvents;

public class ToggleMenuItem {

	public static final String KEY = "debugEnabled"; //$NON-NLS-1$
	
	public static final String ENABLED = "ENABLED";
	public static final String DISABLED = "DISABLED";
	
	@Inject
	EventBroker eventBroker;
	
	@Execute
	public void execute(IEclipseContext context) {
		String enabled = context.get(KEY).toString();
		context.modify(KEY, ENABLED.equals(enabled) ? DISABLED : ENABLED);
		
		// we changed the value that is used for the core expression
		// trigger updates via IEventBroker
		eventBroker.post(UIEvents.REQUEST_ENABLEMENT_UPDATE_TOPIC,
				UIEvents.ALL_ELEMENT_ID);
	}
}
