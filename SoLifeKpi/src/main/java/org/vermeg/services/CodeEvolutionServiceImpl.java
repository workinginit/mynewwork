package org.vermeg.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections4.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.vermeg.entities.CodeEvo;
import org.vermeg.entities.NombreDeCommit;
import org.vermeg.entities.SvnCommit;
import org.vermeg.repository.CodeEvoRepository;
import org.vermeg.repository.SonarRepository;
import org.vermeg.repository.SvnCommitRepository;
import org.vermeg.repository.SvnModuleRepository;

@Service
public class CodeEvolutionServiceImpl implements CodeEvolutionService{

	@Autowired
    private CodeEvoRepository codeEvoRepository;

	@Autowired
    private SvnCommitRepository svnCommitRepository;

	@Autowired
    private SonarRepository sonarRepository;
	
	@Autowired
	private CodeChangeService codeChangeService;
	
    @Autowired
    private SvnModuleRepository svnService;
    
	@Override
	public List<CodeEvo> listOfEvolution(String module, String startDate, String endDate) {
		
		return codeEvoRepository.findByModuleAndDateWeekBetweenOrderByDateWeekAsc("SoLifeSatellite-SvnService","2018-01-01", "2018-06-01");
	}

	@Override
	public CodeEvo projectEvolution() {

    	List<SvnCommit> listsvncommit = IteratorUtils.toList(svnCommitRepository.findAll().iterator( ));
		int count = 0;

    	for(SvnCommit si :  listsvncommit) {
    		for(String path: si.getPaths()) {
    				count++;	
    		}	
    	}
    	
		int sonar = Integer.valueOf((int) sonarRepository.count());
		
		CodeEvo ce = new CodeEvo(count, sonar, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));
		
		return ce;
	}

	@Override
	public List<CodeEvo> listcodeevolution() {
    	List<CodeEvo> listOfCommit = new ArrayList<CodeEvo>();
        List<String> listmodule = svnService.findById("1000").get().getModule();
    	List<SvnCommit> listsvncommit = IteratorUtils.toList(svnCommitRepository.findAll().iterator( ));

        for(String rt : listmodule) {
        	
    		int sonar = Integer.valueOf((int) sonarRepository.countByModule(rt));

    		int svn = countSvnCommit(rt, listsvncommit);

    		CodeEvo ce = new CodeEvo(rt,rt,svn, sonar, new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()));

    		listOfCommit.add(ce);
        }

		return listOfCommit;
	}

	@Override
	public int countSvnCommit(String module, List<SvnCommit> list) {
		int count = 0;
		for(SvnCommit si : list) {
			for(String path: si.getPaths()) {
				if(path.startsWith(module)) {
					count++;
				}
			}
		}

		return count;
	}
	
	

}
