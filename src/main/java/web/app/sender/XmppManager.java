/*package web.app.sender;

import org.apache.log4j.Logger;
import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.Roster;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.ConnectionConfiguration.SecurityMode;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.packet.Presence.Type;

public class XmppManager extends Sender {
	private static final Logger LOG = Logger.getLogger(XmppManager.class);

	private static final int packetReplyTimeout = 500; // millis
	private String server = "jabber.kiev.ua";
	private int port = 5222;
	private ConnectionConfiguration config;
	private XMPPConnection connection;
	private ChatManager chatManager;
	private MessageListener messageListener;

	public XmppManager(String server, int port) {
		LOG.info("XmppManager constr");
		this.server = server;
		this.port = port;
	}

	public void init() throws XMPPException {

		LOG.info("initialization: connect to server");

		SmackConfiguration.setPacketReplyTimeout(packetReplyTimeout);

		config = new ConnectionConfiguration(server, port);
		config.setSASLAuthenticationEnabled(false);
		config.setSecurityMode(SecurityMode.disabled);
		connection = new XMPPConnection(config);
		connection.connect();
		LOG.info("connected: " + connection.isConnected());

		chatManager = connection.getChatManager();
		messageListener = new MyMessageListener();
	}

	public void performLogin(String username, String password) throws XMPPException {
		LOG.info("authorization using: " + username + " and " + password);
		if (connection != null && connection.isConnected()) {
			connection.login(username, password);
		}
	}

	public void setStatus(boolean available, String status) {
		LOG.info("setting status");
		Presence.Type type = available ? Type.available : Type.unavailable;
		Presence presence = new Presence(type);
		presence.setStatus(status);
		connection.sendPacket(presence);
	}

	public void destroy() {
		LOG.info("destroying connection");
		if (connection != null && connection.isConnected()) {
			connection.disconnect();
		}
	}

	public void sendMessage(String message, String buddyJID) throws XMPPException {
		LOG.info("sending message: " + message);
		Chat chat = chatManager.createChat(buddyJID, messageListener);
		chat.sendMessage(message);
	}

	public void createEntry(String user, String name) throws Exception {
		LOG.info("Creating entry for buddy: " + user + "/" + name);
		Roster roster = connection.getRoster();
		roster.createEntry(user, name, null);
	}

	@Override
	public boolean remind(String name, String receiver, String message) {
		LOG.info("sending message..");
		String buddyName = name;
		String buddyJID = receiver;
		String login = "login";
		String pass = "password";

		try {
			XmppManager xmppManager = new XmppManager(server, port);
			xmppManager.init();
			xmppManager.performLogin(login, pass);
			xmppManager.setStatus(true, "Reminder online");
			xmppManager.createEntry(buddyJID, buddyName);
			xmppManager.sendMessage("Dear, " + name + "You got bellow reminder \n\n\n" + message, buddyJID + "@" + server);
			xmppManager.destroy();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	class MyMessageListener implements MessageListener {

		@Override
		public void processMessage(Chat chat, Message message) {
			String from = message.getFrom();
			String body = message.getBody();
			LOG.info("Received message from: " + from + "/" + body);
		}

	}

}*/