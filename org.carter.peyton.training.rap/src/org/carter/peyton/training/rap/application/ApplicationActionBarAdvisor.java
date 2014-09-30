package org.carter.peyton.training.rap.application;

import java.net.URL;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private Action exportAction;
    private Action deployAction;
    private Action reportAction;
    //private Action helpAction;
    private IWorkbenchAction statusAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    @Override
    protected void makeActions(final IWorkbenchWindow window) {

        ImageDescriptor exportActionImage = AbstractUIPlugin
                .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
                        "images/export.png");
        ImageDescriptor deployActionImage = AbstractUIPlugin
                .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
                        "images/deployunit_project.png");
        ImageDescriptor reportActionImage = AbstractUIPlugin
                .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
                        "images/report.png");
        /*ImageDescriptor helpActionImage = AbstractUIPlugin
                .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
                        "images/help.png");*/
        ImageDescriptor statusActionImage = AbstractUIPlugin
                .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
                        "images/normal_mode.png");

        exportAction = new Action() {

            private static final long serialVersionUID = -4850688543837691270L;

            public void run() {
                Shell shell = window.getShell();
                Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
                // Dictionary headers = bundle.getHeaders();
                Object version = bundle.getVersion();
                MessageDialog.openInformation(shell, "RAP Workbench Demo",
                        "Running on RAP version: " + version);
            }
        };
        exportAction.setImageDescriptor(exportActionImage);
        exportAction.setId("org.carter.peyton.training.rap.application.ApplicationActionBarAdvisor.exportAction");
        exportAction.setText("Export");
        register(exportAction);

        deployAction = new Action() {

            private static final long serialVersionUID = 6298064560120056843L;

            public void run() {
                Shell shell = window.getShell();
                Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
                // Dictionary headers = bundle.getHeaders();
                Object version = bundle.getVersion();
                MessageDialog.openInformation(shell, "RAP Workbench Demo",
                        "Running on RAP version: " + version);
            }
        };
        deployAction.setImageDescriptor(deployActionImage);
        deployAction.setId("org.carter.peyton.training.rap.application.ApplicationActionBarAdvisor.deployAction");
        deployAction.setText("Deploy");
        register(deployAction);

        reportAction = new Action() {

            private static final long serialVersionUID = -8007854293580718715L;

            public void run() {
                Shell shell = window.getShell();
                Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
                // Dictionary headers = bundle.getHeaders();
                Object version = bundle.getVersion();
                MessageDialog.openInformation(shell, "RAP Workbench Demo",
                        "Running on RAP version: " + version);
            }
        };
        reportAction.setImageDescriptor(reportActionImage);
        reportAction.setId("org.carter.peyton.training.rap.application.ApplicationActionBarAdvisor.reportAction");
        reportAction.setText("Report");
        register(reportAction);

        /*helpAction = new Action() {

            private static final long serialVersionUID = 4845121860362402418L;

            public void run() {
                Shell shell = window.getShell();
                Bundle bundle = Platform.getBundle(PlatformUI.PLUGIN_ID);
                // Dictionary headers = bundle.getHeaders();
                Object version = bundle.getVersion();
                MessageDialog.openInformation(shell, "RAP Workbench Demo",
                        "Running on RAP version: " + version);
            }
        };
        helpAction.setImageDescriptor(helpActionImage);
        helpAction.setId("org.carter.peyton.training.rap.application.ApplicationActionBarAdvisor.reportAction");
        helpAction.setText("Help Links");
        register(helpAction);*/

        statusAction = ActionFactory.QUIT.create(window);
        statusAction.setImageDescriptor(statusActionImage);
        register(statusAction);
    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {

        coolBar.setLockLayout(true);

        // Main toolbar
        IToolBarManager mainToolbar = new ToolBarManager(SWT.FLAT);
        coolBar.add(new ToolBarContributionItem(mainToolbar, "main"));

        ActionContributionItem exportCI = new ActionContributionItem(
                exportAction);
        exportCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        mainToolbar.add(exportCI);

        ActionContributionItem deployCI = new ActionContributionItem(
                deployAction);
        deployCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        mainToolbar.add(deployCI);

        ActionContributionItem reportCI = new ActionContributionItem(
                reportAction);
        reportCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
        mainToolbar.add(reportCI);

        // Help + Perspective toolbar
        IToolBarManager helpToolbar = new ToolBarManager(SWT.FLAT);

        ToolBarContributionItem helpBarItem = new ToolBarContributionItem(
                helpToolbar, "help") {

            private static final long serialVersionUID = -1346171047087428212L;

            @Override
            public void fill(CoolBar coolBar, int index) {
                // super.fill(coolBar, index);
                createItem(coolBar);
            }
        };

        coolBar.add(helpBarItem);

        /*
         * ActionContributionItem helpCI = new
         * ActionContributionItem(helpAction);
         * helpCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
         * helpToolbar.add(helpCI);
         */
    }

    private CoolItem createItem(final CoolBar coolBar) {
        ToolBar toolBar = createToolBar(coolBar);
        toolBar.pack();
        Point size = toolBar.getSize();
        size = new Point(size.x + (int) (size.x * 0.1), size.y);
        CoolItem item = new CoolItem(coolBar, SWT.NONE);
        item.setControl(toolBar);
        Point preferred = item.computeSize(size.x, size.y);
        item.setPreferredSize(preferred);

        return item;
    }

    private ToolBar createToolBar(final Composite parent) {

        final ToolBar toolBar = new ToolBar(parent, SWT.NONE);

        // Help links drop down
        ToolItem dropDownItem = new ToolItem(toolBar, SWT.DROP_DOWN);
        dropDownItem.setText("Help Links");
        dropDownItem.setImage(getImageDescriptor("help.png").createImage());

        int count = 0;
        final Menu dropDownMenu = new Menu(toolBar.getShell(), SWT.POP_UP);
        for (int i = 0; i < 5; i++) {
            MenuItem item = new MenuItem(dropDownMenu, SWT.PUSH);
            item.setText("Item " + count++);
        }

        dropDownItem.addSelectionListener( new SelectionAdapter() {

            private static final long serialVersionUID = -5518355657297255076L;

            @Override
            public void widgetSelected(final SelectionEvent event) {
              if(event.detail == SWT.ARROW) {
                Point point = toolBar.toDisplay(event.x, event.y);
                dropDownMenu.setLocation(point);
                dropDownMenu.setVisible(true);
              }
            }
        });

        final ToolItem separator = new ToolItem(toolBar, SWT.SEPARATOR);
        separator.setWidth(600);

        // Perspective toolbar item
        ToolItem perspectiveItem;
        String activePerspective = null;

        try {
            activePerspective = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getPerspective().getId();
        } catch (Exception ex) {
            activePerspective = PlatformUI.getWorkbench().getPerspectiveRegistry().getDefaultPerspective();
        }
        
        IPerspectiveDescriptor[] perspectives = PlatformUI.getWorkbench().getPerspectiveRegistry().getPerspectives();
        for (int i = 0; i < perspectives.length; i++) {
            final IPerspectiveDescriptor descriptor = perspectives[i];
            perspectiveItem = new ToolItem(toolBar, SWT.RADIO);
            perspectiveItem.setData(descriptor.getId(), descriptor);
            perspectiveItem.setText(descriptor.getLabel());
            final Image image = descriptor.getImageDescriptor().createImage();
            perspectiveItem.setImage(image);

            perspectiveItem.addDisposeListener(new DisposeListener() {

                private static final long serialVersionUID = 8391877825646668322L;

                @Override
                public void widgetDisposed(DisposeEvent event) {
                    image.dispose();
                }
            });

            perspectiveItem.addSelectionListener(new SelectionListener() {
                
                private static final long serialVersionUID = -4879204973795153500L;

                @Override
                public void widgetSelected(SelectionEvent e) {
                    PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().setPerspective(descriptor);
                }

                @Override
                public void widgetDefaultSelected(SelectionEvent e) {}
            });

            if (descriptor.getId().equals(activePerspective)) {
                perspectiveItem.setSelection(true);
            }
        }

        return toolBar;
    }

    public static ImageDescriptor getImageDescriptor(String name) {
        String iconPath = "images/";
        Bundle bundle = Platform.getBundle("org.carter.peyton.training.rap");
        URL url = BundleUtility.find(bundle, iconPath + name);
        return ImageDescriptor.createFromURL(url);
    }

    @Override
    protected void fillStatusLine(IStatusLineManager statusLine) {
        super.fillStatusLine(statusLine);
    }
}
