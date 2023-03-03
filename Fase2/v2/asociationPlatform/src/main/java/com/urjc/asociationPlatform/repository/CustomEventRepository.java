package com.urjc.asociationPlatform.repository;

import java.util.List;

public interface CustomEventRepository {
    List<Object[]> customQuery(String name, String month, String campus, String asociation);
}
