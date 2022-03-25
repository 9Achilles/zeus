package zeus.annotation;

import org.springframework.context.annotation.Import;
import zeus.config.ZeusConfigurationSelector;

import java.lang.annotation.*;

/**
 * @author lym
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ZeusConfigurationSelector.class)
public @interface EnableZeus {

}
