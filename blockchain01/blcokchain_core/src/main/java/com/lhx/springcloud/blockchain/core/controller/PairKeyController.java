package com.lhx.springcloud.blockchain.core.controller;

import com.lhx.springcloud.blockchain.common.exception.TrustSDKException;
import com.lhx.springcloud.blockchain.core.bean.BaseData;
import com.lhx.springcloud.blockchain.core.bean.ResultGenerator;
import com.lhx.springcloud.blockchain.core.service.PairKeyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wuweifeng wrote on 2018/3/7.
 */
@RestController
@RequestMapping("/pairKey")
public class PairKeyController {
    @Resource
    private PairKeyService pairKeyService;

    /**
     * 生成公钥私钥
     */
    @GetMapping("/random")
    public BaseData generate() throws TrustSDKException {
         return ResultGenerator.genSuccessResult(pairKeyService.generate());
    }
}
