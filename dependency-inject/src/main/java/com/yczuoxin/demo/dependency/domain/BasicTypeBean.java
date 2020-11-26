package com.yczuoxin.demo.dependency.domain;

import com.yczuoxin.demo.dependency.enums.City;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BasicTypeBean {

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Map<String, City> mappingCities;

    private Resource configFilePath;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Resource getConfigFilePath() {
        return configFilePath;
    }

    public void setConfigFilePath(Resource configFilePath) {
        this.configFilePath = configFilePath;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Map<String, City> getMappingCities() {
        return mappingCities;
    }

    public void setMappingCities(Map<String, City> mappingCities) {
        this.mappingCities = mappingCities;
    }

    @Override
    public String toString() {
        return "BasicTypeBean{" +
                "city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", mappingCities=" + mappingCities +
                ", configFilePath=" + configFilePath +
                '}';
    }
}
