package com.team.devmanagement.service;

import com.team.devmanagement.mapper.DeviceMapper;
import com.team.devmanagement.mapper.UserMapper;
import com.team.devmanagement.model.Device;
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
    public User getUserById(Integer did){
        return deviceMapper.getDeviceById(did);
    }
    public List<Device> getAllDevices(){
        return deviceMapper.getAllDevices();
    }
    public int deleteDeviceById(Integer did){
        return deviceMapper.deleteDeviceById(did);
    }
//    public User query(String username,String password){
//        return userMapper.query(username,password);
//    }
}
