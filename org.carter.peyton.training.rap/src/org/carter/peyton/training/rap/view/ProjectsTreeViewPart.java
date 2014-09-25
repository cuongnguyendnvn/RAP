package org.carter.peyton.training.rap.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.carter.peyton.training.rap.dao.impl.ProjectDAOImpl;
import org.carter.peyton.training.rap.dao.impl.UserDAOImpl;
import org.carter.peyton.training.rap.dao.impl.VersionDAOImpl;
import org.carter.peyton.training.rap.domain.TreeObject;
import org.carter.peyton.training.rap.domain.TreeParent;
import org.carter.peyton.training.rap.models.Project;
import org.carter.peyton.training.rap.models.User;
import org.carter.peyton.training.rap.models.Version;
import org.carter.peyton.training.rap.view.action.AddProjectViewToolbar;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.dialogs.FilteredTree;
import org.eclipse.ui.dialogs.PatternFilter;
import org.eclipse.ui.internal.util.BundleUtility;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.Bundle;

public class ProjectsTreeViewPart extends ViewPart {

	public static final String ID = "org.carter.peyton.training.rap.view.ProjectsTreeView";

	public static final String ID_DRIVER = "org.carter.peyton.training.rap.view.DriversTreeView";
	public static final String ID_AREA = "org.carter.peyton.training.rap.view.AreasTreeView";

	private TreeViewer treeViewer;
	private UserDAOImpl userDAOImpl;
	private ProjectDAOImpl projectDAOImpl;
	private VersionDAOImpl versionDAOImpl;
	private CoolBar coolBar;

