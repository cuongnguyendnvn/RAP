package org.carter.peyton.training.rap.view;

import java.util.List;

import org.carter.peyton.training.rap.dao.impl.DeviceDAOImpl;
import org.carter.peyton.training.rap.models.Device;
import org.carter.peyton.training.rap.models.Version;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class DevicesSelectionViewPart extends ViewPart {

	public static final String ID = "org.carter.peyton.training.rap.DevicesSelectionView";
	public static final String ID_SERVICES = "org.carter.peyton.training.rap.ServicesSelectionView";
	public static final String ID_SCENES = "org.carter.peyton.training.rap.ScenesSelectionView";
	public static final String ID_RULES = "org.carter.peyton.training.rap.RulesSelectionView";
	
	private TableViewer tableViewer;
	private DeviceDAOImpl deviceDAOImpl;
	
	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		createSelectionListener(parent);
	}
	
	private void createViewer(Composite parent) {
		tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);

		Table table = tableViewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		tableViewer.setColumnProperties(initColumnProperties(table));
		tableViewer.setLabelProvider( new ViewLabelProvider() );
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
				createTableColumn(table, "Name", 300);
			} else if (i == 1){
				createTableColumn(table, "App Module", 170);
			} else if (i == 2){
				createTableColumn(table, "Device Type", 170);
			} else if (i == 3){
				createTableColumn(table, "Physical Location", 200);
			} else if (i == 4){
				createTableColumn(table, "Manufacturer", 160);
			}
		}

		return result;
	}
	
	private void createTableColumn(Table table, String title, int bound) {
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);

		tableColumn.setText(title);
		tableColumn.setWidth(bound);
		tableColumn.setResizable(true);
		tableColumn.setMoveable(true);
	}
	
	private class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

		/**
		 * 
		 */
		private static final long serialVersionUID = 208403679101827118L;

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			Device row = (Device) element;
			String result = row.getColumnByIndex(columnIndex);
			return result;
		}
	}
	
	private class ViewContentProvider implements IStructuredContentProvider {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1817398953526760067L;

		@Override
		public Object[] getElements(Object inputElement) {
			
			deviceDAOImpl = new DeviceDAOImpl();
			List<Device> listTable = deviceDAOImpl.getDeviceList();
			Object[] result = new Object[listTable.size()];
			listTable.toArray(result);

			return result;
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
			public void selectionChanged(IWorkbenchPart part, ISelection selection) {
				String entry = part.getTitle() + "/";
				IStructuredSelection structuredSelection = (IStructuredSelection) selection;
				Object fistElement = structuredSelection.getFirstElement();
				if (fistElement == null) {
					entry += "null";
				} else {
					if (fistElement instanceof Version) {
						Version version = (Version) fistElement;
						if (version.getIdVersion() == 5) {
							createViewer(parent);
						}
					} else {
						entry += fistElement.toString();
					}
				}
			}
		});
	}
}
