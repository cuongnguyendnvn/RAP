package org.carter.peyton.training.rap.dao;

import java.util.List;

import org.carter.peyton.training.rap.models.Version;

public interface VersionDAO {
    public Version getVersionByName (String versionName);
    public List<Version> getListVersions();
}
