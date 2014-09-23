package org.carter.peyton.training.rap.models;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Version database table.
 * 
 */
@Entity
@NamedQuery(name="Version.findAll", query="SELECT v FROM Version v")
@Table(name="Version")
public class Version implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idVersion;

	@Column(name="DeploySource")
	private String deploySource;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DeployTime")
	private Date deployTime;

	private int idProject;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SaveTime")
	private Date saveTime;

	@Column(name="TargetVersion")
	private String targetVersion;

	@Column(name="VersionName")
	private String versionName;
	
	@Column(name="isLastDeployedVersion")
	private int isLastDeployedVersion;

	//bi-directional many-to-one association to Device
	@OneToMany(mappedBy="version")
	private List<Device> devices;

	public Version() {
	}

	public int getIdVersion() {
		return this.idVersion;
	}

	public void setIdVersion(int idVersion) {
		this.idVersion = idVersion;
	}

	public String getDeploySource() {
		return this.deploySource;
	}

	public void setDeploySource(String deploySource) {
		this.deploySource = deploySource;
	}

	public Date getDeployTime() {
		return this.deployTime;
	}

	public void setDeployTime(Date deployTime) {
		this.deployTime = deployTime;
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public Date getSaveTime() {
		return this.saveTime;
	}

	public void setSaveTime(Date saveTime) {
		this.saveTime = saveTime;
	}

	public String getTargetVersion() {
		return this.targetVersion;
	}

	public void setTargetVersion(String targetVersion) {
		this.targetVersion = targetVersion;
	}

	public String getVersionName() {
		return this.versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getIsLastDeployedVersion() {
		return isLastDeployedVersion;
	}

	public void setIsLastDeployedVersion(int isLastDeployedVersion) {
		this.isLastDeployedVersion = isLastDeployedVersion;
	}

	public List<Device> getDevices() {
		return this.devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public Device addDevice(Device device) {
		getDevices().add(device);
		device.setVersion(this);

		return device;
	}

	public Device removeDevice(Device device) {
		getDevices().remove(device);
		device.setVersion(null);

		return device;
	}

	@Override
	public String toString() {
		return getVersionName();
	}
}