package cz.zcu.kiv.pia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kruzej_status")
public class Status extends BaseEntity {

	
	private String text;
	private Date dateOfStatus;
	
	private int lajk = 0;
	private int hate = 0;
	
	private int commentNumber = 0;

	private User owner;
	
	public Status(String text, Date dateOfStatus)  {
		this.text = text;
		this.dateOfStatus = dateOfStatus;
	}
	

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
	@JoinColumn(name="username")
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
		if (dateOfStatus == null) {
			if (other.dateOfStatus != null)
				return false;
		} else if (!dateOfStatus.equals(other.dateOfStatus))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [text=" + text + ", dateOfStatus=" + dateOfStatus + "]";
	}
	
	
	

}
