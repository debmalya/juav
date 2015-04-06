package sw.airborne.mcu_periph;

public class Sys_time {
private static final int SYS_TIME_NB_TIMER = 8;

public static int	sys_time_register_timer(float duration, sys_time_cb cb){
int start_time = sys_time.nb_tick;
for (int i = 0; i< SYS_TIME_NB_TIMER; i++) {
  if (!sys_time.timer[i].in_use) {
    sys_time.timer[i].cb         = cb;
    sys_time.timer[i].elapsed    = false;
    sys_time.timer[i].end_time   = start_time + sys_time_ticks_of_sec(duration);
    sys_time.timer[i].duration   = sys_time_ticks_of_sec(duration);
    sys_time.timer[i].in_use     = true;
    return i;
  }
}
}
public static boolean sys_time_check_and_ack_timer(int id){ if (sys_time.timer[id].elapsed) {
    sys_time.timer[id].elapsed = false;
    return true;
  }
  return false;}

private static int sys_time_ticks_of_sec(float seconds) {
	// TODO Auto-generated method stub
	return (int)(seconds * sys_time.ticks_per_sec + 0.5);
	//return 0;
 }

 public class sys_time_cb{
	int cb;
}
class sys_time{
	static int nb_sec;       ///< full seconds since startup
	static int nb_sec_rem;   ///< remainder of seconds since startup in CPU_TICKS
	static int nb_tick;      ///< SYS_TIME_TICKS since startup
   static sys_time_timer[] timer;

	  float resolution;               ///< sys_time_timer resolution in seconds
	  static int ticks_per_sec;         ///< sys_time ticks per second (SYS_TIME_FREQUENCY)
	  int resolution_cpu_ticks;  ///< sys_time_timer resolution in cpu ticks
	  int cpu_ticks_per_sec;    
	  }

public class sys_time_timer
{
	  boolean         in_use;
	   sys_time_cb     cb;
	   boolean elapsed;
	    int        end_time; ///< in SYS_TIME_TICKS
	   int        duration;
	  } ///< in SYS_TIME_TICKS


