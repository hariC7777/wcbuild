package com.hari.wcbuild.service;

import com.hari.wcbuild.domain.Video;

import java.util.List;

public interface VideoService {

    List<Video> findAll();

    Video findById(int id);

    int update(Video Video);

    int delete(int id);

    int save(Video video);
}
