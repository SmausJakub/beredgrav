package cz.zcu.kiv.pia.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;



/**
 * Entity representing application User.
 *
 * Date: 26.11.15
 *
 * @author Jakub Danek
 */
@Entity
@Table(name = "kruzej_user")
public class User extends BaseEntity {

    private String username;
    private String password;
    private Date dateOfBirth;
    private String email;
    private String gender;
    private String avatar;
    
    private Set<Status> likes;
    private Set<Status> hates;

    public User(String username, String password, Date dateOfBirth, String gender, String avatar) {
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.avatar = avatar;
    }
    
    /**
     * Validates that user instance is currently in a valid state.
     * @throws UserValidationException in case the instance is not in valid state.
     */
    public void validate() throws UserValidationException {
        if(StringUtils.isBlank(username)) throw new UserValidationException("Username is a required field");
        if(StringUtils.isBlank(password)) throw new UserValidationException("Password is a required field");
    }
    
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    		name = "kruzej_user_likes",
    		joinColumns = { @JoinColumn(name="username")},
    		inverseJoinColumns = { @JoinColumn(name="status_id")}
    		)
    public Set<Status> getLikes() {
		return likes;
	}

	public void setLikes(Set<Status> likes) {
		this.likes = likes;
	}
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
    		name = "kruzej_user_hates",
    		joinColumns = { @JoinColumn(name="username")},
    		inverseJoinColumns = { @JoinColumn(name="status_id")}
    		)
	public Set<Status> getHates() {
		return hates;
	}

	public void setHates(Set<Status> hates) {
		this.hates = hates;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		return other.getId().equals(this.id);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", dateOfBirth=" + dateOfBirth + ", email="
				+ email + ", gender=" + gender + ", avatar=" + avatar + "]";
	}

	
}