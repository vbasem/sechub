package com.daimler.sechub.adapter;

public class AdapterUtil {

    public static AdapterCanceledByUserException asAdapterCanceledByUserException(Adapter<?> adapter, TraceIdProvider provider) {
        return new AdapterCanceledByUserException(adapter,provider);
    }

    public static AdapterException asAdapterException(Adapter<?> adapter, String message, TraceIdProvider provider) {
        return asAdapterException(adapter, message, null, provider);
    }

    public static AdapterException asAdapterException(Adapter<?> adapter, String message, Throwable t, TraceIdProvider provider) {
        return AdapterException.asAdapterException(adapter,message,t,provider);
    }

    
}
