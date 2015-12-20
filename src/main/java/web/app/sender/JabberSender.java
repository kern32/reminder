/*package web.app.sender;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.RosterEntry;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

public class JabberSender extends Sender {
	private static final Logger LOG = Logger.getLogger(JabberSender.class);
	
	private XMPPConnection connection;
	private MsgListener messageListener = new MsgListener();

	@Override
	boolean remind(String name, String receiver, String message) {
		LOG.info("trying to send message");
		try {
			init();
			Roster roster = connection.getRoster();
			Collection<RosterEntry> entries = roster.getEntries();
			
			for (RosterEntry r : entries) {
				if (r.getUser().equalsIgnoreCase(receiver)){
					Chat chat = connection.getChatManager().createChat(receiver, messageListener);

					chat.sendMessage("Dear, " + name + "You got bellow reminder \n\n\n" + message);
					LOG.info("message sent");
					return true;
				}
			}
		} catch (XMPPException e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}

	private void init() {
		LOG.info("initialize XMPP connection");
		try {
			ConnectionConfiguration config = new ConnectionConfiguration("talk.google.com", 5222, "gmail.com");
			XMPPConnection connection = new XMPPConnection(config);
			connection.connect();
			connection.login("login", "password");
		} catch (XMPPException e) {
			LOG.info("can't get XMPP connection");
			e.printStackTrace();
		}

	}

}

class MsgListener implements MessageListener {
	private static final Logger LOG = Logger.getLogger(MessageListener.class);
	
	@Override
	public void processMessage(Chat chat, Message message) {
		LOG.info("Received message: " + message.getFrom() + "/" + message.getBody());
	}

}
*/