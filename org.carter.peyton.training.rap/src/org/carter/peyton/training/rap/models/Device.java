package org.carter.peyton.training.rap.models;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Device database table.
 * 
 */
@Entity
@NamedQuery(name="Device.findAll", query="SELECT d FROM Device d")
@Table(name="Device")
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idDevice;

	@Column(name="AppModule")
	private String appModule;

	@Column(name="DeviceName")
	private String deviceName;

	@Column(name="DeviceType")
	private String deviceType;

	@Column(name="Manufacturer")
	private String manufacturer;

	@Column(name="PhysicalLocation")
	private String physicalLocation;

	//bi-directional many-to-one association to Version
	@ManyToOne
	@JoinColumn(name="idVersion")
	private Version version;

	public Device() {
	}

	public int getIdDevice() {
		return this.idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getAppModule() {
		return this.appModule;
	}

	public void setAppModule(String appModule) {
		this.appModule = appModule;
	}

	public String getDeviceName() {
		return this.deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceType() {
		return this.deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getPhysicalLocation() {
		return this.physicalLocation;
	}

	public void setPhysicalLocation(String physicalLocation) {
		this.physicalLocation = physicalLocation;
	}

	public Version getVersion() {
		return this.version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public String getColumnByIndex(int colIndex) {
		if (colIndex == 0) {
			return deviceName;
		} else if (colIndex == 1) {
			return appModule;
		} else if (colIndex == 2) {
			return deviceType;
		} else if (colIndex == 3) {
			return physicalLocation;
		} else if (colIndex == 4) {
			return manufacturer;
		} else {
			return null;
		}
	}
}