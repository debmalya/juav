package sw.airborne.subsystems;

import sw.airborne.math.*;

public class GpsState {
	EcefCoor_i ecef_pos;    ///< position in ECEF in cm
	LlaCoor_i lla_pos;      ///< position in LLA (lat,lon: rad*1e7; alt: mm over ellipsoid)
	UtmCoor_i utm_pos;      ///< position in UTM (north,east: cm; alt: mm over ellipsoid)
	int hmsl;                  ///< height above mean sea level in mm
	EcefCoor_i ecef_vel;    ///< speed ECEF in cm/s
	NedCoor_i ned_vel;      ///< speed NED in cm/s
	int gspeed;                ///< norm of 2d ground speed in cm/s
	int speed_3d;              ///< norm of 3d speed in cm/s
	int course;                ///< GPS course over ground in rad*1e7, [0, 2*Pi]*1e7 (CW/north)
	int pacc;                 ///< position accuracy in cm
	int sacc;                 ///< speed accuracy in cm/s
	int cacc;                 ///< course accuracy in rad*1e7
	int pdop;                 ///< position dilution of precision scaled by 100
	int num_sv;                ///< number of sat in fix
	int fix;                   ///< status of fix
	int week;                  ///< GPS week
	int tow;                  ///< GPS time of week in ms

	int nb_channels;           ///< Number of scanned satellites
	//SVinfo svinfos[] = new SVinfo[GPS_NB_CHANNELS]; ///< holds information from the Space Vehicles (Satellites)
	SVinfo svinfos[] = new SVinfo[1]; ///< holds information from the Space Vehicles (Satellites)

	int last_3dfix_ticks;     ///< cpu time ticks at last valid 3D fix
	int last_3dfix_time;      ///< cpu time in sec at last valid 3D fix
	int last_msg_ticks;       ///< cpu time ticks at last received GPS message
	int last_msg_time;        ///< cpu time in sec at last received GPS message
	int reset;                ///< hotstart, warmstart, coldstart
}
