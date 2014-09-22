package org.carter.peyton.training.rap.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.carter.peyton.training.rap.dao.impl.ProjectDAOImpl;
import org.carter.peyton.training.rap.dao.impl.UserDAOImpl;
import org.carter.peyton.training.rap.domain.UserDomain;
import org.carter.peyton.training.rap.models.Project;
import org.carter.peyton.training.rap.models.ProjectParent;
import org.carter.peyton.training.rap.models.TreeObject;
import org.carter.peyton.training.rap.models.TreeParent;
import org.carter.peyton.training.rap.models.User;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;

public class ProjectsTreeViewPart extends ViewPart {

	public static final String ID = "org.carter.peyton.training.rap.view.ProjectsTreeView";

	public static final String ID_DRIVER = "org.carter.peyton.training.rap.view.DriversTreeView";
	public static final String ID_AREA = "org.carter.peyton.training.rap.view.AreasTreeView";

	private TreeViewer treeViewer;
	private UserDAOImpl userDAOImpl;
	private ProjectDAOImpl projectDAOImpl;

	@Override
	public void createPartControl(Composite parent) {
		FilteredTree filteredTree = new FilteredTree(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);
		treeViewer = filteredTree.getViewer();
		// treeViewer.addFilter(new customTreeFilter());
		treeViewer.setContentProvider(new ViewContentProvider());

		/*
		 * ILabelDecorator labelDecorator = PlatformUI.getWorkbench()
		 * .getDecoratorManager().getLabelDecorator(); ILabelProvider
		 * labelProvider = new DecoratingLabelProvider( new ViewLabelProvider(),
		 * labelDecorator);
		 */
		treeViewer.setLabelProvider(new ViewLabelProvider());

		treeViewer.setInput(getInitialInput());

		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	class ViewContentProvider implements ITreeContentProvider {

		/**
		 * 
		 */
		private static final long serialVersionUID = 714233580229247232L;

		// private TreeParent treeParent;

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
			/*
			 * if (inputElement instanceof IViewPart) { if (treeParent == null)
			 * { initTreeView(); }
			 * 
			 * return getChildren(treeParent); }
			 */

			return getChildren(inputElement);
		}

		/*
		 * private void initTreeView() { TreeObject treeObject_1 = new
		 * TreeObject( "EclipseCon location", "http://google.com"); TreeObject
		 * treeObject_2 = new TreeObject("Eclipse Foundation",
		 * "http://maps.google.com/maps?q=Ottawa"); TreeObject treeObject_3 =
		 * new TreeObject("Innoopract Inc",
		 * "http://maps.google.com/maps?q=Portland");
		 * 
		 * TreeParent treeParent_1 = new TreeParent("Locate in browser view");
		 * treeParent_1.addChild(treeObject_1);
		 * treeParent_1.addChild(treeObject_2);
		 * treeParent_1.addChild(treeObject_3);
		 * 
		 * TreeObject treeObject_4 = new TreeObject("Leaf 4"); TreeParent
		 * treeParent_2 = new TreeParent("Parent 2");
		 * 
		 * treeParent_2.addChild(treeObject_4);
		 * 
		 * TreeParent treeParent_3 = new TreeParent("Child X - Filter me!");
		 * 
		 * TreeParent root = new TreeParent("Root");
		 * root.addChild(treeParent_1); root.addChild(treeParent_2);
		 * root.addChild(treeParent_3);
		 * 
		 * treeParent = new TreeParent(""); treeParent.addChild(root); }
		 */

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
		/**
		 * 
		 */
		private static final long serialVersionUID = 7155073852861873642L;

		@Override
		public Image getImage(Object element) {
			ImageDescriptor descriptor = null;
			if (element instanceof TreeObject) {
				descriptor = getImageDescriptor("company_group.png");
			}

			// obtain the cached image corresponding to the descriptor
			Image image = descriptor.createImage();
			return image;
		}
	}

	public static ImageDescriptor getImageDescriptor(String name) {
		String iconPath = "images/";
		Bundle bundle = Platform.getBundle("org.carter.peyton.training.rap");
		URL url = BundleUtility.find(bundle, iconPath + name);
		return ImageDescriptor.createFromURL(url);
	}

	public TreeParent getInitialInput() {
		TreeParent userRoot = new TreeParent();
		TreeParent projectRoot = new TreeParent();
		TreeObject object;
		
		// Get list Project
		projectDAOImpl = new ProjectDAOImpl();
		List<Project> listProject = projectDAOImpl.getListProjects();
		
		// Get list User
		userDAOImpl = new UserDAOImpl();
		List<User> listUser = userDAOImpl.getListUser();
		
		for (User user : listUser) {
			if (user.getIdUser() == 3) {
				object = new TreeObject();
				object.setUser(user);
				object.setProject(listProject);
				object.setIndex(3);
				projectRoot.addChild(object);
				userRoot.addChild(projectRoot);
			} else {
				object = new TreeObject();
				object.setUser(user);
				object.setProject(listProject);
				userRoot.addChild(object);
			}
		}

		return userRoot;
	}
}
