package com.lhx.springcloud.blockchain.repository;

import com.lhx.springcloud.blockchain.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限操作
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    /**
     * 查询某个group的所有权限
     * @param groupId
     * 联盟链id
     * @return
     * 权限集合
     */
    List<Permission> findByGroupId(String groupId);

}
