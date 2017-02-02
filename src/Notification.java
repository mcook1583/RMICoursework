import java.io.Serializable;
import java.util.Date;

public class Notification implements Serializable{

	String sender;
	String content;
	Date date;
	
	public Notification(String sender, String content){
		this.sender = sender;
		this.content = content;
		this.date = new Date();
	}
	
	public String display(){
		//display the notification, this can be changed dependiong on application
		return sender + " at " + date +": " + content;
	}
	
}
