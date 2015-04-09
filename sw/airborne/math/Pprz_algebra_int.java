package sw.airborne.math;

import static sw.airborne.math.Pprz_algebra.*;

public class Pprz_algebra_int {
	//public static final int INT32_POS_FRAC= 8;
	public static final double INT32_POS_OF_CM =2.56;
	public static final int INT32_POS_OF_CM_NUM =64;
	public static final int INT32_POS_OF_CM_DEN= 25;

	//public static final int INT32_SPEED_FRAC= 19;
	public static final double INT32_SPEED_OF_CM_S =5242.88;
	public static final int INT32_SPEED_OF_CM_S_NUM =41943;
	public static final int INT32_SPEED_OF_CM_S_DEN =8;

	//#define INT32_ACCEL_FRAC 10
	//#define INT32_MAG_FRAC 11

	public static final int INT32_PERCENTAGE_FRAC =10;

	
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
	public static int INT32_RATE_FRAC=12;
	public static int INT32_ANGLE_PI_4=  ANGLE_BFP_OF_REAL((float)0.7853981633974483096156608458198757);
	public static int INT32_ANGLE_PI_2= ANGLE_BFP_OF_REAL(   (float)1.5707963267948966192313216916397514);
	public static int INT32_ANGLE_PI =    ANGLE_BFP_OF_REAL(   (float)3.1415926535897932384626433832795029);
	public static int INT32_ANGLE_2_PI=  ANGLE_BFP_OF_REAL((float)(2.*3.1415926535897932384626433832795029));
	public static int INT32_ANGLE_FRAC =12;
	public static int INT32_TRIG_FRAC=14;
	public static int INT32_QUAT_FRAC= 15;
	public static int INT32_SPEED_FRAC=19;
	public static int INT32_POS_FRAC= 8;
//	#define INT32_SPEED_OF_CM_S 5242.88
//	#define INT32_SPEED_OF_CM_S_NUM 41943
//	#define INT32_SPEED_OF_CM_S_DEN 8

	public static int INT32_ACCEL_FRAC=10;
	public static int INT32_MAG_FRAC =11;

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
	public static void  INT32_VECT2_RSHIFT(Int32Vect2 _o,Int32Vect2 _i,int _r) { 
	    (_o).x = ((_i).x >> (_r));       
	    (_o).y = ((_i).y >> (_r));       
	    //(_o).z = ((_i).z >> (_r));       
	  }
	public static void INT32_COURSE_NORMALIZE(int _a) {                
	    while ((_a) < 0) (_a) += INT32_ANGLE_2_PI;                  
	    while ((_a) >= INT32_ANGLE_2_PI)  (_a) -= INT32_ANGLE_2_PI; 
	  }
	public static void INT32_ANGLE_NORMALIZE(int _a) {                
		while ((_a) > INT32_ANGLE_PI)  (_a) -= INT32_ANGLE_2_PI;    
	    while ((_a) < -INT32_ANGLE_PI) (_a) += INT32_ANGLE_2_PI;  
	  }
	public static int INT_MULT_RSHIFT(int _a, int _b,int _r) {
		return (((_a)*(_b))>>(_r));}
	
