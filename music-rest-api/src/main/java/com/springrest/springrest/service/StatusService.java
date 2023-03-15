package com.springrest.springrest.service;

import com.springrest.springrest.interfaces.IStatusServiceInterface;
import com.springrest.springrest.model.Status;
import com.springrest.springrest.repository.IStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService implements IStatusServiceInterface {
    @Autowired
    private IStatusRepository iStatusRepository;
    @Override
    public Status addStatus(Status status) {
        return iStatusRepository.save(status);
    }
}
