// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter.netsparker;

import org.springframework.http.HttpStatus;

/* FIXME Albert Tregnaghi, 2018-03-27: remove this by common exception */
public class NetsparkerRESTFailureException extends RuntimeException {

	private static final long serialVersionUID = 6448794893494468643L;
	private final String body;

	public NetsparkerRESTFailureException(HttpStatus status, String body) {
		super("Netsparker REST failed with HTTP Status:" + (status != null ? status.name() : "null"));
		this.body = body;
	}

	public String getResponseBody() {
		return body;
	}

	@Override
	public String toString() {
		return super.toString() + "\nBody:\n" + getResponseBody();
	}
}
