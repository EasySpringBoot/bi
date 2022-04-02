package com.clickhouse.tutorial.bi

import com.alibaba.fastjson.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.Resource


@RestController
@RequestMapping("/bi/v1/api")
class APIQueryController {

    @Resource
    lateinit var clickHouseClient: ClickHouseClient

    @GetMapping(path = ["/", ""])
    fun index() = "success"

    @PostMapping(path = ["/query"])
    fun query(sql: String): List<JSONObject> {
        val s = System.currentTimeMillis()
        val result = clickHouseClient.executeSQL(sql)
        val e = System.currentTimeMillis()
        val executeTime = JSONObject()
        executeTime.put("_EXECUTE_TIME", "${e - s}ms")
        result.add(executeTime)
        return result
    }

}