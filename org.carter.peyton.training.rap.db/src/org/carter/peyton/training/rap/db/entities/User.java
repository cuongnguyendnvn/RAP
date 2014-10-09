package org.carter.peyton.training.rap.db.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;

/**
 * The persistent class for the User database table.
 * 
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
@Table(name = "User")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idUser;

	@Column(name = "UserName")
	private String userName;

	/*// bi-directional many-to-one association to Project
	@OneToMany(mappedBy = "user")
	private List<Project> projects;*/

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public List<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setUser(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setUser(null);

		return project;
	}*/

	@Override
	public String toString() {
		return getUserName();
	}
}