package org.carter.peyton.training.rap.view;

import java.net.URL;
import java.util.List;

import org.carter.peyton.training.rap.db.dao.impl.ProjectDAOImpl;
import org.carter.peyton.training.rap.db.dao.impl.UserDAOImpl;
import org.carter.peyton.training.rap.db.dao.impl.VersionDAOImpl;
import org.carter.peyton.training.rap.db.entities.Project;
import org.carter.peyton.training.rap.db.entities.User;
import org.carter.peyton.training.rap.db.entities.Version;
import org.carter.peyton.training.rap.domain.TreeObject;
import org.carter.peyton.training.rap.domain.TreeParent;
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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
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
    private VersionDAOImpl versionDAOImpl;

    private ToolItem item4;
    private ToolItem item5;
    private ToolItem item6;

    @Override
    public void createPartControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NO_REDRAW_RESIZE);
        composite.setLayout(new GridLayout());
        createToolbaViewer(composite);
        FilteredTree filteredTree = new FilteredTree(composite, SWT.MULTI
                | SWT.H_SCROLL | SWT.V_SCROLL, new PatternFilter(), true);

        treeViewer = filteredTree.getViewer();

        treeViewer.setContentProvider(new ViewContentProvider());

        treeViewer.setLabelProvider(new ViewLabelProvider());

        treeViewer.setInput(getInitialInput());

        treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            @Override
            public void selectionChanged(SelectionChangedEvent event) {
                item4.setEnabled(true);
                item5.setEnabled(true);
                item6.setEnabled(true);
            }
        });

        getSite().setSelectionProvider(treeViewer);
    }

    @Override
    public void setFocus() {
        treeViewer.getControl().setFocus();
    }

    private void createToolbaViewer(Composite parent) {
        Composite composite = new Composite(parent, SWT.RIGHT);
        RowLayout rowLayout = new RowLayout();
        rowLayout.marginLeft = 285;
        composite.setLayout(rowLayout);

        ToolBar toolBar = new ToolBar(composite, SWT.FLAT);

        ToolItem item1 = new ToolItem(toolBar, SWT.PUSH);
        item1.setImage(getImageDescriptor("refresh.png").createImage());

        new ToolItem(toolBar, SWT.SEPARATOR);

        ToolItem item2 = new ToolItem(toolBar, SWT.PUSH);
        item2.setImage(getImageDescriptor("show_advanced_fields.png").createImage());

        new ToolItem(toolBar, SWT.SEPARATOR);

        ToolItem item3 = new ToolItem(toolBar, SWT.PUSH);
        item3.setImage(getImageDescriptor("new_project.png").createImage());

        new ToolItem(toolBar, SWT.SEPARATOR);

        item4 = new ToolItem(toolBar, SWT.PUSH);
        item4.setImage(getImageDescriptor("clone_version.png").createImage());
        item4.setEnabled(false);

        item5 = new ToolItem(toolBar, SWT.PUSH);
        item5.setImage(getImageDescriptor("save_as.png").createImage());
        item5.setEnabled(false);

        item6 = new ToolItem(toolBar, SWT.PUSH);
        item6.setImage(getImageDescriptor("node_delete.png").createImage());
        item6.setEnabled(false);
    }

    class ViewContentProvider implements ITreeContentProvider {

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
