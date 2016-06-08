package org.soaring.bindb;

import org.soaring.bindb.config.RedisConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;

@Configuration
@EnableConfigurationProperties
@ComponentScan
@EnableWebMvc
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BinDBMicroserviceApplication {

	@Resource
	private RedisConfiguration redisConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(BinDBMicroserviceApplication.class, args);
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return (ServletContext servletContext) -> {
			final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
			characterEncodingFilter.setEncoding("UTF-8");
			characterEncodingFilter.setForceEncoding(false);

			servletContext.addFilter("characterEncodingFilter",
					characterEncodingFilter).addMappingForUrlPatterns(
					EnumSet.of(DispatcherType.REQUEST), false, "/*");

			servletContext.addFilter("CORS", new SimpleCORSFilter())
					.addMappingForUrlPatterns(
							EnumSet.of(DispatcherType.REQUEST), false, "/*");
		};
	}

	public class SimpleCORSFilter implements Filter {

		public void doFilter(ServletRequest req, ServletResponse res,
							 FilterChain chain) throws IOException, ServletException {
			HttpServletResponse response = (HttpServletResponse) res;
			response.setHeader("Access-Control-Allow-Origin",
					((HttpServletRequest) req).getHeader("HTTP_ORIGIN"));
			response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers",
					"x-requested-with");
			chain.doFilter(req, res);
		}

		public void init(FilterConfig filterConfig) {
		}

		public void destroy() {
		}

	}

	@Bean
	public StringRedisTemplate getRedisTemplate() {
		JedisConnectionFactory conn = new JedisConnectionFactory();
		conn.setPort(redisConfiguration.getPort());
		conn.setHostName(redisConfiguration.getHost());
		return new StringRedisTemplate(conn);
	}
}
