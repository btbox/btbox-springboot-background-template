package com.btbox.framework.config;

import com.btbox.common.annotation.TranslationType;
import com.btbox.common.translation.TranslationInterface;
import com.btbox.common.translation.handler.TranslationBeanSerializerModifier;
import com.btbox.common.translation.handler.TranslationHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
 * 翻译模块配置类
 *
 * @author Lion Li
 */
@Slf4j
@Configuration
public class TranslationConfig {

    // @Autowired
    // private List<TranslationInterface<?>> list;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        // 当有 TranslationInterface 的 translation 方法实现类后可以解开如下代码和上面的 List<TranslationInterface<?>> list;
        // Map<String, TranslationInterface<?>> map = new HashMap<>(list.size());
        // for (TranslationInterface<?> trans : list) {
        //     if (trans.getClass().isAnnotationPresent(TranslationType.class)) {
        //         TranslationType annotation = trans.getClass().getAnnotation(TranslationType.class);
        //         map.put(annotation.type(), trans);
        //     } else {
        //         log.warn(trans.getClass().getName() + " 翻译实现类未标注 TranslationType 注解!");
        //     }
        // }
        // TranslationHandler.TRANSLATION_MAPPER.putAll(map);
        // 设置 Bean 序列化修改器
        objectMapper.setSerializerFactory(
            objectMapper.getSerializerFactory()
                .withSerializerModifier(new TranslationBeanSerializerModifier()));
    }

}
