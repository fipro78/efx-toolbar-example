 
package org.fipro.efxclipse.expressions.example.app;

import org.eclipse.e4.core.di.annotations.Execute;

public class SimpleHandler {
	
	@Execute
	public void execute() {
		System.out.println("Do nothing!");
	}
		
}