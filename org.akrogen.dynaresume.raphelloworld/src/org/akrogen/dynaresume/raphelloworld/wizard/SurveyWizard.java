package org.akrogen.dynaresume.raphelloworld.wizard;

import org.eclipse.jface.wizard.Wizard;

public class SurveyWizard extends Wizard{
	public SurveyWizard() {
	    // Add the pages
	    addPage( new ComplaintsPage() );
	    addPage( new MoreInformationPage() );
	    addPage( new ThanksPage() );
	    setWindowTitle( "RAP Survey Wizard" );
	  }
	
	public boolean canFinish() {
	    return getContainer() != null && getContainer().getCurrentPage() instanceof ThanksPage;
	}
	
	public boolean performFinish() {
	    // Dismiss the wizard
	    return true;
	}
}
