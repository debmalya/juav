package sw.airborne.math;

import static sw.airborne.math.Pprz_algebra.*;
import static sw.airborne.math.Pprz_algebra_int.INT32_TRIG_FRAC;
import static sw.airborne.math.Pprz_trig_int.*;

public class Pprz_algebra_int {
	public static int INT_MULT_RSHIFT(int _a,int _b,int _r) {
		return (((_a)*(_b))>>(_r));
	}
	
	public static int INT32_VECT2_NORM(int n, Int32Vect2 v){
	    int n2 = (v).x*(v).x + (v).y*(v).y; 
	    //INT32_SQRT(n, n2);   
	    if ((n2) == 0)                                             
	      (n) = 0;                                               
	    else {                                                      
	      int s1, s2;                                          
	      int iter = 0;                                         
	      s2 = n2;                                                 
	      do {                                                      
	        s1 = s2;                                                
	        s2 = (n2) / s1;                                        
	        s2 += s1;                                               
	        s2 /= 2;                                                
	        iter++;                                                 
	      }                                                         
	      while( ( (s1-s2) > 1) && (iter < INT32_SQRT_MAX_ITER));   
	      (n) = s2;                                              
	    }      
	    return n;
	}
	
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
	public static int INT32_PERCENTAGE_FRAC = 10;
	public static int INT32_ACCEL_FRAC=10;
	public static int INT32_MAG_FRAC =11;

	public static int INT32_RAD_OF_DEG(double _deg){
		return (int)(((long)(_deg) * 14964008)/857374503);
	}	
	
	public static int BFP_OF_REAL(float _vr, int _frac) {  return (int)((_vr)*(1<<(_frac)));}
	public static int BFP_OF_REAL(double _vr, int _frac) {  return (int)((_vr)*(1<<(_frac)));}
	public static float FLOAT_OF_BFP( int _vbfp,int _frac){ return (float)(_vbfp)/(1<<(_frac));}
	public static float FLOAT_OF_BFP( float _vbfp,int _frac){ return (float)(_vbfp)/(1<<(_frac));}
	public static int RATE_BFP_OF_REAL(float _af)   {return BFP_OF_REAL((_af), INT32_RATE_FRAC);}
	public static float RATE_FLOAT_OF_BFP(int _ai)  {return FLOAT_OF_BFP((_ai), INT32_RATE_FRAC);}
	public static float RATE_FLOAT_OF_BFP(float _ai)  {return FLOAT_OF_BFP((_ai), INT32_RATE_FRAC);}
	public static int ANGLE_BFP_OF_REAL(float _af)  {return BFP_OF_REAL((_af), INT32_ANGLE_FRAC);}
	public static int ANGLE_BFP_OF_REAL(double _af)  {return BFP_OF_REAL((_af), INT32_ANGLE_FRAC);}
	public static float ANGLE_FLOAT_OF_BFP(int _ai){return FLOAT_OF_BFP((_ai), INT32_ANGLE_FRAC);}
	public static int TRIG_BFP_OF_REAL(float _tf) { return BFP_OF_REAL((_tf), INT32_TRIG_FRAC);}
	public static int TRIG_BFP_OF_REAL(int _tf) { return BFP_OF_REAL((_tf), INT32_TRIG_FRAC);}
	public static int TRIG_BFP_OF_REAL(double _tf) { return BFP_OF_REAL((_tf), INT32_TRIG_FRAC);}
	public static float TRIG_FLOAT_OF_BFP(int _ti) {return FLOAT_OF_BFP((_ti),INT32_TRIG_FRAC);}
	
	public static int QUAT1_BFP_OF_REAL(float _qf)  {return BFP_OF_REAL((_qf), INT32_QUAT_FRAC);}
	 public static float QUAT1_FLOAT_OF_BFP(int _qi) {return FLOAT_OF_BFP((_qi), INT32_QUAT_FRAC);}
	
