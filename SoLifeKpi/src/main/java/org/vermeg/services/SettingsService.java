package org.vermeg.services;

import org.vermeg.entities.ServerConfig;

public interface SettingsService {
	
	boolean saveSettings(ServerConfig sc);

	boolean saveSvnSettings(ServerConfig sc);
	
	boolean saveSonarSettings(ServerConfig sc);

	boolean saveJiraSettings(ServerConfig sc);

	boolean savejenkinsSettings(ServerConfig sc);


}
