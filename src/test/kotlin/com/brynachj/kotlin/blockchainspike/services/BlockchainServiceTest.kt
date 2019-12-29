package com.brynachj.kotlin.blockchainspike.services

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class BlockchainServiceTest {

    var blockchainService: BlockchainService = BlockchainService()

    @Test
    fun getBlockchain() {
        assertThat(blockchainService.getBlockchain()).isEqualTo("blockchain")
    }
}