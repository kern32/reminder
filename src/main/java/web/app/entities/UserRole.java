package web.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity 
@Table(name = "user_roles")
public class UserRole implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("file");
	
	@Id
	@GeneratedValue
	@Column(name = "id", length = 50)
	private Integer id;

	@Column(name = "roleId", length = 50, nullable = false)
	private Integer roleId;

	@Column(name = "userId", length = 50, nullable = false)
	private Integer userId;

	public UserRole() {
		super();
		log.info("UserRole entity: default constr");
	}

	public UserRole(Integer userId, Integer roleId) {
		super();
		this.roleId = roleId;
		this.userId = userId;
		log.info("UserRole entity: constr with params");
	}
	
	public Integer getId() {
		log.info("UserRole entity: get id");
		return id;
	}

	public void setId(Integer id) {
		log.info("UserRole entity: set id");
		this.id = id;
	}

	public Integer getRoleId() {
		log.info("UserRole entity: get role id");
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		log.info("UserRole entity: set role id");
		this.roleId = roleId;
	}

	public Integer getUserId() {
		log.info("UserRole entity: get user id");
		return userId;
	}

	public void setUserId(Integer userId) {
		log.info("UserRole entity: set user id");
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		log.info("UserRole entity: invoke hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.info("UserRole entity: invoke equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		log.info("UserRole entity: invoke toString");
		return "UserRole [id=" + id + ", roleId=" + roleId + ", userId=" + userId + "]";
	}
}
