package com.da.po

import org.noear.weed.annotation.PrimaryKey
import org.noear.weed.annotation.Table

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
 * Time: 15:30
 */
//实体类
@Table("user") // 表名
class User {
    @PrimaryKey // 主键
    var id: Int? = null
    var name: String? = null
    var pass: String? = null
    var deleted: Int? = null
}