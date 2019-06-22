package msoftware.models;

import java.util.HashMap;
import java.util.Map;

public class Cliente {
    private static final Map<String, Cliente> CLIENTES = new HashMap<>();
    private String nome;
    private String cpf;
    
    public static Cliente cadastrarCliente(String cpf, String nome){
        Cliente cliente = new Cliente(nome, cpf);
        return Cliente.CLIENTES.put(cpf, cliente);
    }
    
    public static Cliente procurarCPF(String cpf){
        return Cliente.CLIENTES.get(cpf);
    }

    private Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nome=" + nome + '}';
    }
    
}
