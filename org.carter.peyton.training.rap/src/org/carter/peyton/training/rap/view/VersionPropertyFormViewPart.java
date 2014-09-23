package org.carter.peyton.training.rap.view;

import org.carter.peyton.training.rap.dao.impl.ProjectDAOImpl;
import org.carter.peyton.training.rap.models.Version;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.part.ViewPart;

public class VersionPropertyFormViewPart extends ViewPart {
	
	public static final String ID = "org.carter.peyton.training.rap.VersionPropertyFormView";
	Text versionNameText;
	Text projectVersionText;
	Text deployTimeText;
	Text deploySourceText;
	Text saveTimeText;
	Text targetVersionText;
	
	private ProjectDAOImpl projectDAOImpl = new ProjectDAOImpl();
	
	@Override
	public void createPartControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout());
		
		FormToolkit toolkit = new FormToolkit(composite.getDisplay());
		ScrolledForm form = toolkit.createScrolledForm(composite);
		form.getBody().setLayout(new TableWrapLayout());
		toolkit.decorateFormHeading(form.getForm());
		
		int sectionStyle = Section.TITLE_BAR;
		Section section = toolkit.createSection(form.getBody(), sectionStyle);
		section.setText("Version Properties");
		
		TableWrapData tableWrapData = new TableWrapData(TableWrapData.FILL_GRAB);
		tableWrapData.colspan = 1;
	    tableWrapData.grabHorizontal = true;
	    section.setLayoutData(tableWrapData);
	    
	    Composite content = toolkit.createComposite(section);
	    content.setLayout(new GridLayout(2, false));
	    
	    Label versionNameLabel = toolkit.createLabel(content, "Version: ");
	    versionNameLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE );
	    versionNameText = toolkit.createText(content, null);
	    versionNameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    
	    Label projectVersionLabel = toolkit.createLabel( content, "Project: ");
	    projectVersionLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    projectVersionText = toolkit.createText(content, null);
	    projectVersionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    projectVersionText.setBackground(new Color(null, 220, 220, 220));
	    projectVersionText.setEnabled(false);
	    
	    Label deployTimeLabel = toolkit.createLabel( content, "Deploy Time: ");
	    deployTimeLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    deployTimeText = toolkit.createText(content, null);
	    deployTimeText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    deployTimeText.setBackground(new Color(null, 220, 220, 220));
	    deployTimeText.setEnabled(false);
	    
	    Label deploySourceLabel = toolkit.createLabel( content, "Deploy Source: ");
	    deploySourceLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    deploySourceText = toolkit.createText(content, null);
	    deploySourceText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    deploySourceText.setBackground(new Color(null, 220, 220, 220));
	    deploySourceText.setEnabled(false);
	    
	    Label saveTimeLabel = toolkit.createLabel( content, "Save Time: ");
	    saveTimeLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    saveTimeText = toolkit.createText(content, null);
	    saveTimeText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    saveTimeText.setBackground(new Color(null, 220, 220, 220));
	    saveTimeText.setEnabled(false);
	    
	    Label targetVersionLabel = toolkit.createLabel( content, "Target Version: ");
	    targetVersionLabel.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);
	    targetVersionText = toolkit.createText(content, null);
	    targetVersionText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    targetVersionText.setBackground(new Color(null, 220, 220, 220));
	    targetVersionText.setEnabled(false);
	    
	    createSelectionListener();
	    
	    section.setClient(content);
	}
	
	@Override
	public void setFocus() {}
	
	private void createSelectionListener() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		ISelectionService selectionService = window.getSelectionService();
		selectionService.addSelectionListener(new ISelectionListener() {
			
			@Override
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				String entry = part.getTitle() + "/";
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object fistElement = structuredSelection.getFirstElement();
				if (fistElement == null) {
					entry += "null";
				} else {
					if (fistElement instanceof Version) {
						Version version = (Version) fistElement;
						
						versionNameText.setText(version.getVersionName());
						projectVersionText.setText(projectDAOImpl.getProjectById(version.getIdProject()).getProjectName());
						deployTimeText.setText(version.getDeployTime().toString());
						
						if (version.getDeploySource() == null) {
							deploySourceText.setText("");
						} else {
							deploySourceText.setText(version.getDeploySource());
						}
						
						saveTimeText.setText(version.getSaveTime().toString());
						targetVersionText.setText(version.getTargetVersion());
					} else {
						entry += fistElement.toString();
					}
				}
			}
		});
	}
}
