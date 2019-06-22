package msoftware.models;

import java.util.HashMap;
import java.util.Map;

public class Atendente {
    private static final Map<Integer, Atendente> ATENDENTES = new HashMap<>();
    private static Integer contador = 0;
    private String nome;
    private Integer id;
    
    public static Atendente cadastrarAtendente(String nome){
        Atendente atendente = new Atendente(nome);
        Atendente.contador += 1;
        Atendente.ATENDENTES.put(Atendente.contador, atendente);
        atendente.setId(Atendente.contador);
        return atendente;
    }
    
    public static Atendente procurarId(Integer id){
        return Atendente.ATENDENTES.get(id);
    }

    private Atendente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Atendente{" + "nome=" + nome + ", id=" + id + '}';
    }
    
}
