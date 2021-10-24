package dao.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import com.epam.web.exception.DaoException;
import com.epam.web.model.dao.impl.ApplicantDaoImpl;
import com.epam.web.model.entity.Applicant;

@RunWith(MockitoJUnitRunner.class)
public class ApplicantDaoImplTest {

	
	
  @InjectMocks private ApplicantDaoImpl testDao =
	  ApplicantDaoImpl.getInstance();
	 
	
	
	@Test
	public void findAllTest() throws DaoException {
		List<Applicant> applicants = testDao.findAll();
		assertNotNull(applicants);
	}
	

	@Test
	public void findByIdTest() throws DaoException{
		int id = 2;
		Applicant applicant = testDao.findById(id);
		assertNotNull(applicant);
	}
	
	@Test
	public void findByOrgnizationNameTest() throws DaoException{
		String organizationName = "POLICE OF RUSSIA";
		Applicant applicant = testDao.findByOrganizationName(organizationName);
		assertNotNull(applicant);	
	}
}
