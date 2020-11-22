package com.auth;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.auth.converter.impl.UserDTOToUserConverterImplTest;
import com.auth.integration.UserIntegrationTest;
import com.auth.service.impl.UserServiceImplTest;
import com.auth.validation.impl.UserValidationImplTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	UserDTOToUserConverterImplTest.class, 
	UserIntegrationTest.class,
	UserServiceImplTest.class,
	UserValidationImplTest.class })
public class AllTests {

}
