package web.app.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name = "user_roles")
public class UserRole {

	private static final Logger LOG = Logger.getLogger(UserRole.class);
	
	@Id
	@Column(name = "roleID")
	private Integer roleID;

	@Column(name = "userID")
	private Integer userID;

	public UserRole() {
		LOG.info("default constr UserRole");
	}

	public UserRole(Integer userId, Integer roleId) {
		LOG.info("overloaded constr UserRole 2 params");
		this.userID = userId;
		this.roleID = roleId;
	}
	
	public Integer getUserId() {
		return userID;
	}

	public void setUserId(Integer userId) {
		this.userID = userId;
	}

	public Integer getRoleId() {
		return roleID;
	}

	public void setRoleId(Integer roleId) {
		this.roleID = roleId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleID == null) ? 0 : roleID.hashCode());
		result = prime * result + ((userID == null) ? 0 : userID.hashCode());
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
		UserRole other = (UserRole) obj;
		if (roleID == null) {
			if (other.roleID != null)
				return false;
		} else if (!roleID.equals(other.roleID))
			return false;
		if (userID == null) {
			if (other.userID != null)
				return false;
		} else if (!userID.equals(other.userID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserRole [userId=" + userID + ", roleId=" + roleID + "]";
	}
	
	
}
