package com.coderdream.freeapps.service;


import com.coderdream.freeapps.model.Snapshot;

import java.util.List;

public interface MinioService {

    void upload();


    void uploadSnapshot(List<Snapshot> snapshotList);

    void processDaily();
}
