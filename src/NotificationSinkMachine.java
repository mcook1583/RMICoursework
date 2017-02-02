import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class NotificationSinkMachine {

	public static void main(String args[]) {
		try {
			//find the registry being used by the machine the source(s) is(are) running on
			LocateRegistry.getRegistry("localhost",1099);
			// find the source objects and cast them to their interface
			NotificationSourceInterface source1 = (NotificationSourceInterface) Naming.lookup("rmi://localhost/Source1");
			System.out.println("Source1 Found");
			NotificationSourceInterface source2 = (NotificationSourceInterface) Naming.lookup("rmi://localhost/Source2");
			System.out.println("Source2 Found");
			NotificationSourceInterface source3 = (NotificationSourceInterface) Naming.lookup("rmi://localhost/Source3");
			System.out.println("Source3 Found");
			//instantiate sinks
			NotificationSink sink1 = new NotificationSink("Sink1");
			NotificationSink sink2 = new NotificationSink("Sink2");
			NotificationSink sink3 = new NotificationSink("Sink3");
			// register sinks with various sources
			source1.registerSink(sink1);
			source1.registerSink(sink2);
			source2.registerSink(sink1);
			source2.registerSink(sink2);
			source2.registerSink(sink3);
			source3.registerSink(sink2);
			source3.registerSink(sink3);
		}
		catch (Exception e) {
			System.out.println(e);
			try {
				//wait 5 seconds
				Thread.sleep(5000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("Trying again...");
			//retry connecting by recalling the main method
			main(args);
		} 
	}
}
