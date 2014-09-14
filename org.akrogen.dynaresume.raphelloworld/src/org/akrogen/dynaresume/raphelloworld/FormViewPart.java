package org.akrogen.dynaresume.raphelloworld;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.forms.widgets.*;

public class FormViewPart extends ViewPart {
	
	public static final String ID = "org.akrogen.dynaresume.raphelloworld.formviewpart";
	
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		FormToolkit toolkit = new FormToolkit(composite.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(composite);
		form.getBody().setLayout(new TableWrapLayout());
		toolkit.decorateFormHeading(form.getForm());
		form.setText("User Form");
		int sectionStyle = Section.TITLE_BAR | Section.DESCRIPTION | Section.TWISTIE | Section.EXPANDED;
		Section section = toolkit.createSection(form.getBody(), sectionStyle);
		section.setText("User Details");
		section.setDescription("Contains details of the current logged user");
		TableWrapData tableWrapData = new TableWrapData(TableWrapData.FILL_GRAB);
		tableWrapData.colspan = 1;
	    tableWrapData.grabHorizontal = true;
	    section.setLayoutData(tableWrapData);
	    Composite content = toolkit.createComposite(section);
	    content.setLayout(new GridLayout(2, false));
	    Label firstNameLabel = toolkit.createLabel(content, "<b>First Name:</b> ");
	    firstNameLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE );
	    toolkit.createLabel(content, "John" );
	    Label lastNameLabel = toolkit.createLabel( content, "<b>Last Name:</b> ");
	    lastNameLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    toolkit.createLabel(content, "Smith");
	    Label roleLabel = toolkit.createLabel( content, "<b>Role:</b> ");
	    roleLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    toolkit.createLabel(content, "Administrator");
	    section.setClient(content);
	}
	
	@Override
	public void setFocus() {}
}
