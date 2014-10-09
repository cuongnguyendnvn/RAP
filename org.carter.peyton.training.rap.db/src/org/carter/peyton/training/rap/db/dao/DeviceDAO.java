package org.carter.peyton.training.rap.db.dao;

import java.util.List;

import org.carter.peyton.training.rap.db.entities.Device;

public interface DeviceDAO {
    public List<Device> getDeviceList();
}
