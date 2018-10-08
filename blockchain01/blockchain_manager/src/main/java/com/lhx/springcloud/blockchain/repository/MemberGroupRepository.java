package com.lhx.springcloud.blockchain.repository;

import com.lhx.springcloud.blockchain.model.MemberGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 成员组操作
 */
public interface MemberGroupRepository extends JpaRepository<MemberGroup, Long> {
}
