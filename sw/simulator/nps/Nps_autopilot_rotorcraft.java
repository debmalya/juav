package sw.simulator.nps;
import static sw.airborne.firmwares.rotorcraft.Main.*;
import devices.Gps;
import sw.airborne.subsystems.Imu;
import sw.airborne.subsystems.actuators.motor_mixing.Motor_mixing;
public class Nps_autopilot_rotorcraft 
{
	void nps_autopilot_run_step(double time) 
	{
		if (Imu.gyro_available) {
			//we were using main_event() which in turn called gyro and accel event removed the extra step and called gyro and accel event directly
			on_gyro_event();
			on_accel_event();
		  }
		  if (Gps.gps_available) {
			  //we were using main_event() which in turn called gps event removed the extra step and called gps event directly
			  on_gps_event();
		  }

		 handle_periodic_tasks();

		  //We'll be using this compare the output between c and java autopilot
		 int MAX_PPRZ = 9600;
		  for (int  i=0; i < nps_autopilot.NPS_COMMANDS_NB; i++)
			  nps_autopilot.commands[i] = (double)Motor_mixing.commands[i]/MAX_PPRZ;

		}

}
