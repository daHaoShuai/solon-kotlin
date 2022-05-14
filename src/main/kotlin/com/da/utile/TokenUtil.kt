package com.da.utile

import com.da.po.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.noear.solon.annotation.Component
import org.noear.solon.annotation.Inject
import java.util.*

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
 * Time: 15:51
 * 生成和解析jwt的工具类
 */
@Component
class TokenUtil {

    @Inject("\${jwt.time}")
    var time: Int? = null

    @Inject("\${jwt.key}")
    var key: String? = null

    //    生成jwt
    fun createJwt(user: User): String {
//        忘记怎么写了,看看
        return Jwts.builder()
            .claim("name", user.name)
            .setId(UUID.randomUUID().toString())
//                设置失效时间
            .setExpiration(Date(System.currentTimeMillis() + time!!))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact()
    }

    //    解析jwt
    fun parseJwt(token: String): Boolean {
//        解析成功就说明没有问题
        return try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

}