package com.exult.ProjectCisco.service.ifmDevice.maven;

import com.exult.ProjectCisco.model.Maven;
import com.exult.ProjectCisco.repository.MavenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MavenServiceimp implements MavenService {

    @Autowired
    private MavenRepository mavenRepository;

    @Transactional
    @Override
    public Maven findMavenById(long id) {
        return mavenRepository.findById(id).get();
    }
}
