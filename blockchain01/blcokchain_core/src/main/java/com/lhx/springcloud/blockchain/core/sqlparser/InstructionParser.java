package com.lhx.springcloud.blockchain.core.sqlparser;


import com.lhx.springcloud.blockchain.block.InstructionBase;

/**
 * @author wuweifeng wrote on 2018/3/21.
 */
public interface InstructionParser {
    boolean parse(InstructionBase instructionBase);
}
