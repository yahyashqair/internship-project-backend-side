package com.exult.ProjectCisco.service.Profile;

import com.exult.ProjectCisco.model.Profile;
import com.exult.ProjectCisco.model.Maven;
import com.exult.ProjectCisco.repository.ProfileRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public  @Setter class  ProfileServiceImplementation implements ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    /*
    * Add
    * Delete
    * Update
    * Find
    * */

    @Transactional
    public Set<Profile> findProfile(String x){
        return (Set<Profile>) profileRepository.findByName(x);
    }

    @Transactional
    public boolean deleteProfile(Long id){
        Profile profile =  profileRepository.findById(id).get();
        profileRepository.delete(profile);
        return true;
    }
    @Transactional
    public Profile updateProfile(Long id, String name, Maven maven){
        Profile profile =  profileRepository.findById(id).get();
        profile.setName(name);
        profile.setMaven(maven);
        return profile;
    }

    @Transactional
    public Profile insertProfile(String name, Maven maven){
        Profile profile= new Profile();
        profile.setName(name);
        profile.setMaven(maven);
        profile=profileRepository.save(profile);
        return profile;
    }

    @Override
    public Optional<Profile> findById(Long x) {
        return profileRepository.findById(x);
    }
    @Override
    public List<Profile> findAll(){
        return profileRepository.findAll();
    }

}
