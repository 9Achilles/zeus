package zeus.config.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lym
 */
public class ZeusConfigurationSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{ZeusNameSpaceHandler.class.getName()};
    }
}
