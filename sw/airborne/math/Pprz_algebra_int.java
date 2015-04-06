package sw.airborne.math;

import static sw.airborne.math.Pprz_algebra.*;

public class Pprz_algebra_int {
	
	
	public static void INT32_VECT3_COPY(Int32Vect3 _a,Int32Vect3 _b){
		(_a).x = (_b).x;				
	    (_a).y = (_b).y;				
	    (_a).z = (_b).z;
	}
	public static void INT32_VECT3_COPY(NedCoor_i _a,NedCoor_i _b){
		(_a).x = (_b).x;				
		(_a).y = (_b).y;				
		(_a).z = (_b).z;
	}
	public static void INT32_VECT3_COPY(EnuCoor_i _a,EnuCoor_i _b){
		(_a).x = (_b).x;				
		(_a).y = (_b).y;				
		(_a).z = (_b).z;
	}
	public static void INT32_VECT2_COPY(Int32Vect2 _a,Int32Vect2 _b){
		(_a).x = (_b).x;				
	    (_a).y = (_b).y;				
	    //(_a).z = (_b).z;
	}
	static int INT32_RATE_FRAC=12;
	static int INT32_ANGLE_PI_4=  ANGLE_BFP_OF_REAL((float)0.7853981633974483096156608458198757);
	static int INT32_ANGLE_PI_2= ANGLE_BFP_OF_REAL(   (float)1.5707963267948966192313216916397514);
	static int INT32_ANGLE_PI =    ANGLE_BFP_OF_REAL(   (float)3.1415926535897932384626433832795029);
	static int INT32_ANGLE_2_PI=  ANGLE_BFP_OF_REAL((float)(2.*3.1415926535897932384626433832795029));
	static int INT32_ANGLE_FRAC =12;
	static int INT32_TRIG_FRAC=14;
	static int INT32_QUAT_FRAC= 15;
	static int INT32_SPEED_FRAC=19;
	static int INT32_POS_FRAC= 8;
//	#define INT32_SPEED_OF_CM_S 5242.88
//	#define INT32_SPEED_OF_CM_S_NUM 41943
//	#define INT32_SPEED_OF_CM_S_DEN 8

	static int INT32_ACCEL_FRAC=10;
	static int INT32_MAG_FRAC =11;

	public static int BFP_OF_REAL(float _vr, int _frac) {  return (int)((_vr)*(1<<(_frac)));}
	public static float FLOAT_OF_BFP( int _vbfp,int _frac){ return (float)(_vbfp)/(1<<(_frac));}
	public static int RATE_BFP_OF_REAL(float _af)   {return BFP_OF_REAL((_af), INT32_RATE_FRAC);}
	public static float RATE_FLOAT_OF_BFP(int _ai)  {return FLOAT_OF_BFP((_ai), INT32_RATE_FRAC);}
	public static int ANGLE_BFP_OF_REAL(float _af)  {return BFP_OF_REAL((_af), INT32_ANGLE_FRAC);}
	public static float ANGLE_FLOAT_OF_BFP(int _ai){return FLOAT_OF_BFP((_ai), INT32_ANGLE_FRAC);}
	public static int TRIG_BFP_OF_REAL(float _tf) { return BFP_OF_REAL((_tf), INT32_TRIG_FRAC);}
	public static float TRIG_FLOAT_OF_BFP(int _ti) {return FLOAT_OF_BFP((_ti),INT32_TRIG_FRAC);}
	
	public static int QUAT1_BFP_OF_REAL(float _qf)  {return BFP_OF_REAL((_qf), INT32_QUAT_FRAC);}
	 public static float QUAT1_FLOAT_OF_BFP(int _qi) {return FLOAT_OF_BFP((_qi), INT32_QUAT_FRAC);}
	
	public static int  POS_BFP_OF_REAL(float _af) { return   BFP_OF_REAL((_af), INT32_POS_FRAC);}
	public static float POS_FLOAT_OF_BFP(int _ai)  { return FLOAT_OF_BFP((_ai), INT32_POS_FRAC);}
	public static int  SPEED_BFP_OF_REAL(float _af) {return BFP_OF_REAL((_af), INT32_SPEED_FRAC);}
	public static float SPEED_FLOAT_OF_BFP(int _ai){return FLOAT_OF_BFP((_ai), INT32_SPEED_FRAC);}
	public static int  ACCEL_BFP_OF_REAL(float _af) { return BFP_OF_REAL((_af), INT32_ACCEL_FRAC);}
	public static float ACCEL_FLOAT_OF_BFP(int _ai) {return FLOAT_OF_BFP((_ai), INT32_ACCEL_FRAC);}
	public static int  MAG_BFP_OF_REAL(float _af)   { return BFP_OF_REAL((_af), INT32_MAG_FRAC);}
	public static float MAG_FLOAT_OF_BFP(int _ai)  {return FLOAT_OF_BFP((_ai), INT32_MAG_FRAC);}
	
	public static void  INT32_VECT3_RSHIFT(EnuCoor_i _o,EnuCoor_i _i,int _r) { 
	    (_o).x = ((_i).x >> (_r));       
	    (_o).y = ((_i).y >> (_r));       
	    (_o).z = ((_i).z >> (_r));       
	  }
	
	public static void INT_RATES_ZERO(Int32Rates e){
		RATES_ASSIGN(e, 0,0,0);
	}
	public static void INT_VECT3_ZERO(Int32Vect3 e){
		RATES_ASSIGN(e, 0,0,0);
	}
	
}
