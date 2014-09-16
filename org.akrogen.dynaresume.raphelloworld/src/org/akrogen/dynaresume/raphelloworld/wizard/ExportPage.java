package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IExportWizard;
import org.eclipse.ui.IWorkbench;

public class ExportPage extends Wizard implements IExportWizard {

	private class ExportWizardPage extends WizardPage {
		public ExportWizardPage() {
			super("Demo Export Page");
			setTitle("Demo Export Page");
			setDescription("This is the demo export wizard page");
		}
		
		@Override
		public void createControl(Composite parent) {
			Label label = new Label(parent, SWT.NONE);
			label.setText("Demo Export Page Content");
			setControl(label);
		}
	}
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(new ExportWizardPage());
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {}
}
