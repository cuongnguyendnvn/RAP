package org.akrogen.dynaresume.raphelloworld.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class BarEditor extends EditorPart {
	
	private Text editor;
	
	public BarEditor() {
		super();
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
		MessageDialog.openInformation(getSite().getShell(), "Foo Editor", "Saved");
	}
	
	@Override
	public void doSaveAs() {}
	
	@Override
	public void init(IEditorSite site, IEditorInput input)
			throws PartInitException {
		setSite(site);
		setInput(input);
	}
	
	protected boolean dirty = false;
	
	@Override
	public boolean isDirty() {
		return dirty;
	}
	
	protected void setDirty(boolean value) {
		dirty = value;
		firePropertyChange(PROP_DIRTY);
	}
	
	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new FillLayout());
		editor = new Text(parent, SWT.MULTI);
		editor.setText("");
		editor.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent event) {
				if (editor.getText() != "") {
					setDirty(true);
				} else {
					setDirty(false);
				}
			}
		});
	}
	
	@Override
	public void setFocus() {
		editor.setFocus();
	}
}
