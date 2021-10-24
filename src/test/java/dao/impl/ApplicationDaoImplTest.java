package dao.impl;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.epam.web.exception.DaoException;
import com.epam.web.model.dao.impl.ApplicationDaoImpl;
import com.epam.web.model.entity.Application;

@RunWith(MockitoJUnitRunner.class)
public class ApplicationDaoImplTest {

	@InjectMocks private ApplicationDaoImpl testDao =
			  ApplicationDaoImpl.getInstance();
	
	@Test
	public void findAllTest() throws DaoException{
		List<Application> applications = testDao.findAll();
		assertNotNull(applications);
	}
	
	@Test
	public void findByIdTest() throws DaoException{
		int id = 3;
		Application application = testDao.findById(id);
		assertNotNull(application);
	}
	
	@Test
	public void findByStatusTest() throws DaoException{
		String status = "POSTED";
		List<Application> applications = testDao.findbyStatus(status);
		assertNotNull(applications);
	}
	
	@Test
	public void findByOrganizationNameTest() throws DaoException{
		String organizationName = "POLICE OF RUSSIA";
		List<Application> applications = testDao.findbyOrganizationName(organizationName);
		assertNotNull(applications);
	}
	
	@Test
	public void findUnpostedTest() throws DaoException{
		List<Application> applications = testDao.findUnposted();
		assertNotNull(applications);
	}
	
	@Test
	public void findPostedTest() throws DaoException{
		List<Application> applications = testDao.findPosted();
		assertNotNull(applications);
	}
	
}
