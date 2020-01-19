package com.brynachj.kotlin.blockchainspike.services

import com.brynachj.kotlin.blockchainspike.domain.Block
import com.brynachj.kotlin.blockchainspike.domain.BlockChain
import org.springframework.stereotype.Service

@Service
class BlockchainService {

    private val blockChain: BlockChain = BlockChain()

    fun getBlockchain():String {

        return blockChain.blocks.toString()
    }

    fun addBlock(block: Block): String {
        blockChain.add(block)

        return blockChain.toString()
    }

}
