package com.brynachj.kotlin.blockchainspike.controllers

import com.brynachj.kotlin.blockchainspike.services.BlockchainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class BlockchainController {

    @Autowired
    lateinit var blockchainService: BlockchainService

    @GetMapping("/blockchain")
    fun getBlockchain(): String {
        return blockchainService.getBlockchain()
    }
}