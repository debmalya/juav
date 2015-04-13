package sw.airborne.subsystems.ins;
import sw.airborne.math.*;
import static sw.airborne.subsystems.Imu.*;
import static sw.airborne.math.Pprz_algebra_int.*;
import static sw.airborne.State.*;
import static sw.airborne.subsystems.ins.vf_float.*;
import static sw.airborne.subsystems.ins.hf_float.*;
import static sw.simulator.nps.Nps_autopilot_rotorcraft.*;
import static sw.airborne.firmwares.rotorcraft.Main.*;
public class ins_int {
	public static  boolean USE_HFF = false;//?????
	public static boolean USE_NPS = false;
	public static InsInt ins_impl = new InsInt();

	public static void ins_update_from_vff() {
		  ins_impl.ltp_accel.z = (long) ACCEL_BFP_OF_REAL(vff.zdotdot);
		  ins_impl.ltp_speed.z = (long) SPEED_BFP_OF_REAL(vff.zdot);
		  ins_impl.ltp_pos.z   = (long) POS_BFP_OF_REAL(vff.z);
	}
	
	public static void ins_update_from_hff() {
		  ins_impl.ltp_accel.x = (long) ACCEL_BFP_OF_REAL(b2_hff_state.xdotdot);
		  ins_impl.ltp_accel.y = (long) ACCEL_BFP_OF_REAL(b2_hff_state.ydotdot);
		  ins_impl.ltp_speed.x = (long) SPEED_BFP_OF_REAL(b2_hff_state.xdot);
		  ins_impl.ltp_speed.y = (long) SPEED_BFP_OF_REAL(b2_hff_state.ydot);
		  ins_impl.ltp_pos.x   = (long) POS_BFP_OF_REAL(b2_hff_state.x);
		  ins_impl.ltp_pos.y   = (long) POS_BFP_OF_REAL(b2_hff_state.y);
		}
	
	public static void ins_ned_to_state() {
		  stateSetPositionNed_i(ins_impl.ltp_pos);
		  stateSetSpeedNed_i(ins_impl.ltp_speed);
		  stateSetAccelNed_i(ins_impl.ltp_accel);

		if(SITL && USE_NPS)
		  if (nps_bypass_ins)
		    sim_overwrite_ins();
		//#endif
		}
	
	public static void ins_propagate() {
		  /* untilt accels */
		   Int32Vect3 accel_meas_body = new Int32Vect3();
		  INT32_RMAT_TRANSP_VMULT(accel_meas_body, imu.body_to_imu_rmat, imu.accel);
		   Int32Vect3 accel_meas_ltp = new Int32Vect3();;
		  INT32_RMAT_TRANSP_VMULT(accel_meas_ltp, (stateGetNedToBodyRMat_i()), accel_meas_body);

		  float z_accel_meas_float = ACCEL_FLOAT_OF_BFP(accel_meas_ltp.z);
		  if (ins_impl.baro_initialized) {
		    vff_propagate(z_accel_meas_float);
		    ins_update_from_vff();
		  }
		  else { // feed accel from the sensors
		    // subtract -9.81m/s2 (acceleration measured due to gravity,
		    // but vehicle not accelerating in ltp)
		    ins_impl.ltp_accel.z = accel_meas_ltp.z + ACCEL_BFP_OF_REAL(9.81);
		  }

		if(USE_HFF){
		  /* propagate horizontal filter */
		  b2_hff_propagate();
		  /* convert and copy result to ins_impl */
		  ins_update_from_hff();
		}else{
		  ins_impl.ltp_accel.x = accel_meas_ltp.x;
		  ins_impl.ltp_accel.y = accel_meas_ltp.y;
		}

		  ins_ned_to_state();
		}

	
}
