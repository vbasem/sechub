// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter;

public class AdapterCanceledByUserException extends AdapterException {

	private static final long serialVersionUID = 8698367126373773465L;

	public AdapterCanceledByUserException(Adapter<?> adapter, TraceIdProvider provider) {
		super(adapter, provider, "was canceled by user");
	}

}
