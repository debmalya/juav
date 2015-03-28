package sw.airborne.math;

public class Pprz_algebra_double {

	public static void DOUBLE_VECT3_ROUND(DoubleVect3 _v) {
		DOUBLE_VECT3_RINT(_v, _v);
	}


	public static void DOUBLE_VECT3_RINT(DoubleVect3 _vout,DoubleVect3  _vin) {    
		(_vout).x = rint((_vin).x);         
		(_vout).y = rint((_vin).y);         
		(_vout).z = rint((_vin).z);         
	}

	public static void DOUBLE_VECT3_ASSIGN(DoubleVect3 _a,DoubleVect3 _x,DoubleVect3  _y,DoubleVect3  _z) {
		VECT3_ASSIGN(_a, _x, _y, _z);
	}

	public static void DOUBLE_VECT3_COPY(DoubleVect3 _a,DoubleVect3  _b) {
		VECT3_COPY(_a, _b);
	}

	public static void DOUBLE_VECT3_SUM(DoubleVect3 _c,DoubleVect3 _a,DoubleVect3 _b) {            
		(_c).x = (_a).x + (_b).x;           
		(_c).y = (_a).y + (_b).y;           
		(_c).z = (_a).z + (_b).z;           
	}

	public static void DOUBLE_VECT3_CROSS_PRODUCT(DoubleVect3 vo,DoubleVect3  v1,DoubleVect3  v2) {
		FLOAT_VECT3_CROSS_PRODUCT(vo, v1, v2);
	}

	public static void DOUBLE_RMAT_OF_EULERS(DoubleEulers _rm, DoubleEulers  _e) {
		DOUBLE_RMAT_OF_EULERS_321(_rm, _e)
	}

	public static void DOUBLE_RMAT_OF_EULERS_321(DoubleEulers _rm,DoubleEulers  _e) {                
		
		 double sphi   = sin((_e).phi);                
		 double cphi   = cos((_e).phi);                
		 double stheta = sin((_e).theta);              
		 double ctheta = cos((_e).theta);              
		 double spsi   = sin((_e).psi);                
		 double cpsi   = cos((_e).psi);                
		
		RMAT_ELMT(_rm, 0, 0) = ctheta*cpsi;                 
		RMAT_ELMT(_rm, 0, 1) = ctheta*spsi;                 
		RMAT_ELMT(_rm, 0, 2) = -stheta;                 
		RMAT_ELMT(_rm, 1, 0) = sphi*stheta*cpsi - cphi*spsi;        
		RMAT_ELMT(_rm, 1, 1) = sphi*stheta*spsi + cphi*cpsi;        
		RMAT_ELMT(_rm, 1, 2) = sphi*ctheta;                 
		RMAT_ELMT(_rm, 2, 0) = cphi*stheta*cpsi + sphi*spsi;        
		RMAT_ELMT(_rm, 2, 1) = cphi*stheta*spsi - sphi*cpsi;        
		RMAT_ELMT(_rm, 2, 2) = cphi*ctheta;                 
		
	}




	/* multiply _vin by _mat, store in _vout */
	public static void DOUBLE_MAT33_VECT3_MUL(DoubleVect3 _vout,DoubleMat33  _mat,DoubleVect3  _vin) {     
		(_vout).x = (_mat).m[0]*(_vin).x + (_mat).m[1]*(_vin).y + (_mat).m[2]*(_vin).z;   
		(_vout).y = (_mat).m[3]*(_vin).x + (_mat).m[4]*(_vin).y + (_mat).m[5]*(_vin).z;   
		(_vout).z = (_mat).m[6]*(_vin).x + (_mat).m[7]*(_vin).y + (_mat).m[8]*(_vin).z;   
	}

	/* multiply _vin by the transpose of _mat, store in _vout */
	public static void DOUBLE_MAT33_VECT3_TRANSP_MUL(DoubleVect3 _vout,DoubleMat33 _mat,DoubleVect3 _vin) {      
		(_vout).x = (_mat).m[0]*(_vin).x + (_mat).m[3]*(_vin).y + (_mat).m[6]*(_vin).z;   
		(_vout).y = (_mat).m[1]*(_vin).x + (_mat).m[4]*(_vin).y + (_mat).m[7]*(_vin).z;   
		(_vout).z = (_mat).m[2]*(_vin).x + (_mat).m[5]*(_vin).y + (_mat).m[8]*(_vin).z;   
	}

	public static void DOUBLE_QUAT_OF_EULERS(DoubleQuat _q,DoubleEulers _e) {                 
		
		 double phi2   = (_e).phi/ 2.0;                    
		 double theta2 = (_e).theta/2.0;               
		 double psi2   = (_e).psi/2.0;                 
		
		 double s_phi2   = sin( phi2 );                
		 double c_phi2   = cos( phi2 );                
		 double s_theta2 = sin( theta2 );              
		 double c_theta2 = cos( theta2 );              
		 double s_psi2   = sin( psi2 );                
		 double c_psi2   = cos( psi2 );                
		
		(_q).qi =  c_phi2 * c_theta2 * c_psi2 + s_phi2 * s_theta2 * s_psi2; 
		(_q).qx = -c_phi2 * s_theta2 * s_psi2 + s_phi2 * c_theta2 * c_psi2; 
		(_q).qy =  c_phi2 * s_theta2 * c_psi2 + s_phi2 * c_theta2 * s_psi2; 
		(_q).qz =  c_phi2 * c_theta2 * s_psi2 - s_phi2 * s_theta2 * c_psi2; 
		
	}

	public static void DOUBLE_EULERS_OF_QUAT(DoubleEulers _e,DoubleQuat _q) {                 
		
		 double qx2  = (_q).qx*(_q).qx;                
		 double qy2  = (_q).qy*(_q).qy;                
		 double qz2  = (_q).qz*(_q).qz;                
		 double qiqx = (_q).qi*(_q).qx;                
		 double qiqy = (_q).qi*(_q).qy;                
		 double qiqz = (_q).qi*(_q).qz;                
		 double qxqy = (_q).qx*(_q).qy;                
		 double qxqz = (_q).qx*(_q).qz;                
		 double qyqz = (_q).qy*(_q).qz;                
		 double dcm00 = 1.0 - 2.*(  qy2 +  qz2 );          
		 double dcm01 =       2.*( qxqy + qiqz );          
		 double dcm02 =       2.*( qxqz - qiqy );          
		 double dcm12 =       2.*( qyqz + qiqx );          
		 double dcm22 = 1.0 - 2.*(  qx2 +  qy2 );          
		
		(_e).phi = atan2( dcm12, dcm22 );                   
		(_e).theta = -asin( dcm02 );                    
		(_e).psi = atan2( dcm01, dcm00 );                   
		
	}
}
