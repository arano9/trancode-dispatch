package com.arano.model.http;


import com.arano.component.TranCodeHolder;
import com.arano.constant.WebConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Objects;


/**
 * RealUriRequestWrapper
 * 用于覆写 返回真实 path
 *
 * @author arano
 * @since 2021/7/23 14:48
 */
public class RealUriRequestWrapper extends HttpServletRequestWrapper {


    private static final Logger logger = LoggerFactory.getLogger(RealUriRequestWrapper.class);

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public RealUriRequestWrapper(HttpServletRequest request) {
        super(request);
    }


    @Override
    public String getRequestURI() {
        //如果是请求统一入口
        String uri = super.getRequestURI();
        if (WebConstant.URI_PORTAL.equals(uri)) {
            String tranCode = getHeader(WebConstant.HTTP_HEADER_TRAN_CODE);
            uri = TranCodeHolder.getUriByTranCode(tranCode);
            logger.info("tranCode:{},uri:{}", tranCode, uri);
            if (Objects.isNull(uri) || uri.length() == 0) {
                logger.error("wrong http tran code header value");
                uri = "";
            }
        }
        return uri;
    }

}
