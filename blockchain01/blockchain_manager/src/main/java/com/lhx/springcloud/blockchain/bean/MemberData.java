package com.lhx.springcloud.blockchain.bean;


import com.lhx.springcloud.blockchain.model.Member;

import java.util.List;

public class MemberData extends BaseData {
    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
