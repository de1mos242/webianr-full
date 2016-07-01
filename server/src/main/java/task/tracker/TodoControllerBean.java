package task.tracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoControllerBean {
	
	private DataSource dataSourceProperty;
	
	public DataSource getDataSourceProperty() {
		return dataSourceProperty;
	}

	public void setDataSourceProperty(DataSource dataSourceProperty) {
		this.dataSourceProperty = dataSourceProperty;
	}

	@RequestMapping("/hello")
	public ResponseEntity<String> helloHandler() {
		return new ResponseEntity<>("<h1>hello spring</h1>", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/static-jsp", method = RequestMethod.GET)
	public String jspHandler() {
		return "/WEB-INF/site/static.jsp";
	}
	
	@RequestMapping("/model-view-jsp")
	public ModelAndView mvHandler() {
		return new ModelAndView("/WEB-INF/site/static.jsp");
	}
	
	@RequestMapping(value = "/todo/{todoId}", method = RequestMethod.GET)
	public ResponseEntity<String> getById(@PathVariable String todoId) {
		String result = "Not found";
		try
		{
			Statement statement = dataSourceProperty.getConnection().createStatement();
	        ResultSet resultSet = statement  
	                .executeQuery("SELECT name FROM todos WHERE id = '" + todoId + "';");  
	        if (resultSet.next()) 
	        {  
	        	result = resultSet.getString(1);  
	        }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("<h1>" + result + "</h1>", HttpStatus.OK);
	}
}
