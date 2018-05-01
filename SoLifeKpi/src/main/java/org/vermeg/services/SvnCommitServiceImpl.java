package org.vermeg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vermeg.repository.SvnCommitRepository;

@Service
public class SvnCommitServiceImpl implements SvnCommitService {

	@Autowired
    private SvnCommitRepository svnCommitRepository;
	


}
