/*package web.app.sender;

import net.lamot.java.jskype.general.AbstractMessenger;
import net.lamot.java.jskype.general.MessageListenerInterface;
import net.lamot.java.jskype.windows.Messenger;

//need Skype to be started??
public class JSkypeSender extends Sender implements MessageListenerInterface {

	
	private AbstractMessenger msgr = null;

	@Override
	boolean remind(String name, String receiver, String message) {
		msgr = new Messenger();
		msgr.addListener(this);
		msgr.initialize();
		try {
			msgr.sendMessage("message");
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// create a messenger which we'll use for sending messages

	*//** Creates a new instance of JSkype *//*
	public JSkypeSender() {

	}

	public void onMessageReceived(String str) {
		System.out.println(str);
	}

}
*/