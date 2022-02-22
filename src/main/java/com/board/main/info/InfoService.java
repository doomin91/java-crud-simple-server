package com.board.main.info;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import com.board.main.info.model.Device;
import com.board.main.info.repository.DeviceRepository;


@Service
public class InfoService {

		private final DeviceRepository deviceRepository;
		
		public InfoService(DeviceRepository deviceRepository) {
			this.deviceRepository = deviceRepository;
		}

		public List<Device> getDeviceList() {
			return this.deviceRepository.findList();
		}
		
		public List<Device> getDeviceBySeq(int DeviceSeq) {
			return this.deviceRepository.findDeviceBySeq(DeviceSeq);
		}
		
		public Device insert(Device device) {
			return this.deviceRepository.insert(device);
		}
		
		public Integer updateBySeq(Device device) {
			return this.deviceRepository.updateBySeq(device);
		}
		
		public Integer deleteBySeq(Device device) {
			return this.deviceRepository.deleteBySeq(device);
		}
}
