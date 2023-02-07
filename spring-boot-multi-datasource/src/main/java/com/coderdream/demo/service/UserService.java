package com.coderdream.demo.service;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<Map<String, Object>>  selectAll();
    List<Map<String, Object>>  selectByCondition();
}
