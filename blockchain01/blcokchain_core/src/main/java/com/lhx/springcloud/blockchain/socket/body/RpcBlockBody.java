package com.lhx.springcloud.blockchain.socket.body;


import com.lhx.springcloud.blockchain.block.Block;

/**
 * body里是一个block信息
 */
public class RpcBlockBody extends BaseBody {
    /**
     * blockJson
     */
    private Block block;

    public RpcBlockBody() {
        super();
    }

    public RpcBlockBody(Block block) {
        super();
        this.block = block;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "BlockBody{" +
                "block=" + block +
                '}';
    }
}
