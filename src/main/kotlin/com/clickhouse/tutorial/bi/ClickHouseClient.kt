package com.clickhouse.tutorial.bi

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ru.yandex.clickhouse.ClickHouseConnection
import ru.yandex.clickhouse.ClickHouseDataSource
import ru.yandex.clickhouse.settings.ClickHouseProperties
import java.sql.Connection
import java.sql.ResultSet
import java.sql.SQLException
import java.sql.Statement
import javax.annotation.Resource


@Component
class ClickHouseClient {
    private val log = LoggerFactory.getLogger(ClickHouseClient::class.java)

    fun executeSQL(sql: String): MutableList<JSONObject> {
        log.info("cliockhouse 执行sql：$sql")
        val connection: Connection = getConn()
        try {
            val statement: Statement = connection.createStatement()
            val results: ResultSet = statement.executeQuery(sql)
            val rsmd = results.metaData
            val list: MutableList<JSONObject> = mutableListOf()
            while (results.next()) {
                val row = JSONObject()
                for (i in 1..rsmd.columnCount) {
                    row.put(rsmd.getColumnName(i), results.getString(rsmd.getColumnName(i)))
                }
                list.add(row)
            }
            return list
        } catch (e: SQLException) {
            log.error("execute sql error, SQL={}", sql, e)
            throw IllegalStateException("execute sql error!SQL=${sql}")
        }
    }

    @Resource
    lateinit var clickHouseDataSourceConfig: ClickHouseDataSourceConfig

    fun getConn(): Connection {
        val conn: ClickHouseConnection
        val properties = ClickHouseProperties()
        properties.user = clickHouseDataSourceConfig.username
        properties.password = clickHouseDataSourceConfig.password
        properties.database = clickHouseDataSourceConfig.database
        properties.socketTimeout = clickHouseDataSourceConfig.socketTimeout
        val clickHouseDataSource = ClickHouseDataSource(clickHouseDataSourceConfig.url, properties)
        try {
            conn = clickHouseDataSource.connection
            return conn
        } catch (e: SQLException) {
            log.error("getConn properties={}, ERROR:", JSON.toJSONString(properties), e)
            throw IllegalStateException("get ClickHouseConnection error!")
        }
    }


}