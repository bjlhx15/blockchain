package com.lhx.springcloud.blockchain.core.manager;

import com.lhx.springcloud.blockchain.core.model.SyncEntity;
import com.lhx.springcloud.blockchain.core.repository.SyncRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 */
@Service
public class SyncManager {
    @Resource
    private SyncRepository syncRepository;

    public SyncEntity findLastOne() {
        return syncRepository.findTopByOrderByIdDesc();
    }

    public SyncEntity save(SyncEntity syncEntity) {
        return syncRepository.save(syncEntity);
    }

    public Object findAll(Pageable pageable) {
        return syncRepository.findAll(pageable);
    }

    public void deleteAll() {
        syncRepository.deleteAll();
    }
}
