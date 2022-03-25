package zeus.config;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.AdviceModeImportSelector;
import zeus.annotation.EnableZeus;

/**
 * @author lym
 */
public class ZeusConfigurationSelector extends AdviceModeImportSelector<EnableZeus> {


    @Override
    protected String[] selectImports(AdviceMode adviceMode) {
        //return new String[]{ZeusRegisterOperator.class.getName()};
        return new String[]{"zeus.config.ZeusRegisterOperator"};
    }


}
