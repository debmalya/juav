package sw.simulator.nps;
import static sw.airborne.firmwares.rotorcraft.Main.*;
import sw.airborne.subsystems.actuators.motor_mixing.Motor_mixing;
public class Nps_autopilot_rotorcraft 
{
	void nps_autopilot_run_step(double time) 
	{
		  if (nps_sensors_gps_available()) {
		    gps_feed_value();
		    main_event();
		  }

		  if (nps_bypass_ahrs) {
		    sim_overwrite_ahrs();
		  }

		  if (nps_bypass_ins) {
		    sim_overwrite_ins();
		  }

		 handle_periodic_tasks();

		  /* scale final motor commands to 0-1 for feeding the fdm */
		  for (uint8_t i=0; i < NPS_COMMANDS_NB; i++)
		    autopilot.commands[i] = (double)Motor_mixing.commands[i]/MAX_PPRZ;

		}
	
	/*void main_period(){
		this.autopilot_periodic();
		if(){
			static uint16_t prescaler = 0;			
		    prescaler++;					
		    if (prescaler >= 512) {			
		      prescaler = 0;					
		      { autopilot_flight_time++; datalink_time++; }						\
		    }							
		  
		}
		  static uint16_t prescaler = 0;			
		    prescaler++;					
		    if (prescaler >= 10) {			
		      prescaler = 0;					
		      {};						
		    }							
		  
	}
	void autopilot_periodic(){
			
		    static uint16_t prescaler = 0;			
		    prescaler++;					
		    if (prescaler >= (512 / 16)) {			
		      prescaler = 0;					
		     Navigation.compute_dist2_to_home();						
		    }							
		  }
	 if (autopilot_in_flight) {
		    if (too_far_from_home) {
		      if (dist2_to_home > failsafe_mode_dist2)
		       Autopilot.autopilot_set_mode(FAILSAFE_MODE_TOO_FAR_FROM_HOME);
		      else
		       Autopilot.autopilot_set_mode(AP_MODE_HOME);
		    }
	 	}
	 if (autopilot_mode == AP_MODE_HOME) {
		    RunOnceEvery(NAV_PRESCALER, nav_home());
		  }
		  else {
		    // otherwise always call nav_periodic_task so that carrot is always updated in GCS for other modes
		    RunOnceEvery(NAV_PRESCALER, nav_periodic_task());
		  }
	 Guidance_v.guidance_v_run( autopilot_in_flight );
	  Guidance_h.guidance_h_run( autopilot_in_flight );
	    }
	//void compute_dist2_to_home(){
		
	//}
void handle_periodic_tasks(){
	if (sys_time_check_and_ack_timer(main_periodic_tid))
	    this.main_periodic();
}*/

}
