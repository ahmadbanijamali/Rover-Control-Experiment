/*******************************************************************************
 * Copyright (c) 2019 Dortmund University of Applied Sciences and Arts and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Dortmund University of Applied Sciences and Arts - initial API and implementation
 *******************************************************************************/
package com.appstacle.telemetryui.service;

import java.util.List;

import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.appstacle.telemetryui.dto.RoverDTO;
import com.appstacle.telemetryui.util.InfluxDBClient;
import org.springframework.web.bind.annotation.PathVariable;

@Component
public class TelemetryService {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private InfluxDBClient influxDB;

	private final String deviceIDs[];

	public TelemetryService(@Value("${hono.device.ids}") final String deviceIDs[]) {
		this.deviceIDs = deviceIDs;
	}

	@Scheduled(fixedDelay = 1000)
	public void publishUpdates() {

		for (String deviceID : deviceIDs) {
			RoverDTO telemetryData = getTelemetryData(deviceID);

			if (telemetryData != null) {
				this.template.convertAndSend("/topic/" + deviceID + "/telemetry", telemetryData);
			}
		}
	}

	public RoverDTO getTelemetryData(final String honoDeviceId) {
		/* Obtain the last telemetry data set for the given device */
		final QueryResult result = influxDB.getLatestTelemetryData(honoDeviceId);

		if (result.getResults().get(0).getSeries() != null) {
			InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
			List<RoverDTO> roverDTOs = resultMapper.toPOJO(result, RoverDTO.class);
			for (RoverDTO roverDTO : roverDTOs) {
				return roverDTO;
			}
		}

		return null;
	}
}