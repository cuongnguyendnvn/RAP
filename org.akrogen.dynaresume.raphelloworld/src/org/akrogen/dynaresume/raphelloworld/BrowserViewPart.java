package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;

public class BrowserViewPart extends ViewPart{
	
	Browser browser;
	
	public static final String ID = "org.akrogen.dynaresume.raphelloworld.browserViewPart";
	private static String linkView = "http://www.eclipse.org/birt/phoenix/examples/solution/TopSellingProducts.html";
	
	@Override
	public void createPartControl(Composite parent) {
		browser = new Browser(parent, SWT.None);
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		ISelection selection = window.getSelectionService().getSelection();
		setUrlFromSelection(selection);
		createSelectionListener();
	}
	
	@Override
	public void setFocus() {
		browser.setFocus();
	}
	
	private void createSelectionListener() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		ISelectionService selectionService = window.getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				setUrlFromSelection( selection );
			}
		});
	}
	
	private void setUrlFromSelection(ISelection selection) {
		if (!browser.isDisposed()) {
			browser.setUrl(linkView);
			if (selection != null) {
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object firstElement = structuredSelection.getFirstElement();
				/*if (firstElement instanceof TreeObject) {
					
				}*/
			}
		}
	}
}
