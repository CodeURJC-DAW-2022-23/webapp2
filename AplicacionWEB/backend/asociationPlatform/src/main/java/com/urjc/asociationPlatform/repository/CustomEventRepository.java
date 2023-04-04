package com.urjc.asociationPlatform.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.urjc.asociationPlatform.model.Event;

public interface CustomEventRepository {
    Page<Event> customQuery(String name, String month, String campus, String asociation, Pageable pageable);
}
