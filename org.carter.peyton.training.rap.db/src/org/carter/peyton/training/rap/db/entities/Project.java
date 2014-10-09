package org.carter.peyton.training.rap.db.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@NamedQuery(name = "Project.findAll", query = "SELECT p FROM Project p")
@Table(name = "Project")
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idProject;

	private int idUser;

	@Column(name = "ProjectDescription")
	private String projectDescription;

	@Column(name = "ProjectName")
	private String projectName;

	// bi-directional many-to-one association to User
	/*@ManyToOne
	@JoinColumn(name = "idUser")
	private User user;*/

	public Project() {
	}

	public int getIdProject() {
		return this.idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getProjectDescription() {
		return this.projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	/*public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}*/

	@Override
	public String toString() {
		return getProjectName();
	}
}