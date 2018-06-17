package org.vermeg.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "jenkins", type = "builds")
public class JenkinsBuild {
	
	@Id
	private int number;
	private String name;
	private long duration;
	private String result;
	private String date;
	
	public JenkinsBuild() {
		super();
	}
	public JenkinsBuild(int number, String name, long duration, String result, String date) {
		super();
		this.number = number;
		this.name = name;
		this.duration = duration;
		this.result = result;
		this.date = date;
	}
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + number;
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JenkinsBuild other = (JenkinsBuild) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (duration != other.duration)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (number != other.number)
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}
	
	
}
