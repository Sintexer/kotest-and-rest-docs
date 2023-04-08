package org.example

import io.kotest.core.spec.style.StringSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldNotBe
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [Application::class])
class ApplicationTests(val problemController: ProblemController) : StringSpec({
    "problem controller create" {
        problemController shouldNotBe null
    }
}) {
    override fun extensions() = listOf(SpringExtension)
}