	public static int  POS_BFP_OF_REAL(float _af) { return   BFP_OF_REAL((_af), INT32_POS_FRAC);}
	public static int  POS_BFP_OF_REAL(double _af) { return   BFP_OF_REAL((_af), INT32_POS_FRAC);}
	public static float POS_FLOAT_OF_BFP(int _ai)  { return FLOAT_OF_BFP((_ai), INT32_POS_FRAC);}
	public static float POS_FLOAT_OF_BFP(long _ai)  { return FLOAT_OF_BFP((_ai), INT32_POS_FRAC);}
	public static int  SPEED_BFP_OF_REAL(float _af) {return BFP_OF_REAL((_af), INT32_SPEED_FRAC);}
	public static int  SPEED_BFP_OF_REAL(double _af) {return BFP_OF_REAL((_af), INT32_SPEED_FRAC);}
	public static float SPEED_FLOAT_OF_BFP(int _ai){return FLOAT_OF_BFP((_ai), INT32_SPEED_FRAC);}
	public static int  ACCEL_BFP_OF_REAL(float _af) { return BFP_OF_REAL((_af), INT32_ACCEL_FRAC);}
	public static int  ACCEL_BFP_OF_REAL(double _af) { return BFP_OF_REAL((_af), INT32_ACCEL_FRAC);}
	public static float ACCEL_FLOAT_OF_BFP(int _ai) {return FLOAT_OF_BFP((_ai), INT32_ACCEL_FRAC);}
	public static float ACCEL_FLOAT_OF_BFP(long _ai) {return FLOAT_OF_BFP((_ai), INT32_ACCEL_FRAC);}
	
	public static int  MAG_BFP_OF_REAL(float _af)   { return BFP_OF_REAL((_af), INT32_MAG_FRAC);}
	public static float MAG_FLOAT_OF_BFP(int _ai)  {return FLOAT_OF_BFP((_ai), INT32_MAG_FRAC);}
	
	
	public static int R_FRAC = 14;
	public static int INT32_SQRT_MAX_ITER = 40;
	
	public static void INT_RATES_LSHIFT(Int32Rates _o,Int32Rates _i,int _r) {   
	    (_o).p = ((_i).p << (_r));       
	    (_o).q = ((_i).q << (_r));       
	    (_o).r = ((_i).r << (_r));       
	  }

	
	public static void INT_EULERS_ZERO(Int32Eulers _e) {
		EULERS_ASSIGN(_e, 0, 0, 0);
	}
	
	public static void INT_VECT2_ZERO(Int32Vect2 _v){
		VECT2_ASSIGN(_v, 0, 0);
	}
	public static void INT32_VECT2_RSHIFT(Int32Vect2 _o,Int32Vect2 _i,int _r) { 
		(_o).x = ((_i).x >> (_r)); 
		(_o).y = ((_i).y >> (_r)); 
	}
	public static void INT32_VECT2_RSHIFT(Int32Vect2 _o,Int64Vect2 _i,int _r) { 
		(_o).x = ((_i).x >> (_r)); 
		(_o).y = ((_i).y >> (_r)); 
	}

	public static void INT32_VECT2_LSHIFT(Int32Vect2 _o,Int32Vect2 _i,int _l) { 
		(_o).x = ((_i).x << (_l)); 
		(_o).y = ((_i).y << (_l)); 
}
	public static void  INT32_VECT3_RSHIFT(EnuCoor_i _o,EnuCoor_i _i,int _r) { 
	    (_o).x = ((_i).x >> (_r));       
	    (_o).y = ((_i).y >> (_r));       
	    (_o).z = ((_i).z >> (_r));       
	  }
	public static void  INT32_VECT3_RSHIFT(Int32Vect3 _o,Int32Vect3 _i,int _r) { 
		(_o).x = ((_i).x >> (_r));       
		(_o).y = ((_i).y >> (_r));       
		(_o).z = ((_i).z >> (_r));       
	}
	public static void INT32_COURSE_NORMALIZE(int _a) {                
	    while ((_a) < 0) (_a) += INT32_ANGLE_2_PI;                  
	    while ((_a) >= INT32_ANGLE_2_PI)  (_a) -= INT32_ANGLE_2_PI; 
	  }
	public static void INT32_ANGLE_NORMALIZE(int _a) {                
		while ((_a) > INT32_ANGLE_PI)  (_a) -= INT32_ANGLE_2_PI;    
	    while ((_a) < -INT32_ANGLE_PI) (_a) += INT32_ANGLE_2_PI;  
	  }
	
