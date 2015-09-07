package com.td.model.dto.mapper.orika;

import com.td.model.annotation.DTO;
import com.td.model.annotation.ExcludeMapping;
import com.td.model.dto.DirtySupportDTO;
import ma.glasnost.orika.*;
import ma.glasnost.orika.impl.ConfigurableMapper;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.generator.EclipseJdtCompilerStrategy;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import ma.glasnost.orika.metadata.Property;
import ma.glasnost.orika.metadata.TypeFactory;
import ma.glasnost.orika.unenhance.HibernateUnenhanceStrategy;
import net.sf.corn.cps.CPScanner;
import net.sf.corn.cps.ClassFilter;
import net.sf.corn.cps.PackageNameFilter;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Target;
import org.springframework.beans.BeanUtils;
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
import static org.springframework.util.StringUtils.hasText;

/**
 * Created by zerotul.
 */
@Component
public class OrikaMapper extends ConfigurableMapper {

    Logger logger = Logger.getLogger(getClass());

    private final ClassMapFilter<DirtySupportDTO, Object> filter;

    private final CustomMapperFactory customMapperFactory;

    private Set<String> excludeProperties;

    @Inject
    public OrikaMapper(ClassMapFilter<DirtySupportDTO, Object> filter, CustomMapperFactory customMapperFactory) {
        super(false);
        this.filter = filter;
        this.customMapperFactory = customMapperFactory;
        excludeProperties = new HashSet<>();
    }


    @PostConstruct
    protected void init() {
        initSkipProperties();
        super.init();
    }

    @Override
    public void configure(MapperFactory mapperFactory) {
        try {
            Set<Class<?>> classes = new HashSet<>(CPScanner.scanClasses(new PackageNameFilter("com.td.model.dto*"), new ClassFilter().appendAnnotation(DTO.class)));
            for (Class clazz : classes) {
                DTO dto = (DTO) clazz.getAnnotation(DTO.class);
                Class destClazz = dto.mappedBy();
                ClassMapBuilder classMapBuilder = mapperFactory.classMap(clazz, destClazz)
                        .setFilter(filter);
                PropertyDescriptor[] descriptors = BeanUtils.getPropertyDescriptors(destClazz);
                Set<String> excludeProperties = new HashSet<>();
                for (PropertyDescriptor descriptor : descriptors) {
                    PropertyDescriptor srcDescriptor = BeanUtils.getPropertyDescriptor(clazz, descriptor.getName());
                    if (srcDescriptor != null && !isSkipProperty(descriptor.getName())) {
                        ExcludeMapping excludeMapping = getFieldAnnotation(srcDescriptor, ExcludeMapping.class);
                        if(excludeMapping!=null){
                            excludeProperties.add(srcDescriptor.getName());
                        }
                        Property.Builder builder = buildDestProperty(descriptor, destClazz);
                        classMapBuilder.field(descriptor.getName(), builder);
                    }
                }
                NamedCustomMapper customMapper = customMapperFactory.getNamedMapper(dto.customMapperName());
                if (customMapper != null) {
                    classMapBuilder.customize(customMapper);
                }
                excludeProperties.forEach((String property)->{
                    classMapBuilder.exclude(property);
                });
                mapperFactory.registerClassMap(classMapBuilder.toClassMap());
            }
            customMapperFactory.getCustomMappers().forEach((Mapper mapper) -> mapperFactory.registerMapper(mapper));
        } catch (NoSuchFieldException e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            logger.error(writer.toString());
            throw new RuntimeException(e);
        }
    }

    private boolean isSkipProperty(String name) {
        return excludeProperties.contains(name);
    }

    private void initSkipProperties() {
        excludeProperties.add("class");
    }

    private Property.Builder buildDestProperty(PropertyDescriptor descriptor, Class destClass) throws NoSuchFieldException {

        Property.Builder builder = Property.Builder
                .propertyFor(destClass, descriptor.getName())
                .getter(descriptor.getReadMethod())
                .setter(descriptor.getWriteMethod());
        Annotation annotation = getFieldAnnotation(descriptor, OneToMany.class);
        if (annotation != null) {
            if (!((OneToMany) annotation).targetEntity().equals(void.class)) {
                builder.elementType(TypeFactory.valueOf(((OneToMany) annotation).targetEntity()));
            }
        } else if ((annotation = getFieldAnnotation(descriptor, OneToOne.class)) != null) {
            if (!((OneToOne) annotation).targetEntity().equals(void.class)) {
                builder.elementType(TypeFactory.valueOf(((OneToOne) annotation).targetEntity()));
            }
        } else if ((annotation = getFieldAnnotation(descriptor, ManyToOne.class)) != null) {
            if (!((ManyToOne) annotation).targetEntity().equals(void.class)) {
                builder.elementType(TypeFactory.valueOf(((ManyToOne) annotation).targetEntity()));
            }
        } else if ((annotation = getFieldAnnotation(descriptor, Target.class)) != null) {
            if (!((Target) annotation).value().equals(void.class)) {
                builder.elementType(TypeFactory.valueOf(((Target) annotation).value()));
            }
        } else if ((annotation = getFieldAnnotation(descriptor, ManyToMany.class)) != null) {
            if (!((ManyToMany) annotation).targetEntity().equals(void.class)) {
                builder.elementType(TypeFactory.valueOf(((ManyToMany) annotation).targetEntity()));
            }
        }
        return builder;
    }

    @Override
    public void configureFactoryBuilder(DefaultMapperFactory.Builder builder) {
       // builder.compilerStrategy(new EclipseJdtCompilerStrategy());
        builder.unenhanceStrategy(new HibernateUnenhanceStrategy());
        builder.useAutoMapping(false);
    }
}
