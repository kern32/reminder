package web.app.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.log4j.Logger;

@Entity
@Table(name = "users", uniqueConstraints = {
		@UniqueConstraint(columnNames = "username"),
		@UniqueConstraint(columnNames = "email")
		})
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("file");
	
	@Id
	@GeneratedValue
	@Column(name = "id", length = 50)
	private Integer id;

	@Column(name = "username", unique = true, nullable = false, length = 30)
	private String username;

	@Column(name = "email", unique = true, nullable = false, length = 50)
	private String email;
	
	@Column(name = "skype", nullable = true, length = 30)
	private String skype;
	
	@Column(name = "phone", nullable = true, length = 30)
	private String phone;
	
	@Column(name = "password", nullable = false, length = 100)
	private String password;

	@Column(name = "enabled", nullable = false, length = 1)
	private boolean enabled;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",
		joinColumns = {@JoinColumn(name="userId", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="roleId", referencedColumnName="id")}
	)
	private Role role;
	
	public Role getRole() {
		log.info("Users entity: get role entity");
		return role;
	}

	public void setRole(Role role) {
		log.info("Users entity: set role entity");
		this.role = role;
	}
	
	public Users() {
		super();
		log.info("Users entity: default constr");
	}

	public Users(Integer id, String username, String email, String skype, String phone, String password,
			boolean enabled, Role role) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.skype = skype;
		this.phone = phone;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		log.info("Users entity: constr with params");
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "users")
	public Integer getId() {
		log.info("Users entity: get id");
		return id;
	}

	public void setId(Integer id) {
		log.info("Users entity: set id");
		this.id = id;
	}

	public String getUsername() {
		log.info("Users entity: get user name");
		return username;
	}

	public void setUsername(String username) {
		log.info("Users entity: set user name");
		this.username = username;
	}

	public String getEmail() {
		log.info("Users entity: get email");
		return email;
	}

	public void setEmail(String email) {
		log.info("Users entity: set email");
		this.email = email;
	}

	public String getSkype() {
		log.info("Users entity: get skype");
		return skype;
	}

	public void setSkype(String skype) {
		log.info("Users entity: set skype");
		this.skype = skype;
	}

	public String getPhone() {
		log.info("Users entity: get phone");
		return phone;
	}

	public void setPhone(String phone) {
		log.info("Users entity: set phone");
		this.phone = phone;
	}

	public String getPassword() {
		log.info("Users entity: get password");
		return password;
	}

	public void setPassword(String password) {
		log.info("Users entity: set password");
		this.password = password;
	}

	public boolean isEnabled() {
		log.info("Users entity: get enabled");
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		log.info("Users entity: set enabled");
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		log.info("Users entity: invoke hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((skype == null) ? 0 : skype.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.info("Users entity: invoke equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (skype == null) {
			if (other.skype != null)
				return false;
		} else if (!skype.equals(other.skype))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		log.info("Users entity: invoke toString");
		return "Users [id=" + id + ", username=" + username + ", email=" + email + ", skype=" + skype + ", phone="
				+ phone + ", password=" + password + ", enabled=" + enabled + ", role=" + role + "]";
	}
}