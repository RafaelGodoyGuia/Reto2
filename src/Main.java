public class Main {
    public static void main(String[] args) {
        Grafo estrellas = new Grafo();

        estrellas.agregarPlaneta("Tatooine");
        estrellas.agregarPlaneta("Alderaan");
        estrellas.agregarPlaneta("Coruscant");
        estrellas.agregarRuta("Tatooine", "Alderaan", 10);
        estrellas.agregarRuta("Alderaan", "Coruscant", 5);
        estrellas.agregarRuta("Tatooine", "Coruscant", 15);

        Mapa mapa = new Mapa(estrellas);
        mapa.calcularYMostrarCaminoMinimo("Tatooine", "Coruscant");
    }
}