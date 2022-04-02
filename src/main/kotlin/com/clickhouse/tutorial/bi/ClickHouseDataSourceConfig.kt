package com.clickhouse.tutorial.bi

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring.datasource.clickhouse")
class ClickHouseDataSourceConfig {
    var url: String? = null
    var initialSize: Int = 0
    var maxActive: Int = 0
    var username: String? = null
    var password: String? = null
    var database: String? = null
    var socketTimeout: Int = 0
}