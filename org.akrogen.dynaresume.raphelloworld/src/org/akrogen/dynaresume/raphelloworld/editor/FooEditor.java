package org.akrogen.dynaresume.raphelloworld.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

public class FooEditor extends MultiPageEditorPart {

	private BarEditor editor;
	private BazEditor treeeditor;
	private IContentOutlinePage outlinePage;

	public FooEditor() {
		super();
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		editor.setDirty(false);
		treeeditor.setDirty(false);
	}

	@Override
	public void doSaveAs() {
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public Object getAdapter(Class adapter) {
		Object result = super.getAdapter(adapter);
		if (adapter == IContentOutlinePage.class) {
			if (outlinePage == null) {
				outlinePage = new IContentOutlinePage() {

					private Tree tree;

					@Override
					public void createControl(final Composite parent) {
						tree = new Tree(parent, SWT.SINGLE);
						for (int i = 0; i < 4; i++) {
							TreeItem item = new TreeItem(tree, SWT.NONE);
							item.setText("Node_" + (i + 1));
							if (i < 3) {
								TreeItem subitem = new TreeItem(item, SWT.NONE);
								subitem.setText("Subnode_" + (i + 1));
							}
						}
					}

					public void dispose() {
					}

					public Control getControl() {
						return tree;
					}

					public void setFocus() {
					}

					public ISelection getSelection() {
						return null;
					}

					@Override
					public void setActionBars(IActionBars actionBars) {
					}

					@Override
					public void addSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					@Override
					public void removeSelectionChangedListener(
							ISelectionChangedListener listener) {
					}

					@Override
					public void setSelection(ISelection selection) {
					}

				};
			}
			result = outlinePage;
		}
		return result;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		super.init(site, input);
		setPartName(input.getName());
	}

	@Override
	protected void createPages() {
		editor = new BarEditor();
		treeeditor = new BazEditor();
		int index;
		try {
			ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();
			index = addPage(editor, getEditorInput());
			setPageText(index, "Source");
			setPageImage(index,
					sharedImages.getImage(ISharedImages.IMG_OBJ_FILE));
			index = addPage(treeeditor, getEditorInput());
			setPageText(index, "Design");
			setPageImage(index,
					sharedImages.getImage(ISharedImages.IMG_OBJ_FOLDER));
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
}
