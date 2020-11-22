package com.auth.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.auth.converter.impl.UserDTOToUserConverterImpl;
import com.auth.converter.impl.UserToUserDTOConverterImpl;
import com.auth.dto.UserDTO;
import com.auth.exception.BusinessException;
import com.auth.exception.ConverterException;
import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.auth.util.StampUtil;
import com.auth.validation.impl.UserCreationValidationImpl;
import com.auth.validation.impl.UserUpdationValidationImpl;

@Service
public class UserServiceImpl {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	UserCreationValidationImpl userCreationValidationImpl;

	@Autowired
	UserUpdationValidationImpl userUpdationValidationImpl;

	@Autowired
	UserDTOToUserConverterImpl userDTOtoUserConverterImpl;

	@Autowired
	UserToUserDTOConverterImpl userToUserDTOConverterImpl;

	@Autowired
	UserRepository userRepository;

	@Autowired
	StampUtil stampUtil;

	public UserDTO create(UserDTO userDTO) throws BusinessException {
		logger.info(String.format("%s DTO create( %s)", this.getClass().getCanonicalName(), userDTO));
		if (userCreationValidationImpl.isValid(userDTO)) {
			return save(userDTO);
		}
		throw new BusinessException("Error create");
	}

	public UserDTO update(UserDTO userDTO) throws BusinessException {
		logger.info(String.format("%s DTO update( %s)", this.getClass().getCanonicalName(), userDTO));
		if (userUpdationValidationImpl.isValid(userDTO)) {
			return save(userDTO);
		}
		throw new BusinessException("Error update");
	}

	public UserDTO findByEmail(String email) throws BusinessException {
		logger.info(String.format("UserDTO findByEmail( %s)", email));
		Optional<User> user = userRepository.findByEmail(email);
		return userToUserDTOConverterImpl
				.convertTo(user.orElseThrow(() -> new BusinessException("Error user not found")));
	}

	public UserDTO findById(Long id) throws BusinessException {
		logger.info(String.format("%s DTO findById( %s)", this.getClass().getCanonicalName(), id));
		var user = this.userRepository.findById(id);
		return this.userToUserDTOConverterImpl
				.convertTo(user.orElseThrow(() -> new BusinessException("Error entity not found")));
	}

	public List<UserDTO> findAllActived() throws ConverterException {
		logger.info(String.format("%s List<DTO> findAllActived()", this.getClass().getCanonicalName()));
		var es = this.userRepository.findAllByActive(true);
		return this.userToUserDTOConverterImpl.convertListToList(es);
	}

	public List<UserDTO> findAllInactived() throws ConverterException {
		logger.info(String.format("%s List<DTO> findAllInactived()", this.getClass().getCanonicalName()));
		var es = this.userRepository.findAllByActive(false);
		return this.userToUserDTOConverterImpl.convertListToList(es);
	}

	public boolean activate(UserDTO userDTO) throws BusinessException {
		logger.info(String.format("%s boolean activate( %s)", this.getClass().getCanonicalName(), userDTO));
		var user = this.activation(userDTO, true);
		return user.isActive();
	}

	public boolean inactivate(UserDTO userDTO) throws BusinessException {
		logger.info(String.format("%s boolean inactivate(%s)", this.getClass().getCanonicalName(), userDTO));
		var user = this.activation(userDTO, false);
		return user.isInactive();
	}

	public String findPasswordByEmail(String email) {
		logger.info(String.format("UserDTO findPasswordByEmail( %s)", email));
		Optional<User> user = userRepository.findByEmail(email);
		return user.orElse(new User()).getPassword();
	}

	private UserDTO save(UserDTO userDTO) throws BusinessException {
		User user = userDTOtoUserConverterImpl.convertTo(userDTO);
		user.setCreatedAt(stampUtil.now());
		try {
			User userSaved = userRepository.save(user);
			return userToUserDTOConverterImpl.convertTo(userSaved);
		} catch (DataIntegrityViolationException dive) {
			throw new BusinessException(
					String.format("Entity not saved: %s %s", userDTO, dive.getMostSpecificCause().getMessage()));
		}
	}

	private User activation(UserDTO userDTO, boolean active) throws BusinessException {
		logger.info(
				String.format("%s boolean activation(%s, %s)", this.getClass().getCanonicalName(), userDTO, active));
		Optional<User> optionalUser = userRepository.findById(userDTO.getId());
		if (optionalUser.isPresent()) {
			var entity = optionalUser.get();
			entity.setActive(active);
			entity.setUpdatedAt(stampUtil.now());
			return userRepository.save(entity);
		}
		throw new BusinessException("Invalid activation entity");
	}

}
