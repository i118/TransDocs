package com.td.service.context;

import com.td.service.context.qualifier.PermissionEvaluatorQualifier;
import com.td.service.permit.ComplexPermissionEvaluator;
import com.td.service.permit.PermissionActionEvaluatorFactory;
import com.td.service.permit.PermissionActionEvaluatorFactoryImpl;
import com.td.service.permit.PermissionEvaluatorAdapter;
import com.td.service.permit.file.ChangeFilePermitAction;
import com.td.service.permit.file.CheckInFilePermitAction;
import com.td.service.permit.file.CheckoutFilePermitAction;
import com.td.service.permit.user.EditUserPermitAction;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zerotul on 02.04.15.
 */
@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled=true
      , mode = AdviceMode.ASPECTJ
)
public class PermissionContext extends GlobalMethodSecurityConfiguration {


    @Bean
    @PermissionEvaluatorQualifier
    public <T extends PermissionEvaluatorAdapter & PermissionEvaluator> T complexPermissionEvaluator(){
        return (T) new ComplexPermissionEvaluator();
    }

    @Bean
    public PermissionActionEvaluatorFactory evaluatorFactory(){
      return new PermissionActionEvaluatorFactoryImpl(evaluatorMap());
    }

    @Bean
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(complexPermissionEvaluator());
        return expressionHandler;
    }

    private Map<String, PermissionEvaluatorAdapter> evaluatorMap(){
       Map<String, PermissionEvaluatorAdapter> map = new HashMap<>();
        map.put(EditUserPermitAction.ACTION_NAME, editUserPermitAction());
        map.put("CreateUserAction", editUserPermitAction());
        map.put("DeleteUserAction", editUserPermitAction());
        map.put("RestoreUserAction", editUserPermitAction());
        map.put("ShowDeletedUsers", editUserPermitAction());
        map.put(CheckInFilePermitAction.ACTION_NAME, checkInFilePermitAction());
        map.put(CheckoutFilePermitAction.ACTION_NAME, checkoutFilePermitAction());
        map.put("RenameFile", changeFilePermitAction());
        map.put("DeleteFile", changeFilePermitAction());
        return map;
    }

    @Bean
    public PermissionEvaluatorAdapter editUserPermitAction(){
        return new EditUserPermitAction();
    }

    @Bean
    public PermissionEvaluatorAdapter checkInFilePermitAction(){
        return new CheckInFilePermitAction();
    }

    @Bean
    public PermissionEvaluatorAdapter checkoutFilePermitAction(){
        return new CheckoutFilePermitAction();
    }

    @Bean
    public PermissionEvaluatorAdapter changeFilePermitAction(){
        return new ChangeFilePermitAction();
    }
}
