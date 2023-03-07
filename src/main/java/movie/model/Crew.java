package movie.model;

public class Crew {
	protected String crewId;
	protected String name;
	protected String birthYear;
	protected String deathYear;
	protected String primaryProfession;

	// used for both read from database and write to database
	public Crew(String crewId, String name, String birthYear, String deathYear, String primaryProfession) {
		super();
		this.crewId = crewId;
		this.name = name;
		this.birthYear = birthYear;
		this.deathYear = deathYear;
		this.primaryProfession = primaryProfession;
	}

	public String getCrewId() {
		return crewId;
	}

	public void setCrewId(String crewId) {
		this.crewId = crewId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public String getDeathYear() {
		return deathYear;
	}

	public void setDeathYear(String deathYear) {
		this.deathYear = deathYear;
	}

	public String getPrimaryProfession() {
		return primaryProfession;
	}

	public void setPrimaryProfession(String primaryProfession) {
		this.primaryProfession = primaryProfession;
	}

	@Override
	public String toString() {
		return "Crew [crewId=" + crewId + ", name=" + name + ", birthYear=" + birthYear + ", deathYear=" + deathYear
				+ ", primaryProfession=" + primaryProfession + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthYear == null) ? 0 : birthYear.hashCode());
		result = prime * result + ((crewId == null) ? 0 : crewId.hashCode());
		result = prime * result + ((deathYear == null) ? 0 : deathYear.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((primaryProfession == null) ? 0 : primaryProfession.hashCode());
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
		Crew other = (Crew) obj;
		if (birthYear == null) {
			if (other.birthYear != null)
				return false;
		} else if (!birthYear.equals(other.birthYear))
			return false;
		if (crewId == null) {
			if (other.crewId != null)
				return false;
		} else if (!crewId.equals(other.crewId))
			return false;
		if (deathYear == null) {
			if (other.deathYear != null)
				return false;
		} else if (!deathYear.equals(other.deathYear))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (primaryProfession == null) {
			if (other.primaryProfession != null)
				return false;
		} else if (!primaryProfession.equals(other.primaryProfession))
			return false;
		return true;
	}

}
