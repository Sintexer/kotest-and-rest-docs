package org.example

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import kotlin.random.Random

@Service
class ProblemServiceImpl : ProblemService {

    private val problemsIdGenerator = AtomicLong()

    override fun get() = ProblemTask(problemsIdGenerator.incrementAndGet().toString(), generateProblemDefinition())

    private fun generateProblemDefinition(): String {
        return "${Random.nextInt(-100, 100)} + ${Random.nextInt(-100, 100)} = ?"
    }
}