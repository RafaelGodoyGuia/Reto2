import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Mapa {
    Grafo grafo;

    public Mapa() {
    }

    public Mapa(Grafo grafo) {
        this.grafo = grafo;
    }

    private static class NodoPrioridad implements Comparable<NodoPrioridad> {
        String planeta;
        int distancia;

        public NodoPrioridad(String planeta, int distancia) {
            this.planeta = planeta;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NodoPrioridad otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }

        @Override
        public String toString() {
            return "Nodo(" + planeta + ", dist=" + distancia + ")";
        }
    }

    public void calcularYMostrarCaminoMinimo(String inicio, String fin) {
        Set<String> todosLosPlanetas = grafo.getTodosLosPlanetas();
        if (!todosLosPlanetas.contains(inicio) || !todosLosPlanetas.contains(fin)) {
            System.out.println("Error: El planeta de inicio o fin no existe en el grafo.");
            if (!todosLosPlanetas.contains(inicio)) System.out.println("Planeta inicio no encontrado: " + inicio);
            if (!todosLosPlanetas.contains(fin)) System.out.println("Planeta fin no encontrado: " + fin);
            return;
        }

        if (inicio.equals(fin)) {
            System.out.println("El camino mínimo de " + inicio + " a " + fin + " es: " + inicio);
            System.out.println("Distancia total: 0");
            return;
        }

        Map<String, Integer> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        PriorityQueue<NodoPrioridad> colaPrioridad = new PriorityQueue<>();

        for (String planeta : todosLosPlanetas) {
            distancias.put(planeta, Integer.MAX_VALUE);
            predecesores.put(planeta, null);
        }

        distancias.put(inicio, 0);
        colaPrioridad.add(new NodoPrioridad(inicio, 0));

        while (!colaPrioridad.isEmpty()) {
            NodoPrioridad actual = colaPrioridad.poll();
            String planetaActual = actual.planeta;
            int distanciaActual = actual.distancia;

            if (distanciaActual > distancias.get(planetaActual)) {
                continue;
            }

            if (planetaActual.equals(fin)) {
                break;
            }

            List<Grafo.Arista> aristasSalientes = grafo.getAristasSalientes(planetaActual);
            for (Grafo.Arista arista : aristasSalientes) {
                String vecino = arista.getDestino();
                int pesoArista = arista.getPeso();

                if (distancias.get(planetaActual) == Integer.MAX_VALUE) {
                    continue;
                }

                int nuevaDistancia = distancias.get(planetaActual) + pesoArista;

                if (nuevaDistancia < distancias.get(vecino)) {
                    distancias.put(vecino, nuevaDistancia);
                    predecesores.put(vecino, planetaActual);
                    colaPrioridad.add(new NodoPrioridad(vecino, nuevaDistancia));
                }
            }
        }

        if (distancias.get(fin) == Integer.MAX_VALUE) {
            System.out.println("No se encontró un camino desde " + inicio + " hasta " + fin + ".");
        } else {
            LinkedList<String> camino = new LinkedList<>();
            String pasoActual = fin;
            while (pasoActual != null) {
                camino.addFirst(pasoActual);
                pasoActual = predecesores.get(pasoActual);
            }

            System.out.println("El camino mínimo de " + inicio + " a " + fin + " es:");
            System.out.println(String.join(" -> ", camino));
            System.out.println("Distancia total: " + distancias.get(fin));
        }
    }
}