package sw.airborne;

import sw.airborne.math.*;

public class State_struct {
	 /** @addtogroup state_position
	   *  @{ */

	  /**
	   * Holds the status bits for all position representations.
	   * When the corresponding bit is set the representation
	   * is already computed.
	   */
	  int pos_status;

	  /**
	   * Position in EarthCenteredEarthFixed coordinates.
	   * Units: centimeters
	   */
	  EcefCoor_i ecef_pos_i;

	  /**
	   * Position in Latitude, Longitude and Altitude.
	   * Units lat,lon: radians*1e7
	   * Units alt: milimeters above reference ellipsoid
	   */
	  LlaCoor_i lla_pos_i;

	  /**
	   * Definition of the local (flat earth) coordinate system.
	   * Defines the origin of the local NorthEastDown coordinate system
	   * in ECEF (EarthCenteredEarthFixed) and LLA (LatitudeLongitudeAlt)
	   * coordinates and the roation matrix from ECEF to local frame.
	   * (int version)
	   */
	  LtpDef_i ned_origin_i;

	  /**
	   * true if local int coordinate frame is initialsed
	   */
	  boolean ned_initialized_i;

	  /**
	   * Position in North East Down coordinates.
	   * with respect to ned_origin_i (flat earth)
	   * Units: m in BFP with INT32_POS_FRAC
	   */
	  NedCoor_i ned_pos_i;

	  /**
	   * Position in East North Up coordinates.
	   * with respect to ned_origin_i (flat earth)
	   * Units: m in BFP with INT32_POS_FRAC
	   */
	  EnuCoor_i enu_pos_i;

	  /**
	   * Position in UTM coordinates.
	   * Units x,y: meters.
	   * Units z: meters above MSL
	   */
	  UtmCoor_f utm_pos_f;

	  /**
	   * Altitude above ground level.
	   * Unit: meters
	   */
	  float alt_agl_f;

	  /**
	   * Position in Latitude, Longitude and Altitude.
	   * Units lat,lon: radians
	   * Units alt: meters above reference ellipsoid
	   */
	  LlaCoor_f lla_pos_f;

	  /**
	   * Position in EarthCenteredEarthFixed coordinates.
	   * Units: meters
	   */
	  EcefCoor_f ecef_pos_f;

	  /**
	   * Definition of the local (flat earth) coordinate system.
	   * Defines the origin of the local NorthEastDown coordinate system
	   * in ECEF (EarthCenteredEarthFixed) and LLA (LatitudeLongitudeAlt)
	   * coordinates and the roation matrix from ECEF to local frame.
	   * (float version)
	   */
	  LtpDef_f ned_origin_f;

	  /// True if local float coordinate frame is initialsed
	  boolean ned_initialized_f;

	  /**
	   * Definition of the origin of Utm coordinate system.
	   * Defines the origin of the local NorthEastDown coordinate system
	   * in UTM coordinates, used as a reference when ned_origin is not
	   * initialized.
	   * (float version)
	   */
	  UtmCoor_f utm_origin_f;

	  /// True if utm origin (float) coordinate frame is initialsed
	  boolean utm_initialized_f;

	  /**
	   * Position in North East Down coordinates.
	   * with respect to ned_origin_i (flat earth)
	   * Units: meters
	   */
	  NedCoor_f ned_pos_f;

	  /**
	   * Position in East North Up coordinates.
	   * with respect to ned_origin_i (flat earth)
	   * Units: meters
	   */
	  EnuCoor_f enu_pos_f;
	  /** @}*/


	  /** @addtogroup state_velocity
	   *  @{ */
	  /**
	   * Holds the status bits for all ground speed representations.
	   * When the corresponding bit is one the representation
	   * is already computed.
	   */
	  int speed_status;

