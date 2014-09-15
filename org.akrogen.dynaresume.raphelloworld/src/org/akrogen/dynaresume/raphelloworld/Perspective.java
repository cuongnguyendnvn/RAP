package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {
	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
	    layout.setEditorAreaVisible(false);
	    
	    //
	    IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.25f, editorArea);
	    topLeft.addView(ChartViewPart.ID);
	    
	    //
	    IFolderLayout bottom = layout.createFolder("bottom", IPageLayout.BOTTOM, 0.60f, editorArea);
	    bottom.addView(FormViewPart.ID);
	    
	    //
	    IFolderLayout bottomLeft = layout.createFolder("bottomLeft", IPageLayout.BOTTOM, 9.50f, "topLeft");
	    bottomLeft.addView(TreeViewPart.ID);
	    
	    //
	    IFolderLayout topRight = layout.createFolder("topRight", IPageLayout.RIGHT, 0.70f, editorArea);
	    topRight.addView(SelectionViewPart.ID);
	    
	    // add shortcuts to show view menu
	    layout.addShowViewShortcut(ChartViewPart.ID);
    }
}
