package com.td.model.aop;

import com.td.model.dto.DirtySupportDTO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;

/**
 * Created by zerotul.
 */
@Aspect
public class DTODirtyFieldsAspect {

    @Pointcut("execution(void com.td.model.dto.DirtySupportDTO+.set*(..)) && !@annotation(com.td.model.annotation.SkipDirty)")
    public void dtoSetter() {
    }

    private static class DTODirtyFieldsAspectHolder {
        static final DTODirtyFieldsAspect instance = new DTODirtyFieldsAspect();
    }

    public static DTODirtyFieldsAspect aspectOf(){
        return DTODirtyFieldsAspectHolder.instance;
    }

    @Before(value = "dtoSetter()")
    public void addDirtyField(JoinPoint pjp) throws Throwable {
        DirtySupportDTO baseDTO = (DirtySupportDTO) pjp.getThis();
        Signature signature = pjp.getSignature();
        if (signature instanceof MethodSignature) {
            PropertyDescriptor desc = BeanUtils.findPropertyForMethod(((MethodSignature) signature).getMethod());
            if (desc != null) {
                baseDTO.addDirtyField(desc.getName());
            }
        }
    }
}
