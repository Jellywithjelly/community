package life.manong.community.interceptor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  implements WebMvcConfigurer {

    /*配置创建springcontext上下文对象之前，注入拦截器对象*/
    @Bean
    public HandlerInterceptor getUserInterceptor(){
        return new UserInterceptor();
    }

    /*注入为null的时候，是通过new的方式创建的拦截器，通过new出来的实例是没有交给spring进行管理的，
    没有被spring管理的实例，spring是无法自动注入bean的，所以为null*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( getUserInterceptor()).addPathPatterns("/**");
    }


}
