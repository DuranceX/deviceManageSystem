package com.team.devmanagement.service;

import com.team.devmanagement.mapper.DeviceMapper;
import com.team.devmanagement.mapper.UserMapper;
import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.DevicesInfo;
import com.team.devmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    DeviceMapper deviceMapper;
    public int addDevice(Device device){
        return deviceMapper.addDevice(device);
    }
    public int updateDevice(Device device){
        return deviceMapper.updateDevice(device);
    }
    public Device getDeviceById(Integer did){
        return deviceMapper.getDeviceById(did);
    }
    public Device getDeviceByDuid(String duid){
        return deviceMapper.getDeviceByDuid(duid);
    }
    public List<Device> getAllDevices(){
        return deviceMapper.getAllDevices();
    }
    public int deleteDeviceById(Integer did){
        return deviceMapper.deleteDeviceById(did);
    }
    public List<DevicesInfo> getDevicesGroupByDnameAndDstatus(){
        return deviceMapper.getDevicesGroupByDnameAndDstatus();
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
