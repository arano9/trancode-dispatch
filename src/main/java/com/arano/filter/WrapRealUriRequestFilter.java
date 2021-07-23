package com.arano.filter;

import com.arano.model.http.RealUriRequestWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * WrapRealUriRequestFilter
 * <ul>
 *     <li>{@link RealUriRequestWrapper#getRequestURI()}</li>
 * </ul>
 *
 * @author arano
 * @since 2021/7/23 14:35
 */
public class WrapRealUriRequestFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        filterChain.doFilter(new RealUriRequestWrapper(request), response);
    }
}
