package com.example.eatgo.application;

import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    private RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }


    public List<Region> getRegions() {
        //TODO 구현해야됭

        List<Region> regions = regionRepository.findAll();

        return regions;
    }

    public Region addRegion(String name) {
        //TODO 구현필요
        Region region = Region.builder().name(name).build();

        regionRepository.save(region);

        return region;
    }
}
