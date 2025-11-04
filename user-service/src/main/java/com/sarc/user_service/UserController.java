package com.sarc.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<User> buscarPorMatricula(@PathVariable String matricula) {
        Optional<User> User = userRepository.findByMatricula(matricula);
        return User.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<User>> buscarPorNome(@PathVariable String nome) {
        List<User> Users = userRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(Users);
    }

    @PostMapping
    public ResponseEntity<?> criarUser(@Valid @RequestBody User User) {
        try {
            if (User.getMatricula() != null &&
                    userRepository.existsByMatricula(User.getMatricula())) {
                return ResponseEntity.badRequest()
                        .body("Já existe um usuário com a matrícula: " + User.getMatricula());
            }

            if (User.getEmail() != null &&
                    userRepository.existsByEmail(User.getEmail())) {
                return ResponseEntity.badRequest()
                        .body("Já existe um usuário com o email: " + User.getEmail());
            }

            User UserSalvo = userRepository.save(User);
            return ResponseEntity.status(HttpStatus.CREATED).body(UserSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Usuário Service is running on port 8082");
    }
}
