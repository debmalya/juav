package sw.airborne.mcu_periph;

import static sw.airborne.Paparazzi.*;

public class Sys_time {
	public static final int SYS_TIME_NB_TIMER = 8;
	public static final int SYS_TIME_FREQUENCY =  2 * PERIODIC_FREQUENCY;
	
	public static sys_time time = new sys_time();
	public static int sys_time_register_timer(float duration, sys_time_cb cb) {
		int start_time = time.nb_tick;
		// sys_time systime=new sys_time();
		for (int i = 0; i < SYS_TIME_NB_TIMER; i++) {
			if (!time.timer[i].in_use) {
				time.timer[i].cb = cb;
				time.timer[i].elapsed = false;
				time.timer[i].end_time = start_time
						+ sys_time_ticks_of_sec(duration);
				time.timer[i].duration = sys_time_ticks_of_sec(duration);
				time.timer[i].in_use = true;
				return i;
			}
		}
		return SYS_TIME_NB_TIMER;
	}

	public static boolean sys_time_check_and_ack_timer(int id) {
		if (time.timer[id].elapsed) {
			time.timer[id].elapsed = false;
			return true;
		}
		return false;
	}

	public static long sys_time_ticks_of_sec(float seconds) {
		// TODO Auto-generated method stub
		return (long) (seconds * time.ticks_per_sec + 0.5);
		// return 0;
	}
}



