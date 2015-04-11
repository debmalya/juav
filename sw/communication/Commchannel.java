package sw.communication;
import java.util.StringTokenizer;

import fr.dgac.ivy.* ;
import devices.GpsState;

public class Commchannel implements IvyMessageListener {
	private Ivy bus;
	//Add buffer for GPS
	//Add buffer for AHRS

	public void CommChannel() throws IvyException {
		 bus = new Ivy("JUAV","JUAV Ready",null);
		 bus.bindMsg("GPS_CALCULAED_DEVICE(.*)",new IvyMessageListener() {
		      public void receive(IvyClient client, String[] args) {
			// Process GPS
		    	Long[] gpsState = new Long[17];
		    	int i=0;
		    	 StringTokenizer tokenizedString = new StringTokenizer(args[0], " ");
		    	 while (tokenizedString.hasMoreElements()) {
					String currentString = tokenizedString.nextToken();
					gpsState[i] = Long.parseLong(currentString);
					i++;
				}
		    	 
		      }
		    });
		 bus.bindMsg("^AHRS(.*)",new IvyMessageListener() {
		      public void receive(IvyClient client, String[] args) {
			// Process AHRS
		        
		      }
		    });
		 bus.start(null); // starts the bus on the default domain
	}

	private void close(){
		bus.stop();
	}

	protected void finalize() throws Throwable {
	     try {
	         close();        // close open files
	     } finally {
	         super.finalize();
	     }
	 }
	
	@Override
	public void receive(IvyClient arg0, String[] arg1) {
		// TODO Auto-generated method stub
		
	}
}

