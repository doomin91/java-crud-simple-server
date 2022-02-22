package com.board.main.info;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.board.main.info.model.Device;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin("*")
@RestController
public class InfoController {

	private InfoService infoService;
	
	@Autowired
	public InfoController(InfoService infoService) {
		this.infoService = infoService;
	}
		
	@GetMapping("/info/deviceList")
	public Object deviceList() {
		log.debug("/deviceList start");
		List<Device> deviceList = infoService.getDeviceList();
		return deviceList;
	}
	
	@GetMapping("/info/deviceBySeq")
	public Object deviceListBySeq(
			@RequestParam(value="deviceSeq", required=true) int deviceSeq) {
		log.debug("deviceSeq = {}", deviceSeq);
		List<Device> device = infoService.getDeviceBySeq(deviceSeq);
		return device;
	}
	
	@PostMapping(value="deviceAdd")
	public ResponseEntity<Device> deviceAdd(@RequestBody Device device){
		log.debug("device = {}", device);
		try {
			log.debug("device = {}", device.toString());
			return new ResponseEntity<>(infoService.insert(device), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping(value="deviceEdit")
	public ResponseEntity<String> deviceEdit(@RequestBody Device device){
		log.debug("device = {}", device);
		try {
			log.debug("device = {}", device.toString());
			Integer updateCnt = infoService.updateBySeq(device);
			return new ResponseEntity<>(String.format("%d updated", updateCnt), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}
	
	@PostMapping(value="deviceDelete")
	public ResponseEntity<String> deviceDelete(@RequestBody Device device){
		log.debug("device = {}", device);
		try {
			log.debug("device = {}", device.toString());
			Integer deleteCnt = infoService.deleteBySeq(device);
			return new ResponseEntity<>(String.format("%d updated", deleteCnt), HttpStatus.OK);
		}catch(Exception e){
			log.error(e.toString());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

}


