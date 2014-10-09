package org.carter.peyton.training.rap.view.action;

import org.carter.peyton.training.rap.db.entities.Device;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class TableViewerFilter extends ViewerFilter {

    private static final long serialVersionUID = -3280464395905829610L;

    private String searchString;

    public void setSearchText(String s) {
        // ensure that the value can be used for matching
        this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {

        if (searchString == null || searchString.length() == 0) {
            return true;
        }

        Device d = (Device) element;
        
        if (d.getDeviceName() != null && d.getDeviceName().matches(searchString)) {
            return true;
        }

        if (d.getAppModule() != null && d.getAppModule().matches(searchString)) {
            return true;
        }

        if (d.getDeviceType() != null && d.getDeviceType().matches(searchString)) {
            return true;
        }

        if (d.getPhysicalLocation() != null && d.getPhysicalLocation().matches(searchString)) {
        	return true;
        }

        if (d.getManufacturer() != null && d.getManufacturer().matches(searchString)) {
            return true;
        }

        return false;
    }
}
