package com.lhx.springcloud.blockchain.core.service;

import com.lhx.springcloud.blockchain.block.PairKey;
import com.lhx.springcloud.blockchain.common.TrustSDK;
import com.lhx.springcloud.blockchain.common.exception.TrustSDKException;
import org.springframework.stereotype.Service;

/**
 * @author wuweifeng wrote on 2018/3/7.
 */
@Service
public class PairKeyService {

    /**
     * 生成公私钥对
     * @return PairKey
     * @throws TrustSDKException TrustSDKException
     */
    public PairKey generate() throws TrustSDKException {
        return TrustSDK.generatePairKey(true);
    }
}
