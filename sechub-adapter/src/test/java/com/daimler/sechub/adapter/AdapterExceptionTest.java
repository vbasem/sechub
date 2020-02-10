// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter;

import static com.daimler.sechub.adapter.AdapterException.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.mockito.Mockito.*;

public class AdapterExceptionTest {

	@Rule
	public ExpectedException expected = ExpectedException.none();
    private Adapter<?> adapter;
    private TraceIdProvider traceIdprovider;
    private static final String EXAMPLE_TRACEID = "traceID1234";
    
    @Before
    public void before() {
        adapter = mock(Adapter.class);
        
        when(adapter.getName()).thenReturn("mockAdapter");
        when(adapter.getVersion()).thenReturn(4711);
        
        traceIdprovider = mock(TraceIdProvider.class);
        when(traceIdprovider.getTraceID()).thenReturn(EXAMPLE_TRACEID);
    }
    

	@Test
	public void throwAsAdapterException_throws_adapter_exception_when_exception_is_runtime_exception()
			throws Exception {
		/* prepare */
		RuntimeException cause = new RuntimeException();
	

		/* test */
		expected.expect(AdapterException.class);
		expected.expectMessage("Adapter:mockAdapter Version:4711 failed with message:message1. TraceID:"+EXAMPLE_TRACEID);
		expected.expectCause(equalTo(cause));

		/* execute */
		throwAsAdapterException(adapter,"message1", cause, traceIdprovider);
	}
	
	@Test
	public void throwAsAdapterException_throws_adapter_exception_with_traceid_when_exception_is_runtime_exception()
			throws Exception {
		/* prepare */
		RuntimeException cause = new RuntimeException();

		/* test */
		expected.expect(AdapterException.class);
		expected.expectMessage("Adapter:mockAdapter Version:4711 failed with message:message1. TraceID:"+EXAMPLE_TRACEID);
		expected.expectCause(equalTo(cause));

		/* execute */
		throwAsAdapterException(adapter,"message1", cause, traceIdprovider);
	}

	@Test
	public void throwAsAdapterException_rethrows_adapter_exception_when_exception_is_adapter_exception()
			throws Exception {
		/* prepare */
		AdapterException cause = new AdapterException(adapter, null, "originMessage");

		/* test */
		expected.expect(AdapterException.class);
		expected.expectMessage("Adapter:mockAdapter Version:4711 failed with message:originMessage. TraceID:null");

		/* execute */
		throwAsAdapterException(adapter,"message1", cause, traceIdprovider);
	}

}
