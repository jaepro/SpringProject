package spring.conf;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //<tx:annotation-driven> 태그와 같은 의미이므로 둘 중 하나만 작성하면 됨
@PropertySource("classpath:spring/db.properties")
@MapperScan("user.dao")
@ComponentScan(basePackages = {"user.service"})  // 이 부분 추가
/* DAO에서 바로 처리를 할려고 하면
  root-context에서 bean설정을 하든, 이 문장을 쓰든 둘 중 하나만 작성하면 됨
 * */
public class SpringConfiguration {
	private @Value("${jdbc.driver}") String driver;
	private @Value("${jdbc.url}") String url;
	private @Value("${jdbc.username}") String username;
	private @Value("${jdbc.password}") String password;
	
	@Autowired
	private ApplicationContext context; //import할 때 springFrameWork로 해야 함!!주의!!!
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName(driver);
		basicDataSource.setUrl(url);
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		
		return basicDataSource;
	}
	
	@Bean  //xml에서 sqlSessionFactory 설정한 것을 java에서 함.
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource());
		sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("spring/mybatis-config.xml"));
		//매퍼가 1개일 때
		/*sqlSessionFactoryBean.setMapperLocations(new ClassPathResource("mapper/userMapper.xml"));*/
		
		/*  mapper가 여러 개일 때 (private ApplicationContext context; 를 생성안한 경우
		sqlSessionFactoryBean.setMapperLocations(
				new ClassPathResource("mapper/userMapper.xml"),
				new ClassPathResource("mapper/userUploadMapper.xml"));  */
		
		//mapper가 여러 개 일 때, 하나하나 다 적을 필요 없이 ApplicationContext로 mapper의 환경변수를 잡아줄 수 있음
		sqlSessionFactoryBean.setMapperLocations(
				context.getResources("classpath:mapper/*Mapper.xml"));
		
		
		//이렇게 알리아스들은 mybatis-configuration.xml에서 alias를 작성햇는데 여기서 처리함. => mybatis-configuration.xml 삭제해도 됨!!!
		//sqlSessionFactoryBean.setTypeAliasesPackage("user.bean");  //클래스명을 알리아스로 변환 user.bean.UserDTO -> userDTO로 된다는 소리임
		sqlSessionFactoryBean.setTypeAliasesPackage("*.bean");  //이렇게 와일드카드로 만들어도 됨.
		
		return sqlSessionFactoryBean.getObject(); //sqlSessionFactory 변환 
	}
	
	@Bean  //xml에서 sqlSession 설정한 것을 java에서 함.
	public SqlSessionTemplate sqlSession() throws Exception {
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
		
	}
	
	@Bean //xml에서 Transaction 설정한 것을 java에서 함.
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = 
				new DataSourceTransactionManager(dataSource());
		
		return dataSourceTransactionManager;
	}

}
