package cn.tofucat.toolkit.webapi.config;

import cn.tofucat.toolkit.llc.LLC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadBean {

    @Bean
    public LLC llc() {
        return new LLC();
    }
}
