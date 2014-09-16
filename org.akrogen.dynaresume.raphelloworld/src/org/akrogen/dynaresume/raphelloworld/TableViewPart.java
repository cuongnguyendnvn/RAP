package org.akrogen.dynaresume.raphelloworld;

import java.util.List;

import org.akrogen.dynaresume.raphelloworld.model.TableModel;
import org.akrogen.dynaresume.raphelloworld.model.TableModelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
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
import org.eclipse.ui.part.ViewPart;

public class TableViewPart extends ViewPart {

	public static final String ID = "org.akrogen.dynaresume.raphelloworld.tableviewpart";
	private TableViewer tableViewer;

	@Override
	public void createPartControl(Composite parent) {
		GridLayout gridLayout = new GridLayout(2, false);
		parent.setLayout(gridLayout);
		createViewer(parent);
		getSite().setSelectionProvider(tableViewer);
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

	private class ViewContentProvider implements IStructuredContentProvider {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1817398953526760067L;

		@Override
		public Object[] getElements(Object inputElement) {

			List<TableModel> listTable = TableModelProvider.INSTANCE.getListTable();
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

	private class ViewLabelProvider extends LabelProvider implements ITableLabelProvider {

		/**
		 * 
		 */
		private static final long serialVersionUID = 208403679101827118L;

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			TableModel row = (TableModel) element;
			String result = row.getColumnByIndex(columnIndex);
			return result;
		}
	}

	private String[] initColumnProperties(Table table) {

		String[] result = new String[10];

		for (int i = 0; i < 10; i++) {
			result[i] = "Column" + i;
			
			if (i == 2) {
				createTableColumn(table, "Column" + i, 190);
			} else {
				createTableColumn(table, "Column" + i, 70);
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

	@Override
	public void setFocus() {
		tableViewer.getControl().setFocus();
	}
}
