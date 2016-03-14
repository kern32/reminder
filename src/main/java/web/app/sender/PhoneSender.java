package web.app.sender;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import web.app.entities.Reminder;

public class PhoneSender  extends Sender{
	private static Logger log = Logger.getLogger("file");
	
	@Autowired
    private Environment env;

	String login    = env.getProperty("smsc.login");
	String password = env.getProperty("smsc.password");
	String charset  = env.getProperty("smsc.charset");
	boolean https   = env.getProperty("smsc.https", Boolean.class);
	boolean debug   = env.getProperty("smsc.debug", Boolean.class);
	boolean post    = env.getProperty("smsc.post", Boolean.class);
	
	public PhoneSender() {
		log.info("PhoneSender: default constr");
	}

	public PhoneSender(String login, String password) {
		log.info("PhoneSender: constr with 2 params");
		this.login    = login;
		this.password = password;
	}

	public PhoneSender(String login, String password, String charset) {
		log.info("PhoneSender: constr with 3 params");
		this.login    = login;
		this.password = password;
		this.charset  = charset;
	}

	public PhoneSender(String login, String password, String charset, boolean debug) {
		log.info("PhoneSender: constr with 4 params");
		this.login    = login;
		this.password = password;
		this.charset  = charset;
		this.debug    = debug;
	}

	/**
	 * Send SMS
	 * 
	 * @param phones - phone list
	 * @param message - text message
	 * @param translit - translate or not in translit (1,2 or 0)
	 * @param time - required delivery time (DDMMYYhhmm, h1-h2, 0ts, +m)
	 * @param id - message id (32-bit)
	 * @param format - message format (0 - text sms, 1 - flash-sms, 2 - wap-push, 3 - hlr, 4 - bin, 5 - bin-hex, 6 - ping-sms)
	 * @param sender - sender name
	 * @param query - additional parameters added to the URL-request ("valid=01:00&maxsms=3&tz=2")
	 * @return array (<id>, <number of sms>, <cost>, <balance>) if successfully sent
	 * 				 (<id>, -<error code>) in case of error
	 */
	
	public String[] sendSms(String phones, String message, int translit, String time, String id, int format, String sender, String query) {
		log.info("PhoneSender: send sms message");
		String[] formats = {"", "flash=1", "push=1", "hlr=1", "bin=1", "bin=2", "ping=1"};
		String[] m = {};
		try {
			m = sendSmsRequest("send", "cost=3&phones=" + URLEncoder.encode(phones, charset) 
					+ "&mes=" + URLEncoder.encode(message, charset)  
					+ "&translit=" + translit + "&id=" + id + (format > 0 ? "&" + formats[format] : "") 
					+ (sender == "" ? "" : "&sender=" + URLEncoder.encode(sender, charset))  
					+ (time == "" ? "" : "&time=" + URLEncoder.encode(time, charset) )
					+ (query == "" ? "" : "&" + query));
		}
		catch (UnsupportedEncodingException e) {
			log.error("PhoneSender: catch UnsupportedEncodingException while sending sms message, full stack trace follows:", e);
			e.printStackTrace();
		}
		if (debug) {
			if (Integer.parseInt(m[1]) > 0) {
				log.info("PhoneSender: message successfully sent. ID: " + m[0] + ", sent SMS: " + m[1] + ", cost: " + m[2] + ", balance: " + m[3]);
			}
			else {
				log.error("PhoneSender: error № " + Math.abs(Integer.parseInt(m[1])) + "\n" + (Integer.parseInt(m[0])>0 ? ("ID: " + m[0]) : ""));
			}
		}
		return m;
	};

	/**
	 * Cost of SMS
	 *
	 * @param phones - phone list
	 * @param message - text message
	 * @param translit - translate or not in translit (1,2 or 0)
	 * @param format - message format (0 - text sms, 1 - flash-sms, 2 - wap-push, 3 - hlr, 4 - bin, 5 - bin-hex, 6 - ping-sms)
	 * @param sender - sender name
	 * @param query - additional parameters added to the URL-request ("valid=01:00&maxsms=3&tz=2")
	 * @return array(<cost>, <sms count>) or (0, -<error code>) in case of error
	 */

	public String[] getSmsCost(String phones, String message, int translit, int format, String sender, String query) {
		log.info("PhoneSender: get sms cost");
		String[] formats = {"", "flash=1", "push=1", "hlr=1", "bin=1", "bin=2", "ping=1"};
		String[] m = {};
		try { 
				m = sendSmsRequest("send", "cost=1&phones=" + URLEncoder.encode(phones, charset) 
						+ "&mes=" + URLEncoder.encode(message, charset)  
						+ "&translit=" + translit + (format > 0 ? "&" + formats[format] : "") 
						+ (sender == "" ? "" : "&sender=" + URLEncoder.encode(sender, charset)) 
						+ (query == "" ? "" : "&" + query));
		}
		catch (UnsupportedEncodingException e) {
			log.error("PhoneSender: catch UnsupportedEncodingException while get sms cost, full stack trace follows:", e);
			e.printStackTrace();
		} 
		if (debug) {
			if (Integer.parseInt(m[1]) > 0) {
				log.info("PhoneSender: cost of sending: " + m[0] + ", sent SMS: " + m[1]);
			}
			else {
				log.error("PhoneSender: error № " + Math.abs(Integer.parseInt(m[1])));
			}
		}
		return m;
	}

