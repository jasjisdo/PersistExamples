package de.hbmexample2.util;

import de.hbmexample2.service.AbstractDbService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.beans.Introspector;

/**
 * Created by domann on 12.05.15.
 */
public class ContextUtil {

    public static <T extends AbstractDbService> T getDbService(String contextLocation, Class<T> type){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextLocation);
        return type.cast( context.getBean(Introspector.decapitalize(type.getSimpleName())) );
    }
    
    public static <T extends AbstractDbService> T getDbService(ClassPathXmlApplicationContext context, Class<T> type){
        return type.cast( context.getBean(Introspector.decapitalize(type.getSimpleName())) );
    }

    public static <T extends AbstractDbService> T getDbDataSource(String contextLocation, Class<T> type){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(contextLocation);
        return type.cast( context.getBean(Introspector.decapitalize(type.getSimpleName())) );
    }

    public static <T extends DataSource> T getDbDataSource(ClassPathXmlApplicationContext context, Class<T> type){
        return type.cast( context.getBean(Introspector.decapitalize(type.getSimpleName())) );
    }
}
