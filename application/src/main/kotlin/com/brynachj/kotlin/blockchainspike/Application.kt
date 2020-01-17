package com.brynachj.kotlin.blockchainspike

import com.brynachj.kotlin.blockchainspike.domain.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigInteger
import java.security.*
import java.util.*

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)

	val blockChain = BlockChain(difficulty = 1)
	val wallet1 = Wallet.create(blockChain)
	val wallet2 = Wallet.create(blockChain)

	println("Wallet 1 balance: ${wallet1.balance}")
	println("Wallet 2 balance: ${wallet2.balance}")

	val tx1 = Transaction.create(sender = wallet1.publicKey, recipient = wallet1.publicKey, amount = 100)
	tx1.outputs.add(TransactionOutput(recipient = wallet1.publicKey, amount = 100, transactionHash = tx1.hash))

	var genesisBlock = Block(previousHash = "0", data="")
	tx1.sign(wallet1.privateKey)
	genesisBlock.addTransaction(tx1)
	genesisBlock = blockChain.add(genesisBlock)

	println("Wallet 1 balance: ${wallet1.balance}")
	println("Wallet 2 balance: ${wallet2.balance}")

	val tx2 = wallet1.sendFundsTo(recipient = wallet2.publicKey, amountToSend = 50)
	tx2.sign(wallet1.privateKey)
	var secondBlock = Block(genesisBlock.hash, data = "")
	secondBlock.addTransaction(tx2)
	blockChain.add(secondBlock)

	println("Wallet 1 balance: ${wallet1.balance}")
	println("Wallet 2 balance: ${wallet2.balance}")
}

fun String.hash(algorithm: String = "SHA-256"): String {

	val messageDigest = MessageDigest.getInstance(algorithm)
	messageDigest.update(this.toByteArray())
	return String.format("%064x", BigInteger(1, messageDigest.digest()))
}

fun String.sign(privateKey: PrivateKey, algorithm: String = "SHA256withRSA") : ByteArray {
	val rsa = Signature.getInstance(algorithm)
	rsa.initSign(privateKey)
	rsa.update(this.toByteArray())
	return rsa.sign()
}

fun String.verifySignature(publicKey: PublicKey, signature: ByteArray, algorithm: String = "SHA256withRSA") : Boolean {
	val rsa = Signature.getInstance(algorithm)
	rsa.initVerify(publicKey)
	rsa.update(this.toByteArray())
	return rsa.verify(signature)
}

fun Key.encodeToString() : String {
	return Base64.getEncoder().encodeToString(this.encoded)
}