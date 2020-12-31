package com.hywx.sitm.bo.gps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.hywx.sitm.bo.param.SitmParam;
import com.hywx.sitm.po.GpsFrame;
import com.hywx.sitm.util.ByteUtil;
import com.hywx.sitm.util.TimeUtil;

public class SitmGpsWeek extends AbstractSitmGps {

	@Override
	public SitmParam getGpsParam(String satelliteId, String value, GpsFrame frame) {
		String time = frame.getTime();
		LocalDateTime dt = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
		double week = TimeUtil.calGpsWeek(dt);
		
		return new SitmParam(ByteUtil.fromDouble2Bytes(week, false), String.valueOf(week));
	}

}