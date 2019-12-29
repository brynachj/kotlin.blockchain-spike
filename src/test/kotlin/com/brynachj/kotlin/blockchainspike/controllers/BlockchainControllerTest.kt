package com.brynachj.kotlin.blockchainspike.controllers

import com.brynachj.kotlin.blockchainspike.services.BlockchainService
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@RunWith(SpringRunner::class)
@WebMvcTest(BlockchainController::class)
class BlockchainControllerTest {

    private val BLOCKCHAIN_SERVICE_RESULT: String = "blockchain service response"
    @MockBean
    lateinit var blockchainService: BlockchainService
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getBlockchainEndpointCallsBlockchainService() {
        given(blockchainService.getBlockchain()).willReturn(BLOCKCHAIN_SERVICE_RESULT)
        mockMvc.get("/blockchain").andExpect {
            status { isOk }
            content { string(BLOCKCHAIN_SERVICE_RESULT) }
        }
    }
}