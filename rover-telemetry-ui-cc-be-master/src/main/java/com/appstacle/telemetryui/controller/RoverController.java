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
package com.appstacle.telemetryui.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.appstacle.telemetryui.dto.CommandDTO;
import com.appstacle.telemetryui.dto.RoverDTO;
import com.appstacle.telemetryui.service.CommandControlService;
import com.appstacle.telemetryui.service.TelemetryService;

@RestController
@RequestMapping(path = "/rover")
public class RoverController {

	private static final Logger log = LoggerFactory.getLogger(RoverController.class);

	@Autowired
	private CommandControlService commandService;

	@Autowired
	private TelemetryService telemetryService;

	/*
	 * This function returns the lastest telemetry entry of the database on request
	 */
	@GetMapping(path = "{roverID}/telemetry")
	public RoverDTO getLatestTelemetry(@PathVariable final String roverID) {
		return this.telemetryService.getTelemetryData(roverID);
	}

	@CrossOrigin(origins = "http://localhost:9001")
	@PostMapping(path = "/{roverID}/command-control")
	public ResponseEntity<String> sendCommand(@RequestBody final CommandDTO command,
			@PathVariable final String roverID) {
		log.info("Command received: Type: " + command.getCommand() + " - Speed: " + command.getSpeed() + " - Rover: "
				+ roverID);

		//int port = request.getServerPort();

		//log.info("rover port is: "+  );

		commandService.sendCommand(roverID, command);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@HystrixCommand(fallbackMethod = "fallBackServer",
			commandKey = "server", groupKey = "server")
	@CrossOrigin(origins = "http://localhost:9001")
	@GetMapping(path = "/{roverID}/command-control")
	public ResponseEntity<String> shootCommand() {

		//wrong
		if (RandomUtils.nextBoolean()){
			throw new RuntimeException("Failed!");
		}

		CommandDTO model = new CommandDTO();
		model.setCommand("D");
		model.setSpeed(460);
		String roverID = "4";

		log.info("Command received: Type: " + model.getCommand() + " - Speed: " + model.getSpeed() + " - Rover: " + roverID);

		//int port = request.getServerPort();

		//log.info("rover port is: "+  );

		commandService.sendCommand(roverID, model);

		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	public ResponseEntity<String> fallBackServer(){
		return new ResponseEntity<>("Fall Back Hello initiator!", HttpStatus.OK);
	}

}