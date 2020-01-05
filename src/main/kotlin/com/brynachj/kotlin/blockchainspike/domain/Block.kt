package com.brynachj.kotlin.blockchainspike.domain

import com.brynachj.kotlin.blockchainspike.hash
import java.time.Instant

data class Block(val previousHash: String = "0",
                 val data: String,
                 val timestamp: Long = Instant.now().toEpochMilli(),
                 val nonce: Long = 0,
                 var hash: String = "",
                 val recalcHash: Boolean = true,
                 val transactions: MutableList<Transaction> = mutableListOf()) {

    init {
        hash = calculateHash()
    }

    fun calculateHash(): String {
        return if(recalcHash) "$previousHash$data$timestamp$nonce".hash() else hash
    }

    fun addTransaction(transaction: Transaction) : Block {

        if (transaction.isSignatureValid())
            transactions.add(transaction)
        return this
    }

}