package zeus.config;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class EnableZeusConfigSelector implements ImportSelector {

    public static String dept;

    public static String name;

    public static String host;

    public static int retries;

    public static int sessionTimeOut;

    public static int connectionTimeOut;

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> map = annotationMetadata.getAnnotationAttributes("zeus.annotation.EnableZeus");

        this.dept = (String) map.get("dept");
        this.name = (String) map.get("name");
        this.host = (String) map.get("host");
        this.retries = (Integer) map.get("retries");
        this.sessionTimeOut = (Integer) map.get("sessionTimeOut");
        this.connectionTimeOut = (Integer) map.get("connectionTimeOut");

        return new String[]{"zeus.constant.ZeusAnnotation"};
    }


}
