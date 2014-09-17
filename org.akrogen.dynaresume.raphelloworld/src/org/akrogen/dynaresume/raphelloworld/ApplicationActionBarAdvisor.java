package org.akrogen.dynaresume.raphelloworld;

import java.net.URL;

import org.akrogen.dynaresume.raphelloworld.editor.FooEditorInput;
import org.akrogen.dynaresume.raphelloworld.wizard.SurveyWizard;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.internal.IWorkbenchConstants;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {
	
	private IWebBrowser browser;
	
	// For File menu
	private IWorkbenchAction exitAction;
	private IWorkbenchAction importAction;
	private IWorkbenchAction exportAction;
	
	// For Window menu
	private MenuManager showViewMenuMgr;
	private IWorkbenchAction preferencesAction;
	
	// For Help menu
	private Action aboutAction;
	private Action rapWebSiteAction;
	
	// For Main Cool bar
	private Action wizardAction;
	private Action browserAction;
	private Action newEditorAction;
	private static int browserIndex;
	private IWorkbenchAction saveAction;
	private IWorkbenchAction saveAllAction;
	
	
	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }
 
    protected void makeActions(final IWorkbenchWindow window) {
    	ImageDescriptor quitActionImage = AbstractUIPlugin.imageDescriptorFromPlugin("org.akrogen.dynaresume.raphelloworld", "images/ttt.gif");
    	ImageDescriptor helpActionImage = AbstractUIPlugin.imageDescriptorFromPlugin("org.akrogen.dynaresume.raphelloworld", "images/help.gif");
    	ImageDescriptor rapWebsiteActionImage = AbstractUIPlugin.imageDescriptorFromPlugin("org.akrogen.dynaresume.raphelloworld", "images/browser.gif");
    	ImageDescriptor wizardActionImage = AbstractUIPlugin.imageDescriptorFromPlugin( "org.akrogen.dynaresume.raphelloworld","images/login.gif" );
    	ImageDescriptor browserActionImage = AbstractUIPlugin.imageDescriptorFromPlugin( "org.akrogen.dynaresume.raphelloworld","images/internal_browser.gif" );
    	
    	// Create and register action for File menu
    	importAction = ActionFactory.IMPORT.create(window);
    	register(importAction);
    	
    	exportAction = ActionFactory.EXPORT.create(window);
    	register(exportAction);
    	
    	exitAction = ActionFactory.QUIT.create(window);
    	exitAction.setImageDescriptor(quitActionImage);
    	register(exitAction);
    	
    	// Create and register action for Window menu
    	showViewMenuMgr = new MenuManager("Show View", "showView");
    	IContributionItem showViewMenu = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
    	showViewMenuMgr.add(showViewMenu);
    			
    	preferencesAction = ActionFactory.PREFERENCES.create(window);
    	register(preferencesAction);
    	
    	// Create and register action for Help menu
    	rapWebSiteAction = new Action() {
    		public void run() {
    			IWorkbenchBrowserSupport browserSupport;
    			browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
    			try {
    				int style = IWorkbenchBrowserSupport.AS_EXTERNAL;
    				browser = browserSupport.createBrowser(style, rapWebSiteAction.getId(), "", "");
    				browser.openURL(new URL("http://eclipse.org/rap"));
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
		};
		
		rapWebSiteAction.setText("RAP Home Page");
		rapWebSiteAction.setId("org.carter.peyton.training.rap.rapWebsite");
		rapWebSiteAction.setImageDescriptor(rapWebsiteActionImage);
		register(rapWebSiteAction);
		
		aboutAction = new Action() {
			public void run() {
				Shell shell = window.getShell();
				Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
				//Dictionary headers = bundle.getHeaders();
				Object version = bundle.getVersion();
				MessageDialog.openInformation(shell, "RAP Workbench Demo", "Running on RAP version: " + version);
			}
		};
		
		aboutAction.setText("About");
		aboutAction.setId("org.carter.peyton.training.rap.about");
		aboutAction.setImageDescriptor(helpActionImage);
		register(aboutAction);
		
		// Create and register action for Main cool bar
		// Wizard action
		wizardAction = new Action() {
			public void run() {
				SurveyWizard surveyWizard = new SurveyWizard();
				WizardDialog wizardDialog = new WizardDialog(window.getShell(), surveyWizard);
				wizardDialog.open();
			}
		};
		
		wizardAction.setText("Open Wizard");
		wizardAction.setId("org.carter.peyton.training.rap.wizard");
		wizardAction.setImageDescriptor(wizardActionImage);
		register(wizardAction);
		
		// Browser action
		browserAction = new Action() {
			public void run() {
				browserIndex++;
				try{
					window.getActivePage().showView(BrowserViewPart.ID, String.valueOf(browserIndex), IWorkbenchPage.VIEW_ACTIVATE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		browserAction.setText("Open New Browser View");
		browserAction.setId("org.carter.peyton.training.rap.browser");
		browserAction.setImageDescriptor(browserActionImage);
		register(browserAction);
		
		// New editor action
		newEditorAction = new Action() {
			@Override
			public void run() {
				try {
					window.getActivePage().openEditor(new FooEditorInput(ApplicationActionBarAdvisor.this), "org.akrogen.dynaresume.raphelloworld.editor.fooeditor", true);
				} catch (PartInitException e) {
					e.printStackTrace();
				}
			}
		};
		
		newEditorAction.setText("Open New Editor");
		newEditorAction.setId("org.carter.peyton.training.rap.editor");
		newEditorAction.setImageDescriptor(window.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_NEW_WIZARD));
		register(newEditorAction);
		
		// Save action
		saveAction = ActionFactory.SAVE.create(window);
		register(saveAction);
		
		// Save all action
		saveAllAction = ActionFactory.SAVE_ALL.create(window);
		register(saveAllAction);
    }
 
    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager("File", IWorkbenchActionConstants.M_FILE);
    	MenuManager windowMenu = new MenuManager("Window", IWorkbenchActionConstants.M_WINDOW);
    	MenuManager helpMenu = new MenuManager("Help", IWorkbenchActionConstants.M_HELP);
    	
    	// File
    	menuBar.add(fileMenu);
    	fileMenu.add(importAction);
    	fileMenu.add(exportAction);
    	fileMenu.add(exitAction);
    	
    	// Window
    	menuBar.add(windowMenu);
    	windowMenu.add(showViewMenuMgr);
    	windowMenu.add(preferencesAction);
    	
    	// Help
    	menuBar.add(helpMenu);
    	helpMenu.add(rapWebSiteAction);
    	helpMenu.add(new Separator("about"));
    	helpMenu.add(aboutAction);
    }
    
    protected void fillCoolBar(ICoolBarManager coolBar) {
    	// Main cool bar
        IToolBarManager mainToolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(mainToolbar, "main"));
        mainToolbar.add(wizardAction);
        mainToolbar.add(browserAction);
        mainToolbar.add(aboutAction);
        mainToolbar.add(exitAction);
        
        // Editor cool bar
        IToolBarManager editorToolbar = new ToolBarManager(SWT.FLAT | SWT.RIGHT);
        coolBar.add(new ToolBarContributionItem(editorToolbar, "editor"));
        editorToolbar.add(newEditorAction);
        editorToolbar.add(saveAction);
        editorToolbar.add(saveAllAction);
    }
    
    protected void fillStatusLine( final IStatusLineManager statusLine ) {
        statusLine.add(aboutAction);
      }
}
