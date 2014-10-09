package org.carter.peyton.training.rap.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.carter.peyton.training.rap.db.dao.impl.DeviceDAOImpl;
import org.carter.peyton.training.rap.db.entities.Device;
import org.carter.peyton.training.rap.db.entities.Version;
import org.carter.peyton.training.rap.view.action.TableViewerComparator;
import org.carter.peyton.training.rap.view.action.TableViewerFilter;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class DevicesSelectionViewPart extends ViewPart {

    public static final String ID = "org.carter.peyton.training.rap.DevicesSelectionView";
    public static final String ID_SERVICES = "org.carter.peyton.training.rap.ServicesSelectionView";
    public static final String ID_SCENES = "org.carter.peyton.training.rap.ScenesSelectionView";
    public static final String ID_RULES = "org.carter.peyton.training.rap.RulesSelectionView";

    public static final String IP_CAMERAS = "IP Cameras";
    public static final String DVR_NVR = "DVR/NVR";
    public static final String FIREPLACE_CONTROLLER = "Fireplace Controller";

    private TableViewer tableViewer;
    private TableViewerComparator tableViewerComparator;
    private TableViewerFilter tableViewerFilter;
    private DeviceDAOImpl deviceDAOImpl = new DeviceDAOImpl();;

    @Override
    public void createPartControl(Composite parent) {
        parent.setLayout(new GridLayout(1, false));

        createToolbaViewer(parent);
        createFilterViewer(parent);
        createTableViewer(parent);

        // Set the sorter for the table
        tableViewerComparator = new TableViewerComparator();
        tableViewer.setComparator(tableViewerComparator);

        // Set the filter for the table
        tableViewerFilter = new TableViewerFilter();
        tableViewer.addFilter(tableViewerFilter);

        createSelectionListener(parent);
        tableViewer.addDoubleClickListener(new IDoubleClickListener() {

            @Override
            public void doubleClick(DoubleClickEvent event) {
                String msg = "You double clicked on: "
                        + event.getSelection().toString();
                Shell shell = tableViewer.getTable().getShell();
                MessageDialog.openInformation(shell, "Device", msg);
            }
        });

        parent.setVisible(false);
    }

    private void createToolbaViewer(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = 570;
        composite.setLayout(rowLayout);

        ToolBar toolBar = new ToolBar(composite, SWT.FLAT);

        ToolItem item1 = new ToolItem(toolBar, SWT.PUSH );
        item1.setImage(getImageDescriptor("refresh.png").createImage());

        new ToolItem( toolBar, SWT.SEPARATOR );

        ToolItem item2 = new ToolItem(toolBar, SWT.PUSH);
        item2.setImage(getImageDescriptor("add.png").createImage());

        ToolItem item3 = new ToolItem(toolBar, SWT.PUSH);
        item3.setImage(getImageDescriptor("node_clone.png").createImage());
        item3.setEnabled(false);

        ToolItem item4 = new ToolItem(toolBar, SWT.PUSH);
        item4.setImage(getImageDescriptor("delete.png").createImage());
        item4.setEnabled(false);

        new ToolItem( toolBar, SWT.SEPARATOR );

        ToolItem item5 = new ToolItem(toolBar, SWT.PUSH);
        item5.setImage(getImageDescriptor("import_device.png").createImage());

        ToolItem item6 = new ToolItem(toolBar, SWT.PUSH);
        item6.setImage(getImageDescriptor("import_zwave.png").createImage());

        ToolItem item7 = new ToolItem(toolBar, SWT.PUSH);
        item7.setImage(getImageDescriptor("template_child.png").createImage());
        item7.setEnabled(false);

        ToolItem item8 = new ToolItem(toolBar, SWT.PUSH);
        item8.setImage(getImageDescriptor("export.png").createImage());

        new ToolItem( toolBar, SWT.SEPARATOR );

        ToolItem item9 = new ToolItem(toolBar, SWT.PUSH);
        item9.setImage(getImageDescriptor("system.png").createImage());

        ToolItem item10 = new ToolItem(toolBar, SWT.PUSH);
        item10.setImage(getImageDescriptor("expand.png").createImage());

        ToolItem item11 = new ToolItem(toolBar, SWT.PUSH);
        item11.setImage(getImageDescriptor("collapse.png").createImage());

        new ToolItem( toolBar, SWT.SEPARATOR );

        ToolItem item12 = new ToolItem(toolBar, SWT.PUSH);
        item12.setImage(getImageDescriptor("update.png").createImage());

        ToolItem item13 = new ToolItem(toolBar, SWT.PUSH);
        item13.setImage(getImageDescriptor("request_online_status.png").createImage());
    }

    private void createFilterViewer(final Composite parent) {
        Composite toolbarComposite = new Composite(parent, SWT.NONE);
        toolbarComposite.setLayout(new GridLayout(2, false));

        Composite filterComposite = new Composite(toolbarComposite, SWT.NONE);
        filterComposite.setLayout(new GridLayout(3, false));

        Label filterLabel = new Label(filterComposite, SWT.NONE);
        filterLabel.setText("Filter: ");

        final Text filterText = new Text(filterComposite, SWT.BORDER | SWT.SEARCH);
        GridData filterTextGridData = new GridData(280, 10);
        filterText.setLayoutData(filterTextGridData);

        final Button filterButton = new Button(filterComposite, SWT.NONE);
        filterButton.setImage(getImageDescriptor("find.png").createImage());
        filterButton.setEnabled(false);

        filterText.addKeyListener(new KeyAdapter() {

            private static final long serialVersionUID = -6726454020327955753L;

            public void keyReleased(KeyEvent ke) {
                tableViewerFilter.setSearchText(filterText.getText());
                tableViewer.refresh();
                filterButton.setEnabled(true);
            }
        });
        
        filterButton.addSelectionListener(new SelectionListener() {

            private static final long serialVersionUID = -3133820023605778075L;

            @Override
            public void widgetSelected(SelectionEvent e) {
                tableViewerFilter.setSearchText("");
                tableViewer.refresh();
                filterText.setText("");
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}
        });

        Composite findComposite = new Composite(toolbarComposite, SWT.NONE);
        GridLayout findGridLayout = new GridLayout(4, false);
        findGridLayout.marginLeft = 200;
        findComposite.setLayout(findGridLayout);

        Label findLabel = new Label(findComposite, SWT.NONE);
        findLabel.setText("Find: ");

        final Text findText = new Text(findComposite, SWT.BORDER | SWT.SEARCH);
        GridData findTextGridData = new GridData(170, 10);
        findText.setLayoutData(findTextGridData);

        final Button findButtonUp = new Button(findComposite, SWT.NONE);
        findButtonUp.setImage(getImageDescriptor("arrow_top.png").createImage());
        findButtonUp.addSelectionListener(new SelectionListener() {

            private static final long serialVersionUID = -4398198586604913723L;

            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] tableItems = tableViewer.getTable().getItems();
                int currentItem = tableViewer.getTable().getSelectionIndex();
                int startIndex = tableItems.length - 1;

                if (!findText.getText().isEmpty()) {
                    if (currentItem == -1) {
                        startIndex = 0;
                    } else if (currentItem != 0) {
                        startIndex = currentItem - 1; 
                    }

                    for (int i = startIndex; i >= 0; i--) {
                        for (int j = 0; j < 5; j++) {
                            if (tableItems[i].getText(j).toLowerCase().contains(findText.getText().toLowerCase())) {
                                tableViewer.getTable().setSelection(tableItems[i]);
                                i = -1;
                                break;
                            }
                        }
                    }

                    if (currentItem == tableViewer.getTable().getSelectionIndex()) {
                        for (int i = tableItems.length - 1; i > startIndex; i--) {
                            for (int j = 0; j < 5; j++) {
                                if (tableItems[i].getText(j).toLowerCase().contains(findText.getText().toLowerCase())) {
                                    tableViewer.getTable().setSelection(tableItems[i]);
                                    i = -1;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}
        });

        final Button findButtonDown = new Button(findComposite, SWT.NONE);
        findButtonDown.setImage(getImageDescriptor("arrow_bottom.png").createImage());
        findButtonDown.addSelectionListener(new SelectionListener() {

            private static final long serialVersionUID = -5291996045680476803L;

            @Override
            public void widgetSelected(SelectionEvent e) {
                TableItem[] tableItems = tableViewer.getTable().getItems();
                int currentItem = tableViewer.getTable().getSelectionIndex();
                int startIndex = 0;

                if (!findText.getText().isEmpty()) {
                    if (currentItem != -1 || currentItem != tableItems.length - 1) {
                        startIndex = currentItem + 1;
                    }

                    for (int i = startIndex; i < tableItems.length; i++) {
                        for (int j = 0; j < 5; j++) {
                            if (tableItems[i].getText(j).toLowerCase().contains(findText.getText().toLowerCase())) {
                                tableViewer.getTable().setSelection(tableItems[i]);
                                i = tableItems.length;
                                break;
                            }
                        }
                    }

                    if (currentItem == tableViewer.getTable().getSelectionIndex()) {
                        for (int i = 0; i < startIndex; i++) {
                            for (int j = 0; j < 5; j++) {
                                if (tableItems[i].getText(j).toLowerCase().contains(findText.getText().toLowerCase())) {
                                    tableViewer.getTable().setSelection(tableItems[i]);
                                    i = startIndex;
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {}
        });
    }

    private void createTableViewer(Composite parent) {
        tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

        Table table = tableViewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);
        table.setData(RWT.MARKUP_ENABLED, Boolean.TRUE);

        tableViewer.setColumnProperties(initColumnProperties(table));
        tableViewer.setLabelProvider(new ViewLabelProvider());
        tableViewer.setContentProvider(new ViewContentProvider());
        tableViewer.setInput(this);

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalAlignment = GridData.FILL;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        tableViewer.getControl().setLayoutData(gridData);
    }

    private String[] initColumnProperties(Table table) {

        String[] result = new String[5];

        for (int i = 0; i < 5; i++) {
            result[i] = "Column" + i;

            if (i == 0) {
                createTableColumn(table, "Name", 300, i);
            } else if (i == 1) {
                createTableColumn(table, "App Module", 170, i);
            } else if (i == 2) {
                createTableColumn(table, "Device Type", 170, i);
            } else if (i == 3) {
                createTableColumn(table, "Physical Location", 200, i);
            } else if (i == 4) {
                createTableColumn(table, "Manufacturer", 160, i);
            }
        }

        return result;
    }

    private void createTableColumn(Table table, String title, int bound,
            int colNumber) {
        TableColumn tableColumn = new TableColumn(table, SWT.NONE);

        tableColumn.setText(title);
        tableColumn.setWidth(bound);
        tableColumn.setResizable(true);
        tableColumn.setMoveable(true);
        tableColumn.addSelectionListener(getSelectionAdapter(tableColumn,
                colNumber));
    }

    private SelectionAdapter getSelectionAdapter(final TableColumn column,
            final int index) {
        SelectionAdapter selectionAdapter = new SelectionAdapter() {

            private static final long serialVersionUID = -3024566727614483785L;

            @Override
            public void widgetSelected(SelectionEvent e) {
                tableViewerComparator.setColumn(index);
                int dir = tableViewerComparator.getDirection();
                tableViewer.getTable().setSortDirection(dir);
                tableViewer.getTable().setSortColumn(column);
                tableViewer.refresh();
            }
        };
        return selectionAdapter;
    }

    private class ViewLabelProvider extends LabelProvider implements
            ITableLabelProvider {

        private static final long serialVersionUID = 208403679101827118L;

        public Image getColumnImage(Object element, int columnIndex) {
            Device row = (Device) element;
            ImageDescriptor descriptor = null;
            Image image = null;

            if (columnIndex == 0) {
                if (IP_CAMERAS.equals(row.getDeviceType())) {
                    descriptor = getImageDescriptor("camera.png");
                } else if (DVR_NVR.equals(row.getDeviceType())) {
                    descriptor = getImageDescriptor("cctv.png");
                } else if (FIREPLACE_CONTROLLER.equals(row.getDeviceType())) {
                    descriptor = getImageDescriptor("fireplace-controller.png");
                }

            }

            // obtain the cached image corresponding to the descriptor
            if (descriptor != null) {
                image = descriptor.createImage();
            }

            return image;
        }

        public String getColumnText(Object element, int columnIndex) {
            Device row = (Device) element;
            String result = null;

            if (row.getColumnByIndex(columnIndex) == null) {
                result = row.getColumnByIndex(columnIndex);
            } else {
                result = "<a href=\"http://eclipse.org/rap\" target=\"_blank\">" + row.getColumnByIndex(columnIndex) + "</a>";
            }

            return result;
        }
    }

    public static ImageDescriptor getImageDescriptor(String name) {
        String iconPath = "images/";
        Bundle bundle = Platform.getBundle("org.carter.peyton.training.rap");
        URL url = BundleUtility.find(bundle, iconPath + name);
        return ImageDescriptor.createFromURL(url);
    }

    private class ViewContentProvider implements IStructuredContentProvider {

        private static final long serialVersionUID = -1817398953526760067L;

        @Override
        public Object[] getElements(Object inputElement) {

            return new Object[0];
        }

        @Override
        public void dispose() {
        }

        @Override
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }

    @Override
    public void setFocus() {
        tableViewer.getControl().setFocus();
    }

    private void createSelectionListener(final Composite parent) {
        IWorkbench workbench = PlatformUI.getWorkbench();
        IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
        ISelectionService selectionService = window.getSelectionService();
        selectionService.addSelectionListener(new ISelectionListener() {

            @Override
            public void selectionChanged(IWorkbenchPart part,
                    ISelection selection) {
                IStructuredSelection structuredSelection = (IStructuredSelection) selection;
                Object fistElement = structuredSelection.getFirstElement();
                if (fistElement != null) {
                    if (fistElement instanceof Version) {
                        Version version = (Version) fistElement;
                        deviceDAOImpl = new DeviceDAOImpl();
                        List<Device> listDevice = deviceDAOImpl.getDeviceList();
                        List<Device> newListDevice = new ArrayList<Device>();
                        for (Device device : listDevice) {
                            if (version.getIdVersion() == device.getIdVersion()) {
                                newListDevice.add(device);
                            }
                        }

                        final Object[] result = new Object[newListDevice.size()];
                        newListDevice.toArray(result);

                        tableViewer.setContentProvider(new IStructuredContentProvider() {

                                    private static final long serialVersionUID = -8323734161192822131L;

                                    @Override
                                    public void inputChanged(Viewer viewer,
                                            Object oldInput, Object newInput) {
                                    }

                                    @Override
                                    public void dispose() {
                                    }

                                    @Override
                                    public Object[] getElements(
                                            Object inputElement) {
                                        return result;
                                    }
                                });

                        tableViewer.setInput(this);
                        parent.setVisible(true);
                    } else {
                        tableViewer.setContentProvider(new IStructuredContentProvider() {

                                    private static final long serialVersionUID = -8323734161192822131L;

                                    @Override
                                    public void inputChanged(Viewer viewer,
                                            Object oldInput, Object newInput) {
                                    }

                                    @Override
                                    public void dispose() {
                                    }

                                    @Override
                                    public Object[] getElements(
                                            Object inputElement) {
                                        return new Object[0];
                                    }
                                });

                        tableViewer.setInput(this);
                        parent.setVisible(false);
                    }
                }
            }
        });
    }
}
