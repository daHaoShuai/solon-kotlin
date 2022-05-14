package com.da.controller

import com.da.po.User
import com.da.utile.TokenUtil
import org.noear.solon.annotation.*
import org.noear.solon.core.handle.Result
import org.noear.solon.validation.annotation.Logined
import org.noear.solon.validation.annotation.NotNull
import org.noear.solon.validation.annotation.Valid
import org.noear.weed.DbContext
import org.noear.weed.annotation.Db

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
 * Time: 15:21
 */
@Valid //这个注解一定要加类上（或者基类上）
@Logined // 要登录了才给访问
@Controller // 整个接口
class IndexController {

    @Db
    var db: DbContext? = null

    @Inject // jwt工具类
    var tokenUtil: TokenUtil? = null

    @Mapping("/index")
    @NotNull("name") // name不允许为空
//    允许name为空,kotlin是这样写
//    我们发现收不到参数
//    有2种解决办法,使用@Param("name") ,或者 让 kotlin 编译保持参数名不变
    fun index(name: String?): String {
        return "hello $name"
    }

    @Mapping("/users")
    fun list(): Result<MutableList<User>>? {
        val list = db?.table("user")?.selectList("*", User::class.java)
        return Result.succeed(list)
    }

    //    登录接口
    @Post
    @Mapping("/login")
    fun login(name: String?, pass: String?): Result<Any> {
        val user: User? = db?.mapperBase(User::class.java)?.selectItem { it ->
//            账号和密码要一致才登录成功
            it.whereEq(User::name.name, name)
                .andEq(User::pass.name, pass)
        }
//        查出来id不为null说明用户登录成功了
        return if (user?.id !== null) {
//            生成token
            val token = tokenUtil?.createJwt(user)
            Result.succeed(token)
        } else {
            Result.failure("登录失败")
        }
    }


}