package com.example.seebook.domain;

import com.example.seebook.domain.role.domain.RoleCode;
import com.example.seebook.domain.role.domain.RoleInfo;
import com.example.seebook.domain.role.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer{

    private final RoleRepository roleRepository;

    @PostConstruct
    public void run(){
        roleRepository.saveAll(Arrays.asList(
                new RoleInfo(RoleCode.ADMIN),
                new RoleInfo(RoleCode.USER)
        ));
    }
}
