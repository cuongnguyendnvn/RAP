package org.akrogen.dynaresume.raphelloworld.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class SampleAction implements IWorkbenchWindowActionDelegate{
	
	private IWorkbenchWindow window;
	
	@Override
	public void dispose() {}
	
	@Override
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
	
	@Override
	public void run(IAction action) {
		MessageDialog.openInformation(window.getShell(), "RAP Demo Plug-in", "Hello, Eclipse world");
	}
	
	@Override
	public void selectionChanged(IAction action, ISelection selection) {}
}
