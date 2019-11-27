package com.server.venus.annotation;

import java.lang.annotation.*;

/**
 * 项目名称：venus-admin-server
 * 类名称：LogAnnotation
 * 类描述：日志管理Controller注解类
 * 创建人：yingx
 * 创建时间： 2019/11/27
 * 修改人：yingx
 * 修改时间： 2019/11/27
 * 修改备注：
 */
@Target({ElementType.METHOD, ElementType.TYPE}) // 表示该注解可以使用在类/接口还有方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时注解
@Inherited // 可以被子类继承
@Documented // 执行javadoc时，注解会生成相关文档
public @interface LogAnnotation {

    /**
     * 用户操作的步骤说明
     */
    String description() default "";
}
