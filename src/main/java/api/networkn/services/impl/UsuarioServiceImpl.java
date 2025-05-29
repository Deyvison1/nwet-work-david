package api.networkn.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import api.networkn.exception.NotFoundException;
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

	@Transactional
	public UsuarioDTO editar(UsuarioDTO usuarioDTO) {
		Usuario userEntity = findById(usuarioDTO.getId());
		Usuario usuarioAAtualizar = montarUsuario(usuarioDTO, userEntity);

		return userMapper.toDto(repository.save(usuarioAAtualizar));
	}

	public List<UsuarioDTO> getAll() {
		return userMapper.toDto(repository.findAll());
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repository.findByLogin(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

		String[] roles = usuario.getRole().split(",");

		return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).roles(roles).build();
	}
}
