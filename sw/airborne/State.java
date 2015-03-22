package sw.airborne;

import sw.airborne.math.*; 

public class State {
	/* *
	 * This general state interface holds all the most important vehicle states like
	 * position, velocity, attitude, etc. It handles coordinate system and
	 * fixed-/floating-point conversion on the fly when needed.
	 *
	 * You can set e.g. the position in any coordinate system you wish:
	 * stateSetPositionNed_i() to set the position in fixed-point NED coordinates.
	 * If you need to read the position somewhere else in a different representation,
	 * call: stateGetPositionLla_f() and only then the LLA float position representation
	 * is calculated on the fly and returned. It's also only calculated once,
	 * until a new position is set which invalidates all the other representations again.
	 */

	
	public static final int POS_ECEF_I = 0;
	public static final int POS_NED_I = 1;
	public static final int POS_ENU_I  =2;
	public static final int POS_LLA_I  =3;
	public static final int POS_UTM_I = 4;
	public static final int POS_ECEF_F =5;
	public static final int POS_NED_F  =6;
	public static final int POS_ENU_F  =7;
	public static final int POS_LLA_F  =8;
	public static final int POS_UTM_F  =9;
	public static final int POS_LOCAL_COORD = ((1<<POS_NED_I)|(1<<POS_NED_F)|(1<<POS_ENU_I)|(1<<POS_ENU_F));
	public static final int POS_GLOBAL_COORD = ((1<<POS_ECEF_I)|(1<<POS_ECEF_F)|(1<<POS_LLA_I)|(1<<POS_LLA_F)|(1<<POS_UTM_I)|(1<<POS_UTM_F));
	
	public static final int SPEED_ECEF_I = 0;
	public static final int SPEED_NED_I   =1;
	public static final int SPEED_ENU_I  = 2;
	public static final int SPEED_HNORM_I= 3;
	public static final int SPEED_HDIR_I  =4;
	public static final int SPEED_ECEF_F  =5;
	public static final int SPEED_NED_F  = 6;
	public static final int SPEED_ENU_F  = 7;
	public static final int SPEED_HNORM_F= 8;
	public static final int SPEED_HDIR_F = 9;
	public static final double SPEED_LOCAL_COORD = ((1<<SPEED_NED_I)|(1<<SPEED_ENU_I)|(1<<SPEED_NED_F)|(1<<SPEED_ENU_F));
	
	public static final int ACCEL_ECEF_I= 0;
	public static final int ACCEL_NED_I = 1;
	public static final int ACCEL_ECEF_F= 2;
	public static final int ACCEL_NED_F = 3;
	
	public static final int RATE_I= 0;
	public static final int RATE_F= 1;
	
	public static final int WINDSPEED_I =0;
	public static final int AIRSPEED_I  =1;
	public static final int WINDSPEED_F =2;
	public static final int AIRSPEED_F  =3;
	public static final int AOA_F       =4;
	public static final int SIDESLIP_F  =5;
	
	public static State_struct state;
	
	public static void stateSetLocalOrigin_i(LtpDef_i ltp_def) {
		//memcpy(&state.ned_origin_i, ltp_def, sizeof( LtpDef_i));
		state.ned_origin_i  = ltp_def.clone();  
		/* convert to float */
		ECEF_FLOAT_OF_BFP(state.ned_origin_f.ecef, state.ned_origin_i.ecef);
		LLA_FLOAT_OF_BFP(state.ned_origin_f.lla, state.ned_origin_i.lla);
		HIGH_RES_RMAT_FLOAT_OF_BFP(state.ned_origin_f.ltp_of_ecef, state.ned_origin_i.ltp_of_ecef);
		state.ned_origin_f.hmsl = M_OF_MM(state.ned_origin_i.hmsl);

		/* clear bits for all local frame representations */
		state.pos_status &= ~(POS_LOCAL_COORD);
		state.speed_status &= ~(SPEED_LOCAL_COORD);
		ClearBit(state.accel_status, ACCEL_NED_I);
		ClearBit(state.accel_status, ACCEL_NED_F);

		state.ned_initialized_i = true;
		state.ned_initialized_f = true;
	}
	
