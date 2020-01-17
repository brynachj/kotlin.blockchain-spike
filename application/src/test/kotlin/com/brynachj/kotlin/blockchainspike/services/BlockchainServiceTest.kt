package com.brynachj.kotlin.blockchainspike.services

import com.brynachj.kotlin.blockchainspike.domain.Block
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BlockchainServiceTest {

    @Test
    fun getEmptyBlockchain() {
        var blockchainService = BlockchainService()
        assertThat(blockchainService.getBlockchain()).isEqualTo("[]")
    }

    @Test
    fun addBlock() {
        var blockchainService = BlockchainService()
        val block = Block(previousHash = "1234", data = "Welcome to big school", hash="00000000000000023654", recalcHash = true)
        assertThat(blockchainService.addBlock(block)).isEqualTo(block.hash)
    }

    @Test
    fun getNonEmptyBlockchain() {
        var blockchainService = BlockchainService()
        val block = Block(previousHash = "1234", data = "Welcome to big school", hash="00000000000000023654", recalcHash = true)
        blockchainService.addBlock(block)

        assertThat(blockchainService.getBlockchain()).contains("Block(previousHash=1234, data=Welcome to big school", "nonce=0, hash=00000000000000023654)")
    }
}