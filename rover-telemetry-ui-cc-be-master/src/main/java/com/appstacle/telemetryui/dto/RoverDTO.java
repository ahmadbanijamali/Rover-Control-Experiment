/*
 * ******************************************************************************
 * Copyright (c) 2019 Dortmund University of Applied Scienes and Arts.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/org/documents/epl-2.0/index.php
 *
 *  Contributors:
 *      Philipp Heisig (FH Dortmund) - initial API and functionality
 * *****************************************************************************
 */

package com.appstacle.telemetryui.dto;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "rover3")
public class RoverDTO {
	@Column(name = "time")
	private Instant time;

	@Column(name = "cores-core0")
	private String core1;
	@Column(name = "cores-core1")
	private String core2;
	@Column(name = "cores-core2")
	private String core3;
	@Column(name = "cores-core3")
	private String core4;

	@Column(name = "gy521-accel-x")
	private String accelX;
	@Column(name = "gy521-accel-y")
	private String accelY;
	@Column(name = "gy521-accel-z")
	private String accelZ;
	@Column(name = "gy521-angle-x")
	private String angleX;
	@Column(name = "gy521-angle-y")
	private String angleY;
	@Column(name = "gy521-angle-z")
	private String angleZ;
	@Column(name = "gy521-gyro-x")
	private String gyroX;
	@Column(name = "gy521-gyro-y")
	private String gyroY;
	@Column(name = "gy521-gyro-z")
	private String gyroZ;

	@Column(name = "hmc5883l-bearing")
	private String bearing;

	@Column(name = "infrared-frontleft")
	private String infraredFrontLeft;
	@Column(name = "infrared-frontright")
	private String infraredFrontRight;
	@Column(name = "infrared-rearleft")
	private String infraredRearLeft;
	@Column(name = "infrared-rearright")
	private String infraredRearRight;

	@Column(name = "ultrasonic-front")
	private String ultrasonicFront;
	@Column(name = "ultrasonic-rear")
	private String ultrasonicRear;

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public String getCore1() {
		return core1;
	}

	public void setCore1(String core1) {
		this.core1 = core1;
	}

	public String getCore2() {
		return core2;
	}

	public void setCore2(String core2) {
		this.core2 = core2;
	}

	public String getCore3() {
		return core3;
	}

	public void setCore3(String core3) {
		this.core3 = core3;
	}

	public String getCore4() {
		return core4;
	}

	public void setCore4(String core4) {
		this.core4 = core4;
	}

	public String getAccelX() {
		return accelX;
	}

	public void setAccelX(String accelX) {
		this.accelX = accelX;
	}

	public String getAccelY() {
		return accelY;
	}

	public void setAccelY(String accelY) {
		this.accelY = accelY;
	}

	public String getAccelZ() {
		return accelZ;
	}

	public void setAccelZ(String accelZ) {
		this.accelZ = accelZ;
	}

	public String getAngleX() {
		return angleX;
	}

	public void setAngleX(String angleX) {
		this.angleX = angleX;
	}

	public String getAngleY() {
		return angleY;
	}

	public void setAngleY(String angleY) {
		this.angleY = angleY;
	}

	public String getAngleZ() {
		return angleZ;
	}

	public void setAngleZ(String angleZ) {
		this.angleZ = angleZ;
	}

	public String getGyroX() {
		return gyroX;
	}

	public void setGyroX(String gyroX) {
		this.gyroX = gyroX;
	}

	public String getGyroY() {
		return gyroY;
	}

	public void setGyroY(String gyroY) {
		this.gyroY = gyroY;
	}

	public String getGyroZ() {
		return gyroZ;
	}

	public void setGyroZ(String gyroZ) {
		this.gyroZ = gyroZ;
	}

	public String getBearing() {
		return bearing;
	}

	public void setBearing(String bearing) {
		this.bearing = bearing;
	}

	public String getInfraredFrontLeft() {
		return infraredFrontLeft;
	}

	public void setInfraredFrontLeft(String infraredFrontLeft) {
		this.infraredFrontLeft = infraredFrontLeft;
	}

	public String getInfraredFrontRight() {
		return infraredFrontRight;
	}

	public void setInfraredFrontRight(String infraredFrontRight) {
		this.infraredFrontRight = infraredFrontRight;
	}

	public String getInfraredRearLeft() {
		return infraredRearLeft;
	}

	public void setInfraredRearLeft(String infraredRearLeft) {
		this.infraredRearLeft = infraredRearLeft;
	}

	public String getInfraredRearRight() {
		return infraredRearRight;
	}

	public void setInfraredRearRight(String infraredRearRight) {
		this.infraredRearRight = infraredRearRight;
	}

	public String getUltrasonicFront() {
		return ultrasonicFront;
	}

	public void setUltrasonicFront(String ultrasonicFront) {
		this.ultrasonicFront = ultrasonicFront;
	}

	public String getUltrasonicRear() {
		return ultrasonicRear;
	}

	public void setUltrasonicRear(String ultrasonicRear) {
		this.ultrasonicRear = ultrasonicRear;
	}
}
