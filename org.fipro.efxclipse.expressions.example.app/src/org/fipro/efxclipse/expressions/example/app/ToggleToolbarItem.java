package org.fipro.efxclipse.expressions.example.app;
 

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBar;
import org.eclipse.e4.ui.model.application.ui.menu.MToolBarElement;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class ToggleToolbarItem {
	
	@Execute
	public void execute(MWindow window, EModelService modelService) {
		MPart mPart = (MPart) modelService.find("org.fipro.efxclipse.expressions.example.app.part.0", window);
		
		MToolBarElement second = mPart.getToolbar().getChildren().get(1);
		second.setVisible(!second.isVisible());
		
		MMenuElement m = window.getMainMenu().getChildren().get(1);
		m.setVisible(!m.isVisible());
		
		MToolBar trimStack = (MToolBar) modelService.find("org.fipro.e4.toolbar.example.toolbar.0", window);
		MToolBarElement tbe = trimStack.getChildren().get(1);
		tbe.setVisible(!tbe.isVisible());
	}
}