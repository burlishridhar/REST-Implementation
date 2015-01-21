package lookup.repository.implementation;

import java.util.List;

import junit.framework.TestCase;
import lookup.domain.User;

public class UserRepositoryImplementationTest extends TestCase {
	
	private UserRepositoryImplementation u;
	
	@Override
	protected void setUp() throws Exception {
		u = new UserRepositoryImplementation();
	}

	public void testGetAllUsers() {
		List<User> list = u.getAllUsers();
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetUserByName() {
		List<User> list = u.getUserByName("Ruchir");
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetUserByLoginID() {
		List<User> list = u.getUserByLoginID("shridharburli@tamu.edu");
		assertTrue(list!=null && list.size()!=0);
	}

	public void testGetUserById() {
		User user = u.getUserById(1);
		assertTrue(user!=null);
	}
	
	 public void testSaveUser() {
		User user = new User();
		user.setName("Test");
		user.setEmail("test@test.com");
		user.setPhone("123456789");
		user.setLoginID("testlogin");
		int result = u.saveUser(user, "testPassword");
		assertTrue(result!=-1);
	}

	public void testUpdateUser() {
		User user = new User();
		user.setId(1);
		user.setEmail("newTest@email.com");
		int result = u.updateUser(user);
		assertTrue(result!=-1);
	}

	@Override
	protected void tearDown() throws Exception {
		u = null;
	}

}
