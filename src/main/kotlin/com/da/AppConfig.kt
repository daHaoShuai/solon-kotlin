package com.da

import com.zaxxer.hikari.HikariDataSource
import org.noear.solon.Solon
import org.noear.solon.annotation.Bean
import org.noear.solon.annotation.Configuration
import org.noear.solon.annotation.Inject
import org.noear.weed.Command
import org.noear.weed.WeedConfig
import javax.sql.DataSource


/**
 * Author Da
 * Description: <br/>
 * 三十年生死两茫茫，写程序，到天亮。
 * 千行代码，Bug何处藏。
 * 纵使上线又怎样，朝令改，夕断肠。
 * 领导每天新想法，天天改，日日忙。
 * 相顾无言，惟有泪千行。
 * 每晚灯火阑珊处，夜难寐，又加班。
 * Date: 2022-05-14
 * Time: 15:20
 */
//配置类
@Configuration
class AppConfig {
    //配置数据源
    @Bean
    fun db1(@Inject("\${demo.db1}") ds: HikariDataSource?): DataSource? {
        return ds
    }
}

//启动入口
fun main(args: Array<String>) {
    Solon.start(AppConfig::class.java, args) {
        if (Solon.cfg().isDebugMode || Solon.cfg().isFilesMode) {
            //执行后打印sql,idea会自动帮你转换代码,有点牛
            WeedConfig.onExecuteAft { cmd: Command ->
                println(
                    """
        [Weed3]${cmd.text}
        ${cmd.paramMap()}
        """.trimIndent()
                )
            }
        }
    }
}