package com.td.model.factory;

import com.td.model.entity.IPersistent;
import net.sf.corn.cps.CPScanner;
import net.sf.corn.cps.ClassFilter;
import net.sf.corn.cps.PackageNameFilter;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.persistence.Table;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by konstantinchipunov on 22.09.14.
 */
public class TypeFactory implements ITypeFactory{

    Logger logger = Logger.getLogger(getClass());

    private Map<String, Class> typeMap;

    @PostConstruct
    public void init(){
        Map<String, Class> map = new HashMap<>();
        List<Class<?>> classes = CPScanner.scanClasses(new PackageNameFilter("com.td.model.entity*"), new ClassFilter().appendAnnotation(Table.class));
        for(Class clazz : classes){
           Table table = (Table) clazz.getAnnotation(Table.class);
           if(table!=null){
               String typeName = table.name();
               map.put(typeName, clazz);
           }
        }
        typeMap = Collections.unmodifiableMap(map);
    }

    @Override
    public Class getClassByType(String type) {
        return typeMap.get(type);
    }

    public <T extends IPersistent> T createObjectByType(String type){
        Class<T> objectClass = getClassByType(type);
        if(objectClass==null) throw new IllegalArgumentException("file class not found. fileType = "+type);
        try {
            return objectClass.newInstance();
        } catch (Exception e) {
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            logger.error(writer.toString());
            throw new RuntimeException(e);
        }
    }

}
