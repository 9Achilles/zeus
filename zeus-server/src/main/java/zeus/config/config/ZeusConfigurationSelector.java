package zeus.config.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import zeus.config.annotation.EnableZeus;

/**
 * @author lym
 */
public class ZeusConfigurationSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{ZeusRegisterOperator.class.getName()};
    }
}
