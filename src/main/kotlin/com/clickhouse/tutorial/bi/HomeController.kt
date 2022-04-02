package com.clickhouse.tutorial.bi

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/bi")
class HomeController {

    @GetMapping(path = ["", "/", "/index"])
    fun index() = "index"

}