	/**
	 * status of SMS or HLR-request
	 *
	 * @param id - ID message
	 * @param phone - phone number
	 * @param all - (<receiving time>, <phone number>, <cost>, <sender id>, <status>, <message text>)
	 * @return array
	 * for SMS (<статус>, <time changing>, <error code>)
	 * for HLR-request(<status>, <time changing>, <error code>, <country code>, <phone operator code>,
	 * <country>, <operator>, <roaming country>, <roaming opeartor>,
	 * <code IMSI of SIM-card>, <phone number of service center>)
	 *  in case of error (0, -<error code>)
	 */

	public String[] getSmsStatus(int id, String phone, int all) {
		log.info("PhoneSender: get sms status");
		String[] m = {};
		try {
			m = sendSmsRequest("status", "phone=" + URLEncoder.encode(phone, charset) + "&id=" + id + "&all=" + all);
			if (debug) {
				if (m[1] != "" && Integer.parseInt(m[1]) >= 0) {
					log.info("PhoneSender: SMS status= " + m[0]);
				}
				else
					log.error("PhoneSender: error № " + Math.abs(Integer.parseInt(m[1])));
			}
			if (all == 1 && m.length > 9 && (m.length < 14 || m[14] != "HLR")) {
				m = implode(m, ",").split(",", 9);
			}
		}
		catch (UnsupportedEncodingException e) {
			log.error("PhoneSender: catch UnsupportedEncodingException while get sms status, full stack trace follows:", e);
			e.printStackTrace();
		}
		return m;
	}

	/**
	 * Balance
	 *
	 * @return Balance or empty String in case of error
	 */

	public String getBalance() {
		log.info("PhoneSender: get balance");
		String[] m = sendSmsRequest("balance", ""); // (balance) or (0, -error)
		if (debug) {
			if (m.length == 1)
				log.info("PhoneSender: balance " + m[0]);
			else
				log.error("PhoneSender: error № " + Math.abs(Integer.parseInt(m[1])));
		}
		return m.length == 2 ?	"" : m[0];
	}

	/**
	 * Sending request
	 * @param cmd - command
	 * @param arg - additional parameters
	 */

	private String[] sendSmsRequest(String cmd, String arg){
		log.info("PhoneSender: send sms request");
		String ret = ",";
		try {
			String url = (https ? "https" : "http") + "://smsc.ru/sys/" + cmd +".php?login=" + URLEncoder.encode(login, charset) 
				+ "&psw=" + URLEncoder.encode(password, charset) 
				+ "&fmt=1&charset=" + charset + "&" + arg;
			
			int i = 0;
			do {
				if (i > 0)
					Thread.sleep(2000 + 1000 * i);
				if (i == 2)
					url = url.replace("://smsc.ru/", "://www2.smsc.ru/");

				ret = readUrl(url);
			}
			while (ret == "" && ++i < 4);
		}
		catch (UnsupportedEncodingException e) {
			log.error("PhoneSender: catch UnsupportedEncodingException while sending sms request, full stack trace follows:", e);
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			log.error("PhoneSender: catch InterruptedException while sending sms request, full stack trace follows:", e);
			e.printStackTrace();
		}
		return ret.split(",");
	}

	/**
	 * URL
	 * @param url - ID message
	 * @return line - server response
	 */
	private String readUrl(String url) {
		log.info("PhoneSender: read sms response");
		String line = "", realURL = url;
		String[] param = {};
		boolean isPost = (post || url.length() > 2000);
		if (isPost) {
			param = url.split("\\?",2);
			realURL = param[0];
		}
		try {
			URL u = new URL(realURL);
			InputStream is;
			if (isPost){
				URLConnection conn = u.openConnection();
				conn.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(conn.getOutputStream(), charset);
				os.write(param[1]);
				os.flush();
				os.close();
				log.info("PhoneSender: post request");
				is = conn.getInputStream();
			} else {
				is = u.openStream();
			}
			InputStreamReader reader = new InputStreamReader(is, charset);

			int ch;
			while ((ch = reader.read()) != -1) {
				line += (char)ch;
			}
			reader.close();
		}
		catch (MalformedURLException e) { 
			log.error("PhoneSender: catch MalformedURLException: wrong url/request, full stack trace follows:", e);
			e.printStackTrace();
		}
		catch (IOException e) {
			log.error("PhoneSender: catch IOException while reading sms response, full stack trace follows:", e);
			e.printStackTrace();
		}
		return line;
	}

	private static String implode(String[] ary, String delim) {
		log.info("PhoneSender: invoke implode method");
		String out = "";
		for (int i = 0; i < ary.length; i++) {
			if (i != 0) 
				out += delim;
			out += ary[i];
		}
		return out;
	}

	@Override
	public boolean send(Reminder entity) {
		log.info("PhoneSender: send remind message");
		PhoneSender sd= new PhoneSender(login, password, charset, true);
        String[] sms = sd.sendSms(entity.getReceiver(), entity.getMessage(), 1, "", "", 0, "", "");
        log.info(sms);
        String[] smsCost = sd.getSmsCost(entity.getReceiver(), entity.getMessage(), 0, 0, "", "");
        log.info(smsCost);
        log.info(sd.getBalance());
        boolean isSent = sms.length > 1 ? true: false;
        log.info(isSent ? "PhoneSender: reminder was sent via sms successfully":
        				"PhoneSender: reminder was not sent via sms");
        return isSent;
	}
}
