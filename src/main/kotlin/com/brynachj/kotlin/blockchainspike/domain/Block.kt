package com.brynachj.kotlin.blockchainspike.domain

import com.brynachj.kotlin.blockchainspike.hash
import java.time.Instant

data class Block(val previousHash: String,
                 val data: String,
                 val timestamp: Long = Instant.now().toEpochMilli(),
                 val nonce: Long = 0,
                 var hash: String = "") {

    init {
        hash = calculateHash()
    }

    private fun calculateHash(): String {
        return "$previousHash$data$timestamp$nonce".hash()
    }

}