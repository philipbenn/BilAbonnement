package com.example.bilabonnement.service;

import com.example.bilabonnement.model.Car_Return_DamageDTO;
import com.example.bilabonnement.repository.WorkplaceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkplaceService {

    @Autowired
    WorkplaceRepo workplaceRepo;

    public List<Car_Return_DamageDTO> getAllDamageInfo() {
        return workplaceRepo.getAllDamageInfo();
    }
}
