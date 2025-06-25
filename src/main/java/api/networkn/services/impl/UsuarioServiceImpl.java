package api.networkn.services.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.networkn.config.SortByConfig;
import api.networkn.exception.NotFoundException;
import api.networkn.exception.SenhaInvalidaException;
import api.networkn.exception.SortByException;
import api.networkn.models.Usuario;
import api.networkn.models.dtos.UsuarioDTO;
import api.networkn.models.repository.IUsuarioRepository;
import api.networkn.utils.mappers.IUsuarioMapper;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private IUsuarioRepository repository;

	@Autowired
	private IUsuarioMapper userMapper;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		return repository.save(usuario);
	}

	public Usuario findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	public Usuario montarUsuario(UsuarioDTO userDTO, Usuario usuario) {
		usuario.setLogin(userDTO.getLogin());
		setPassword(userDTO, usuario);
		usuario.setRole(userDTO.getRole());
		usuario.setUpdatedAt(LocalDateTime.now());
		return usuario;
	}

	private void setPassword(UsuarioDTO dto, Usuario usuario) {
		if (!dto.getSenha().equals(usuario.getSenha())) {
			usuario.setSenha(encoder.encode(dto.getSenha()));
		}
	}

	public Long countBy() {
		return this.repository.count();
	}
	
	@Transactional
	public UsuarioDTO editUserPartial(UsuarioDTO usuarioDTO) {
		Usuario userEntity = findById(usuarioDTO.getId());
		mountUserPartial(usuarioDTO, userEntity);

		return userMapper.toDto(repository.save(userEntity));
	}
	
	private void mountUserPartial(UsuarioDTO userDTO, Usuario usuario) {
		usuario.setLogin(userDTO.getLogin());
		setPassword(userDTO, usuario);
	}
	

	@Transactional
	public UsuarioDTO editar(UsuarioDTO usuarioDTO) {
		Usuario userEntity = findById(usuarioDTO.getId());
		Usuario usuarioAAtualizar = montarUsuario(usuarioDTO, userEntity);

		return userMapper.toDto(repository.save(usuarioAAtualizar));
	}

	public Page<UsuarioDTO> getAll(Pageable pageable, String sortBy) {
		if (Strings.isBlank(sortBy)) {
			throw new SortByException("Not found sort by");
		}
		Pageable sortedByPriceDescNameAsc = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
				Sort.by(SortByConfig.getDirection(sortBy), SortByConfig.getFieldName(sortBy)));
		List<UsuarioDTO> usersDTO = userMapper.toDto(repository.findAll(sortedByPriceDescNameAsc));
		return new PageImpl<>(usersDTO);
	}

	@Transactional
	public void delete(Long id) {
		Usuario userEntity = findById(id);
		repository.delete(userEntity);
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());

		if (senhasBatem) {
			return user;
		}

		throw new SenhaInvalidaException();
	}

	public UsuarioDTO findByLogin(String login) {
		return this.userMapper.toDto(this.repository.findByLogin(login).orElseThrow(() -> new NotFoundException()));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

		String[] roles = usuario.getRole().split(",");

		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(roles).build();
	}
}
