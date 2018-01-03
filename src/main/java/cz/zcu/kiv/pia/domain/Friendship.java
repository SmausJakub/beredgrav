package cz.zcu.kiv.pia.domain;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kruzej_friendship")
public class Friendship extends BaseEntity {
	
	private int approved;
	
	private User initiator;
	
	private User target;
	
	
	public Friendship(User initiator, User target) {
		this.initiator = initiator;
		this.target = target;
		this.approved = 0;
	}


	public int getApproved() {
		return approved;
	}


	public void setApproved(int approved) {
		this.approved = approved;
	}

	@OneToOne
	public User getInitiator() {
		return initiator;
	}


	public void setInitiator(User initiator) {
		this.initiator = initiator;
	}

	@OneToOne
	public User getTarget() {
		return target;
	}


	public void setTarget(User target) {
		this.target = target;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approved;
		result = prime * result + ((initiator == null) ? 0 : initiator.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		Friendship other = (Friendship) obj;
		return other.getId().equals(this.id);
	}


	@Override
	public String toString() {
		return "Friendship [approved=" + approved + ", initiator=" + initiator + ", target=" + target + "]";
	}

	

	
	
	
	
}
