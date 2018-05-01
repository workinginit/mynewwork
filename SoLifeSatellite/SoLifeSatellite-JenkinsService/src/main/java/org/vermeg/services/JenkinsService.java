package org.vermeg.services;

import java.util.List;

import org.vermeg.entities.JenkinsBuild;

public interface JenkinsService {

	List<JenkinsBuild> allJenkinsBuild(String ProjectName);
}
