package spring.todolist.config;

import org.modelmapper.ModelMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("spring.todoList.repository")
public class JavaConfig {
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
