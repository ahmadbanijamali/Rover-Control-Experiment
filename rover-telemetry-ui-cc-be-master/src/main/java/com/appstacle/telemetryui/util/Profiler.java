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
package com.appstacle.telemetryui.util;

import java.time.Duration;
import java.time.Instant;

public class Profiler {
	private Instant start;
	private Instant end;
	
	
	public void start() {
		this.start = Instant.now();
	}
	
	public void end() {
		this.end = Instant.now();
	}
	
	public long getMeasuredTimeInMillis() {
		return Duration.between(start, end).toMillis();
	}
	
	public long getMeasuredTimeInSeconds() {
		return Duration.between(start, end).getSeconds();
	}
	
	public long getMeasuredTimeInNanos() {
		return Duration.between(start, end).getNano();
	}
	
	
	
	
	
	
}
