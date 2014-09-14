package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.rap.rwt.application.EntryPoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.WorkbenchAdvisor;

public class Application implements EntryPoint {
	public int createUI() {
        Display display = PlatformUI.createDisplay();
        WorkbenchAdvisor advisor = new ApplicationWorkbenchAdvisor();
        return PlatformUI.createAndRunWorkbench( display, advisor );
    }
}
