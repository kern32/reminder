/*package web.app.sender;

import org.apache.log4j.Logger;
import org.quartz.Scheduler;

import com.skype.ContactList;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;

public class Skype4JavaSender extends Sender {
	
	private static final Logger LOG = Logger.getLogger(Scheduler.class);
	private static ContactList list = null;
	private static Friend[] friends = null;
	
	
	public static ContactList getContactList() throws SkypeException{
		if (list == null){
			list = Skype.getContactList();
		}
		return list;
	}
	
	public static Friend[] getFriends() throws SkypeException{
		if (friends == null){
			friends = list.getAllFriends();
		}
		return friends;
	}
	
	public void initialize(){
		LOG.info("trying to initialize contact and friend list");
		try {
			getContactList();
			getFriends();
		} catch (SkypeException e) {
			LOG.info("can't initialize contact and friend list");
			e.printStackTrace();
		}
		LOG.info("contact and friend list initialized");
		
	}

	public boolean addContact(String name) {
		LOG.info("trying to add contact");
		try {
			Friend friend = getFriend(name);
			if (friend != null) {
				LOG.info("contact is already exists");
				return true;
			}
			
			Friend[] userWaitingAuth = list.getAllUserWaitingForAuthorization();
			for (Friend f : userWaitingAuth) {
				if (f.getFullName().equalsIgnoreCase(name)) {
					list.addGroup(f.getFullName());
					LOG.info("contact is added");
					f.send("User " + f.getFullName() + " is successfully added to contact list");
				}
			}
		} catch (SkypeException e) {
			LOG.info("can't add user to contact list");
			e.printStackTrace();
		}
		LOG.info("user added to contact list");
		return true;
	}
	
	@Override
	public boolean remind(String name, String receiver, String message) {
		initialize();
		addContact(name);
		LOG.info("trying to send reminder");
		try {
			Friend friend = getFriend(receiver);
			if (friend != null) {
				friend.send("Dear, " + name + "You got bellow reminder \n\n\n" + message);
			}
		} catch (SkypeException e) {
			LOG.info("trying to authorize user");
			e.printStackTrace();
			return false;
		}
		LOG.info("reminder is sent");
		return true;
	}

	private static Friend getFriend(String name) throws SkypeException {
		Friend friend = null;
		for (Friend f : friends) {
			if (f.getFullName().equalsIgnoreCase(name)) {
				friend = f;
			}
		}
		return friend;
	}
}
*/