package sw.airborne.subsystems;

import sw.airborne.math.*;

public class GpsState {
	public EcefCoor_i ecef_pos = new EcefCoor_i();    ///< position in ECEF in cm
	public LlaCoor_i lla_pos = new LlaCoor_i();      ///< position in LLA (lat,lon: rad*1e7; alt: mm over ellipsoid)
	public UtmCoor_i utm_pos = new UtmCoor_i();      ///< position in UTM (north,east: cm; alt: mm over ellipsoid)
	public long hmsl;                  ///< height above mean sea level in mm
	public EcefCoor_i ecef_vel = new EcefCoor_i();    ///< speed ECEF in cm/s
	public NedCoor_i ned_vel = new NedCoor_i();      ///< speed NED in cm/s
	public long gspeed;                ///< norm of 2d ground speed in cm/s
	public long speed_3d;              ///< norm of 3d speed in cm/s
	public long course;                ///< GPS course over ground in rad*1e7, [0, 2*Pi]*1e7 (CW/north)
	public long pacc;                 ///< position accuracy in cm
	public long sacc;                 ///< speed accuracy in cm/s
	public long cacc;                 ///< course accuracy in rad*1e7
	public long pdop;                 ///< position dilution of precision scaled by 100
	public long num_sv;                ///< number of sat in fix
	public long fix;                   ///< status of fix
	public long week;                  ///< GPS week
	public long tow;                  ///< GPS time of week in ms

	public long nb_channels;           ///< Number of scanned satellites
	//SVinfo svinfos[] = new SVinfo[GPS_NB_CHANNELS]; ///< holds information from the Space Vehicles (Satellites)
	public SVinfo svinfos[] = new SVinfo[1]; ///< holds information from the Space Vehicles (Satellites)

	public long last_3dfix_ticks;     ///< cpu time ticks at last valid 3D fix
	public long last_3dfix_time;      ///< cpu time in sec at last valid 3D fix
	public long last_msg_ticks;       ///< cpu time ticks at last received GPS message
	public long last_msg_time;        ///< cpu time in sec at last received GPS message
	public long reset;                ///< hotstart, warmstart, coldstart
}
