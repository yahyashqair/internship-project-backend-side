package com.exult.ProjectCisco.service.ifmDevice.Profile;

import com.exult.ProjectCisco.model.*;
import com.exult.ProjectCisco.repository.ProfileRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public @Setter
class ProfileServiceImplementation implements ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    /*
     * Add
     * Delete
     * Update
     * Find
     * */

    @Transactional
    public Profile findProfile(String x) {
        List<Profile> profileSet = (List<Profile>) profileRepository.findByName(x);
        if (profileSet.size() > 0) {
            return profileSet.get(0);
        }
        return null;
    }

    @Transactional
    public boolean deleteProfile(Long id) {
        Profile profile = profileRepository.findById(id).get();
        profileRepository.delete(profile);
        return true;
    }

    @Transactional
    public Profile updateProfile(Long id, String name, Maven maven) {
        Profile profile = profileRepository.findById(id).get();
        profile.setName(name);
        profile.setMaven(maven);
        return profile;
    }

    @Transactional
    public Profile insertProfile(String name, Maven maven, Server server) {
        try {
            Profile profile = new Profile();
            profile.setName(name);
            profile.setServer(server);
            profile.setMaven(maven);
            profile = profileRepository.save(profile);
            return profile;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public Profile findById(Long x) {
        return profileRepository.findById(x).get();
    }

    @Transactional
    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Transactional
    @Override
    public Set<FeatureXde> getFeatureXde(long id) {
        Set<Feature> featureSet = findById(id).getFeatures();
        Set<FeatureXde> featureXdes = new HashSet<>();
        for (Feature feature : featureSet) {
            featureXdes.addAll(feature.getXdeSet());
        }
        return featureXdes;
    }

    @Override
    public Page<Profile> findAllPage(Pageable P) {
        Page<Profile> profilePage = profileRepository.findAll(P);
        return profilePage;
    }

    @Override
    public Page<Profile> findByNameLikeAndServer(Server server,String search, Pageable pageable) {
        return profileRepository.findByNameLikeAndServer(search,server,pageable);
    }

//    @Override
//    public List<Profile> findByNameLike(String search) {
//        return profileRepository.findByNameLike(search);
//    }

    @Override
    public List<Profile> getAllProfilesBelongToServer(Server server) {
        return profileRepository.findAllByServer(server);
    }

    @Override
    public Page<Profile> getAllProfilesBelongToServer(Server server, Pageable P) {
        return profileRepository.findAllByServer(server, P);
    }

    @Override
    public Integer countAllByServer(Server server) {
        return profileRepository.countAllByServer(server);
    }

}
