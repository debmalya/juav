package sw.airborne.subsystems;

import sw.airborne.math.*;

public class ImuState {
	Int32Rates gyro;             ///< gyroscope measurements
	Int32Vect3 accel;            ///< accelerometer measurements
	Int32Vect3 mag;              ///< magnetometer measurements
	Int32Rates gyro_prev;        ///< previous gyroscope measurements
	Int32Vect3 accel_prev;       ///< previous accelerometer measurements
	Int32Rates gyro_neutral;     ///< gyroscope bias
	Int32Vect3 accel_neutral;    ///< accelerometer bias
	Int32Vect3 mag_neutral;      ///< magnetometer neutral readings (bias)
	Int32Rates gyro_unscaled;    ///< unscaled gyroscope measurements
	Int32Vect3 accel_unscaled;   ///< unscaled accelerometer measurements
	Int32Vect3 mag_unscaled;     ///< unscaled magnetometer measurements
	Int32Quat  body_to_imu_quat; ///< rotation from body to imu frame as a unit quaternion
	Int32RMat  body_to_imu_rmat; ///< rotation from body to imu frame as a rotation matrix
}
