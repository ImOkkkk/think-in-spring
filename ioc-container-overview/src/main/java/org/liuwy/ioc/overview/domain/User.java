package org.liuwy.ioc.overview.domain;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.liuwy.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

/**
 * 用户类
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/7/26 21:32
 */
public class User implements BeanNameAware {
    private Long id;
    private String name;

    private City city;

    private City[] workCities;

    private List<City> lifeCities;

    private Resource configFileLocation;

    private transient String beanName;

    public String getBeanName() {
        return beanName;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public static User createUser() {
        User user = new User();
        user.setName("i3-ImOkkkk");
        user.setId(1L);
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                ", beanName='" + beanName + '\'' +
                '}';
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean[" + beanName + "]初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean[" + beanName + "]销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
