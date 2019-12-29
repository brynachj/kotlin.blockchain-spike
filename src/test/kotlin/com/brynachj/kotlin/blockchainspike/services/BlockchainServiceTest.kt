package com.brynachj.kotlin.blockchainspike.services

import org.junit.Test
import org.assertj.core.api.Assertions.assertThat

class BlockchainServiceTest {

    var blockchainService: BlockchainService = BlockchainService()

    @Test
    fun getBlockchain() {
        assertThat(blockchainService.getBlockchain()).isEqualTo("blockchain")
    }
}