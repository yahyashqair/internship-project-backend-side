package com.exult.ProjectCisco.service.ifmDevice.Feature;

import com.exult.ProjectCisco.model.*;
import com.exult.ProjectCisco.repository.FeatureRepository;
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
class FeatureServiceImplementation implements FeatureService {
    @Autowired
    private FeatureRepository featureRepository;

    /*
     * Add
     * Delete
     * Update
     * Find
     * */

    @Transactional
    public Set<Xde> getFeatureXdeSet(Long id) {

        Set<FeatureXde> featureXdes = findFeatureById(id).getXdeSet();
        Set<Xde> xdes = new HashSet<>();
        for (FeatureXde featureXde : featureXdes) {
            xdes.add(featureXde.getXde());
        }
        return xdes;
    }

    @Override
    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    @Override
    public Page<Feature> findByNameLikeAndServer(Server server,String username, Pageable pageable) {
        return featureRepository.findByNameLikeAndServer(username,server,pageable);
    }

    @Override
    public Page<Feature> findAllPage(Pageable pageable) {
        return featureRepository.findAll(pageable);
    }

    @Override
    public List<Feature> getAllFeaturesBelongToServer(Server server) {
        return featureRepository.findAllByServer(server);
    }

    @Override
    public Page<Feature> getAllFeaturesBelongToServer(Server server, Pageable pageable) {
        return featureRepository.findAllByServer(server, pageable);
    }

    @Override
    public Integer countAllByServer(Server server) {
        return featureRepository.countAllByServer(server);
    }

    @Transactional
    public Feature findFeature(String x) {
        List<Feature> featureList = featureRepository.findByName(x);
        if (featureList.size() > 0) {
            return featureList.get(0);
        }
        return null;
    }

    @Transactional
    public boolean deleteFeature(Long id) {
        Feature feature = featureRepository.findById(id).get();
        featureRepository.delete(feature);
        return true;
    }

    @Transactional
    public Feature updateFeature(Long id, String name, Maven maven) {
        Feature feature = featureRepository.findById(id).get();
        feature.setMaven(maven);
        feature.setName(name);
        return feature;
    }

    @Transactional
    public Feature insertFeature(String name, Maven maven, Server server) {
        try {
            Feature feature = new Feature();
            feature.setMaven(maven);
            feature.setName(name);
            feature.setServer(server);
            feature = featureRepository.save(feature);
            return feature;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Transactional
    @Override
    public Feature findFeatureById(Long x) {
        return featureRepository.findById(x).get();
    }


}
