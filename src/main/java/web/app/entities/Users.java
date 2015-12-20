package web.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import org.apache.log4j.Logger;

@Entity
@Table(name = "users")
public class Users implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Users.class);
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;
	
	@Column(name = "skype")
	private String skype;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Integer enabled;

	
	public Users() {
		LOG.info("default constr User");
	}

	public Users(Integer id) {
		LOG.info("overloaded constr User 1 params");
		this.id = id;
	}

	public Users(Integer id, String name, String email, String skype, String phone, String jabber, String psw, Integer key) {
		LOG.info("overloaded constr User 5 params");
		this.id = id;
		this.username = name;
		this.email = email;
		this.skype = skype;
		this.phone = phone;
		this.password = psw;
		this.enabled = key;
	}

	public Integer getId() {
		LOG.info("calling getter User id");
		return id;
	}

	public void setId(Integer id) {
		LOG.info("calling setter User id");
		this.id = id;
	}

	public String getName() {
		LOG.info("calling getter User name");
		return username;
	}

	public void setName(String name) {
		LOG.info("calling setter User name");
		this.username = name;
	}

	public String getEmail() {
		LOG.info("calling getter User email");
		return email;
	}

	public void setEmail(String email) {
		LOG.info("calling setter User email");
		this.email = email;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPsw() {
		LOG.info("calling getter User password");
		return password;
	}

	public void setPsw(String psw) {
		LOG.info("calling setter User password");
		this.password = psw;
	}

	public Integer getEnabled() {
		LOG.info("calling getter User enabled");
		return enabled;
	}

	public void setEnabled(String key) {
		LOG.info("calling setter User enabled");
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((enabled == null) ? 0 : enabled.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((skype == null) ? 0 : skype.hashCode());
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
		Users other = (Users) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (enabled == null) {
			if (other.enabled != null)
				return false;
		} else if (!enabled.equals(other.enabled))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (skype == null) {
			if (other.skype != null)
				return false;
		} else if (!skype.equals(other.skype))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", email=" + email + ", skype=" + skype + ", phone=" + phone + ", psw=" + password + ", enabled=" + enabled + "]";
	}

	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinTable(name="user_roles",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private Role role;
	
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}	
		
	
	
}