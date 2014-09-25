package org.carter.peyton.training.rap.view.action;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;

public class AddProjectViewToolbar extends Action implements IWorkbenchAction {

    private static final long serialVersionUID = -8598126942837174117L;

    private static final String ID = "com.carter.peyton.training.rap.view.action.AddProjectViewToolbar";

    public AddProjectViewToolbar() {
        setId(ID);
    }

    @Override
    public void run() {

        Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow()
                .getShell();
        String dialogBoxTitle = "Message";
        String message = "You clicked something!";
        MessageDialog.openInformation(shell, dialogBoxTitle, message);
    }

    @Override
    public void dispose() {
    }

}
