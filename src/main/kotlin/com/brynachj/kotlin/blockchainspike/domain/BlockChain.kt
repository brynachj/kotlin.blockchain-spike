package com.brynachj.kotlin.blockchainspike.domain

class BlockChain {


    private val difficulty = 5
    private val validPrefix = "0".repeat(difficulty)

    private var blocks: MutableList<Block> = mutableListOf()

    fun add(block: Block) : Block {
        val minedBlock = if (isMined(block)) block else mine(block)
        blocks.add(minedBlock)
        return minedBlock
    }

    private fun isMined(block: Block): Boolean {
        return block.hash.startsWith(validPrefix)
    }

    private fun mine(block: Block): Block {
        println("Mining: $block")

        var minedBlock = block.copy()

        while(!isMined(minedBlock)) {
            minedBlock.copy(nonce = minedBlock.nonce + 1)
        }

        println("Mined: $block")

        return minedBlock
    }
}