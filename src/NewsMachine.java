import java.rmi.*;
import java.rmi.registry.LocateRegistry;


public class NewsMachine {
	public static void main(String args[]) {
		try {
			//create registry for news channels on this machine
			LocateRegistry.createRegistry(1099);
			//instantiate news channels as sources and bind to their names in the registry
			NotificationSource bbcnews = new NotificationSource("BBC News");
			Naming.rebind("rmi://localhost/bbcnews", bbcnews);
			NotificationSource skynews = new NotificationSource("Sky News");
			Naming.rebind("rmi://localhost/skynews", skynews);
			NotificationSource telegraph = new NotificationSource("The Telegraph");
			Naming.rebind("rmi://localhost/telegraph", telegraph);
			//send various news to all sinks of a channel over time
			//wait 20 seconds
			Thread.sleep(20000);
			bbcnews.notifySinks(new Notification(bbcnews.getName(), "Man appears in court after Tube attack"));	
			skynews.notifySinks(new Notification(skynews.getName(), "Storm Desmond Floods Hit Road And Rail Travel"));
			//wait 10 seconds
			Thread.sleep(10000);	
			telegraph.notifySinks(new Notification(telegraph.getName(), "Obama Warns Of 'New Phase' Of Terror Threat"));	
			//wait 10 seconds
			Thread.sleep(10000);
			skynews.notifySinks(new Notification(skynews.getName(), "Storm brings 'highest' waterfall to life"));
			//wait 5 seconds
			Thread.sleep(5000);	
			bbcnews.notifySinks(new Notification(bbcnews.getName(), "Storm Desmond flooding chaos continues"));
		}
		catch (Exception e) {
			//print relevant error
			System.out.println(e);			
		} 
	} 

} 
