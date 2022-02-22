package com.board.main.info.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.board.main.info.model.Device;

public class DeviceRowMapper implements RowMapper<Device>{
	
	@Override
	public Device mapRow(ResultSet rs, int rowNum) throws SQLException{
		Device device = new Device();
		device.setDeviceSeq(rs.getInt("deviceSeq"));
		device.setDeviceName(rs.getString("deviceName"));
		device.setDeviceVendor(rs.getString("deviceVendor"));
		device.setDeviceIp(rs.getString("deviceIp"));
		return device;
	}

	
}
