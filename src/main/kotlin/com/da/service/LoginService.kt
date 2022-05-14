package com.da.service

import org.noear.solon.annotation.Component
import org.noear.solon.core.handle.Context
import org.noear.solon.validation.annotation.Logined
import org.noear.solon.validation.annotation.LoginedChecker

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
 * Time: 15:41
 */
@Component // 忘记加到容器中了
class LoginService : LoginedChecker {
    override fun check(anno: Logined?, ctx: Context?, userKeyName: String?): Boolean {
        println(ctx?.path().equals("/login"))
//        这里处理登录逻辑
//        如果当前的路径不是登录就获取header中有没有token
        if (!ctx?.path().equals("/login")) {
            val token = ctx?.header("token")
//            没有token就不给进去
            if (token.equals("") || token == null) {
                return false
            }
            return true
        }
//        放行/login接口
        return true
    }
}