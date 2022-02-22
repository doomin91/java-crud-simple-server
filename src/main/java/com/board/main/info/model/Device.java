package com.board.main.info.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Device {
	private Integer deviceSeq;
	private String deviceName;
	private String deviceVendor;
	private String deviceIp;
	@JsonIgnore
	private Date regDate;
}
