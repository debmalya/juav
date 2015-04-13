package sw.simulator.nps;

import sw.airborne.math.*;

public class NpsFdm {
	double time;
	double init_dt;
	double curr_dt;
	boolean on_ground;
	int nan_count;

	/*  position */
	 EcefCoor_d ecef_pos;
	 NedCoor_d ltpprz_pos;
	 LlaCoor_d lla_pos;
	double hmsl;
	// for debugging
	 LlaCoor_d lla_pos_pprz; //lla converted by pprz from ecef
	 LlaCoor_d lla_pos_geod; //geodetic lla from jsbsim
	 LlaCoor_d lla_pos_geoc; //geocentric lla from jsbsim
	double agl; //AGL from jsbsim in m

	/*  velocity and acceleration wrt inertial frame expressed in ecef frame */
	//   EcefCoor_d  ecef_inertial_vel;
	//   EcefCoor_d  ecef_inertial_accel;
	/*  velocity and acceleration wrt ecef frame expressed in ecef frame     */
	 EcefCoor_d ecef_ecef_vel;
	 EcefCoor_d ecef_ecef_accel;
	/*  velocity and acceleration wrt ecef frame expressed in body frame     */
	 DoubleVect3 body_ecef_vel; /* aka UVW */
	 DoubleVect3 body_ecef_accel;
	/*  velocity and acceleration wrt ecef frame expressed in ltp frame     */
	 NedCoor_d ltp_ecef_vel;
	 NedCoor_d ltp_ecef_accel;
	/*  velocity and acceleration wrt ecef frame expressed in ltppprz frame */
	 NedCoor_d ltpprz_ecef_vel;
	 NedCoor_d ltpprz_ecef_accel;

	/* attitude */
	 DoubleQuat ecef_to_body_quat;
	 DoubleQuat ltp_to_body_quat;
	 DoubleEulers ltp_to_body_eulers;
	 DoubleQuat ltpprz_to_body_quat;
	 DoubleEulers ltpprz_to_body_eulers;

	/*  velocity and acceleration wrt ecef frame expressed in body frame     */
	 DoubleRates body_ecef_rotvel;
	 DoubleRates body_ecef_rotaccel;

	 DoubleVect3 ltp_g;
	 DoubleVect3 ltp_h;

	 DoubleVect3 wind; ///< velocity in m/s in NED


}
