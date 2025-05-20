import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class Grafo {
    public Map<String, List<Arista>> adyacencias;

    public static class Arista {
        private int peso;
        private String destino;

        public Arista(int peso, String destino) {
            this.peso = peso;
            this.destino = destino;
        }

        public int getPeso() {
            return this.peso;
        }

        public String getDestino() {
            return this.destino;
        }

        public void setPeso(int peso) {
            this.peso = peso;
        }

        public void setDestino(String destino) {
            this.destino = destino;
        }

        @Override
        public String toString() {
            return "-> " + destino + " (peso:" + peso + ")";
        }
    }

    public Grafo() {
        this.adyacencias = new HashMap<>();
    }

    public void agregarPlaneta(String planeta) {
        adyacencias.putIfAbsent(planeta, new LinkedList<>());
    }

    public void agregarRuta(String planeta1, String planeta2, int distancia) {
        this.agregarPlaneta(planeta1);
        this.agregarPlaneta(planeta2);

        Arista arista = new Arista(distancia, planeta2);

        List<Arista> listaDeAdyacenciasPlaneta1 = adyacencias.get(planeta1);
        if (listaDeAdyacenciasPlaneta1 != null) {
            listaDeAdyacenciasPlaneta1.add(arista);
        }
    }

    public List<String> obtenerNombresAdyacencias(String planeta) {
        List<String> nombreAdyacencias = new LinkedList<>();
        if (adyacencias.containsKey(planeta)) {
            List<Arista> aristasDelPlaneta = adyacencias.get(planeta);
            for (Arista arista : aristasDelPlaneta) {
                nombreAdyacencias.add(arista.getDestino());
            }
        }
        return nombreAdyacencias;
    }

    public List<Arista> getAristasSalientes(String planeta) {
        return adyacencias.getOrDefault(planeta, Collections.emptyList());
    }

    public Set<String> getTodosLosPlanetas() {
        return adyacencias.keySet();
    }
}
