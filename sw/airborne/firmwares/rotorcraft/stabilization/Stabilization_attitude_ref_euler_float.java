package sw.airborne.firmwares.rotorcraft.stabilization;

public class Stabilization_attitude_ref_euler_float {
	
	public static FloatEulers stab_att_sp_euler;
	public static  FloatEulers stab_att_ref_euler;
	public static FloatRates  stab_att_ref_rate;
	public static FloatRates  stab_att_ref_accel;
	
	
	public static double DT_UPDATE = (1./PERIODIC_FREQUENCY);

	public static int REF_ACCEL_MAX_P =STABILIZATION_ATTITUDE_REF_MAX_PDOT;
	public static int REF_ACCEL_MAX_Q =STABILIZATION_ATTITUDE_REF_MAX_QDOT;
	public static int REF_ACCEL_MAX_R =STABILIZATION_ATTITUDE_REF_MAX_RDOT;

	public static int REF_RATE_MAX_P= STABILIZATION_ATTITUDE_REF_MAX_P;
	public static int REF_RATE_MAX_Q =STABILIZATION_ATTITUDE_REF_MAX_Q;
	public static int REF_RATE_MAX_R= STABILIZATION_ATTITUDE_REF_MAX_R;

	public static int OMEGA_P =  STABILIZATION_ATTITUDE_REF_OMEGA_P;
	public static int OMEGA_Q =  STABILIZATION_ATTITUDE_REF_OMEGA_Q;
	public static int OMEGA_R =  STABILIZATION_ATTITUDE_REF_OMEGA_R;

	public static int ZETA_P  =  STABILIZATION_ATTITUDE_REF_ZETA_P;
	public static int ZETA_Q  =  STABILIZATION_ATTITUDE_REF_ZETA_Q;
	public static int ZETA_R   = STABILIZATION_ATTITUDE_REF_ZETA_R;


	public static boolean USE_REF = true;

	public static void reset_psi_ref_from_body() {
	  //sp has been set from body using stabilization_attitude_get_yaw_f, use that value
	  stab_att_ref_euler.psi = stab_att_sp_euler.psi;
	  stab_att_ref_rate.r = 0;
	  stab_att_ref_accel.r = 0;
	}

	public static void stabilization_attitude_ref_enter()
	{
	  reset_psi_ref_from_body();
	}

	public static void stabilization_attitude_ref_update() {

		if(USE_REF){

			/* dumb integrate reference attitude        */
			FloatRates delta_rate;
			RATES_SMUL(delta_rate, stab_att_ref_rate, DT_UPDATE);
			FloatEulers delta_angle;
			EULERS_ASSIGN(delta_angle, delta_rate.p, delta_rate.q, delta_rate.r);
			EULERS_ADD(stab_att_ref_euler, delta_angle );
			FLOAT_ANGLE_NORMALIZE(stab_att_ref_euler.psi);

			/* integrate reference rotational speeds   */
			FloatRates delta_accel;
			RATES_SMUL(delta_accel, stab_att_ref_accel, DT_UPDATE);
			RATES_ADD(stab_att_ref_rate, delta_accel);

			/* compute reference attitude error        */
			FloatEulers ref_err;
			EULERS_DIFF(ref_err, stab_att_ref_euler, stab_att_sp_euler);
			/* wrap it in the shortest direction       */
			FLOAT_ANGLE_NORMALIZE(ref_err.psi);

			/* compute reference angular accelerations */
			stab_att_ref_accel.p = -2.*ZETA_P*OMEGA_P*stab_att_ref_rate.p - OMEGA_P*OMEGA_P*ref_err.phi;
			stab_att_ref_accel.q = -2.*ZETA_Q*OMEGA_P*stab_att_ref_rate.q - OMEGA_Q*OMEGA_Q*ref_err.theta;
			stab_att_ref_accel.r = -2.*ZETA_R*OMEGA_P*stab_att_ref_rate.r - OMEGA_R*OMEGA_R*ref_err.psi;

			/*	saturate acceleration */
			FloatRates MIN_ACCEL = { -REF_ACCEL_MAX_P, -REF_ACCEL_MAX_Q, -REF_ACCEL_MAX_R };
			FloatRates MAX_ACCEL = {  REF_ACCEL_MAX_P,  REF_ACCEL_MAX_Q,  REF_ACCEL_MAX_R };
			RATES_BOUND_BOX(stab_att_ref_accel, MIN_ACCEL, MAX_ACCEL);

			/* saturate speed and trim accel accordingly */
			SATURATE_SPEED_TRIM_ACCEL();
		}
		else{  /* !USE_REF */
			EULERS_COPY(stab_att_ref_euler, stabilization_att_sp);
			FLOAT_RATES_ZERO(stab_att_ref_rate);
			FLOAT_RATES_ZERO(stab_att_ref_accel);
		}/* USE_REF */

	}
}
