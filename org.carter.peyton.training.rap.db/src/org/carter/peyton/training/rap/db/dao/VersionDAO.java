package org.carter.peyton.training.rap.db.dao;

import java.util.List;

import org.carter.peyton.training.rap.db.entities.Version;

public interface VersionDAO {
    public Version getVersionByName (String versionName);
    public List<Version> getListVersions();
}
