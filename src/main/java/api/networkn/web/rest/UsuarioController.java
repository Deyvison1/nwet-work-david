package api.networkn.web.rest;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import api.networkn.exception.SenhaInvalidaException;
import api.networkn.models.Usuario;
import api.networkn.models.dtos.UsuarioDTO;
import api.networkn.security.jwt.JwtService;
import api.networkn.services.impl.UsuarioServiceImpl;
import api.networkn.web.rest.dtos.CredenciaisDTO;
import api.networkn.web.rest.dtos.TokenDTO;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final JwtService jwtService;

    public UsuarioController(final UsuarioServiceImpl usuarioService, final JwtService jwtService) {
    	this.usuarioService = usuarioService;
    	this.jwtService = jwtService;
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar( @RequestBody @Validated Usuario usuario ){
        return usuarioService.salvar(usuario);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO editar( @RequestBody @Validated UsuarioDTO usuario ){
        return usuarioService.editar(usuario);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO editUserPartial(@PathVariable Long id,@RequestBody @Validated UsuarioDTO usuario ){
        return usuarioService.editUserPartial(usuario);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable Long id){
    	usuarioService.delete(id);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public  ResponseEntity<List<UsuarioDTO>> getAll(Pageable pageable, @RequestParam Map<String, String> requestParams){
    	HttpHeaders headers = new HttpHeaders();
    	String sortBy = requestParams.get("sortby");
    	Page<UsuarioDTO> usersDTO = usuarioService.getAll(pageable, sortBy);
    	
    	final Long total = usuarioService.countBy();
    	
		headers.add("X_TOTAL_COUNT", String.valueOf(total));
		return new ResponseEntity<List<UsuarioDTO>>(usersDTO.getContent(), headers, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/get-login/{login}")
    public ResponseEntity<UsuarioDTO> findByLogin(@PathVariable String login) {
    	return ResponseEntity.ok(this.usuarioService.findByLogin(login));
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            Usuario usuario = new Usuario();
            usuario.setLogin(credenciais.getLogin());
            usuario.setSenha(credenciais.getSenha());
            UserDetails usuarioAutenticado = usuarioService.autenticar(usuario);
            usuario.setRole(usuarioAutenticado.getAuthorities().toString());
            String token = jwtService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
    
    @PostMapping("/created")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario createUser(@RequestBody @Validated Usuario usuario ){
        return usuarioService.salvar(usuario);
    }

}