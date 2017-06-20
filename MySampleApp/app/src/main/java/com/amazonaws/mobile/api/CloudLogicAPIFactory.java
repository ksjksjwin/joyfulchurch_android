package com.amazonaws.mobile.api;

//
//  CloudLogicAPIFactory.java
//
//
// Copyright 2017 Amazon.com, Inc. or its affiliates (Amazon). All Rights Reserved.
//
// Code generated by AWS Mobile Hub. Amazon gives unlimited permission to 
// copy, distribute and modify it.
//
// Source code generated from template: aws-my-sample-app-android v0.18
//

/**
 * Produces instances of Cloud Logic API configuration.
 */
public class CloudLogicAPIFactory {

    private CloudLogicAPIFactory() {}

    /**
     * Gets the configured micro-service instances.
     * @return
     */
    public static CloudLogicAPIConfiguration[] getAPIs() {
        final CloudLogicAPIConfiguration[] apis = new CloudLogicAPIConfiguration[] {
                new CloudLogicAPIConfiguration("createSermon",
                                              "creates sermon",
                                              "https://logqzc12cb.execute-api.us-west-1.amazonaws.com/Development",
                                              new String[] {
                                                  "/items",
                                                  "/items/123",
                                              },
                                              com.amazonaws.mobile.api.idlogqzc12cb.CreateSermonMobileHubClient.class),
                new CloudLogicAPIConfiguration("sendNotification",
                                              "Sends notification to topic",
                                              "https://n11kqznxh0.execute-api.us-west-1.amazonaws.com/beta",
                                              new String[] {
                                                  "/sendnotification",
                                                  "/",
                                                  "/registeriosdevice",
                                              },
                                              com.amazonaws.mobile.api.idn11kqznxh0.SendNotificationClient.class),
        };

        return apis;
    }
}
