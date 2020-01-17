package com.brynachj.kotlin.blockchainspike.domain

import com.brynachj.kotlin.blockchainspike.encodeToString
import com.brynachj.kotlin.blockchainspike.hash
import java.security.PublicKey

data class TransactionOutput(val recipient: PublicKey,
                             val amount: Int,
                             val transactionHash: String,
                             var hash: String = "") {
    init {
        hash = "${recipient.encodeToString()}$amount$transactionHash".hash()
    }

    fun isMine(me: PublicKey) : Boolean {
        return recipient == me
    }
}
