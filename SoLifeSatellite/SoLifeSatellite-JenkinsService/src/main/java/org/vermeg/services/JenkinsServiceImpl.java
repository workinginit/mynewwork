package org.vermeg.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vermeg.entities.JenkinsBuild;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;

@Service
public class JenkinsServiceImpl implements JenkinsService {

	@Value("${jenkins.url:http://localhost:8080}")
	private String url;

	@Value("${jenkins.userName:hedi}")
	private String userName;

	@Value("${jenkins.password:Hedihunter12}")
	private String password;

	@Override
	public List<JenkinsBuild> allJenkinsBuild(String ProjectName) {
		JenkinsServer jenkins = ConnexionJenkinsService.getInstance(url, userName, password);

		ArrayList<JenkinsBuild> listofbuild = new ArrayList<JenkinsBuild>();
		Map<String, Job> jobs;
		try {
			jobs = jenkins.getJobs();
			
			JobWithDetails job = jobs.get(ProjectName).details();

			for (Build build : job.getAllBuilds()) {
				JenkinsBuild jk = new JenkinsBuild(build.getNumber(), build.details().getFullDisplayName(),
						build.details().getDuration(), build.details().getResult().toString(),
						DateFormatUtils.format(build.details().getTimestamp(), "yyyy-MM-dd"));
				listofbuild.add(jk);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return listofbuild;
	}

}
