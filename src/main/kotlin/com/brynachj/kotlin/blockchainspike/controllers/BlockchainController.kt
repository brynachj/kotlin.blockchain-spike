package com.brynachj.kotlin.blockchainspike.controllers

import com.brynachj.kotlin.blockchainspike.domain.Block
import com.brynachj.kotlin.blockchainspike.services.BlockchainService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class BlockchainController {

    @Autowired
    lateinit var blockchainService: BlockchainService

    @GetMapping("/blockchain")
    fun getBlockchain(): String {
        return blockchainService.getBlockchain()
    }

    @PostMapping("/addBlock")
    fun addBlock(@RequestBody block: Block): String {
        return blockchainService.addBlock(block)
    }
}