	public static void INT_RATES_ZERO(Int32Rates e){
		RATES_ASSIGN(e, 0,0,0);
	}
	public static void INT_VECT3_ZERO(Int32Vect3 e){
		RATES_ASSIGN(e, 0,0,0);
	}
	
	public static void VECT3_ENU_OF_NED(EnuCoor_f _o, NedCoor_f _i){
		(_o).x = (_i).y;                    
	    (_o).y = (_i).x;                    
	    (_o).z = -(_i).z;   
	}
	public static void VECT3_ENU_OF_NED(EnuCoor_i _o, NedCoor_i _i){
		(_o).x = (_i).y;                    
		(_o).y = (_i).x;                    
		(_o).z = -(_i).z;   
	}
	public static void VECT3_ENU_OF_NED(NedCoor_i _o, EnuCoor_i _i){
		(_o).x = (_i).y;                    
		(_o).y = (_i).x;                    
		(_o).z = -(_i).z;   
	}
	public static void VECT3_ENU_OF_NED(NedCoor_f _o, EnuCoor_f _i){
		(_o).x = (_i).y;                    
		(_o).y = (_i).x;                    
		(_o).z = -(_i).z;   
	}
	
	public static void INT32_VECT3_LSHIFT(EnuCoor_i _o,EnuCoor_i _i,int _l) { 
	    (_o).x = ((_i).x << (_l));       
	    (_o).y = ((_i).y << (_l));       
	    (_o).z = ((_i).z << (_l));       
	  }

	public static void INT32_EULERS_OF_RMAT(Int32Eulers _e,Int32RMat _rm) {                 

		float dcm00 = TRIG_FLOAT_OF_BFP((_rm).m[0]);          
		float dcm01 = TRIG_FLOAT_OF_BFP((_rm).m[1]);          
		float dcm02 = TRIG_FLOAT_OF_BFP((_rm).m[2]);          
		float dcm12 = TRIG_FLOAT_OF_BFP((_rm).m[5]);          
		float dcm22 = TRIG_FLOAT_OF_BFP((_rm).m[8]);          
		float phi   =(float) Math.atan2( dcm12, dcm22 );             
		float theta =(float) -Math.asin( dcm02 );                
		float psi   =(float) Math.atan2( dcm01, dcm00 );             
		(_e).phi   = ANGLE_BFP_OF_REAL(phi);                
		(_e).theta = ANGLE_BFP_OF_REAL(theta);              
		(_e).psi   = ANGLE_BFP_OF_REAL(psi);                

	}

