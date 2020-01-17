package com.brynachj.kotlin.blockchainspike.domain

import com.brynachj.kotlin.blockchainspike.encodeToString
import com.brynachj.kotlin.blockchainspike.hash
import com.brynachj.kotlin.blockchainspike.sign
import com.brynachj.kotlin.blockchainspike.verifySignature
import java.security.PrivateKey
import java.security.PublicKey

data class Transaction(val sender: PublicKey,
                       val recipient: PublicKey,
                       val amount: Int,
                       var hash: String = "",
                       val inputs: MutableList<TransactionOutput> = mutableListOf(),
                       val outputs: MutableList<TransactionOutput> = mutableListOf()) {

    private var signature: ByteArray = ByteArray(0)

    init {
        hash = "${sender.encodeToString()}${recipient.encodeToString()}$amount$salt".hash()
    }

    companion object {
        fun create(sender: PublicKey, recipient: PublicKey, amount: Int): Transaction {
            return Transaction(sender, recipient, amount)
        }

        var salt: Long = 0
            get() {
                field += 1
                return field
            }
    }

    fun sign(privateKey: PrivateKey): Transaction {
        signature = "${sender.encodeToString()}${recipient.encodeToString()}$amount".sign(privateKey)
        return this
    }

    fun isSignatureValid(): Boolean {
        return "${sender.encodeToString()}${recipient.encodeToString()}$amount".verifySignature(sender, signature)
    }
}