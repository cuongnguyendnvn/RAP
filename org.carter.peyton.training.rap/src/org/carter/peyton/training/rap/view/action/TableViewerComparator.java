package org.carter.peyton.training.rap.view.action;

import org.carter.peyton.training.rap.models.Device;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

public class TableViewerComparator extends ViewerComparator {

    private static final long serialVersionUID = -7624051535087453558L;

    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public TableViewerComparator() {
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            // Same column as last sort; toggle the direction
            direction = 1 - direction;
        } else {
            // New column; do an ascending sort
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
      public int compare(Viewer viewer, Object e1, Object e2) {
        Device p1 = (Device) e1;
        Device p2 = (Device) e2;
        int rc = 0;
        switch (propertyIndex) {
        case 0:
          rc = p1.getDeviceName().compareTo(p2.getDeviceName());
          break;
        case 1:
          rc = p1.getAppModule().compareTo(p2.getAppModule());
          break;
        case 2:
          rc = p1.getDeviceType().compareTo(p2.getDeviceType());
          break;
        case 3:
          rc = p1.getPhysicalLocation().compareTo(p2.getPhysicalLocation());
          break;
        case 4:
          rc = p1.getManufacturer().compareTo(p2.getManufacturer());
          break;
        default:
          rc = 0;
        }
        // If descending order, flip the direction
        if (direction == DESCENDING) {
          rc = -rc;
        }
        return rc;
      }
}
