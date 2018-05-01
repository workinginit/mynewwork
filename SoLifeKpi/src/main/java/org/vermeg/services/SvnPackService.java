package org.vermeg.services;


import org.vermeg.entities.PackageByModule;

public interface SvnPackService {

	 PackageByModule findByModule(String module);

}
