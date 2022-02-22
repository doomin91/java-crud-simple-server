package com.board.main.info.repository;

import java.util.List;

import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.board.main.info.model.Device;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class DeviceRepository {
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	private final DeviceRowMapper deviceRowMapper;
	
	public DeviceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
		this.deviceRowMapper = new DeviceRowMapper();
	}

	public List<Device> findList(){		
		log.debug("findList qurey : {}", "SELECT * FROM device ORDER BY DeviceSeq DESC;");
		
		return namedParameterJdbcTemplate.query("SELECT * FROM device WHERE DelYn = 'N' ORDER BY DeviceSeq DESC;", EmptySqlParameterSource.INSTANCE, this.deviceRowMapper);
	}
	
	public List<Device> findDeviceBySeq(int DeviceSeq){
		log.debug("findList qurey : {}", "SELECT * FROM device;");
		
		String qry = "SELECT * FROM device WHERE DeviceSeq = :deviceSeq";
		SqlParameterSource param = new MapSqlParameterSource("deviceSeq", DeviceSeq);
		
		return namedParameterJdbcTemplate.query(qry, param, this.deviceRowMapper);
	}
	
	public Device insert(Device device) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new MapSqlParameterSource("deviceName", device.getDeviceName())
				.addValue("deviceVendor", device.getDeviceVendor())
				.addValue("deviceIp", device.getDeviceIp());
		
		String qry = "INSERT INTO device (DeviceName, DeviceVendor, DeviceIp) values (:deviceName, :deviceVendor, :deviceIp)";
		int affectedRows = namedParameterJdbcTemplate.update(qry, parameterSource, keyHolder);
		log.debug("{} inserted, new id = {}", affectedRows, keyHolder.getKey());
		device.setDeviceSeq(keyHolder.getKey().intValue());
		
		return device;
	}
	
	public Integer updateBySeq(Device device) {
		String qry = "UPDATE device SET DeviceName = :deviceName"
				+ ", DeviceVendor = :deviceVendor"
				+ ", DeviceIp = :deviceIp"
				+ " WHERE DeviceSeq = :deviceSeq";
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("deviceSeq", device.getDeviceSeq())
				.addValue("deviceName", device.getDeviceName())
				.addValue("deviceVendor", device.getDeviceVendor())
				.addValue("deviceIp", device.getDeviceIp());
		
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
	
	public Integer deleteBySeq(Device device) {
		String qry = "UPDATE device SET DelYn = 'Y'"
				+ " WHERE DeviceSeq = :deviceSeq";
		
		SqlParameterSource parameterSource = new MapSqlParameterSource("deviceSeq", device.getDeviceSeq());
		
		return namedParameterJdbcTemplate.update(qry, parameterSource);
	}
}
