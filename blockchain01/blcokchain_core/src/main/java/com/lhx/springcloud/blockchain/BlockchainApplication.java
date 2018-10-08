package com.lhx.springcloud.blockchain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author lihongxu
 * @desc @link(https://github.com/bjlhx15/blockchain)
 * @since 2018/10/2 上午11:04
 */
@SpringBootApplication
@EnableScheduling
@EnableAsync
public class BlockchainApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlockchainApplication.class, args);
    }
}
