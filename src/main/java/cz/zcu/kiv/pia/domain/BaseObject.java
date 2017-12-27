package cz.zcu.kiv.pia.domain;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class BaseObject {

    private Long id;

    /**
     *
     * @return true if the entity hasn't been persisted yet
     */
    @Transient
    public boolean isNew() {
        return id == null;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