	public static void INT32_EULERS_OF_QUAT(Int32Eulers _e,Int32Quat _q) {                  

		int qx2  = INT_MULT_RSHIFT((_q).qx,(_q).qx, INT32_QUAT_FRAC); 
		int qy2  = INT_MULT_RSHIFT((_q).qy,(_q).qy, INT32_QUAT_FRAC); 
		int qz2  = INT_MULT_RSHIFT((_q).qz,(_q).qz, INT32_QUAT_FRAC); 
		int qiqx = INT_MULT_RSHIFT((_q).qi,(_q).qx, INT32_QUAT_FRAC); 
		int qiqy = INT_MULT_RSHIFT((_q).qi,(_q).qy, INT32_QUAT_FRAC); 
		int qiqz = INT_MULT_RSHIFT((_q).qi,(_q).qz, INT32_QUAT_FRAC); 
		int qxqy = INT_MULT_RSHIFT((_q).qx,(_q).qy, INT32_QUAT_FRAC); 
		int qxqz = INT_MULT_RSHIFT((_q).qx,(_q).qz, INT32_QUAT_FRAC); 
		int qyqz = INT_MULT_RSHIFT((_q).qy,(_q).qz, INT32_QUAT_FRAC); 
		int one = TRIG_BFP_OF_REAL( 1);               
		int two = TRIG_BFP_OF_REAL( 2);               

		/* dcm00 = 1.0 - 2.*(  qy2 +  qz2 ); */             
		int idcm00 =  one - INT_MULT_RSHIFT( two, (qy2+qz2),  
				INT32_TRIG_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC); 
		/* dcm01 =       2.*( qxqy + qiqz ); */             
		int idcm01 = INT_MULT_RSHIFT( two, (qxqy+qiqz),       
				INT32_TRIG_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC); 
		/* dcm02 =       2.*( qxqz - qiqy ); */             
		int idcm02 = INT_MULT_RSHIFT( two, (qxqz-qiqy),       
				INT32_TRIG_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC); 
		/* dcm12 =       2.*( qyqz + qiqx ); */             
		int idcm12 = INT_MULT_RSHIFT( two, (qyqz+qiqx),       
				INT32_TRIG_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC); 
		/* dcm22 = 1.0 - 2.*(  qx2 +  qy2 ); */             
		int idcm22 = one - INT_MULT_RSHIFT( two, (qx2+qy2),   
				INT32_TRIG_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC); 
		float dcm00 = (float)idcm00/(1<<INT32_TRIG_FRAC);     
		float dcm01 = (float)idcm01/(1<<INT32_TRIG_FRAC);     
		float dcm02 = (float)idcm02/(1<<INT32_TRIG_FRAC);     
		float dcm12 = (float)idcm12/(1<<INT32_TRIG_FRAC);     
		float dcm22 = (float)idcm22/(1<<INT32_TRIG_FRAC);     

		float phi   =(float) Math.atan2( dcm12, dcm22 );             
		float theta =(float) -Math.asin( dcm02 );                
		float psi   = (float) Math.atan2( dcm01, dcm00 );             
		(_e).phi   = ANGLE_BFP_OF_REAL(phi);                
		(_e).theta = ANGLE_BFP_OF_REAL(theta);              
		(_e).psi   = ANGLE_BFP_OF_REAL(psi);                

	}
	