	@Override
	public void createPartControl(Composite parent) {
		parent.setLayout(new GridLayout());
		createCoolbar(parent);
		FilteredTree filteredTree = new FilteredTree(parent, SWT.MULTI
				| SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);

		treeViewer = filteredTree.getViewer();

		treeViewer.setContentProvider(new ViewContentProvider());

		treeViewer.setLabelProvider(new ViewLabelProvider());

		treeViewer.setInput(getInitialInput());

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				// TODO Auto-generated method stub

			}
		});

		/*
		 * AddProjectViewToolbar toolbar1 = new AddProjectViewToolbar();
		 * toolbar1.setText("Reset perspective");
		 * toolbar1.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/reset_perspective.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar1);
		 * 
		 * AddProjectViewToolbar toolbar2 = new AddProjectViewToolbar();
		 * toolbar2.setText("Show advance field");
		 * toolbar2.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/show_advanced_fields.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar2);
		 * 
		 * AddProjectViewToolbar toolbar3 = new AddProjectViewToolbar();
		 * toolbar3.setText("New project");
		 * toolbar3.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/new_project.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar3);
		 * 
		 * AddProjectViewToolbar toolbar4 = new AddProjectViewToolbar();
		 * toolbar4.setText("Clone version");
		 * toolbar4.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/clone_version.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar4);
		 * 
		 * AddProjectViewToolbar toolbar5 = new AddProjectViewToolbar();
		 * toolbar5.setText("Save as");
		 * toolbar5.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/save_as.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar5);
		 * 
		 * AddProjectViewToolbar toolbar6 = new AddProjectViewToolbar();
		 * toolbar6.setText("Node delete");
		 * toolbar6.setImageDescriptor(AbstractUIPlugin
		 * .imageDescriptorFromPlugin("org.carter.peyton.training.rap",
		 * "images/node_delete.png"));
		 * getViewSite().getActionBars().getToolBarManager().add(toolbar6);
		 */

		getSite().setSelectionProvider(treeViewer);
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	private void createCoolbar(Composite parent) {
		coolBar = new CoolBar(parent, SWT.RIGHT);

		List<String> firstToolbarItemImageLocation = new ArrayList<String>();
		firstToolbarItemImageLocation.add("images/reset_perspective.png");
		createItem(coolBar, firstToolbarItemImageLocation);
		
		List<String> secondToolbarItemImageLocation = new ArrayList<String>();
		secondToolbarItemImageLocation.add("images/show_advanced_fields.png");
		createItem(coolBar, secondToolbarItemImageLocation);
		
		List<String> thirdToolbarItemImageLocation = new ArrayList<String>();
		thirdToolbarItemImageLocation.add("images/new_project.png");
		createItem(coolBar, thirdToolbarItemImageLocation);
		
		List<String> fourthToolbarItemImageLocation = new ArrayList<String>();
		fourthToolbarItemImageLocation.add("images/clone_version.png");
		fourthToolbarItemImageLocation.add("images/save_as.png");
		fourthToolbarItemImageLocation.add("images/node_delete.png");
		createItem(coolBar, fourthToolbarItemImageLocation);
		
		coolBar.setLocation(5, 5);
		coolBar.setSize(coolBar.computeSize(parent.getSize().x - 10, SWT.DEFAULT));
	}
	
	private CoolItem createItem(final CoolBar coolBar, List<String> imageLocation) {
		ToolBar toolBar = createToolBar(coolBar, imageLocation);
		toolBar.pack();
		Point size = toolBar.getSize();
		// TODO [fappel]: check whether size adjustment is really needed or a
		// bug .
		size = new Point(size.x + (int) (size.x * 0.1), size.y);
		CoolItem item = new CoolItem(coolBar, SWT.RIGHT);
		item.setControl(toolBar);
		Point preferred = item.computeSize(size.x, size.y);
		item.setPreferredSize(preferred);
		return item;
	}

	private ToolBar createToolBar(final Composite parent, List<String> imageLocation) {
		
		ToolBar toolBar = new ToolBar(parent, SWT.HORIZONTAL | SWT.RIGHT | SWT.FLAT);
		
		ToolItem item;
		
		for (String image : imageLocation) {
			item = new ToolItem(toolBar, SWT.PUSH);
			item.setImage(AbstractUIPlugin.imageDescriptorFromPlugin("org.carter.peyton.training.rap", image).createImage());
		}
		
		return toolBar;
	}

	class ViewContentProvider implements ITreeContentProvider {

		/**
         * 
         */
		private static final long serialVersionUID = 714233580229247232L;

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

			return getChildren(inputElement);
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
		/**
         * 
         */
		private static final long serialVersionUID = 7155073852861873642L;

		@Override
		public Image getImage(Object element) {
			ImageDescriptor descriptor = null;

			if (element instanceof TreeParent) {
				TreeParent treeParent = (TreeParent) element;
				if (treeParent.isUserObject()) {
					descriptor = getImageDescriptor("company_group.png");
				} else if (treeParent.isProjectObject()) {
					descriptor = getImageDescriptor("project.png");
				}
			}

			if (element instanceof User) {
				descriptor = getImageDescriptor("company_group.png");
			} else if (element instanceof Project) {
				descriptor = getImageDescriptor("project.png");
			} else if (element instanceof Version) {
				Version version = (Version) element;
				if (version.getIsLastDeployedVersion() == 1) {
					descriptor = getImageDescriptor("last_deployed_version.png");
				} else {
					descriptor = getImageDescriptor("version.png");
				}
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
		TreeParent projectRoot;
		TreeParent versionRoot;

		// Get list User
		userDAOImpl = new UserDAOImpl();
		List<User> listUser = userDAOImpl.getListUser();

		// Get list Project
		projectDAOImpl = new ProjectDAOImpl();
		List<Project> listProject = projectDAOImpl.getListProjects();

		// Get list Version
		versionDAOImpl = new VersionDAOImpl();
		List<Version> listVersion = versionDAOImpl.getListVersions();

		for (User user : listUser) {
			if (indexReferUserProject(user, listProject)) {
				// Set project for user
				projectRoot = new TreeParent();
				for (Project project : listProject) {
					if (user.getIdUser() == project.getIdUser()) {
						if (indexReferProjectVersion(project, listVersion)) {
							// Set version for project
							versionRoot = new TreeParent();
							for (int i = listVersion.size() - 1; i > 0; i--) {
								if (project.getIdProject() == listVersion
										.get(i).getIdProject()) {
									versionRoot.addChild(listVersion.get(i));
								}
							}

							versionRoot.setRootName(project.toString());
							versionRoot.setProjectObject(true);
							projectRoot.addChild(versionRoot);
						} else {
							projectRoot.addChild(project);
						}
					}
				}

				// Set name of user own project
				projectRoot.setRootName(user.toString());
				projectRoot.setUserObject(true);
				userRoot.addChild(projectRoot);
			} else {
				userRoot.addChild(user);
			}
		}

		return userRoot;
	}

	private boolean indexReferUserProject(User user, List<Project> listProject) {
		for (Project project : listProject) {
			if (user.getIdUser() == project.getIdUser()) {
				return true;
			}
		}

		return false;
	}

	private boolean indexReferProjectVersion(Project project,
			List<Version> listVersion) {
		for (Version version : listVersion) {
			if (project.getIdProject() == version.getIdProject()) {
				return true;
			}
		}

		return false;
	}
}
