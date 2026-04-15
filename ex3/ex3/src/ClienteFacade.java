public class ClienteFacade {
    public static void main(String[] args) {
        HomeTheaterFacade meuCinema = new HomeTheaterFacade();

        meuCinema.assistirFilme();

        meuCinema.ouvirMusica();
    }
}