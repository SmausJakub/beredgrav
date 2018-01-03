package cz.zcu.kiv.pia.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kruzej_comment")
public class Comment extends BaseEntity {
	
	private String text;
	private Date dateOfComment;
	
	private User owner;
	
	private Status status;
	

	public Comment(String text, Date dateOfComment, User owner) {
		this.text = text;
		this.dateOfComment = dateOfComment;
		this.owner = owner;
	}
	
	
	@ManyToOne
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Lob
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public Date getDateOfComment() {
		return dateOfComment;
	}

	public void setDateOfComment(Date dateOfComment) {
		this.dateOfComment = dateOfComment;
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
		result = prime * result + ((dateOfComment == null) ? 0 : dateOfComment.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
		Comment other = (Comment) obj;
		return other.getId().equals(this.id);
	}

	@Override
	public String toString() {
		return "Comment [text=" + text + ", dateOfComment=" + dateOfComment + ", owner=" + owner + ","
				+ "]";
	}
	
	
	
	

}
