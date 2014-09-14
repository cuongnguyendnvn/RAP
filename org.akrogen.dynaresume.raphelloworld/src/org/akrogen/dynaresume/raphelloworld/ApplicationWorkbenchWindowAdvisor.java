package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
	public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }
	
	public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
	
	public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(800, 600));
        configurer.setShowCoolBar(true);
        configurer.setShowStatusLine(true);
        configurer.setTitle("RAP Workbench Demo");
        configurer.setShowPerspectiveBar(true);
        configurer.setShellStyle( SWT.TITLE | SWT.MAX | SWT.RESIZE );
        configurer.setShowProgressIndicator(true);
    }
	
	public void postWindowOpen() {
	    IWorkbench workbench = PlatformUI.getWorkbench();
	    IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
	    Shell shell = window.getShell();
	    Rectangle shellBounds = shell.getBounds();
	    if( !shell.getMaximized() && shellBounds.x == 0 && shellBounds.y == 0 ) {
	      shell.setLocation( 70, 25 );
	    }
	  }
}
