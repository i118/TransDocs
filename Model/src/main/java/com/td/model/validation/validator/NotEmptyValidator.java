package com.td.model.validation.validator;

import com.td.model.entity.IPersistent;
import com.td.model.validation.annotation.NotEmpty;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Created by konstantinchipunov on 07.08.14.
 */
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, Object> {

    private String fieldName;

    @Override
    public void initialize(NotEmpty notEmpty) {
      this.fieldName = notEmpty.fieldName();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        if(obj instanceof IPersistent){
            IPersistent persistent = (IPersistent) obj;
            try {
              if(fieldName == null) throw new IllegalStateException("field not determined");

              BeanInfo beanInfo = Introspector.getBeanInfo(persistent.getClass());
              PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
              for(PropertyDescriptor descriptor : propertyDescriptors){
                  if(descriptor.getName().equals(fieldName)){
                      Method readMethod = descriptor.getReadMethod();
                      if(readMethod!=null){
                          try {
                             Object fieldValue = readMethod.invoke(persistent, new Object[]{});
                             return validateValue(fieldValue);
                          } catch (IllegalAccessException e) {
                              throw new IllegalStateException();
                          } catch (InvocationTargetException e) {
                              throw new IllegalStateException();
                          }
                      } else {
                          throw new IllegalStateException("getter for "+persistent.getClass().getName()+"#"+fieldName+"not found");
                      }
                  }
              }
            } catch (IntrospectionException e) {
                throw new IllegalStateException(e);
            }
        }else{
          return validateValue(obj);
        }
        return false;
    }

    protected boolean validateValue(Object value){
        if(value==null) return false;
        if(value instanceof String){
            return StringUtils.hasText(((String) value).trim());
        } else if(value instanceof Number){
           return  ((Number)value).doubleValue() > 0;
        }else if(value instanceof Collection){
            return !((Collection)value).isEmpty();
        }

      return true;
    }
}
