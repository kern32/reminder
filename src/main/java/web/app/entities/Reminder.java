package web.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.log4j.Logger;

//import com.skype.Call.Status;

import web.app.configuration.DeliveryType;
import web.app.configuration.StatusType;

@Entity
@Table(name="messages")
public class Reminder implements Serializable{

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Reminder.class);
	
	@Id
	@GeneratedValue
	@Column (name="id")
	private Integer id;
	
	@Column (name="userID")
	private Integer userID;
	
	@Column (name="date")
	private Timestamp date;
	
	@Column (name="delivery", columnDefinition="enum('Email','Phone','Skype','Jabber')")
	private String delivery;
	
	@Column (name="message")
	private String message;
	
	@Column (name="receiver")
	private String receiver;
	
	@Column (name="status", columnDefinition="enum('WAITING','DONE','CANCELED')")
	private String status;

	public Reminder() {
		super();
	}

	public Reminder(Integer id) {
		super();
		this.id = id;
	}

	public Reminder(Integer id, Integer userId, Timestamp date, String delivery, String message, String receiver, String status) {
		super();
		this.id = id;
		this.userID = userId;
		this.date = date;
		this.delivery = delivery;
		this.message = message;
		this.receiver = receiver;
		this.status = status;
	}

	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userID;
	}

	public void setUserId(Integer userId) {
		this.userID = userId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		Reminder other = (Reminder) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (delivery != other.delivery)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (status != other.status)
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
		return "Reminder [id=" + id + ", userId=" + userID + ", date=" + date + ", delivery=" + delivery + ", message=" + message + ", receiver=" + receiver + ", status=" + status + "]";
	}
	
	

	
}
