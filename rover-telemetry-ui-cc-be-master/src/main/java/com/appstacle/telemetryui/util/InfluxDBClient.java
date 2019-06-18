/*
 * ******************************************************************************
* Copyright (c) 2017, 2019 Bosch Software Innovations GmbH.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/index.php
 *
 *  Contributors:
 *      Johannes Kristan (Bosch Software Innovations GmbH) - initial API and functionality
 *      Leon Graser (Bosch Software Innovations GmbH)
 * *****************************************************************************
 */
package com.appstacle.telemetryui.util;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

@Component
public class InfluxDBClient {

	/* standard logger for logging information and errors */
	private static final Logger LOGGER = LoggerFactory.getLogger(InfluxDBClient.class);

	/* influxDB connection wrapper */
	private final InfluxDB influxDB;

	private String dbName;

	/**
	 * Creates a new connector to an influxDB database to write forwarded messages
	 * to.
	 *
	 * @param influxURL url of the influxDB deployment
	 * @param dbName    name of the database
	 * @throws MalformedURLException throws exception if the given url is in the
	 *                               wrong format
	 */
	public InfluxDBClient(@Value("${influxdb.url}") final String influxURL, @Value("${influxdb.db.name}") String dbName)
			throws MalformedURLException {
		this.dbName = dbName;

		// check the given url string
		URL url = new URL(influxURL);
		influxDB = InfluxDBFactory.connect(url.toString());

		LOGGER.info("will connect to InfluxDB server at {} and database {} ", influxURL, dbName);
	}

	public QueryResult getLatestTelemetryData(String honoDeviceId) {
		Query query = new Query("SELECT * FROM " + honoDeviceId + " ORDER BY time desc limit 1", dbName);
		return influxDB.query(query);
	}
}