	public static void INT32_RMAT_OF_QUAT(Int32RMat _rm,Int32Quat _q) {                                   
	     int _2qi2_m1  = INT_MULT_RSHIFT((_q).qi,(_q).qi, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1)-TRIG_BFP_OF_REAL( 1); 
	    (_rm).m[0]      = INT_MULT_RSHIFT((_q).qx,(_q).qx, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	    (_rm).m[4]      = INT_MULT_RSHIFT((_q).qy,(_q).qy, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	    (_rm).m[8]      = INT_MULT_RSHIFT((_q).qz,(_q).qz, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	                                                            
	     int _2qiqx   = INT_MULT_RSHIFT((_q).qi,(_q).qx, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);   
	     int _2qiqy   = INT_MULT_RSHIFT((_q).qi,(_q).qy, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);   
	     int _2qiqz   = INT_MULT_RSHIFT((_q).qi,(_q).qz, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);   
	    (_rm).m[1]      = INT_MULT_RSHIFT((_q).qx,(_q).qy, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	    (_rm).m[2]      = INT_MULT_RSHIFT((_q).qx,(_q).qz, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	    (_rm).m[5]      = INT_MULT_RSHIFT((_q).qy,(_q).qz, INT32_QUAT_FRAC+INT32_QUAT_FRAC-INT32_TRIG_FRAC-1);  
	                            
	    (_rm).m[0] += _2qi2_m1;             
	    (_rm).m[3] = (_rm).m[1]-_2qiqz;         
	    (_rm).m[6] = (_rm).m[2]+_2qiqy;         
	    (_rm).m[7] = (_rm).m[5]-_2qiqx;         
	    (_rm).m[4] += _2qi2_m1;             
	    (_rm).m[1] += _2qiqz;               
	    (_rm).m[2] -= _2qiqy;               
	    (_rm).m[5] += _2qiqx;               
	    (_rm).m[8] += _2qi2_m1;             
	  }
	
	public static void INT32_RMAT_OF_EULERS(Int32RMat _rm,Int32Eulers _e){
		INT32_RMAT_OF_EULERS_321(_rm, _e);
	}

	public static void INT32_RMAT_OF_EULERS_321(Int32RMat _rm,Int32Eulers _e) {             
	                                    
	    int sphi;                           
	    sphi =  PPRZ_ITRIG_SIN((_e).phi);                 
	    int cphi;                           
	    cphi = PPRZ_ITRIG_COS((_e).phi);                 
	    int stheta;                         
	    stheta = PPRZ_ITRIG_SIN((_e).theta);                 
	    int ctheta;                         
	    ctheta = PPRZ_ITRIG_COS((_e).theta);                 
	    int spsi;                           
	    spsi = PPRZ_ITRIG_SIN((_e).psi);                 
	    int cpsi;                           
	    cpsi = PPRZ_ITRIG_COS((_e).psi);                 
	                                        
	    int ctheta_cpsi = INT_MULT_RSHIFT(ctheta, cpsi,   INT32_TRIG_FRAC); 
	    int ctheta_spsi = INT_MULT_RSHIFT(ctheta, spsi,   INT32_TRIG_FRAC); 
	    int cphi_spsi   = INT_MULT_RSHIFT(cphi,   spsi,   INT32_TRIG_FRAC); 
	    int cphi_cpsi   = INT_MULT_RSHIFT(cphi,   cpsi,   INT32_TRIG_FRAC); 
	    int cphi_ctheta = INT_MULT_RSHIFT(cphi,   ctheta, INT32_TRIG_FRAC); 
	    int cphi_stheta = INT_MULT_RSHIFT(cphi,   stheta, INT32_TRIG_FRAC); 
	    int sphi_ctheta = INT_MULT_RSHIFT(sphi,   ctheta, INT32_TRIG_FRAC); 
	    int sphi_stheta = INT_MULT_RSHIFT(sphi,   stheta, INT32_TRIG_FRAC); 
	    int sphi_spsi   = INT_MULT_RSHIFT(sphi,   spsi,   INT32_TRIG_FRAC); 
	    int sphi_cpsi   = INT_MULT_RSHIFT(sphi,   cpsi,   INT32_TRIG_FRAC); 
	                                        
	    int sphi_stheta_cpsi = INT_MULT_RSHIFT(sphi_stheta, cpsi, INT32_TRIG_FRAC); 
	    int sphi_stheta_spsi = INT_MULT_RSHIFT(sphi_stheta, spsi, INT32_TRIG_FRAC); 
	    int cphi_stheta_cpsi = INT_MULT_RSHIFT(cphi_stheta, cpsi, INT32_TRIG_FRAC); 
	    int cphi_stheta_spsi = INT_MULT_RSHIFT(cphi_stheta, spsi, INT32_TRIG_FRAC); 
	                                        
	    (_rm).m[0] = ctheta_cpsi;                 
	    (_rm).m[1] = ctheta_spsi;                 
	    (_rm).m[2] = -stheta;                 
	    (_rm).m[3] = sphi_stheta_cpsi - cphi_spsi;        
	    (_rm).m[4] = sphi_stheta_spsi + cphi_cpsi;        
	    (_rm).m[5]= sphi_ctheta;                 
	    (_rm).m[6] = cphi_stheta_cpsi + sphi_spsi;        
	    (_rm).m[7] = cphi_stheta_spsi - sphi_cpsi;        
	    (_rm).m[8] = cphi_ctheta;                 
	                                        
	  }
	
	public static void INT32_QUAT_OF_RMAT(Int32Quat _q,Int32RMat _r) {                                    
	     int tr = RMAT_TRACE(_r);                                  
	    if (tr > 0) {                                                       
	       int two_qi_two = TRIG_BFP_OF_REAL(1.) + tr;             
	      int two_qi;                                                   
	      //INT32_SQRT(two_qi, (two_qi_two<<INT32_TRIG_FRAC));
	      int _in = (two_qi_two<<INT32_TRIG_FRAC),_out;
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
	      while( ( (s1-s2) > 1) && (iter < INT32_SQRT_MAX_ITER));   
	      (_out) = s2;                                              
	    }        
	      two_qi = _out;
	      two_qi = two_qi << (INT32_QUAT_FRAC - INT32_TRIG_FRAC);           
	      (_q).qi = two_qi / 2;                                             
	      (_q).qx = ((_r.m[5] - _r.m[7]) <<         
	                 (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	        / two_qi;                                                       
	      (_q).qy = ((_r.m[6] - _r.m[2]) <<         
	                 (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	        / two_qi;                                                       
	      (_q).qz = ((_r.m[1] - _r.m[3]) <<         
	                 (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	        / two_qi;                                                       
	    }                                                                   
	    else {                                                              
	      if (_r.m[0] > _r.m[4] &&                  
	          _r.m[0] > _r.m[8]) {                  
	         int two_qx_two = _r.m[0] - _r.m[4] 
	          - _r.m[8] + TRIG_BFP_OF_REAL(1.);                 
	        int two_qx;                                                 
	        //INT32_SQRT(two_qx, (two_qx_two<<INT32_TRIG_FRAC));              
	        int _in = (two_qx_two<<INT32_TRIG_FRAC),_out;
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
		      while( ( (s1-s2) > 1) && (iter < INT32_SQRT_MAX_ITER));   
		      (_out) = s2;                                              
		    }        
		      two_qx = _out;
	        //------------------
	        two_qx = two_qx << (INT32_QUAT_FRAC - INT32_TRIG_FRAC);         
	        (_q).qi = ((_r.m[5] - _r.m[7]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qx;                                                     
	        (_q).qx = two_qx / 2;                                           
	        (_q).qy = ((_r.m[1] + _r.m[3]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qx;                                                     
	        (_q).qz = ((_r.m[6] + _r.m[2]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qx;                                                     
	      }                                                                 
	      else if (_r.m[4] > _r.m[8]) {             
	         int two_qy_two = _r.m[4] - _r.m[0] 
	          - _r.m[8] + TRIG_BFP_OF_REAL(1.);                 
	        int two_qy;                                                 
	        //INT32_SQRT(two_qy, (two_qy_two<<INT32_TRIG_FRAC));              
	        int _in = (two_qy_two<<INT32_TRIG_FRAC),_out;
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
		      while( ( (s1-s2) > 1) && (iter < INT32_SQRT_MAX_ITER));   
		      (_out) = s2;                                              
		    }        
		      two_qy = _out;
	        
	        
	        
	        two_qy = two_qy << (INT32_QUAT_FRAC - INT32_TRIG_FRAC);         
	        (_q).qi = ((_r.m[6] - _r.m[2]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qy;                                                     
	        (_q).qx = ((_r.m[1] + _r.m[3]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qy;                                                     
	        (_q).qy = two_qy / 2;                                           
	        (_q).qz = ((_r.m[5] + _r.m[7]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qy;                                                     
	      }                                                                 
	      else {                                                            
	         int two_qz_two = _r.m[8] - _r.m[0] 
	          - _r.m[4] + TRIG_BFP_OF_REAL(1.);                 
	        int two_qz;                                                 
	        //INT32_SQRT(two_qz, (two_qz_two<<INT32_TRIG_FRAC));              
	        int _in = (two_qz_two<<INT32_TRIG_FRAC),_out;
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
		      while( ( (s1-s2) > 1) && (iter < INT32_SQRT_MAX_ITER));   
		      (_out) = s2;                                              
		    }        
		      two_qz = _out;
	        
	        two_qz = two_qz << (INT32_QUAT_FRAC - INT32_TRIG_FRAC);         
	        (_q).qi = ((_r.m[1] - _r.m[3]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qz;                                                     
	        (_q).qx = ((_r.m[6] + _r.m[2]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qz;                                                     
	        (_q).qy = ((_r.m[5] + _r.m[7]) <<       
	                   (INT32_QUAT_FRAC - INT32_TRIG_FRAC + INT32_QUAT_FRAC - 1)) 
	          / two_qz;                                                     
	        (_q).qz = two_qz / 2;                                           
	      }                                                                 
	    }                                                                   
	  }
	
	public static void INT32_QUAT_OF_EULERS(Int32Quat _q,Int32Eulers _e) {                  
	     int phi2   = (_e).phi   / 2;              
	     int theta2 = (_e).theta / 2;              
	     int psi2   = (_e).psi   / 2;              
	                                        
	    int s_phi2;                         
	    s_phi2 = PPRZ_ITRIG_SIN(phi2);                   
	    int c_phi2;                         
	    c_phi2 =  PPRZ_ITRIG_COS(phi2);                   
	    int s_theta2;                           
	    s_theta2 =  PPRZ_ITRIG_SIN(theta2);                   
	    int c_theta2;                           
	    c_theta2 = PPRZ_ITRIG_COS( theta2);                   
	    int s_psi2;                         
	    s_psi2 = PPRZ_ITRIG_SIN( psi2);                   
	    int c_psi2;                         
	    c_psi2 = PPRZ_ITRIG_COS(psi2);                   
	                                    
	    int c_th_c_ps = INT_MULT_RSHIFT(c_theta2, c_psi2, INT32_TRIG_FRAC); 
	    int c_th_s_ps = INT_MULT_RSHIFT(c_theta2, s_psi2, INT32_TRIG_FRAC); 
	    int s_th_s_ps = INT_MULT_RSHIFT(s_theta2, s_psi2, INT32_TRIG_FRAC); 
	    int s_th_c_ps = INT_MULT_RSHIFT(s_theta2, c_psi2, INT32_TRIG_FRAC); 
	                                    
	    (_q).qi = INT_MULT_RSHIFT( c_phi2, c_th_c_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC) + 
	              INT_MULT_RSHIFT( s_phi2, s_th_s_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC);  
	    (_q).qx = INT_MULT_RSHIFT(-c_phi2, s_th_s_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC) + 
	              INT_MULT_RSHIFT( s_phi2, c_th_c_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC);  
	    (_q).qy = INT_MULT_RSHIFT( c_phi2, s_th_c_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC) + 
	              INT_MULT_RSHIFT( s_phi2, c_th_s_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC);  
	    (_q).qz = INT_MULT_RSHIFT( c_phi2, c_th_s_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC) + 
	              INT_MULT_RSHIFT(-s_phi2, s_th_c_ps, INT32_TRIG_FRAC + INT32_TRIG_FRAC - INT32_QUAT_FRAC);  
	  }
	
	public static void INT32_RMAT_TRANSP_RATEMULT(Int32Rates _vb, Int32RMat _m_b2a, Int32Rates _va){
		(_vb).p = ( (_m_b2a).m[0]*(_va).p + (_m_b2a).m[3]*(_va).q + (_m_b2a).m[6]*(_va).r)>>INT32_TRIG_FRAC; 
	    (_vb).q = ( (_m_b2a).m[1]*(_va).p + (_m_b2a).m[4]*(_va).q + (_m_b2a).m[7]*(_va).r)>>INT32_TRIG_FRAC; 
	    (_vb).r = ( (_m_b2a).m[2]*(_va).p + (_m_b2a).m[5]*(_va).q + (_m_b2a).m[8]*(_va).r)>>INT32_TRIG_FRAC; 
	  
		
	}
	
	public static void INT32_RMAT_TRANSP_VMULT(Int32Vect3 _vb, Int32RMat _m_b2a, Int32Vect3 _va){
		(_vb).x = ( (_m_b2a).m[0]*(_va).x + (_m_b2a).m[3]*(_va).y + (_m_b2a).m[6]*(_va).z)>>INT32_TRIG_FRAC; 
	    (_vb).y = ( (_m_b2a).m[1]*(_va).x + (_m_b2a).m[4]*(_va).y + (_m_b2a).m[7]*(_va).z)>>INT32_TRIG_FRAC; 
	    (_vb).z = ( (_m_b2a).m[2]*(_va).x + (_m_b2a).m[5]*(_va).y + (_m_b2a).m[8]*(_va).z)>>INT32_TRIG_FRAC; 
		
	}
}
