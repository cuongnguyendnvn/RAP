package org.carter.peyton.training.rap.application;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

public class Application implements EntryPoint {
    
    @Override
    public int createUI() {
        Display display = PlatformUI.createDisplay();
        WorkbenchAdvisor workbenchAdvisor = new ApplicationWorkbenchAdvisor();
        return PlatformUI.createAndRunWorkbench(display, workbenchAdvisor);
    }
}
