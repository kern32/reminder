package web.app.sender;

import web.app.entities.Reminder;

public abstract class Sender {
	
	abstract boolean send(Reminder entity);
	
}