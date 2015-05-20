package sw.airborne.mcu_periph;

public class sys_time_timer {
	public boolean in_use;
	public sys_time_cb cb;
	public volatile boolean elapsed;
	public long end_time; // /< in SYS_TIME_TICKS
	public long duration;
}
