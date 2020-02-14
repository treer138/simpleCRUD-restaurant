package com.example.eatgo.application;

import com.example.eatgo.domain.Region;
import com.example.eatgo.domain.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class RegionServiceTests {

    @Autowired
    private RegionService regionService;

    @Mock
    private RegionRepository regionRepository;

    @BeforeEach
    public void serUp() {
        MockitoAnnotations.initMocks(this);
        regionService = new RegionService(regionRepository);
    }


    @Test
    public void getRegions() {
        List<Region> mockregions = new ArrayList<>();
        mockregions.add(Region.builder().name("Seoul").build());

        given(regionRepository.findAll()).willReturn(mockregions);

        List<Region> regions = regionService.getRegions();

        Region region = regions.get(0);
        Assertions.assertEquals(region.getName(), "Seoul");

    }

    @Test
    public void addRegion() {
        Region region = regionService.addRegion("Seoul");

        Assertions.assertEquals(region.getName(), "Seoul");

        verify(regionRepository).save(any());
    }


}