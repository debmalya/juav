package sw.airborne.subsystems;

import sw.airborne.math.*;

public class ImuState {
	public static Int32Rates gyro;             ///< gyroscope measurements
	public static Int32Vect3 accel;            ///< accelerometer measurements
	public static Int32Vect3 mag;              ///< magnetometer measurements
	public static Int32Rates gyro_prev;        ///< previous gyroscope measurements
	public static Int32Vect3 accel_prev;       ///< previous accelerometer measurements
	public static Int32Rates gyro_neutral;     ///< gyroscope bias
	public static Int32Vect3 accel_neutral;    ///< accelerometer bias
	public static Int32Vect3 mag_neutral;      ///< magnetometer neutral readings (bias)
	public static Int32Rates gyro_unscaled;    ///< unscaled gyroscope measurements
	public static Int32Vect3 accel_unscaled;   ///< unscaled accelerometer measurements
	public static Int32Vect3 mag_unscaled;     ///< unscaled magnetometer measurements
	public static Int32Quat  body_to_imu_quat; ///< rotation from body to imu frame as a unit quaternion
	public static Int32RMat  body_to_imu_rmat; ///< rotation from body to imu frame as a rotation matrix
}
