package com.lhx.springcloud.blockchain.manager;

import com.lhx.springcloud.blockchain.repository.MemberGroupRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberGroupManager {
    @Resource
    private MemberGroupRepository memberGroupRepository;
}
