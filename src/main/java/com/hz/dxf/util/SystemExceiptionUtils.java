package com.hz.dxf.util;

/**
 * @Desscription : 
 * @author Blossom
 * @time 2016年12月21日 下午2:27:10
 */
public class SystemExceiptionUtils extends Throwable {
	

	private static final long serialVersionUID = -5190215988467280659L;
	
	public static final String SYSTEM_INCOMPLETE_PARAMETER_ERROR = "参数不全!";

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月21日 下午2:28:32
	 */
	public SystemExceiptionUtils() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月21日 下午2:28:32
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public SystemExceiptionUtils(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月21日 下午2:28:32
	 * @param message
	 * @param cause
	 */
	public SystemExceiptionUtils(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月21日 下午2:28:32
	 * @param message
	 */
	public SystemExceiptionUtils(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @Description : 
	 * @author Blossom
	 * @time 2016年12月21日 下午2:28:32
	 * @param cause
	 */
	public SystemExceiptionUtils(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	

}
