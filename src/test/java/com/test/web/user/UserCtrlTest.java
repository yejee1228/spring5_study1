package com.test.web.user;
import static org.junit.Assert.*;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.test.web.cfg.RootConfig;
import com.test.web.cfg.ServletConfig;
import com.test.web.cfg.WebConfig;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.test.web.cfg.ServletConfig;
import com.test.web.proxy.Calculator;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServletConfig.class}, loader = AnnotationConfigWebContextLoader.class)
@WebAppConfiguration
public class UserCtrlTest {
	@Autowired UserCtrl userCtrl;
	@Test
	public void testRowCount() {
		assertThat(userCtrl.rowCount(), not(equalTo(0)));
	}

}
