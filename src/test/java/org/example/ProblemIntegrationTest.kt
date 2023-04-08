package org.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.restdocs.ManualRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
@WebAppConfiguration       // (1)
@ContextConfiguration(classes = [Application::class])
class ProblemIntegrationTest(
    val webApplicationContext: WebApplicationContext    // (2)
) : StringSpec({

    val restDocumentation = ManualRestDocumentation()   // (3)

    lateinit var mockMvc: MockMvc   // (4)

    beforeAny {  // (5)
        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)  // (2)
            .apply<DefaultMockMvcBuilder>(MockMvcRestDocumentation.documentationConfiguration(restDocumentation))
            .build()
        restDocumentation.beforeTest(ProblemIntegrationTest::class.java, it.name.testName)
    }

    afterAny {  // (6)
        restDocumentation.afterTest()
    }

    // (7)
    "GET /api/problems/any returns something" {
        mockMvc.perform(get("/api/problems/any").accept(APPLICATION_JSON))
            .andExpect(status().isOk)
            .andDo(document("problem"))  // (8)
    }

}) {
    override fun extensions() = listOf(SpringExtension)
}