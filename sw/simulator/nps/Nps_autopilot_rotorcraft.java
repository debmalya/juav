package sw.simulator.nps;

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
		    autopilot.commands[i] = (double)motor_mixing.commands[i]/MAX_PPRZ;

		}

}
