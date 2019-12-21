package com.brynachj.kotlin.blockchainspike

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.math.BigInteger
import java.security.MessageDigest

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)

	val genesisBlock = Block(previousHash = "0", data = "I'm the first")
	val secondBlock = Block(genesisBlock.hash, data = "I'm the second")
	val thirdBlock = Block(secondBlock.hash, data = "I'm the first")

	println(genesisBlock)
	println(secondBlock)
	println(thirdBlock)
}

fun String.hash(algorithm: String = "SHA-256"): String {

	val messageDigest = MessageDigest.getInstance(algorithm)
	messageDigest.update(this.toByteArray())
	return String.format("%064x", BigInteger(1, messageDigest.digest()))
}