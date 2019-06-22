package msoftware;

import java.util.Scanner;
import msoftware.models.Atendente;
import msoftware.models.Atendimento;
import msoftware.models.Cliente;
import msoftware.models.Item;
import msoftware.models.Pagamento;

public class Msoftware {

    private static final Scanner SCAN = new Scanner(System.in);

    public static void main(String[] args) {
        int menu = 0;

        while (menu != 4) {
            printMenuInicial();
            menu = SCAN.nextInt();
            if(menu == 0){
                cadastrarCliente();
            }else if (menu == 1) {
                cadastrarItem();
            } else if (menu == 2) {
                cadastrarAtendente();
            } else if (menu == 3) {
                Atendente atendente = pegarAtendente();
                if (atendente != null) {
                    int menuAtendimento = 0;
                    while (menuAtendimento != 3) {
                        printMenuAtendimento();
                        menuAtendimento = SCAN.nextInt();
                        if (menuAtendimento != 3) {
                            Atendimento atendimento = iniciarAtendimento(menuAtendimento, atendente);
                            if(atendimento != null){
                                Pagamento pagamento = finalizarAtendimento(atendimento);
                                concluirPagamento(pagamento);
                                gerarNotaFiscal(atendimento);
                            }
                        }
                    }
                } else {
                    System.out.println("ATENDENTE NÃO ENCONTRADO");
                }
            }
        }
        SCAN.close();

    }

    private static Atendente pegarAtendente() {
        Integer id = getIntegerWithMessage("DIGITE O SEU ID");
        Atendente atendente = Atendente.procurarId(id);
        return atendente;
    }

    private static void concluirPagamento(Pagamento pagamento) {
        printMenuPagamento();
        int menuPagamento = SCAN.nextInt();
        if(menuPagamento == 1){
            pagamento.pagar(Pagamento.TIPO_PAGAMENTO.CARTAO, pagamento.getTotal());
        }else if(menuPagamento == 2){
            Integer valorPago = getIntegerWithMessage("VALOR PAGO EM DINHEIRO");
            pagamento.pagar(Pagamento.TIPO_PAGAMENTO.CARTAO, valorPago);
        }
    }

    private static void gerarNotaFiscal(Atendimento atendimento) {
        System.out.println("NOTA FISCAL");
        System.out.println(atendimento);
    }

    private static Pagamento finalizarAtendimento(Atendimento atendimento) {
        Pagamento pagamento = null;
        int menuAtendendo = 0;
        while (menuAtendendo != 2) {
            printMenuAtendendo();
            menuAtendendo = SCAN.nextInt();
            if (menuAtendendo == 1) {
                String cod = getStringWithMessage("DIGITE CÓDIGO DO ITEM");
                Integer num = getIntegerWithMessage("DIGITE QUANTIDADE DE ITENS");
                try{
                    atendimento.registrarPedido(cod, num);
                } catch(RuntimeException e){
                    System.out.println(e.getMessage().toUpperCase());
                }
            } else if (menuAtendendo == 2) {
                pagamento = atendimento.fecharAtendimento();
            }
        }
        return pagamento;
    }

    private static Atendimento iniciarAtendimento(int menuAtendimento, Atendente atendente) {
        Atendimento atendimento = null;
        while (atendimento == null) {
            if (menuAtendimento == 1) {
                String cpfCliente = getStringWithMessage("DIGITE O CPF DO CLIENTE");
                atendimento = Atendimento.criarAtendimento(cpfCliente, atendente);
                if(atendimento == null){
                    System.out.println("CPF INVÁLIDO");
                    return null;
                }else{
                    System.out.println("BEM VINDO "+atendimento.getCliente().getNome());
                }
            } else if (menuAtendimento == 2) {
                atendimento = Atendimento.criarAtendimento(atendente);
            }
        }
        return atendimento;
    }
    
    private static void cadastrarCliente() {
        String nome = getStringWithMessage("DIGITE O NOME DO CLIENTE");
        String cpf = getStringWithMessage("DIGITE O CPF DO CLIENTE");
        Cliente.cadastrarCliente(cpf, nome);
        System.out.println("O CADASTRADO COM SUCESSO");
    }

    private static void cadastrarAtendente() {
        String nome = getStringWithMessage("DIGITE O NOME DO ATENDENTE");
        Atendente atendente = Atendente.cadastrarAtendente(nome);
        System.out.println("ID DO ATENDENTE É: " + atendente.getId());
    }

    private static void cadastrarItem() {
        String nome = getStringWithMessage("DIGITE O NOME DO ITEM");
        Double valor = getDoubleWithMessage("DIGITE O VALOR DO ITEM");
        Item itemCadastrado = Item.cadastrarItem(nome, valor);
        System.out.println("CODIGO DO ITEM É: " + itemCadastrado.getCod());
    }

    private static void printMenuInicial() {
        System.out.println("0 - CADASTRAR CLIENTE");
        System.out.println("1 - CADASTRAR ITEM");
        System.out.println("2 - CADASTRAR ATENDENTE");
        System.out.println("3 - LOGAR COM ID");
        System.out.println("4 - SAIR");
    }

    private static void printMenuAtendimento() {
        System.out.println("1 - CRIAR ATENDIMENTO COM CPF");
        System.out.println("2 - CRIAR ATENDIMENTO SEM CPF");
        System.out.println("3 - VOLTAR MENU INICIAL");
    }

    private static void printMenuAtendendo() {
        System.out.println("1 - PASSAR ITEM");
        System.out.println("2 - FECHAR COMPRA");
    }

    private static void printMenuPagamento() {
        System.out.println("1 - CARTÃO");
        System.out.println("2 - DINHEIRO");
    }

    private static String getStringWithMessage(String message) {
        System.out.println(message);
        return SCAN.next();
    }

    private static Double getDoubleWithMessage(String message) {
        return Double.parseDouble(getStringWithMessage(message));
    }

    private static Integer getIntegerWithMessage(String message) {
        return Integer.parseInt(getStringWithMessage(message));
    }

}
