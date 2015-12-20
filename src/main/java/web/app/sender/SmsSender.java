package web.app.sender;

public class SmsSender extends Sender {

	@Override
	boolean remind(String name, String receiver, String message) {
		
		return false;
	}

}
