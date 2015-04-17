package sw.airborne.mcu_periph;

import static sw.airborne.mcu_periph.Sys_time.*;

public class sys_time {

	public volatile int nb_sec; ///< full seconds since startup
	public volatile int nb_sec_rem; ///< remainder of seconds since startup in CPU_TICKS
	public volatile int nb_tick; ///< SYS_TIME_TICKS since startup
	public volatile sys_time_timer[] timer = new sys_time_timer[SYS_TIME_NB_TIMER];

	public float resolution; ///< sys_time_timer resolution in seconds
	public int ticks_per_sec; ///< sys_time ticks per second (SYS_TIME_FREQUENCY)
	public int resolution_cpu_ticks; ///< sys_time_timer resolution in cpu ticks
	public int cpu_ticks_per_sec;
	
	sys_time(){
		for(int i = 0; i<SYS_TIME_NB_TIMER; i++){
			timer[i] = new sys_time_timer();
		}
	}
	
}