	  /**
	   * Velocity in EarthCenteredEarthFixed coordinates.
	   * Units: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  EcefCoor_i ecef_speed_i;

	  /**
	   * Velocity in North East Down coordinates.
	   * Units: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  NedCoor_i ned_speed_i;

	  /**
	   * Velocity in East North Up coordinates.
	   * Units: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  EnuCoor_i enu_speed_i;

	  /**
	   * Norm of horizontal ground speed.
	   * Unit: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  int h_speed_norm_i;

	  /**
	   * Direction of horizontal ground speed.
	   * Unit: rad in BFP with #INT32_ANGLE_FRAC
	   * (clockwise, zero=north)
	   */
	  int h_speed_dir_i;

	  /**
	   * Velocity in EarthCenteredEarthFixed coordinates.
	   * Units: m/s
	   */
	  EcefCoor_f ecef_speed_f;

	  /**
	   * @brief speed in North East Down coordinates
	   * @details Units: m/s */
	  NedCoor_f ned_speed_f;

	  /**
	   * Velocity in East North Up coordinates.
	   * Units: m/s
	   */
	  EnuCoor_f enu_speed_f;

	  /**
	   * Norm of horizontal ground speed.
	   * Unit: m/s
	   */
	  float h_speed_norm_f;

	  /**
	   * Direction of horizontal ground speed.
	   * Unit: rad (clockwise, zero=north)
	   */
	  float h_speed_dir_f;
	  /** @}*/


	  /** @addtogroup state_acceleration
	   *  @{ */
	  /**
	   * Holds the status bits for all acceleration representations.
	   * When the corresponding bit is one the representation
	   * is already computed.
	   */
	  int accel_status;

	  /**
	   * Acceleration in North East Down coordinates.
	   * Units: m/s^2 in BFP with #INT32_ACCEL_FRAC
	   */
	  NedCoor_i ned_accel_i;

	  /**
	   * Acceleration in EarthCenteredEarthFixed coordinates.
	   * Units: m/s^2 in BFP with INT32_ACCEL_FRAC
	   */
	  EcefCoor_i ecef_accel_i;

	  /**
	   * Acceleration in North East Down coordinates.
	   * Units: m/s^2
	   */
	  NedCoor_f ned_accel_f;

	  /**
	   * Acceleration in EarthCenteredEarthFixed coordinates.
	   * Units: m/s^2
	   */
	  EcefCoor_f ecef_accel_f;
	  /** @}*/


	  /** @defgroup state_attitude Attitude representations
	   */
	  OrientationReps ned_to_body_orientation;


	  /** @addtogroup state_rate
	   *  @{ */
	  /**
	   * Holds the status bits for all angular rate representations.
	   * When the corresponding bit is one the representation
	   * is already computed.
	   */
	  int rate_status;

	  /**
	   * Angular rates in body frame.
	   * Units: rad/s in BFP with #INT32_RATE_FRAC
	   */
	  Int32Rates body_rates_i;

	  /**
	   * Angular rates in body frame.
	   * Units: rad/s
	   */
	  FloatRates  body_rates_f;
	  /** @}*/


	  /** @addtogroup state_wind_airspeed
	   *  @{ */
	  /**
	   * Holds the status bits for all wind- and airspeed representations.
	   * When the corresponding bit is one the representation
	   * is already computed.
	   */
	  int wind_air_status;

	  /**
	   * Horizontal windspeed in north/east.
	   * Units: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  Int32Vect2 h_windspeed_i;

	  /**
	   * Norm of horizontal ground speed.
	   * @details Unit: m/s in BFP with #INT32_SPEED_FRAC
	   */
	  int airspeed_i;

	  /**
	   * Horizontal windspeed.
	   * Units: m/s with x=north, y=east
	   */
	  FloatVect2 h_windspeed_f;

	  /**
	   * Norm of relative air speed.
	   * Unit: m/s
	   */
	  float airspeed_f;

	  /**
	   * Angle of attack
	   * Unit: rad
	   */
	  float angle_of_attack_f;

	  /**
	   * Sideslip angle
	   * Unit: rad
	   */
	  float sideslip_f;

	  /** @}*/
}
