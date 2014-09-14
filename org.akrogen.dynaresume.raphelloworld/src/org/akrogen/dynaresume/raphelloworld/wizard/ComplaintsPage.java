package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ComplaintsPage extends WizardPage {

	private Button radioButtonYes;
	private Button radioButtonNo;
	
	public ComplaintsPage() {
		super("Complaints");
		setTitle("Complaints");
		setErrorMessage("You need to select at least one entry");
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		// Set label
		Label label = new Label(composite, SWT.LEFT);
		label.setText("Do you have complaints?");
		
		Composite radioComposite = new Composite(composite, SWT.NONE);
		radioComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		// Set radiobutton
		radioButtonYes = new Button(radioComposite, SWT.RADIO);
		radioButtonYes.setText("Yes");
		radioButtonYes.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(true);
				setErrorMessage(null);
			}
		});
		
		radioButtonNo = new Button(radioComposite, SWT.RADIO);
		radioButtonNo.setText("No");
		radioButtonNo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(true);
				setErrorMessage(null);
			}
		});
		
		// required to avoid an error in the system
	    setControl(composite);
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(radioButtonYes.getSelection()) {
			return super.getNextPage();
		}
		
		return getWizard().getPage("Thanks");
	}
	
	@Override
	public boolean canFlipToNextPage() {
		if( radioButtonYes.getSelection() || radioButtonNo.getSelection() ) {
		      return true;
		}
		
		setErrorMessage( "You need to select at least one entry" );
	    return false;
	}
}
