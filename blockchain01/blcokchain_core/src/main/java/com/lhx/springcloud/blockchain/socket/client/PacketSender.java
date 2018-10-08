package com.lhx.springcloud.blockchain.socket.client;

import com.lhx.springcloud.blockchain.ApplicationContextProvider;
import com.lhx.springcloud.blockchain.core.event.ClientRequestEvent;
import com.lhx.springcloud.blockchain.socket.common.Const;
import com.lhx.springcloud.blockchain.socket.packet.BlockPacket;
import org.springframework.stereotype.Component;
import org.tio.client.ClientGroupContext;
import org.tio.core.Aio;

import javax.annotation.Resource;


/**
 * 发送消息的工具类
 * @author wuweifeng wrote on 2018/3/12.
 */
@Component
public class PacketSender {
    @Resource
    private ClientGroupContext clientGroupContext;

    public void sendGroup(BlockPacket blockPacket) {
        //对外发出client请求事件
        ApplicationContextProvider.publishEvent(new ClientRequestEvent(blockPacket));
        //发送到一个group
        Aio.sendToGroup(clientGroupContext, Const.GROUP_NAME, blockPacket);
    }

}
