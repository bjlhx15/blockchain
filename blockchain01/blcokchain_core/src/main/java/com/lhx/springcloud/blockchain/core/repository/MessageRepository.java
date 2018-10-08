package com.lhx.springcloud.blockchain.core.repository;

import com.lhx.springcloud.blockchain.core.model.MessageEntity;

/**
 * @author wuweifeng wrote on 2017/10/25.
 */
public interface MessageRepository extends BaseRepository<MessageEntity> {
    /**
     * 删除一条记录
     * @param messageId  messageId
     */
    void deleteByMessageId(String messageId);

    /**
     * 查询一个
     * @param messageId messageId
     * @return MessageEntity
     */
    MessageEntity findByMessageId(String messageId);
}
