package com.arano.component;


import com.arano.annotation.TranCode;
import com.arano.util.SpringUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TranCodeHolder
 * 启动时加载 tranCode注解的value 映射原始业务uri mapping
 *
 * @author arano
 * @since 2021/7/23 14:21
 */
public class TranCodeHolder {
    private static final Map<String, String> tranCodePathMap = new ConcurrentHashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(TranCodeHolder.class);
    private static final ObjectMapper ojectMapper = SpringUtil.getBean(ObjectMapper.class);

    static void refreshTranCode() {
        tranCodePathMap.clear();
        RequestMappingHandlerMapping requestMappingHandlerMapping = SpringUtil.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> methods = requestMappingHandlerMapping.getHandlerMethods();
        methods.entrySet()
                .stream()
                .filter(requestMappingInfoHandlerMethodEntry -> AnnotationUtils.getAnnotation(requestMappingInfoHandlerMethodEntry.getValue().getMethod(), TranCode.class) != null)
                .forEach(requestMappingInfoHandlerMethodEntry -> {
                    TranCode tranCodeAnno = AnnotationUtils.getAnnotation(requestMappingInfoHandlerMethodEntry.getValue().getMethod(), TranCode.class);
                    String path = requestMappingInfoHandlerMethodEntry.getKey()
                            .getPatternsCondition()
                            .getPatterns()
                            .stream()
                            .iterator()
                            .next();
                    String tranCode = tranCodeAnno.tranCode();
                    if (Objects.isNull(tranCode) || tranCode.equals(Strings.EMPTY)) {
                        throw new RuntimeException("tranCode is blank ");
                    }
                    if (tranCodePathMap.putIfAbsent(tranCode, path) != null) {
                        throw new IllegalArgumentException(MessageFormat.format(" tranCode must be unique ,tranCode:{1} path:{2}", tranCode, path));
                    }
                });
        try {
            logger.info(" tran code map：{}", ojectMapper.writeValueAsString(tranCodePathMap));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("serializing tranCodePathMap happened  error", e);

        }
    }

    public static String getUriByTranCode(String tranCode) {
        if (tranCode == null || tranCode.length() == 0) {
            throw new IllegalArgumentException("tranCode is empty");
        }
        return tranCodePathMap.get(tranCode);
    }


}
