package api.networkn.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.networkn.exception.SenhaInvalidaException;
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
		Optional<Usuario> user = repository.findById(id);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	public Usuario montarUsuario(UsuarioDTO user) {
		return Usuario.builder().id(user.getId()).login(user.getLogin()).senha(encoder.encode(user.getSenha()))
				.role(user.getRole()).updatedAt(LocalDateTime.now()).build();
	}

	@Transactional
	public UsuarioDTO editar(UsuarioDTO usuarioDTO) {
		Usuario userEntity = findById(usuarioDTO.getId());
		if (Objects.nonNull(userEntity)) {
			Usuario usuarioAAtualizar = montarUsuario(usuarioDTO);

			return userMapper.toDto(repository.save(usuarioAAtualizar));
		}
		return null;
	}

	public List<UsuarioDTO> getAll() {
		return userMapper.toDto(repository.findAll());
	}
	
	@Transactional
	public void delete(Long id) {
		Usuario userEntity = findById(id);
		if(Objects.nonNull(userEntity)) {
			repository.delete(userEntity);
		}
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		boolean senhasBatem = encoder.matches(usuario.getSenha(), user.getPassword());
		
		if (senhasBatem) {
			return user;
		}

		throw new SenhaInvalidaException();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

		String[] roles = usuario.getRole().split(",");

		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(roles).build();
	}
}
