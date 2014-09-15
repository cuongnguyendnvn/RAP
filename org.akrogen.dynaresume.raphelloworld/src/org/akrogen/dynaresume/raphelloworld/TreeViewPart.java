package org.akrogen.dynaresume.raphelloworld;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

public class TreeViewPart extends ViewPart {

	public static final String ID = "org.akrogen.dynaresume.raphelloworld.treeviewpart";
	private TreeViewer treeViewer;

	@Override
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new ViewContentProvider());

		ILabelDecorator labelDecorator = PlatformUI.getWorkbench()
				.getDecoratorManager().getLabelDecorator();
		ILabelProvider labelProvider = new DecoratingLabelProvider(
				new ViewLabelProvider(), labelDecorator);
		treeViewer.setLabelProvider(labelProvider);

		treeViewer.setInput(File.listRoots());

		treeViewer.addDoubleClickListener(new IDoubleClickListener() {

			@Override
			public void doubleClick(DoubleClickEvent event) {
				String msg = "You double clicked on: "
						+ event.getSelection().toString();
				Shell shell = treeViewer.getTree().getShell();
				MessageDialog.openInformation(shell, "Treeviewer", msg);
			}
		});

		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	class ViewContentProvider implements ITreeContentProvider, IStructuredContentProvider {

		private TreeParent treeParent;

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof TreeParent) {
				return ((TreeParent) element).hasChildren();
			}

			return false;
		}

		@Override
		public Object getParent(Object element) {
			if (element instanceof TreeObject) {
				return ((TreeObject) element).getParent();
			}

			return null;
		}

		@Override
		public Object[] getElements(Object inputElement) {
			if (inputElement instanceof IViewPart) {
				if (treeParent == null) {
					initTreeView();
				}

				return getChildren(treeParent);
			}

			return getChildren(inputElement);
		}

		private void initTreeView() {
			TreeObject treeObject_1 = new TreeObject(
					"EclipseCon location",
					"http://maps.google.com/maps?q=5001%20Great%20America%20Pkwy%20Santa%20Clara%20CA%2095054");
			TreeObject treeObject_2 = new TreeObject("Eclipse Foundation",
					"http://maps.google.com/maps?q=Ottawa");
			TreeObject treeObject_3 = new TreeObject("Innoopract Inc",
					"http://maps.google.com/maps?q=Portland");

			TreeParent treeParent_1 = new TreeParent("Locate in browser view");
			treeParent_1.addChild(treeObject_1);
			treeParent_1.addChild(treeObject_2);
			treeParent_1.addChild(treeObject_3);

			TreeObject treeObject_4 = new TreeObject("Leaf 4");
			TreeParent treeParent_2 = new TreeParent("Parent 2");

			treeParent_2.addChild(treeObject_4);

			TreeParent treeParent_3 = new TreeParent("Child X - Filter me!");

			TreeParent root = new TreeParent("Root");
			root.addChild(treeParent_1);
			root.addChild(treeParent_2);
			root.addChild(treeParent_3);

			treeParent = new TreeParent("");
			treeParent.addChild(root);
		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof TreeParent) {
				return ((TreeParent) parentElement).getChildren();
			}

			return new Object[0];
		}

		@Override
		public void dispose() {
		}
	}

	class ViewLabelProvider extends LabelProvider {
		@Override
		public Image getImage(Object element) {
			IWorkbench workbench = PlatformUI.getWorkbench();
			ISharedImages sharedImages = workbench.getSharedImages();
			return sharedImages.getImage(ISharedImages.IMG_OBJ_ELEMENT);
		}
	}

	class TreeObject implements IPropertySource {
		private static final String PROP_ID_LOCATION = "location";
		private static final String PROP_ID_NAME = "name";
		private String name;
		private String location;
		private TreeParent parent;

		public TreeObject(String name) {
			this(name, "");
		}

		public TreeObject(String name, String location) {
			this.name = name;
			this.location = location;
		}

		public String getName() {
			return name;
		}

		public String getLocation() {
			return location;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		@Override
		public String toString() {
			return getName();
		}

		@Override
		public Object getEditableValue() {
			return this;
		}

		@Override
		public IPropertyDescriptor[] getPropertyDescriptors() {
			return new IPropertyDescriptor[] {
					new TextPropertyDescriptor(PROP_ID_NAME, "Name"),
					new TextPropertyDescriptor(PROP_ID_LOCATION, "Location"), };
		}

		@Override
		public Object getPropertyValue(Object id) {
			Object result = null;
			if (PROP_ID_NAME.equals(id)) {
				result = name;
			} else if (PROP_ID_LOCATION.equals(id)) {
				result = location;
			}
			return result;
		}

		@Override
		public void resetPropertyValue(Object id) {
			if (PROP_ID_NAME.equals(id)) {
				name = "";
			} else if (PROP_ID_LOCATION.equals(id)) {
				location = "";
			}
		}

		@Override
		public void setPropertyValue(Object id, Object value) {
			if (PROP_ID_NAME.equals(id)) {
				name = (String) value;
			} else if (PROP_ID_LOCATION.equals(id)) {
				location = (String) value;
			}
			treeViewer.update(this, null);
		}

		@Override
		public boolean isPropertySet(Object id) {
			boolean result = false;
			if (PROP_ID_NAME.equals(id)) {
				result = name != null && !"".equals(name);
			} else if (PROP_ID_LOCATION.equals(id)) {
				result = location != null && !"".equals(location);
			}
			return result;
		}
	}

	private class TreeParent extends TreeObject {
		private final List<TreeObject> children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList<TreeObject>();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public TreeObject[] getChildren() {
			TreeObject[] result = new TreeObject[children.size()];
			children.toArray(result);
			return result;
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	private class BrokenTreeObject extends TreeObject {
		public BrokenTreeObject(String name) {
			super(name);
		}
	}
}
