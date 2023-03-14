package com.springrest.springrest.service;

import com.springrest.springrest.model.IStatusInterface;
import com.springrest.springrest.model.Status;
import com.springrest.springrest.repository.IStatusRepository;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements IStatusInterface {
    @Autowired
    private IStatusRepository iStatusRepository;
    @Override
    public Status setStatus(JSONObject jsonObject) {
        Status status1 = new Status();
        status1.setStatus_name(jsonObject.getString("status_name"));
        status1.setStatus_description(jsonObject.getString("status_description"));
        return status1;
    }
    @Override
    public int saveSatus(Status newStatus) {

        Status status =  iStatusRepository.save(newStatus);
        return status.getStatus_id();

    }
}
