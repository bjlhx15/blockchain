package com.lhx.springcloud.blockchain.socket.pbft.listener;

import javax.annotation.Resource;

import com.lhx.springcloud.blockchain.socket.body.VoteBody;
import com.lhx.springcloud.blockchain.socket.client.PacketSender;
import com.lhx.springcloud.blockchain.socket.packet.BlockPacket;
import com.lhx.springcloud.blockchain.socket.packet.PacketBuilder;
import com.lhx.springcloud.blockchain.socket.packet.PacketType;
import com.lhx.springcloud.blockchain.socket.pbft.event.MsgPrepareEvent;
import com.lhx.springcloud.blockchain.socket.pbft.msg.VoteMsg;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


/**
 * @author wuweifeng wrote on 2018/4/25.
 */
@Component
public class PrepareEventListener {
    @Resource
    private PacketSender packetSender;

    /**
     * block已经开始进入Prepare状态
     *
     * @param msgPrepareEvent
     *         msgIsPrepareEvent
     */
    @EventListener
    public void msgIsPrepare(MsgPrepareEvent msgPrepareEvent) {
        VoteMsg voteMsg = (VoteMsg) msgPrepareEvent.getSource();

        //群发消息，通知别的节点，我已对该Block Prepare
        BlockPacket blockPacket = new PacketBuilder<>().setType(PacketType.PBFT_VOTE).setBody(new
                VoteBody(voteMsg)).build();

        //广播给所有人我已Prepare
        packetSender.sendGroup(blockPacket);
    }
}
