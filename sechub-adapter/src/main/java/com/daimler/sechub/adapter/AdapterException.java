// SPDX-License-Identifier: MIT
package com.daimler.sechub.adapter;

public class AdapterException extends Exception {

    private static final long serialVersionUID = -2234915005975876283L;

    public AdapterException(Adapter<?> adapter, TraceIdProvider provider, String message) {
        this(adapter, null, message, null);
    }

    public AdapterException(Adapter<?> adapter, TraceIdProvider provider, String message, Throwable cause) {
        super(createDescription(adapter, provider, message), cause);
    }
    

    static String createDescription(Adapter<?> adapter, TraceIdProvider provider, String message) {
        StringBuilder sb = new StringBuilder();
        sb.append("Adapter:");
        sb.append(adapter.getName());
        sb.append(" Version:");
        sb.append(adapter.getVersion());
        sb.append(" failed");
        if (message != null) {
            sb.append(" with message:").append(message);
        }
        sb.append(". TraceID:");
        if (provider != null) {
            sb.append(provider.getTraceID());
        } else {
            sb.append("null");
        }
        return sb.toString();
    }

    /**
     * Throws given exception as adapter exception. If the exception is already an
     * adapter exception the origin exception will be thrown (the given message and
     * adapter idwill be ignored then)
     * 
     * @param message
     * @param t
     * @throws AdapterException
     */
    public static void throwAsAdapterException(Adapter<?> adapter, String message, Throwable t, TraceIdProvider provider) throws AdapterException {
        throw asAdapterException(adapter, message, t, provider);
    }

    /**
     * Returns an exception for given one. If the exception is already an adapter
     * exception the origin exception will be thrown (the given message and adapter
     * will be ignored then)
     * 
     * @param id
     * @param message
     * @param t
     * @return
     * @throws AdapterException
     */
    public static AdapterException asAdapterException(Adapter<?> adapter, String message, Throwable t, TraceIdProvider provider) {
        if (t instanceof AdapterException) {
            return (AdapterException) t;
        } else {
            return new AdapterException(adapter, provider, message, t);
        }
    }

}
