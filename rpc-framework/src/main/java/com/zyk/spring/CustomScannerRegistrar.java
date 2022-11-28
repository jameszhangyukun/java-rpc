package com.zyk.spring;

import com.zyk.annotation.RpcScan;
import com.zyk.annotation.RpcService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * scan and filter specified annotations
 */
@Slf4j
public class CustomScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {

    private static final String SPRING_BEAN_BASE_PACKAGE = "com.zyk";
    private static final String BASE_PACKAGE_ATTRIBUTE_NAME = "basePackage";


    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes rpcScanAnnotationAttributes = AnnotationAttributes.
                fromMap(annotationMetadata.getAnnotationAttributes(RpcScan.class.getName()));

        String[] rpcScanBasePackage = new String[0];
        if (rpcScanAnnotationAttributes != null) {
            rpcScanBasePackage = rpcScanAnnotationAttributes.getStringArray(BASE_PACKAGE_ATTRIBUTE_NAME);
        }
        if (rpcScanBasePackage.length == 0) {
            rpcScanBasePackage = new String[]{
                    ((StandardAnnotationMetadata) annotationMetadata).getIntrospectedClass().getPackage().getName()
            };
        }
        CustomScanner rpcServiceScanner = new CustomScanner(beanDefinitionRegistry, RpcService.class);

        CustomScanner springBeanScanner = new CustomScanner(beanDefinitionRegistry, Component.class);

        if (resourceLoader != null) {
            rpcServiceScanner.setResourceLoader(resourceLoader);
            springBeanScanner.setResourceLoader(resourceLoader);
        }
        int springBeanAmount = springBeanScanner.scan(SPRING_BEAN_BASE_PACKAGE);
        log.info("springBeanScanner扫描的数量 [{}]", springBeanAmount);
        int rpcServiceCount = rpcServiceScanner.scan(rpcScanBasePackage);
        log.info("rpcServiceScanner扫描的数量 [{}]", rpcServiceCount);
    }
}
