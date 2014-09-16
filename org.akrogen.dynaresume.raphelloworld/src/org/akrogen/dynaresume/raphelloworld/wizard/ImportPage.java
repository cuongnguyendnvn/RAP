package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

public class ImportPage extends Wizard implements IImportWizard{
	
	private class ImportWizardPage extends WizardPage {
		/**
		 * 
		 */
		private static final long serialVersionUID = -3652752971915140952L;

		private ImportWizardPage() {
			super("Demo Import Page");
			setTitle("Demo Import Page");
			setDescription("This is the demo import wizard page.");
		}
		
		@Override
		public void createControl(Composite parent) {
			Label label = new Label(parent, SWT.NONE);
			label.setText("Demo Import Page Content");
			setControl(label);
		}
	}
	
	@Override
	public void addPages() {
		super.addPages();
		addPage(new ImportWizardPage());
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}
	
	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {}
}
