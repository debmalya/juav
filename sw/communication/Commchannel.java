package sw.communication;
import fr.dgac.ivy.* ;
import devices.GpsState;

public class Commchannel implements IvyMessageListener {
	private Ivy bus;
	//Add buffer for GPS
	//Add buffer for AHRS

	public void CommChannel() throws IvyException {
		 bus = new Ivy("JUAV","JUAV Ready",null);
		 bus.bindMsg("NPS_SEN_NICE_GPS(.*)",new IvyMessageListener() {
		      public void receive(IvyClient client, String[] args) {
			// Process GPS
		        
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

