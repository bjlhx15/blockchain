package com.lhx.springcloud.blockchain.core.controller;

import javax.annotation.Resource;

import com.lhx.springcloud.blockchain.ApplicationContextProvider;
import com.lhx.springcloud.blockchain.block.Block;
import com.lhx.springcloud.blockchain.block.BlockBody;
import com.lhx.springcloud.blockchain.block.Instruction;
import com.lhx.springcloud.blockchain.block.Operation;
import com.lhx.springcloud.blockchain.block.check.BlockChecker;
import com.lhx.springcloud.blockchain.common.exception.TrustSDKException;
import com.lhx.springcloud.blockchain.core.bean.BaseData;
import com.lhx.springcloud.blockchain.core.bean.ResultGenerator;
import com.lhx.springcloud.blockchain.core.event.DbSyncEvent;
import com.lhx.springcloud.blockchain.core.manager.DbBlockManager;
import com.lhx.springcloud.blockchain.core.manager.MessageManager;
import com.lhx.springcloud.blockchain.core.manager.SyncManager;
import com.lhx.springcloud.blockchain.core.requestbody.BlockRequestBody;
import com.lhx.springcloud.blockchain.core.requestbody.InstructionBody;
import com.lhx.springcloud.blockchain.core.service.BlockService;
import com.lhx.springcloud.blockchain.core.service.InstructionService;
import com.lhx.springcloud.blockchain.socket.body.RpcBlockBody;
import com.lhx.springcloud.blockchain.socket.client.PacketSender;
import com.lhx.springcloud.blockchain.socket.packet.BlockPacket;
import com.lhx.springcloud.blockchain.socket.packet.PacketBuilder;
import com.lhx.springcloud.blockchain.socket.packet.PacketType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import cn.hutool.core.collection.CollectionUtil;

/**
 */
@RestController
@RequestMapping("/block")
public class BlockController {
    @Resource
    private BlockService blockService;
    @Resource
    private PacketSender packetSender;
    @Resource
    private DbBlockManager dbBlockManager;
    @Resource
    private InstructionService instructionService;
    @Resource
    private SyncManager syncManager;
    @Resource
    private MessageManager messageManager;
    @Resource
    private BlockChecker blockChecker;

    /**
     * 添加一个block，需要先在InstructionController构建1-N个instruction指令，然后调用该接口生成Block
     *
     * @param blockRequestBody
     *         指令的集合
     * @return 结果
     */
    @PostMapping
    public BaseData add(@RequestBody BlockRequestBody blockRequestBody) throws TrustSDKException {
    	String msg = blockService.check(blockRequestBody);
        if (msg != null) {
            return ResultGenerator.genFailResult(msg);
        }
        return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
    }

    /**
     * 测试生成一个insert:Block，公钥私钥可以通过PairKeyController来生成
     * @param content
     * sql内容
     */
    @GetMapping
    public BaseData test(String content) throws Exception {
        InstructionBody instructionBody = new InstructionBody();
        instructionBody.setOperation(Operation.ADD);
        instructionBody.setTable("message");
        instructionBody.setJson("{\"content\":\"" + content + "\"}");
        instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
        instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
        Instruction instruction = instructionService.build(instructionBody);

        BlockRequestBody blockRequestBody = new BlockRequestBody();
        blockRequestBody.setPublicKey(instructionBody.getPublicKey());
        BlockBody blockBody = new BlockBody();
        blockBody.setInstructions(CollectionUtil.newArrayList(instruction));

        blockRequestBody.setBlockBody(blockBody);

        return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
    }
    
