package com.team.devmanagement.mapper;

import com.team.devmanagement.model.Device;
import com.team.devmanagement.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceMapper {
    int addDevice(Device device);
    int deleteDeviceById(Integer did);
    int updateDevice(Device device);
    User getDeviceById(Integer did);
    List<Device> getAllDevices();
}
