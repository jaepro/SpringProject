package spring.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Getter;
import lombok.Setter;

@Configuration
@PropertySource("classpath:spring/naver.properties")
@Getter
@Setter
public class NaverConfiguration {
	private @Value("${ncp.accessKey}") String accessKey; //주의 : import걸 때, Spring~것으로 해야 함!!
	private @Value("${ncp.secretKey}") String secretKey;
	private @Value("${ncp.regionName}") String regionName;
	private @Value("${ncp.endPoint}") String endPoint;

}
