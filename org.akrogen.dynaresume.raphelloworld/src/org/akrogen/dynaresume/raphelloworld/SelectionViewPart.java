package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.part.ViewPart;

public class SelectionViewPart extends ViewPart {
	
	public static final String ID = "org.akrogen.dynaresume.raphelloworld.selectionviewpart";
	private List list;
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Label label = new Label(parent, SWT.NONE);
		label.setText("Selection Log");
		list = new List(parent, SWT.FLAT | SWT.H_SCROLL | SWT.V_SCROLL);
		list.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}
	
	@Override
	public void setFocus() {}
}