	public static void INT_RATES_ZERO(Int32Rates e){
		RATES_ASSIGN(e, 0,0,0);
	}
	public static void INT_VECT3_ZERO(Int32Vect3 e){
		RATES_ASSIGN(e, 0,0,0);
	}
	public static void INT32_QUAT_ZERO( Int32Quat _q) {                       
	    (_q).qi = QUAT1_BFP_OF_REAL(1);                 
	    (_q).qx = 0;                            
	    (_q).qy = 0;                            
	    (_q).qz = 0;}
	public static void INT_EULERS_ZERO(Int32Eulers _e){ EULERS_ASSIGN(_e, 0, 0, 0);
	}
	public static void INT32_VECT2_NORM(int n,Int32Vect2 v) {            
	    int n2 = (v).x*(v).x + (v).y*(v).y; 
	    INT32_SQRT(n, n2);                  
	  }
	public static void INT32_SQRT(int _out, int _in) {                                  
	    if ((_in) == 0)                                             
	      (_out) = 0;                                               
	    else {                                                      
	      int s1, s2;                                          
	      int iter = 0;                                         
	      s2 = _in;                                                 
	      do {                                                      
	        s1 = s2;                                                
	        s2 = (_in) / s1;                                        
	        s2 += s1;                                               
	        s2 /= 2;                                                
	        iter++;                                                 
	      }                                                         
	      while( ( (s1-s2) > 1) && (iter < 40));   
	      (_out) = s2;                                              
	    }                                                           
	  }
	public static void INT32_QUAT_WRAP_SHORTEST(Int32Quat q) {                   
	    if ((q).qi < 0)                         
	      QUAT_EXPLEMENTARY(q,q);                       
	  }
	public static void INT32_QUAT_INV_COMP(Int32Quat _b2c, Int32Quat _a2b, Int32Quat _a2c) {             
	    (_b2c).qi = ((_a2b).qi*(_a2c).qi + (_a2b).qx*(_a2c).qx + (_a2b).qy*(_a2c).qy + (_a2b).qz*(_a2c).qz)>>INT32_QUAT_FRAC; 
	    (_b2c).qx = ((_a2b).qi*(_a2c).qx - (_a2b).qx*(_a2c).qi - (_a2b).qy*(_a2c).qz + (_a2b).qz*(_a2c).qy)>>INT32_QUAT_FRAC; 
	    (_b2c).qy = ((_a2b).qi*(_a2c).qy + (_a2b).qx*(_a2c).qz - (_a2b).qy*(_a2c).qi - (_a2b).qz*(_a2c).qx)>>INT32_QUAT_FRAC; 
	    (_b2c).qz = ((_a2b).qi*(_a2c).qz - (_a2b).qx*(_a2c).qy + (_a2b).qy*(_a2c).qx - (_a2b).qz*(_a2c).qi)>>INT32_QUAT_FRAC; 
	  }
	public static void INT32_QUAT_NORMALIZE(Int32Quat q) {                   
	    int n = 0;                                      
	    INT32_QUAT_NORM(n, q);                          
	    if (n > 0) {                                    
	      (q).qi = (q).qi * QUAT1_BFP_OF_REAL(1) / n;   
	      (q).qx = (q).qx * QUAT1_BFP_OF_REAL(1) / n;   
	      (q).qy = (q).qy * QUAT1_BFP_OF_REAL(1) / n;   
	      (q).qz = (q).qz * QUAT1_BFP_OF_REAL(1) / n;  
	    }                                              
	  }
	public static void INT32_QUAT_NORM(int n, Int32Quat q) {                                 
	    int n2 = (q).qi*(q).qi + (q).qx*(q).qx + (q).qy*(q).qy + (q).qz*(q).qz; 
	    INT32_SQRT(n, n2);                          
	  }
	 public static void INT32_QUAT_COMP(Int32Quat _a2c, Int32Quat _a2b, Int32Quat _b2c) {             
		    (_a2c).qi = ((_a2b).qi*(_b2c).qi - (_a2b).qx*(_b2c).qx - (_a2b).qy*(_b2c).qy - (_a2b).qz*(_b2c).qz)>>INT32_QUAT_FRAC; 
		    (_a2c).qx = ((_a2b).qi*(_b2c).qx + (_a2b).qx*(_b2c).qi + (_a2b).qy*(_b2c).qz - (_a2b).qz*(_b2c).qy)>>INT32_QUAT_FRAC; 
		    (_a2c).qy = ((_a2b).qi*(_b2c).qy - (_a2b).qx*(_b2c).qz + (_a2b).qy*(_b2c).qi + (_a2b).qz*(_b2c).qx)>>INT32_QUAT_FRAC; 
		    (_a2c).qz = ((_a2b).qi*(_b2c).qz + (_a2b).qx*(_b2c).qy - (_a2b).qy*(_b2c).qx + (_a2b).qz*(_b2c).qi)>>INT32_QUAT_FRAC; 
		  }
	public static void  INT32_EULERS_OF_QUAT(Int32Eulers _e,Int32Quat _q) {                  
         
     final int qx2  = INT_MULT_RSHIFT((_q).qx,(_q).qx, INT32_QUAT_FRAC); 
 final int qy2  = INT_MULT_RSHIFT((_q).qy,(_q).qy, INT32_QUAT_FRAC); 
 final int qz2  = INT_MULT_RSHIFT((_q).qz,(_q).qz, INT32_QUAT_FRAC); 
  final int qiqx = INT_MULT_RSHIFT((_q).qi,(_q).qx, INT32_QUAT_FRAC); 
 final int qiqy = INT_MULT_RSHIFT((_q).qi,(_q).qy, INT32_QUAT_FRAC); 
 final int qiqz = INT_MULT_RSHIFT((_q).qi,(_q).qz, INT32_QUAT_FRAC); 
 final int qxqy = INT_MULT_RSHIFT((_q).qx,(_q).qy, INT32_QUAT_FRAC); 
 final int qxqz = INT_MULT_RSHIFT((_q).qx,(_q).qz, INT32_QUAT_FRAC); 
 final int qyqz = INT_MULT_RSHIFT((_q).qy,(_q).qz, INT32_QUAT_FRAC); 
 final int one = TRIG_BFP_OF_REAL( 1);               
 final int two = TRIG_BFP_OF_REAL( 2);   }   
	public static void INT32_VECT2_LSHIFT(Int32Vect2 _o,Int32Vect2 _i,int _l) { 
		  (_o).x = ((_i).x << (_l)); 
		  (_o).y = ((_i).y << (_l)); 
		}
	public static void INT_VECT2_ZERO(Int32Vect2 _v) {VECT2_ASSIGN(_v, 0, 0);}
}
