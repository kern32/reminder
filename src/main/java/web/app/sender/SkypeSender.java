package web.app.sender;

import org.apache.log4j.Logger;

import web.app.entities.Reminder;

import com.skype.ContactList;
import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;
import com.skype.User.BuddyStatus;

public class SkypeSender extends Sender {

	private static Logger log = Logger.getLogger("file");

	private ContactList list = null;
	private Friend[] friends = null;

	public SkypeSender() {
		log.info("SkypeSender: constr with 2 params");
		this.list = getContactList();
		this.friends = getFriends();
	}

	public ContactList getContactList() {
		log.info("SkypeSender: get contact list");
		try {
			if (list == null)
				list = Skype.getContactList();
		} catch (SkypeException e) {
			log.error("SkypeSender: catch SkypeException while getting contact list, full stack trace follows:", e);
			e.printStackTrace();
		}
		return list;
	}

	public Friend[] getFriends() {
		log.info("SkypeSender: get friend list");
		try {
			if (friends == null)
				friends = list.getAllFriends();
		} catch (SkypeException e) {
			log.error("SkypeSender: catch SkypeException while getting frind list, full stack trace follows:", e);
			e.printStackTrace();
		}
		return friends;
	}

	public static boolean addContact(String receiver) {
		log.info("SkypeSender: adding contact " + receiver);
		try {
			Skype.getContactList().addFriend(receiver,
					"Please, add reminder agent to your contact list");
		} catch (SkypeException e) {
			log.error("SkypeSender: catch SkypeException while adding " + receiver + " to contact list, full stack trace follows:", e);
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean send(Reminder entity) {
		log.info("SkypeSender: send remind message");
		boolean isSent = false;
		if (isContactAdded(entity.getReceiver())) {
			try {
				Friend friend = getFriend(entity.getReceiver());
				if (friend != null) {
					friend.send(entity.getMessage());
					isSent = true;
				}
			} catch (SkypeException e) {
				log.error("SkypeSender: catch SkypeException while sending reminder, full stack trace follows:", e);
				e.printStackTrace();
				isSent = false;
			}
			log.info("SkypeSender: reminder " + (isSent ? "was sent on skype successfully":
				"was not sent via skype"));
		}
		return isSent;
	}

	public Friend getFriend(String receiver) throws SkypeException {
		log.info("SkypeSender: get friend entity");
		Friend friend = null;
		for (Friend f : friends) {
			if (f.getId().equalsIgnoreCase(receiver)) {
				friend = f;
			}
		}
		return friend;
	}

	public static boolean isContactAdded(String receiver) {
		log.info("SkypeSender: check if " + receiver + " is added to contact list");
		BuddyStatus buddyStatus = null;
		try {
			Friend friend = new SkypeSender().getFriend(receiver);
			if (friend != null) {
				buddyStatus = friend.getBuddyStatus();
			}
		} catch (SkypeException e) {
			log.error("SkypeSender: catch SkypeException while checking if " + receiver + " is added to contact list, full stack trace follows:", e);
			e.printStackTrace();
		}
		return buddyStatus == BuddyStatus.ADDED;
	}
}
