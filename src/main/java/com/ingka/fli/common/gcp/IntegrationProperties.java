package com.ingka.fli.common.gcp;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public class IntegrationProperties{
    /**
     * The resource name
     */
    String resource;

    /**
     * The message name
     */
    private final String messageName;

    /**
     * The adapter name
     */
    private final String adapterName;

    /**
     * The message version
     */
    private final String messageVersion;

    /**
     * The target system
     */
    private final String targetSystem;

    /**
     * The retry limit
     */
    String retryLimit;

    /**
     * The sending system
     */
    String sendingSystem;
}

