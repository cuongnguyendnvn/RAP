package org.carter.peyton.training.rap.application.perspective;

import org.carter.peyton.training.rap.view.DevicesSelectionViewPart;
import org.carter.peyton.training.rap.view.ProjectsTreeViewPart;
import org.carter.peyton.training.rap.view.VersionPropertyFormViewPart;
import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class UsersPerspective implements IPerspectiveFactory {

    @Override
    public void createInitialLayout(IPageLayout layout) {
        String editorLayout = layout.getEditorArea();
        layout.setEditorAreaVisible(false);
        layout.setFixed(true);

        IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.50f, editorLayout);
        topLeft.addView(ProjectsTreeViewPart.ID);
        topLeft.addView(ProjectsTreeViewPart.ID_DRIVER);
        topLeft.addView(ProjectsTreeViewPart.ID_AREA);

        IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.TOP, 0.60f, editorLayout);
        topRight.addView(DevicesSelectionViewPart.ID);

        layout.addStandaloneView(VersionPropertyFormViewPart.ID, false, IPageLayout.BOTTOM, 0.65f, "topLeft");
    }

}
