package org.example

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/problems")
@RestController
class ProblemController(private val problemService: ProblemService) {

    @GetMapping("/any")
    fun anyProblem(): ProblemTask = problemService.get()
}