package web.app.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

@Entity
@Table(name="delivery")
public class Delivery implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Delivery.class);

	@Id
	@GeneratedValue
	@Column (name = "id")
	private Integer id;

	@Column (name = "messageID")
	private Integer messageID;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "phone")
	private String phone;
	
	@Column (name = "skype")
	private String skype;
	
	public Delivery(){
		LOG.info("default constr Delivery");
	}
	
	public Delivery(Integer id){
		LOG.info("default constr Delivery 1 param");
		this.id = id;
	}
	
	public Delivery(Integer id, Integer messageId, String email, String phone, String skype) {
		LOG.info("default constr Delivery 5 param");
		this.id = id;
		this.messageID = messageId;
		this.email = email;
		this.phone = phone;
		this.skype = skype;
	}

	public Integer getId() {
		LOG.info("calling getter Delivery id");
		return id;
	}
	public void setId(Integer id) {
		LOG.info("calling setter Delivery id");
		this.id = id;
	}
	public Integer getMessageId() {
		LOG.info("calling getter Delivery message_id");
		return messageID;
	}
	public void setMessageId(Integer messageId) {
		LOG.info("calling setter Delivery message_id");
		this.messageID = messageId;
	}
	public String getEmail() {
		LOG.info("calling getter Delivery email");
		return email;
	}
	public void setEmail(String email) {
		LOG.info("calling setter Delivery email");
		this.email = email;
	}
	public String getPhone() {
		LOG.info("calling getter Delivery phone");
		return phone;
	}
	public void setPhone(String phone) {
		LOG.info("calling setter Delivery phone");
		this.phone = phone;
	}
	public String getSkype() {
		LOG.info("calling getter Delivery skype");
		return skype;
	}
	public void setSkype(String skype) {
		LOG.info("calling setter Delivery skype");
		this.skype = skype;
	}
	@Override
	public int hashCode() {
		LOG.info("calling Delivery hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((messageID == null) ? 0 : messageID.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((skype == null) ? 0 : skype.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		LOG.info("calling Delivery equals");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Delivery other = (Delivery) obj;
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
		if (messageID == null) {
			if (other.messageID != null)
				return false;
		} else if (!messageID.equals(other.messageID))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
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
		return "Delivery [id=" + id + ", messageId=" + messageID + ", email=" + email + ", phone=" + phone + ", skype=" + skype + "]";
	}
	
	
}
