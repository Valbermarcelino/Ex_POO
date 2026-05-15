import java.util.ArrayList;
import java.util.List;

class Chat {
    private final List<Usuario> usuarios = new ArrayList<>();

    void registrar(Usuario u) { 
        usuarios.add(u); 
    }

    void transmitir(String msg, Usuario remetente) {
        for (Usuario u : usuarios) {
            if (u != remetente) {
                u.receber(remetente.nome + ": " + msg);
            }
        }
    }
}

class Usuario {
    final String nome;
    private final Chat chat;

    Usuario(String nome, Chat chat) {
        this.nome = nome;
        this.chat = chat;
        this.chat.registrar(this);
    }

    void enviar(String msg) {
        chat.transmitir(msg, this);
    }

    void receber(String msg) {
        System.out.println("[" + nome + "] recebeu -> " + msg);
    }
}

public class mediator {
    public static void main(String[] args) {
        Chat chat = new Chat();

        Usuario u1 = new Usuario("Alice", chat);
        Usuario u2 = new Usuario("Bob", chat);
        Usuario u3 = new Usuario("Carlos", chat);

        u1.enviar("Oi gnt!");
        System.out.println();
        u3.enviar("Opa, de boa?");
    }
}
