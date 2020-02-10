// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter;

/**
 * interface for SecHub adapters
 * 
 * @author Albert Tregnaghi
 *
 */
public interface Adapter<C extends AdapterConfig> {

    /**
     * Returns name of adapter. Default implementation is equivalent to getClass().getSimpleName()! 
     * <br>
     * <br>
     * Attention: This should not be overridden! Instead each adapter should have always a Unique name 
     * in implementation! E.g. `OpenVASAdapter` or `CheckmarxAdapter`.
     * @return per default simple class name
     */
    public default String getName() {
        return getClass().getSimpleName();
    }
    
	/**
	 * @return version of adapter
	 */
	public int getVersion();
	
	/**
	 * Starts and returns result 
	 * 
	 * @param config
	 * @return result
	 * @throws NessusAdapterException
	 */
	String start(C config) throws AdapterException;
}
