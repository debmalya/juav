package sw.airborne.arch.sim.mcu_periph;

import static sw.include.Std.*;
import static sw.airborne.mcu_periph.Sys_time.*;

public class Sys_time_arch {

	public static void sys_tick_handler(){
		//System.out.println("Debug: in sys_tick_handler()");
		time.nb_tick++;
		time.nb_sec_rem += time.resolution_cpu_ticks;
		if (time.nb_sec_rem >= time.cpu_ticks_per_sec) {
			time.nb_sec_rem -= time.cpu_ticks_per_sec;
			time.nb_sec++;
		}
		for (int i=0; i<SYS_TIME_NB_TIMER; i++) {
			if (time.timer[i].in_use &&
					time.nb_tick >= time.timer[i].end_time) {
				time.timer[i].end_time += time.timer[i].duration;
				time.timer[i].elapsed = true;
				if (time.timer[i].cb != null) {
					time.timer[i].cb.execute(i);
				}
			}
		}
	}
}
