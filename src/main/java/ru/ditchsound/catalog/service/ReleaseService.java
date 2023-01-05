package ru.ditchsound.catalog.service;

import ru.ditchsound.catalog.model.Release;

import java.util.List;

public interface ReleaseService {

    public Release findById(Long id);

    public List<Release> findAll();

    public List<Release> findReleaseByBandName(String name);

    public List<Release> findByStatus(String status);

}
