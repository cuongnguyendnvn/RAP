package org.carter.peyton.training.rap.application.perspective;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class LogoutPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);
    }

}