	public static void stateSetLocalUtmOrigin_f(UtmCoor_f utm_def) {
		  //memcpy(&state.utm_origin_f, utm_def, sizeof( UtmCoor_f));
		state.utm_origin_f = utm_def.clone();  
		state.utm_initialized_f = true;

		/* clear bits for all local frame representations */
		state.pos_status &= ~(POS_LOCAL_COORD);
		state.speed_status &= ~(SPEED_LOCAL_COORD);
		ClearBit(state.accel_status, ACCEL_NED_I);
		ClearBit(state.accel_status, ACCEL_NED_F);
	}
	
	// Test if local coordinates are valid.
	public static boolean stateIsLocalCoordinateValid() {
		return ((state.ned_initialized_i || state.ned_initialized_f || state.utm_initialized_f) && ((state.pos_status  & (POS_LOCAL_COORD )) != 0));
	}
	/// Test if global coordinates are valid.
	public static boolean stateIsGlobalCoordinateValid() {
		  return ((state.pos_status & (POS_GLOBAL_COORD)) !=0 || stateIsLocalCoordinateValid());
	}

	/// Set position from ECEF coordinates (int).
	public static void stateSetPositionEcef_i( EcefCoor_i ecef_pos) {
	  INT32_VECT3_COPY(state.ecef_pos_i, ecef_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_ECEF_I);
	}

	/// Set position from local NED coordinates (int).
	public static void stateSetPositionNed_i(NedCoor_i ned_pos) {
	  INT32_VECT3_COPY(state.ned_pos_i, ned_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_NED_I);
	}

	/// Set position from local ENU coordinates (int).
	public static void stateSetPositionEnu_i( EnuCoor_i enu_pos) {
	  INT32_VECT3_COPY(state.enu_pos_i, enu_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_ENU_I);
	}

	/// Set position from LLA coordinates (int).
	public static  void stateSetPositionLla_i( LlaCoor_i lla_pos) {
	  LLA_COPY(state.lla_pos_i, lla_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_LLA_I);
	}

	/// Set multiple position coordinates (int).
	public static  void stateSetPosition_i(
	     EcefCoor_i ecef_pos,
	     NedCoor_i ned_pos,
	     EnuCoor_i enu_pos,
	     LlaCoor_i lla_pos) {
	  /* clear all status bit */
	  state.pos_status = 0;
	  if (ecef_pos != null) {
	    INT32_VECT3_COPY(state.ecef_pos_i, ecef_pos);
	    state.pos_status |= (1 << POS_ECEF_I);
	  }
	  if (ned_pos != null) {
	    INT32_VECT3_COPY(state.ned_pos_i, ned_pos);
	    state.pos_status |= (1 << POS_NED_I);
	  }
	  if (enu_pos != null) {
	    INT32_VECT3_COPY(state.enu_pos_i, enu_pos);
	    state.pos_status |= (1 << POS_ENU_I);
	  }
	  if (lla_pos != null) {
	    LLA_COPY(state.lla_pos_i, lla_pos);
	    state.pos_status |= (1 << POS_LLA_I);
	  }
	}

