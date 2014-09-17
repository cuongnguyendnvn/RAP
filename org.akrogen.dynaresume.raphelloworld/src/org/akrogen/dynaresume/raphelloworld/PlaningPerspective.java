package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PlaningPerspective implements IPerspectiveFactory{
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(true);
		
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f, editorArea);
		topLeft.addView(SelectionViewPart.ID);
		topLeft.addView(BrowserViewPart.ID);
		
		IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 0.50f, "topLeft");
		bottomLeft.addView(TreeViewPart.ID);
		
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.70f, editorArea);
		right.addView(TableViewPart.ID);
		
		// add shortcuts to show view menu
	    layout.addShowViewShortcut(ChartViewPart.ID);
	    layout.addShowViewShortcut(TreeViewPart.ID_II);
	    // add shortcut for other perspective
	    layout.addPerspectiveShortcut("org.akrogen.dynaresume.raphelloworld.demoperspective");
	}
}
