package org.akrogen.dynaresume.raphelloworld;

import org.akrogen.dynaresume.raphelloworld.model.TableModel;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
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
		createSelectionListener();
	}
	
	@Override
	public void setFocus() {
		list.setFocus();
	}
	
	private void createSelectionListener() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		ISelectionService selectionService = window.getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				String entry = part.getTitle() + "/";
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object fistElement = structuredSelection.getFirstElement();
				if (fistElement == null) {
					entry += "null";
				} else {
					if (fistElement instanceof TableModel) {
						entry += "Table count: " + structuredSelection.size();
					} else {
						entry += fistElement.toString();
					}
				}
				
				if (!list.isDisposed()) {
					list.add(entry, 0);
					list.setSelection(0);
				}
			}
		});
	}
}
