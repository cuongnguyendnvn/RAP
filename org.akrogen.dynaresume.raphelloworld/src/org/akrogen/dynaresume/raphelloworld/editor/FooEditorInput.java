package org.akrogen.dynaresume.raphelloworld.editor;

import org.akrogen.dynaresume.raphelloworld.ApplicationActionBarAdvisor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class FooEditorInput implements IEditorInput {
	
	public FooEditorInput(ApplicationActionBarAdvisor actionBarAdvisor) {}
	
	@Override
	public boolean exists() {
		return true;
	}

	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}
	
	@Override
	public String getName() {
		return this.hashCode() + ".bar";
	}
	
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}
	
	@Override
	public String getToolTipText() {
		return "/foo/bar/" + getName();
	}
}
