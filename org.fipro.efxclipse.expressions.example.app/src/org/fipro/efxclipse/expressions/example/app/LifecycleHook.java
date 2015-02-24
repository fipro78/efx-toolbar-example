package org.fipro.efxclipse.expressions.example.app;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.lifecycle.ProcessAdditions;

public class LifecycleHook {

	@ProcessAdditions
	public void processAdditions(IEclipseContext context) {
		context.set(ToggleMenuItem.KEY, ToggleMenuItem.DISABLED);
		context.declareModifiable(ToggleMenuItem.KEY);
	}
}
