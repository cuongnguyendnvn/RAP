package org.carter.peyton.training.rap.view;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

public class VersionPropertyFormViewPart extends ViewPart {
	
	public static final String ID = "org.carter.peyton.training.rap.VersionPropertyFormView";
	
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		FormToolkit toolkit = new FormToolkit(composite.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(composite);
		form.getBody().setLayout(new TableWrapLayout());
		toolkit.decorateFormHeading(form.getForm());
		//form.setText("User Form");
		int sectionStyle = Section.TITLE_BAR;
		Section section = toolkit.createSection(form.getBody(), sectionStyle);
		section.setText("Version Properties");
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
