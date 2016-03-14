package web.app.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;

import web.app.configuration.DeliveryType;
import web.app.configuration.StatusType;

@Entity
@Table(name="reminders")
public class Reminder implements Serializable{
	private static final long serialVersionUID = 1L;
	private static Logger log = Logger.getLogger("file");
	
	@Id
	@GeneratedValue
	@Column (name="id", length = 50)
	private Integer id;
	
	@Column (name="userId", length = 50, nullable = false)
	private Integer userId;
	
	@Column (name="date", nullable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
	private Timestamp date;
	
	@Enumerated(EnumType.STRING)
	@Column (name="delivery", columnDefinition="enum('Email','Phone','Skype')", nullable = false)
	private DeliveryType delivery;
	
	@Column (name="subject", length = 50, nullable = false)
	private String subject;
	
	@Column (name="message", length = 150, nullable = false)
	private String message;
	
	@Column (name="receiver", length = 50, nullable = false)
	private String receiver;
	
	@Enumerated(EnumType.STRING)
	@Column (name="status", columnDefinition="enum('WAITING','DONE','CANCELED')", nullable = false)
	private StatusType status;

	public Reminder() {
		super();
		log.info("Reminder entity: default constr");
	}

	public Reminder(Integer userId, Timestamp date, DeliveryType delivery, String subject, String message,
			String receiver, StatusType status) {
		super();
		this.userId = userId;
		this.date = date;
		this.delivery = delivery;
		this.subject = subject;
		this.message = message;
		this.receiver = receiver;
		this.status = status;
		log.info("Reminder entity: constr with params");
	}
	
	public static class Builder {
	    private int userId;
	    private Timestamp date;
	    private DeliveryType delivery;
	    private String subject;
	    private String message;
	    private String receiver;
	    private StatusType status;
	    
	    public Builder userId(int val) {
	    	log.info("Reminder.Builder: set user id");
	    	userId= val;
	    	return this;
	    }

	    public Builder date (Timestamp val) {
	    	log.info("Reminder.Builder: set date");
	    	date = val;
	    	return this;
	    }

	    public Builder delivery (DeliveryType val) {
	    	log.info("Reminder.Builder: set delivery");
	    	delivery = val;
	    	return this;
	    }

	    public Builder subject (String val) {
	    	log.info("Reminder.Builder: set subject");
	    	subject = val;
	    	return this;
	    }
	    
	    public Builder message (String val) {
	    	log.info("Reminder.Builder: set message");
	    	message = val;
	    	return this;
	    }
	    
	    public Builder receiver (String val) {
	    	log.info("Reminder.Builder: set receiver");
	    	receiver = val;
	    	return this;
	    }
	    
	    public Builder status (StatusType val) {
	    	log.info("Reminder.Builder: set status");
	    	status = val;
	    	return this;
	    }

	    public Reminder build() {
	      log.info("Reminder.Builder: build Reminder entity");
	      return new Reminder(this);
	    }
	  }

	private Reminder(Builder builder) {
		log.info("Reminder entity: constr using Builder");
		userId = builder.userId;
		date = builder.date;
		delivery = builder.delivery;
		subject = builder.subject;
		message = builder.message;
		receiver = builder.receiver;
		status = builder.status;
	}

	public Integer getId() {
		log.info("Reminder entity: get id");
		return id;
	}

	public void setId(Integer id) {
		log.info("Reminder entity: set id");
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public Integer getUserId() {
		log.info("Reminder entity: get user id");
		return userId;
	}

	public void setUserId(Integer userId) {
		log.info("Reminder entity: set user id");
		this.userId = userId;
	}

	public Timestamp getDate() {
		log.info("Reminder entity: get date");
		return date;
	}

	public void setDate(Timestamp date) {
		log.info("Reminder entity: set date");
		this.date = date;
	}

	public DeliveryType getDelivery() {
		log.info("Reminder entity: get delivery type");
		return delivery;
	}

	public void setDelivery(DeliveryType delivery) {
		log.info("Reminder entity: set delivery type");
		this.delivery = delivery;
	}

	public String getSubject() {
		log.info("Reminder entity: get subject");
		return subject;
	}

	public void setSubject(String subject) {
		log.info("Reminder entity: set subject");
		this.subject = subject;
	}

	public String getMessage() {
		log.info("Reminder entity: get message");
		return message;
	}

	public void setMessage(String message) {
		log.info("Reminder entity: set message");
		this.message = message;
	}

	public String getReceiver() {
		log.info("Reminder entity: get receiver");
		return receiver;
	}

	public void setReceiver(String receiver) {
		log.info("Reminder entity: set receiver");
		this.receiver = receiver;
	}

	public StatusType getStatus() {
		log.info("Reminder entity: get status");
		return status;
	}

	public void setStatus(StatusType status) {
		log.info("Reminder entity: set status");
		this.status = status;
	}

	@Override
	public int hashCode() {
		log.info("Reminder entity: invoke hashcode");
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((delivery == null) ? 0 : delivery.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		log.info("Reminder entity: invoke equals");
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
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
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
		log.info("Reminder entity: invoke toString");
		return "Reminder [id=" + id + ", userId=" + userId + ", date=" + date + ", delivery=" + delivery + ", subject="
				+ subject + ", message=" + message + ", receiver=" + receiver + ", status=" + status + "]";
	}
}

	