    /**
     * 测试生成一个update:Block，公钥私钥可以通过PairKeyController来生成
     * @param id 更新的主键
     * @param content
     * sql内容
     */
    @GetMapping("testUpdate")
    public BaseData testUpdate(String id,String content) throws Exception {
    	if(StringUtils.isBlank(id)) ResultGenerator.genSuccessResult("主键不可为空");
    	InstructionBody instructionBody = new InstructionBody();
    	instructionBody.setOperation(Operation.UPDATE);
    	instructionBody.setTable("message");
    	instructionBody.setInstructionId(id);
    	instructionBody.setJson("{\"content\":\"" + content + "\"}");
    	instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
    	instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
    	Instruction instruction = instructionService.build(instructionBody);
    	
    	BlockRequestBody blockRequestBody = new BlockRequestBody();
    	blockRequestBody.setPublicKey(instructionBody.getPublicKey());
    	BlockBody blockBody = new BlockBody();
    	blockBody.setInstructions(CollectionUtil.newArrayList(instruction));
    	
    	blockRequestBody.setBlockBody(blockBody);
    	
    	return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
    }
    
    /**
     * 测试生成一个delete:Block，公钥私钥可以通过PairKeyController来生成
     * @param id 待删除记录的主键
     * sql内容
     */
    @GetMapping("testDel")
    public BaseData testDel(String id) throws Exception {
    	if(StringUtils.isBlank(id)) ResultGenerator.genSuccessResult("主键不可为空");
    	InstructionBody instructionBody = new InstructionBody();
    	instructionBody.setOperation(Operation.DELETE);
    	instructionBody.setTable("message");
    	instructionBody.setInstructionId(id);
    	instructionBody.setPublicKey("A8WLqHTjcT/FQ2IWhIePNShUEcdCzu5dG+XrQU8OMu54");
    	instructionBody.setPrivateKey("yScdp6fNgUU+cRUTygvJG4EBhDKmOMRrK4XJ9mKVQJ8=");
    	Instruction instruction = instructionService.build(instructionBody);
    	
    	BlockRequestBody blockRequestBody = new BlockRequestBody();
    	blockRequestBody.setPublicKey(instructionBody.getPublicKey());
    	BlockBody blockBody = new BlockBody();
    	blockBody.setInstructions(CollectionUtil.newArrayList(instruction));
    	
    	blockRequestBody.setBlockBody(blockBody);
    	
    	return ResultGenerator.genSuccessResult(blockService.addBlock(blockRequestBody));
    }

    /**
     * 查询已落地的sqlite里的所有数据
     */
    @GetMapping("sqlite")
    public BaseData sqlite() {
        return ResultGenerator.genSuccessResult(messageManager.findAll());
    }

    /**
     * 查询已落地的sqlite里content字段
     */
    @GetMapping("sqlite/content")
    public BaseData content() {
        return ResultGenerator.genSuccessResult(messageManager.findAllContent());
    }

    /**
     * 获取最后一个block的信息
     */
    @GetMapping("db")
    public BaseData getRockDB() {
        return ResultGenerator.genSuccessResult(dbBlockManager.getLastBlock());
    }

    /**
     * 手工执行区块内sql落地到sqlite操作
     * @param pageable
     * 分页
     * @return
     * 已同步到哪块了的信息
     */
    @GetMapping("sync")
    public BaseData sync(@PageableDefault Pageable pageable) {
        ApplicationContextProvider.publishEvent(new DbSyncEvent(""));
        return ResultGenerator.genSuccessResult(syncManager.findAll(pageable));
    }
    
    /**
     * 全量检测区块是否正常
     * @return
     * null - 通过
     * hash - 第一个异常hash
     */
    @GetMapping("checkb")
    public BaseData checkAllBlock() {
    	
    	Block block = dbBlockManager.getFirstBlock();
    	
    	String hash = null;
    	while(block != null && hash == null) {
    		hash = blockChecker.checkBlock(block);
    		block = dbBlockManager.getNextBlock(block);
    	}
    	return ResultGenerator.genSuccessResult(hash);
    }

    @GetMapping("/next")
    public BaseData nextBlock() {
        Block block = dbBlockManager.getFirstBlock();
        BlockPacket packet = new PacketBuilder<RpcBlockBody>()
                .setType(PacketType.NEXT_BLOCK_INFO_REQUEST)
                .setBody(new RpcBlockBody(block)).build();
        packetSender.sendGroup(packet);
        return null;
    }
}
