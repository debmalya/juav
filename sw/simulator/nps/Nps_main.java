package sw.simulator.nps;

import fr.dgac.ivy.IvyException;
import sw.communication.Commchannel;

public class Nps_main {
	static double sim_time;
	static double host_time_elapsed;
	Nps_autopilot_rotorcraft npsRotorcraft = new Nps_autopilot_rotorcraft();
	void nps_main_run_sim_step() 
	{
		npsRotorcraft.nps_autopilot_run_step(sim_time);
	}
	public void updateSimTime(Double val)
	{
		sim_time = val;
	}
	Nps_main()
	{
		host_time_elapsed =System.currentTimeMillis();
	}
	
	public static void main(String args[]) throws IvyException
	{
		Nps_main npsMain = new Nps_main();
		Commchannel.CommChannel();
		while (Nps_main.sim_time <= npsMain.host_time_elapsed) {
			Nps_main.host_time_elapsed =System.currentTimeMillis();
			npsMain.nps_main_run_sim_step();
		}
	}
	
		
}
