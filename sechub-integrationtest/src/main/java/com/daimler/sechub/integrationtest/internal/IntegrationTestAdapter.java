// SPDX-License-Identifier: MIT
package com.daimler.sechub.integrationtest.internal;

import com.daimler.sechub.adapter.Adapter;
import com.daimler.sechub.adapter.AdapterException;

public class IntegrationTestAdapter implements Adapter<IntegrationTestAdapterConfig>{


	@Override
	public String start(IntegrationTestAdapterConfig config) throws AdapterException {
		return null;
	}
	
	@Override
	public int getVersion() {
		return 666;
	}

}
