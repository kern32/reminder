package web.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "roles")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("file");
	
	@Id
	@GeneratedValue
	@Column(name = "id", length = 50)
	private Integer id;

	@Column(name = "role", length = 20, nullable = false)
	private String role;

	public Role() {
		super();
		log.info("Role entity: default constr");
	}

	public Role(Integer id, String role) {
		log.info("Role entity: constr with params");
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		log.info("Role entity: get id");
		return id;
	}

	public void setId(Integer id) {
		log.info("Role entity: set id");
		this.id = id;
	}

	public String getRole() {
		log.info("Role entity: get role name");
		return role;
	}

	public void setRole(String role) {
		log.info("Role entity: set role name");
		this.role = role;
	}

	@Override
	public int hashCode() {
		log.info("Role entity: invoke hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.info("Role entity: invoke equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		log.info("Role entity: invoke toString");
		return "Role [id=" + id + ", role=" + role + "]";
	}
}
