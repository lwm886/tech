package com.tech.ioc.m;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lw
 * @since 2023-05-09
 */
public class ServiceImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String className = importingClassMetadata.getClassName();
        System.out.println(className);
        return new String[]{"com.tech.ioc.m.Tank"};
    }
}
