package sw.airborne.subsystems;

import sw.airborne.math.*;

public class ImuState {
	public  Int32Rates gyro = new Int32Rates();             ///< gyroscope measurements
	public  Int32Vect3 accel = new Int32Vect3();            ///< accelerometer measurements
	public  Int32Vect3 mag= new Int32Vect3();              ///< magnetometer measurements
	public  Int32Rates gyro_prev= new Int32Rates();        ///< previous gyroscope measurements
	public  Int32Vect3 accel_prev= new Int32Vect3();       ///< previous accelerometer measurements
	public  Int32Rates gyro_neutral= new Int32Rates();     ///< gyroscope bias
	public  Int32Vect3 accel_neutral= new Int32Vect3();    ///< accelerometer bias
	public  Int32Vect3 mag_neutral= new Int32Vect3();      ///< magnetometer neutral readings (bias)
	public  Int32Rates gyro_unscaled= new Int32Rates();    ///< unscaled gyroscope measurements
	public  Int32Vect3 accel_unscaled= new Int32Vect3();   ///< unscaled accelerometer measurements
	public  Int32Vect3 mag_unscaled= new Int32Vect3();     ///< unscaled magnetometer measurements
	public  Int32Quat  body_to_imu_quat= new Int32Quat(); ///< rotation from body to imu frame as a unit quaternion
	public  Int32RMat  body_to_imu_rmat= new Int32RMat(); ///< rotation from body to imu frame as a rotation matrix
}
