package cz.zcu.kiv.pia.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kruzej_status")
public class Status extends BaseEntity implements Comparable<Status> {

	
	private String text;
	private Date dateOfStatus;
	
	private Set<User> likes;
	private Set<User> hates;
	
	private User owner;
	
	public Status() {
		
	}
	
	public Status(User owner, String text, Date dateOfStatus)  {
		this.text = text;
		this.dateOfStatus = dateOfStatus;
		this.owner = owner;
		this.likes = new HashSet<>();
		this.hates = new HashSet<>();
	}
	
	
	@ManyToMany(mappedBy = "likes", fetch = FetchType.EAGER)
	public Set<User> getLikes() {
		return likes;
	}



	public void setLikes(Set<User> likes) {
		this.likes = likes;
	}


	@ManyToMany(mappedBy = "hates", fetch = FetchType.EAGER)
	public Set<User> getHates() {
		return hates;
	}



	public void setHates(Set<User> hates) {
		this.hates = hates;
	}



	@Lob
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getDateOfStatus() {
		return dateOfStatus;
	}

	public void setDateOfStatus(Date dateOfStatus) {
		this.dateOfStatus = dateOfStatus;
	}
	

	@OneToOne
	public User getOwner() {
		return owner;
	}


	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfStatus == null) ? 0 : dateOfStatus.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Status other = (Status) obj;
		
		return other.getId().equals(this.id);
	}

	@Override
	public String toString() {
		return "Status [text=" + text + ", dateOfStatus=" + dateOfStatus + "]";
	}


	@Override
	public int compareTo(Status o) {
		return o.getDateOfStatus().compareTo(this.dateOfStatus);
	}
	
	
	

}
