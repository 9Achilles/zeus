package zeus.annotation;

import zeus.constant.ZeusZKDeptConstant;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableZeus {

    String dept() default ZeusZKDeptConstant.ZEUS_PUBLIC_DEPT;

    String name() default "";

    String host() default "";

    int retries() default 0;

    int sessionTimeOut() default 30000;

    int connectionTimeOut() default 10000;

}
