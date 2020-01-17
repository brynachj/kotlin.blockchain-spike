package com.brynachj.kotlin.blockchainspike.domain

import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class BlockChainTest {

    @Test
    fun toStringTest() {

        var blockChain = BlockChain(difficulty = 1)

        populateBlockChain(blockChain)

        assertThat(blockChain.blocks.size).isEqualTo(3)
        assertThat(blockChain.blocks[0].data).isEqualTo("I'm the first")
        assertThat(blockChain.blocks[1].data).isEqualTo("I'm the second")
        assertThat(blockChain.blocks[2].data).isEqualTo("I'm the third")
        blockChain.blocks.forEach {
            assertThat(it.hash.startsWith("0"))
        }
    }

    @Test
    fun toStringWithDifficultyThreeTest() {

        var blockChain = BlockChain(difficulty = 3)

        populateBlockChain(blockChain)

        assertThat(blockChain.blocks.size).isEqualTo(3)
        assertThat(blockChain.blocks[0].data).isEqualTo("I'm the first")
        assertThat(blockChain.blocks[1].data).isEqualTo("I'm the second")
        assertThat(blockChain.blocks[2].data).isEqualTo("I'm the third")
        blockChain.blocks.forEach {
            assertThat(it.hash.startsWith("000"))
        }
    }

    private fun populateBlockChain(blockchain: BlockChain) {
        val genesisBlock = Block(previousHash = "0", data = "I'm the first", hash="000000123", recalcHash = false)
        val secondBlock = Block(genesisBlock.hash, data = "I'm the second", recalcHash = true)
        val thirdBlock = Block(secondBlock.hash, data = "I'm the third", recalcHash = true)

        blockchain.add(genesisBlock)
        blockchain.add(secondBlock)
        blockchain.add(thirdBlock)
    }
}