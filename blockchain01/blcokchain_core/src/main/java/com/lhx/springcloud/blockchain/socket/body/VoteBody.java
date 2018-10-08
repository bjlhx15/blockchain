package com.lhx.springcloud.blockchain.socket.body;


import com.lhx.springcloud.blockchain.socket.pbft.msg.VoteMsg;

/**
 * pbft投票
 * @author wuweifeng wrote on 2018/4/25.
 */
public class VoteBody extends BaseBody {
    private VoteMsg voteMsg;

    public VoteBody() {
        super();
    }

    public VoteBody(VoteMsg voteMsg) {
        super();
        this.voteMsg = voteMsg;
    }

    public VoteMsg getVoteMsg() {
        return voteMsg;
    }

    public void setVoteMsg(VoteMsg voteMsg) {
        this.voteMsg = voteMsg;
    }
}
