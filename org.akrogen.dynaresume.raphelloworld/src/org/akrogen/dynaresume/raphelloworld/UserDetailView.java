package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;

public class UserDetailView extends ViewPart {

	public static final String ID = "org.akrogen.dynaresume.raphelloworld.userdetailview";
	
	public UserDetailView() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createPartControl(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        
        // User
        final Label userLabel = new Label(composite, SWT.NONE);
        GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
        gridData.horizontalSpan = 2;
        userLabel.setLayoutData(gridData);
        
		// User name
	    Label nameLabel = new Label(composite, SWT.NONE);
	    nameLabel.setText("Name:");
	    final Text nameText = new Text(composite, SWT.BORDER);
	    nameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	 
	    // User lastName
	    Label lastNameLabel = new Label(composite, SWT.NONE);
	    lastNameLabel.setText("Last name:");
	    final Text lastNameText = new Text(composite, SWT.BORDER);
	    lastNameText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    
	    // Add Listener
	    nameText.addModifyListener(new ModifyListener() {          
	        @Override
	        public void modifyText(ModifyEvent event) {
	            userLabel.setText(nameText.getText() + " " + lastNameText.getText());
	        }
	    });
	    // Add Listener
	    lastNameText.addModifyListener(new ModifyListener() {          
	        @Override
	        public void modifyText(ModifyEvent event) {
	            userLabel.setText(nameText.getText() + " " + lastNameText.getText());
	        }
	    });
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

}
