package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MoreInformationPage extends WizardPage{
	
	protected MoreInformationPage() {
		super("More Info");
		setTitle("More Informations");
		setMessage("Please enter your comment", IMessageProvider.WARNING);
		setPageComplete( false );
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		
		// Set Label
		Label label = new Label(composite, SWT.LEFT);
		label.setText("Please enter your complaints");
		
		// Set Textbox
		final Text textbox = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		textbox.setLayoutData(new GridData(GridData.FILL_BOTH));
		textbox.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent event) {
				if(textbox.getText().length() > 0) {
					setMessage("Great!", IMessageProvider.INFORMATION);
					setPageComplete(true);
				} else {
					setMessage("Please enter your comment", IMessageProvider.WARNING);
					setPageComplete(false);
				}
			}
		});
		
		// required to avoid an error in the system
	    setControl(composite);
	}
}
