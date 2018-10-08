package com.lhx.springcloud.blockchain.core.manager;

import com.lhx.springcloud.blockchain.core.model.MessageEntity;
import com.lhx.springcloud.blockchain.core.repository.MessageRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wuweifeng wrote on 2018/3/28.
 */
@Component
public class MessageManager {
    @Resource
    private MessageRepository messageRepository;

    public List<MessageEntity> findAll() {
        return messageRepository.findAll();
    }

    public List<String> findAllContent() {
        return findAll().stream().map(MessageEntity::getContent).collect(Collectors.toList());
    }
}