	/// Set position from UTM coordinates (float).
	public static void stateSetPositionUtm_f(UtmCoor_f utm_pos) {
	  //memcpy(&state.utm_pos_f, utm_pos, sizeof( UtmCoor_f));
		state.utm_pos_f = utm_pos.clone();
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_UTM_F);
	}

	/// Set position from ECEF coordinates (float).
	public static void stateSetPositionEcef_f(EcefCoor_f ecef_pos) {
	  VECT3_COPY(state.ecef_pos_f, ecef_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_ECEF_F);
	}

	/// Set position from local NED coordinates (float).
	public static  void stateSetPositionNed_f( NedCoor_f ned_pos) {
	  VECT3_COPY(state.ned_pos_f, ned_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_NED_F);
	}

	/// Set position from local ENU coordinates (float).
	public static  void stateSetPositionEnu_f( EnuCoor_f enu_pos) {
	  VECT3_COPY(state.enu_pos_f, enu_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_ENU_F);
	}

	/// Set position from LLA coordinates (float).
	public static  void stateSetPositionLla_f( LlaCoor_f lla_pos) {
	  LLA_COPY(state.lla_pos_f, lla_pos);
	  /* clear bits for all position representations and only set the new one */
	  state.pos_status = (1 << POS_LLA_F);
	}

	/// Set multiple position coordinates (float).
	public static  void stateSetPosition_f(
			EcefCoor_f ecef_pos,
			NedCoor_f ned_pos,
			EnuCoor_f enu_pos,
			LlaCoor_f lla_pos,
			UtmCoor_f utm_pos) {
		/* clear all status bit */
		state.pos_status = 0;
		if (ecef_pos != NULL) {
			VECT3_COPY(state.ecef_pos_f, ecef_pos);
			state.pos_status |= (1 << POS_ECEF_F);
		}
		if (ned_pos != NULL) {
			VECT3_COPY(state.ned_pos_f, ned_pos);
			state.pos_status |= (1 << POS_NED_F);
		}
		if (enu_pos != NULL) {
			VECT3_COPY(state.enu_pos_f, enu_pos);
			state.pos_status |= (1 << POS_ENU_F);
		}
		if (lla_pos != NULL) {
			LLA_COPY(state.lla_pos_f, lla_pos);
			state.pos_status |= (1 << POS_LLA_F);
		}
		if (utm_pos != NULL) {
			//memcpy(state.utm_pos_f, utm_pos, sizeof( UtmCoor_f));
			state.utm_pos_f = utm_pos.clone();
			state.pos_status |= (1 << POS_UTM_F);
		}
	}

	/* *********************** Get functions *************************** */

	/// Get position in ECEF coordinates (int).
	public static   EcefCoor_i stateGetPositionEcef_i() {
	  if (!bit_is_set(state.pos_status, POS_ECEF_I))
	    stateCalcPositionEcef_i();
	  return state.ecef_pos_i;
	}

	/// Get position in local NED coordinates (int).
	public static   NedCoor_i stateGetPositionNed_i() {
	  if (!bit_is_set(state.pos_status, POS_NED_I))
	    stateCalcPositionNed_i();
	  return state.ned_pos_i;
	}

	/// Get position in local ENU coordinates (int).
	public static   EnuCoor_i stateGetPositionEnu_i() {
	  if (!bit_is_set(state.pos_status, POS_ENU_I))
	    stateCalcPositionEnu_i();
	  return state.enu_pos_i;
	}

	/// Get position in LLA coordinates (int).
	public public static   LlaCoor_i stateGetPositionLla_i() {
	  if (!bit_is_set(state.pos_status, POS_LLA_I))
	    stateCalcPositionLla_i();
	  return state.lla_pos_i;
	}

	/// Get position in UTM coordinates (float).
	public static   UtmCoor_f stateGetPositionUtm_f() {
	  if (!bit_is_set(state.pos_status, POS_UTM_F))
	    stateCalcPositionUtm_f();
	  return state.utm_pos_f;
	}

	/// Get position in ECEF coordinates (float).
	public static   EcefCoor_f stateGetPositionEcef_f() {
	  if (!bit_is_set(state.pos_status, POS_ECEF_F))
	    stateCalcPositionEcef_f();
	  return state.ecef_pos_f;
	}

	/// Get position in local NED coordinates (float).
	public static   NedCoor_f stateGetPositionNed_f() {
	  if (!bit_is_set(state.pos_status, POS_NED_F))
	    stateCalcPositionNed_f();
	  return state.ned_pos_f;
	}

	/// Get position in local ENU coordinates (float).
	public static   EnuCoor_f stateGetPositionEnu_f() {
	  if (!bit_is_set(state.pos_status, POS_ENU_F))
	    stateCalcPositionEnu_f();
	  return state.enu_pos_f;
	}

	/// Get position in LLA coordinates (float).
	public static   LlaCoor_f stateGetPositionLla_f() {
	  if (!bit_is_set(state.pos_status, POS_LLA_F))
	    stateCalcPositionLla_f();
	  return state.lla_pos_f;
	}

	/** @}*/



	/* ****************************************************************************
	 *                                                                            *
	 * Set and Get functions for the SPEED representations                        *
	 *                                                                            *
	 *************************************************************************** */
	

	/* ********************** Set functions ************************** */

	// Set ground speed in local NED coordinates (int).
	public static  void stateSetSpeedNed_i( NedCoor_i ned_speed) {
	  INT32_VECT3_COPY(state.ned_speed_i, ned_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_NED_I);
	}

	// Set ground speed in local ENU coordinates (int).
	public static  void stateSetSpeedEnu_i( EnuCoor_i enu_speed) {
	  INT32_VECT3_COPY(state.enu_speed_i, enu_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_ENU_I);
	}

	/// Set ground speed in ECEF coordinates (int).
	public static  void stateSetSpeedEcef_i( EcefCoor_i ecef_speed) {
	  INT32_VECT3_COPY(state.ecef_speed_i, ecef_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_ECEF_I);
	}

	/// Set multiple speed coordinates (int).
	public static  void stateSetSpeed_i(
	     EcefCoor_i ecef_speed,
	     NedCoor_i ned_speed,
	     EnuCoor_i enu_speed) {
	  /* clear all status bit */
	  state.speed_status = 0;
	  if (ecef_speed != NULL) {
	    INT32_VECT3_COPY(state.ecef_speed_i, ecef_speed);
	    state.speed_status |= (1 << SPEED_ECEF_I);
	  }
	  if (ned_speed != NULL) {
	    INT32_VECT3_COPY(state.ned_speed_i, ned_speed);
	    state.speed_status |= (1 << SPEED_NED_I);
	  }
	  if (enu_speed != NULL) {
	    INT32_VECT3_COPY(state.enu_speed_i, enu_speed);
	    state.speed_status |= (1 << SPEED_ENU_I);
	  }
	}

	/// Set ground speed in local NED coordinates (float).
	public static  void stateSetSpeedNed_f( NedCoor_f ned_speed) {
	  VECT3_COPY(state.ned_speed_f, ned_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_NED_F);
	}

	/// Set ground speed in local ENU coordinates (float).
	public static  void stateSetSpeedEnu_f( EnuCoor_f enu_speed) {
	  VECT3_COPY(state.enu_speed_f, enu_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_ENU_F);
	}

	/// Set ground speed in ECEF coordinates (float).
	public static  void stateSetSpeedEcef_f( EcefCoor_f ecef_speed) {
	  VECT3_COPY(state.ecef_speed_f, ecef_speed);
	  /* clear bits for all speed representations and only set the new one */
	  state.speed_status = (1 << SPEED_ECEF_F);
	}

	/// Set multiple speed coordinates (float).
	public static  void stateSetSpeed_f(
	     EcefCoor_f ecef_speed,
	     NedCoor_f ned_speed,
	     EnuCoor_f enu_speed) {
	  /* clear all status bit */
	  state.speed_status = 0;
	  if (ecef_speed != NULL) {
	    VECT3_COPY(state.ecef_speed_f, ecef_speed);
	    state.speed_status |= (1 << SPEED_ECEF_F);
	  }
	  if (ned_speed != NULL) {
	    VECT3_COPY(state.ned_speed_f, ned_speed);
	    state.speed_status |= (1 << SPEED_NED_F);
	  }
	  if (enu_speed != NULL) {
	    VECT3_COPY(state.enu_speed_f, enu_speed);
	    state.speed_status |= (1 << SPEED_ENU_F);
	  }
	}

	/* ********************** Get functions ************************** */

	/// Get ground speed in local NED coordinates (int).
	public static   NedCoor_i stateGetSpeedNed_i() {
	  if (!bit_is_set(state.speed_status, SPEED_NED_I))
	    stateCalcSpeedNed_i();
	  return state.ned_speed_i;
	}

	/// Get ground speed in local ENU coordinates (int).
	public static   EnuCoor_i stateGetSpeedEnu_i() {
	  if (!bit_is_set(state.speed_status, SPEED_ENU_I))
	    stateCalcSpeedEnu_i();
	  return state.enu_speed_i;
	}

	/// Get ground speed in ECEF coordinates (int).
	public static   EcefCoor_i stateGetSpeedEcef_i() {
	  if (!bit_is_set(state.speed_status, SPEED_ECEF_I))
	    stateCalcSpeedEcef_i();
	  return state.ecef_speed_i;
	}

	/// Get norm of horizontal ground speed (int).
	public static  int stateGetHorizontalSpeedNorm_i() {
	  if (!bit_is_set(state.speed_status, SPEED_HNORM_I))
	    stateCalcHorizontalSpeedNorm_i();
	  return state.h_speed_norm_i;
	}

	/// Get dir of horizontal ground speed (int).
	public static  int stateGetHorizontalSpeedDir_i() {
	  if (!bit_is_set(state.speed_status, SPEED_HDIR_I))
	    stateCalcHorizontalSpeedDir_i();
	  return state.h_speed_dir_i;
	}

	/// Get ground speed in local NED coordinates (float).
	public static   NedCoor_f stateGetSpeedNed_f() {
	  if (!bit_is_set(state.speed_status, SPEED_NED_F))
	    stateCalcSpeedNed_f();
	  return state.ned_speed_f;
	}

	/// Get ground speed in local ENU coordinates (float).
	public static   EnuCoor_f stateGetSpeedEnu_f() {
	  if (!bit_is_set(state.speed_status, SPEED_ENU_F))
	    stateCalcSpeedEnu_f();
	  return state.enu_speed_f;
	}

	/// Get ground speed in ECEF coordinates (float).
	public static   EcefCoor_f stateGetSpeedEcef_f() {
	  if (!bit_is_set(state.speed_status, SPEED_ECEF_F))
	    stateCalcSpeedEcef_f();
	  return state.ecef_speed_f;
	}

	/// Get norm of horizontal ground speed (float).
	public static  float stateGetHorizontalSpeedNorm_f() {
	  if (!bit_is_set(state.speed_status, SPEED_HNORM_F))
	    stateCalcHorizontalSpeedNorm_f();
	  return state.h_speed_norm_f;
	}

	/// Get dir of horizontal ground speed (float).
	public static  float stateGetHorizontalSpeedDir_f() {
	  if (!bit_is_set(state.speed_status, SPEED_HDIR_F))
	    stateCalcHorizontalSpeedDir_f();
	  return state.h_speed_dir_f;
	}
	/** @}*/



	/* ****************************************************************************
	 *                                                                            *
	 * Set and Get functions for the ACCELERATION representations                 *
	 *                                                                            *
	 *************************************************************************** */
	/*  @addtogroup state_acceleration
	 *  @{ */

	

	/* ******************** validity test functions *************** **/

	/// Test if accelerations are valid.
	public static  bool_t stateIsAccelValid() {
	  return (state.accel_status);
	}

	/* ********************** Set functions ****************************/

	/// Set acceleration in NED coordinates (int).
	public static  void stateSetAccelNed_i( NedCoor_i ned_accel) {
	  INT32_VECT3_COPY(state.ned_accel_i, ned_accel);
	  /* clear bits for all accel representations and only set the new one */
	  state.accel_status = (1 << ACCEL_NED_I);
	}

	/// Set acceleration in ECEF coordinates (int).
	public static  void stateSetAccelEcef_i( EcefCoor_i ecef_accel) {
	  INT32_VECT3_COPY(state.ecef_accel_i, *ecef_accel);
	  /* clear bits for all accel representations and only set the new one */
	  state.accel_status = (1 << ACCEL_ECEF_I);
	}

	/// Set acceleration in NED coordinates (float).
	public static  void stateSetAccelNed_f( NedCoor_f ned_accel) {
	  VECT3_COPY(state.ned_accel_f, ned_accel);
	  /* clear bits for all accel representations and only set the new one */
	  state.accel_status = (1 << ACCEL_NED_F);
	}

	/// Set acceleration in ECEF coordinates (float).
	public static  void stateSetAccelEcef_f( EcefCoor_f ecef_accel) {
	  VECT3_COPY(state.ecef_accel_f, ecef_accel);
	  /* clear bits for all accel representations and only set the new one */
	  state.accel_status = (1 << ACCEL_ECEF_F);
	}

	/* ********************** Get functions ************************* */

	/// Get acceleration in NED coordinates (int).
	public static   NedCoor_i stateGetAccelNed_i() {
	  if (!bit_is_set(state.accel_status, ACCEL_NED_I))
	    stateCalcAccelNed_i();
	  return state.ned_accel_i;
	}

	/// Get acceleration in ECEF coordinates (int).
	public static   EcefCoor_i stateGetAccelEcef_i() {
	  if (!bit_is_set(state.accel_status, ACCEL_ECEF_I))
	    stateCalcAccelEcef_i();
	  return state.ecef_accel_i;
	}

	/// Get acceleration in NED coordinates (float).
	public static   NedCoor_f stateGetAccelNed_f() {
	  if (!bit_is_set(state.accel_status, ACCEL_NED_F))
	    stateCalcAccelNed_f();
	  return state.ned_accel_f;
	}

	/// Get acceleration in ECEF coordinates (float).
	public static   EcefCoor_f stateGetAccelEcef_f() {
	  if (!bit_is_set(state.accel_status, ACCEL_ECEF_F))
	    stateCalcAccelEcef_f();
	  return state.ecef_accel_f;
	}
	/* @}*/

	/* ****************************************************************************
	*                                                                             *
	* Set and Get functions for the ATTITUDE representations                      *
	* (Calls the functions in math/pprz_orientation_conversion)                   *
	*                                                                             *
	************************************************************************* **/
	/* addtogroup state_attitude
	* @{ */
	/* ****************** validity test functions **************** */

	/// Test if attitudes are valid.
	public static  bool_t stateIsAttitudeValid() {
	  return (orienationCheckValid(state.ned_to_body_orientation));
	}

	/* ******************** Set functions ************************* */

	/// Set vehicle body attitude from quaternion (int).
	public static  void stateSetNedToBodyQuat_i( Int32Quat ned_to_body_quat) {
	  orientationSetQuat_i(state.ned_to_body_orientation,ned_to_body_quat);
	}

	/// Set vehicle body attitude from rotation matrix (int).
	public static  void stateSetNedToBodyRMat_i( Int32RMat ned_to_body_rmat) {
	  orientationSetRMat_i(state.ned_to_body_orientation,ned_to_body_rmat);
	}

	/// Set vehicle body attitude from euler angles (int).
	public static  void stateSetNedToBodyEulers_i( Int32Eulers ned_to_body_eulers) {
	  orientationSetEulers_i(state.ned_to_body_orientation,ned_to_body_eulers);
	}

	/// Set vehicle body attitude from quaternion (float).
	public static  void stateSetNedToBodyQuat_f( FloatQuat ned_to_body_quat) {
	  orientationSetQuat_f(state.ned_to_body_orientation,ned_to_body_quat);
	}

	/// Set vehicle body attitude from rotation matrix (float).
	public static  void stateSetNedToBodyRMat_f( FloatRMat ned_to_body_rmat) {
	  orientationSetRMat_f(state.ned_to_body_orientation,ned_to_body_rmat);
	}

	/// Set vehicle body attitude from euler angles (float).
	public static  void stateSetNedToBodyEulers_f( FloatEulers ned_to_body_eulers) {
	  orientationSetEulers_f(state.ned_to_body_orientation,ned_to_body_eulers);
	}

	/*  ********************* Get functions ***************************/

	/// Get vehicle body attitude quaternion (int).
	public static   Int32Quat stateGetNedToBodyQuat_i() {
	  return orientationGetQuat_i(state.ned_to_body_orientation);
	}

	/// Get vehicle body attitude rotation matrix (int).
	public static   Int32RMat stateGetNedToBodyRMat_i() {
	  return orientationGetRMat_i(state.ned_to_body_orientation);
	}

	/// Get vehicle body attitude euler angles (int).
	public static   Int32Eulers stateGetNedToBodyEulers_i() {
	  return orientationGetEulers_i(state.ned_to_body_orientation);
	}

	/// Get vehicle body attitude quaternion (float).
	public static   FloatQuat stateGetNedToBodyQuat_f() {
	  return orientationGetQuat_f(state.ned_to_body_orientation);
	}

	/// Get vehicle body attitude rotation matrix (float).
	public static   FloatRMat stateGetNedToBodyRMat_f() {
	  return orientationGetRMat_f(state.ned_to_body_orientation);
	}

	/// Get vehicle body attitude euler angles (float).
	public static   FloatEulers* stateGetNedToBodyEulers_f(void) {
	  return orientationGetEulers_f(state.ned_to_body_orientation);
	}


	/// Test if rates are valid.
	public static  bool_t stateIsRateValid() {
	  return (state.rate_status);
	}

	/* ********************* Set functions ************************* */

	/// Set vehicle body angular rate (int).
	public static  void stateSetBodyRates_i( Int32Rates body_rate) {
	  RATES_COPY(state.body_rates_i, body_rate);
	  /* clear bits for all attitude representations and only set the new one */
	  state.rate_status = (1 << RATE_I);
	}

	/// Set vehicle body angular rate (float).
	public static  void stateSetBodyRates_f( FloatRates body_rate) {
	  RATES_COPY(state.body_rates_f, body_rate);
	  /* clear bits for all attitude representations and only set the new one */
	  state.rate_status = (1 << RATE_F);
	}

	/* ******************** Get functions ************************ */

	/// Get vehicle body angular rate (int).
	public static   Int32Rates stateGetBodyRates_i() {
	  if (!bit_is_set(state.rate_status, RATE_I))
	    stateCalcBodyRates_i();
	  return state.body_rates_i;
	}

	/// Get vehicle body angular rate (float).
	public static   FloatRates stateGetBodyRates_f() {
	  if (!bit_is_set(state.rate_status, RATE_F))
	    stateCalcBodyRates_f();
	  return state.body_rates_f;
	}

	

	/// test if wind speed is available.
	public static  boolean stateIsWindspeedValid() {
	  return (state.wind_air_status &= ~((1<<WINDSPEED_I)|(1<<WINDSPEED_F)));
	}

	/// test if air speed is available.
	public static  boolean stateIsAirspeedValid() {
	  return (state.wind_air_status &= ~((1<<AIRSPEED_I)|(1<<AIRSPEED_F)));
	}

	/// test if angle of attack is available.
	public static  boolean stateIsAngleOfAttackValid() {
	  return (state.wind_air_status &= ~(1<<AOA_F));
	}

	/// test if sideslip is available.
	public static  boolean stateIsSideslipValid() {
	  return (state.wind_air_status &= ~(1<<SIDESLIP_F));
	}

	/************************ Set functions ****************************/

	/// Set horizontal windspeed (int).
	public static  void stateSetHorizontalWindspeed_i( Int32Vect2 h_windspeed) {
	  VECT2_COPY(state.h_windspeed_i, h_windspeed);
	  /* clear bits for all windspeed representations and only set the new one */
	  ClearBit(state.wind_air_status, WINDSPEED_F);
	  SetBit(state.wind_air_status, WINDSPEED_I);
	}

	/// Set airspeed (int).
	public static  void stateSetAirspeed_i(int airspeed) {
	  state.airspeed_i = airspeed;
	  /* clear bits for all airspeed representations and only set the new one */
	  ClearBit(state.wind_air_status, AIRSPEED_F);
	  SetBit(state.wind_air_status, AIRSPEED_I);
	}

	/// Set horizontal windspeed (float).
	public static  void stateSetHorizontalWindspeed_f( FloatVect2 h_windspeed) {
	  VECT2_COPY(state.h_windspeed_f, h_windspeed);
	  /* clear bits for all windspeed representations and only set the new one */
	  ClearBit(state.wind_air_status, WINDSPEED_I);
	  SetBit(state.wind_air_status, WINDSPEED_F);
	}

	/// Set airspeed (float).
	public static  void stateSetAirspeed_f(float airspeed) {
	  state.airspeed_f = airspeed;
	  /* clear bits for all airspeed representations and only set the new one */
	  ClearBit(state.wind_air_status, AIRSPEED_I);
	  SetBit(state.wind_air_status, AIRSPEED_F);
	}

	/// Set angle of attack in radians (float).
	public static  void stateSetAngleOfAttack_f(float aoa) {
	  state.angle_of_attack_f = aoa;
	  /* clear bits for all AOA representations and only set the new one */
	  /// @todo no integer yet
	  SetBit(state.wind_air_status, AOA_F);
	}

	/// Set sideslip angle in radians (float).
	public static  void stateSetSideslip_f(float sideslip) {
	  state.sideslip_f = sideslip;
	  /* clear bits for all sideslip representations and only set the new one */
	  /// @todo no integer yet
	  SetBit(state.wind_air_status, SIDESLIP_F);
	}

	

	/// Get horizontal windspeed (int).
	public static   Int32Vect2 stateGetHorizontalWindspeed_i() {
	  if (!bit_is_set(state.wind_air_status, WINDSPEED_I))
	    stateCalcHorizontalWindspeed_i();
	  return state.h_windspeed_i;
	}

	/// Get airspeed (int).
	public static  int32_t stateGetAirspeed_i() {
	  if (!bit_is_set(state.wind_air_status, AIRSPEED_I))
	    stateCalcAirspeed_i();
	  return state.airspeed_i;
	}

	/// Get horizontal windspeed (float).
	public static   FloatVect2 stateGetHorizontalWindspeed_f() {
	  if (!bit_is_set(state.wind_air_status, WINDSPEED_F))
	    stateCalcHorizontalWindspeed_f();
	  return state.h_windspeed_f;
	}

	/// Get airspeed (float).
	public static  float stateGetAirspeed_f() {
	  if (!bit_is_set(state.wind_air_status, AIRSPEED_F))
	    stateCalcAirspeed_f();
	  return state.airspeed_f;
	}

	/// Get angle of attack (float).
	public static  float stateGetAngleOfAttack_f() {
	  ///  @todo only float for now
	//  if (!bit_is_set(state.wind_air_status, AOA_F))
//	    stateCalcAOA_f();
	  return state.angle_of_attack_f;
	}

	/// Get sideslip (float).
	public static  float stateGetSideslip_f() {
	  ///  @todo only float for now
	//  if (!bit_is_set(state.wind_air_status, SIDESLIP_F))
//	    stateCalcSideslip_f();
	  return state.sideslip_f;
	}
	
}
