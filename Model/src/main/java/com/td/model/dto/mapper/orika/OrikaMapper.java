package com.td.model.dto.mapper.orika;

import com.td.model.annotation.DTO;
import com.td.model.dto.DirtySupportDTO;
import com.td.model.entity.dictionary.company.ContractPerson;
import com.td.model.entity.dictionary.company.CustomerPersonModel;
import com.td.model.entity.dictionary.company.IAccountDetails;
import com.td.model.entity.dictionary.company.LegalAccountDetails;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;
import net.sf.corn.cps.CPScanner;
import net.sf.corn.cps.ClassFilter;
import net.sf.corn.cps.PackageNameFilter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Target;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.PropertyAccessorUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import ma.glasnost.orika.Mapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.*;
import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.util.*;

import static com.td.model.utils.ReflectionUtils.getFieldAnnotation;
import static ma.glasnost.orika.metadata.TypeFactory.typeOf;

/**
 * Created by zerotul.
 */
@Component
public class OrikaMapper extends ConfigurableMapper {

    Logger logger = Logger.getLogger(getClass());

    private final ClassMapFilter<DirtySupportDTO, Object> filter;

    private final Set<Mapper> customMappers;

    private Set<String> excludeProperties;

    @Inject
    public OrikaMapper(ClassMapFilter<DirtySupportDTO, Object> filter, Set<Mapper> customMappers) {
        super(false);
        this.filter = filter;
        this.customMappers = customMappers;
        excludeProperties = new HashSet<>();
    }


    @PostConstruct
    protected void init(){
        initExcludeProperties();
        super.init();
    }

    @Override
    public void configure (MapperFactory mapperFactory) {
        Set<Class<?>> classes = new HashSet<>(CPScanner.scanClasses(new PackageNameFilter("com.td.model.dto*"), new ClassFilter().appendAnnotation(DTO.class)));
        for(Class clazz : classes){
            DTO dto = (DTO) clazz.getAnnotation(DTO.class);
            Class destClazz = dto.mappedBy();
            ClassMapBuilder classMapBuilder = mapperFactory.classMap(clazz, destClazz)
                    .setFilter(filter);
            PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(destClazz);
            for(PropertyDescriptor descriptor : descriptors){
                PropertyDescriptor srcDescriptor = BeanUtils.getPropertyDescriptor(clazz, descriptor.getName());
                if(srcDescriptor!=null && !isExcludeProperty(descriptor.getName())) {
                    Property.Builder builder = buildDestProperty(descriptor, destClazz);
                    classMapBuilder.field(descriptor.getName(), builder);
                }
            }
            mapperFactory.registerClassMap(classMapBuilder.toClassMap());
        }
        customMappers.forEach((Mapper mapper) -> mapperFactory.registerMapper(mapper));
    }

    private boolean isExcludeProperty(String name) {
        return excludeProperties.contains(name);
    }

    private void initExcludeProperties() {
        excludeProperties.add("class");
    }

    private Property.Builder buildDestProperty(PropertyDescriptor descriptor, Class destClass) {
        try {
            Property.Builder builder = Property.Builder
                    .propertyFor(destClass, descriptor.getName())
                    .getter(descriptor.getReadMethod())
                    .setter(descriptor.getWriteMethod());
            Annotation annotation = getFieldAnnotation(descriptor, OneToMany.class);
            if (annotation != null) {
                if (!((OneToMany) annotation).targetEntity().equals(void.class)) {
                    builder.elementType(TypeFactory.valueOf(((OneToMany) annotation).targetEntity()));
                }
            } else if ((annotation = getFieldAnnotation(descriptor,OneToOne.class)) != null) {
                if (!((OneToOne) annotation).targetEntity().equals(void.class)) {
                    builder.elementType(TypeFactory.valueOf(((OneToOne) annotation).targetEntity()));
                }
            } else if ((annotation = getFieldAnnotation(descriptor,  ManyToOne.class)) != null) {
                if (!((ManyToOne) annotation).targetEntity().equals(void.class)) {
                    builder.elementType(TypeFactory.valueOf(((ManyToOne) annotation).targetEntity()));
                }
            } else if ((annotation = getFieldAnnotation(descriptor,  Target.class)) != null) {
                if (!((Target) annotation).value().equals(void.class)) {
                    builder.elementType(TypeFactory.valueOf(((Target) annotation).value()));
                }
            }else if ((annotation = getFieldAnnotation(descriptor, ManyToMany.class)) != null) {
                if (!((ManyToMany) annotation).targetEntity().equals(void.class)) {
                    builder.elementType(TypeFactory.valueOf(((ManyToMany) annotation).targetEntity()));
                }
            }
            return builder;
        }catch (NoSuchFieldException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            logger.error(writer.toString());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
      //  builder.compilerStrategy(new JavassistCompilerStrategy());
       builder.unenhanceStrategy(new HibernateUnenhanceStrategy());
       // builder.useAutoMapping(false);
    }
}
