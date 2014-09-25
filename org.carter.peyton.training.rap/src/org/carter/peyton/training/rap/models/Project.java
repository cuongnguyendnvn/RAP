package org.carter.peyton.training.rap.models;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the Project database table.
 * 
 */
@Entity
@NamedQuery(name="Project.findAll", query="SELECT p FROM Project p")
@Table(name="Project")
public class Project implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int idProject;

    private int idUser;

    @Column(name="ProjectDescription")
    private String projectDescription;

    @Column(name="ProjectName")
    private String projectName;

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
    
    @Override
    public String toString() {
        return getProjectName();
    }
}