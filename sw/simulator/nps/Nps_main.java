package sw.simulator.nps;

import fr.dgac.ivy.IvyException;
import sw.communication.Commchannel;
import static sw.airborne.mcu_periph.Sys_time.*;
import static sw.simulator.nps.Nps_autopilot_rotorcraft.*;

public class Nps_main {
	public static double sim_time;
	public static double host_time_elapsed;
	public static double host_time_factor ;
	public static double real_initial_time;
	public static double scaled_initial_time;
	public static double display_time;
	
	public static final double SIM_DT = (1./SYS_TIME_FREQUENCY);
	
	public static Nps_main npsMain;
	public static int iteration = 0;//TODO:debugging. will run for 10 iterations.;
	
	Nps_autopilot_rotorcraft npsRotorcraft = new Nps_autopilot_rotorcraft();
	void nps_main_run_sim_step() 
	{
		nps_autopilot_run_systime_step();
		npsRotorcraft.nps_autopilot_run_step(sim_time);
	}
	public void updateSimTime(Double val)
	{
		sim_time = val;
	}
	Nps_main()
	{
		host_time_elapsed =System.currentTimeMillis();
		nps_main_init();
	}
	
	public static void nps_main_init(){
		 sim_time = 0;
		 display_time = 0;
		 
		 real_initial_time = System.nanoTime();
		 scaled_initial_time = System.nanoTime();
		 
		 host_time_factor = 1. ;//HOST_TIME_FACTOR
		 Nps_autopilot_rotorcraft.nps_autopilot_init();
	}
	
	public static void main(String args[]) throws IvyException
	{
		npsMain = new Nps_main();
		Commchannel.CommChannel();
		
		for(iteration = 0; iteration<1; iteration++){
			nps_main_periodic();
		}
		//System.out.println("Debug: Ran 1 iteration");
	}
	
	public static void nps_main_periodic(){
		
		double host_time_now = System.nanoTime();
		host_time_elapsed = host_time_factor*(host_time_now -scaled_initial_time);//TODO: There is a difference in time scale
		//System.out.println("Debug: host_time_elapsed: "+host_time_elapsed);
		//System.out.println("Debug: sim_time: "+sim_time);
		int i = 0; //TODO: Debug
		while (sim_time <= host_time_elapsed) {
		//while(i<2){	
			npsMain.nps_main_run_sim_step();
			sim_time += SIM_DT;
			i++;
			//System.out.println("Degub: sim_time: "+sim_time);
		}
	}
		
}
