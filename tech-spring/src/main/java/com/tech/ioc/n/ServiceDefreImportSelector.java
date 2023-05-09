package com.tech.ioc.n;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author lw
 * @since 2023-05-09
 */
public class ServiceDefreImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{Tank.class.getName()};
    }
}
