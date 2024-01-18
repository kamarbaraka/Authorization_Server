package org.kamar.authorization_server.rate_limit.filter;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.local.LocalBucket;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class GlobalRateLimitFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        /*create a limit*/
        Bandwidth limit = Bandwidth.simple(1, Duration.ofMinutes(1));

        /*create a bucket*/
        LocalBucket bucket = Bucket.builder().addLimit(limit).build();

        /*consume a token in the bucket*/
        if (bucket.tryConsume(1)) {

            /*proceed*/
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());

    }


    public LoadingCache<String , Bucket> loadingCache(){

        /*create a limit */
        Bandwidth limit = Bandwidth.simple(1, Duration.ofMinutes(1));

        /*create buckets*/
        return CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .build(
                        CacheLoader.from(key -> Bucket.builder().addLimit(limit).build())
                );
    }
}
