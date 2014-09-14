package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ThanksPage extends WizardPage {
	
	protected ThanksPage() {
		super("Thanks");
		setTitle("Last page");
		setPageComplete(true);
	}
	
	@Override
	public void createControl(Composite parent) {
		
		// Set Label
		Label label = new Label(parent, SWT.CENTER);
		label.setText("Thanks!");
		
		// required to avoid an error in the system
	    setControl(label);
	}
}
