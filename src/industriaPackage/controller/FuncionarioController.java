package industriaPackage.controller;

import industriaPackage.model.Funcionario;
import industriaPackage.util.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class  FuncionarioController {
    List<Funcionario> funcionarios = new ArrayList<>();

    public void inserir(String nome, String dataNascimento, String salario, String funcao) {
        try {
            Funcionario novoFuncionario = new Funcionario(nome, util.stringToDate(dataNascimento), util.stringToBigDecimal(salario), funcao);
            funcionarios.add(novoFuncionario);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Erro ao inserir Funcionário (%s, %s, %s, %s). \nMotivo: %s.", nome, dataNascimento, salario, funcao, e.getMessage()));
        }
    }

    public void remover(String nome) {
        funcionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    public Integer quantidade() {
        return funcionarios.size();
    }

    public void listarInformacaoFuncionarios() {
        util.imprimirCabecalhoComFuncao();
        funcionarios.forEach(funcionario -> util.imprimirInformacoesFuncionarioComFuncao(funcionario.getNome(), funcionario.getDataNascimento().toString(), funcionario.getSalario().toString(), funcionario.getFuncao()));
    }

    public void listarInformacaoFuncionariosComDataNascimentoESalarioFormatados() {
        util.imprimirCabecalhoComFuncao();
        funcionarios.forEach(funcionario -> util.imprimirInformacoesFuncionarioComFuncao(funcionario.getNome(), funcionario.getDataNascimentoFormatada(), funcionario.getSalarioFormatado(), funcionario.getFuncao()));
    }

    public void aumentoSalarial(double taxaAumento) {
        double valoraumento =  (taxaAumento / 100) + 1;
        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal(valoraumento)).setScale(2, RoundingMode.UP);
            funcionario.setSalario(novoSalario);
        }
    }

    public void listarFuncionariosAgrupadosPorFuncao() {
        Map<String, List<Funcionario>> funcinariosAgrupadoPorFuncao = getFuncinariosAgrupadoPorFuncao();

        funcinariosAgrupadoPorFuncao.keySet().forEach(funcao -> {
            util.imprimirTexto("Função: " + funcao);
            util.imprimirCabecalho();
            List<Funcionario> funcionariosDaFuncao = funcinariosAgrupadoPorFuncao.get(funcao);
            funcionariosDaFuncao.forEach(funcionario -> util.imprimirInformacoesFuncionario(funcionario.getNome(), funcionario.getDataNascimentoFormatada(), funcionario.getSalarioFormatado()));
            util.imprimirTexto(String.format("Total de Registros: %d\n", funcionariosDaFuncao.size()));
        });
    }

    public void listarFuncionariosPeloMesDeAniversario(List<Integer> listaComMeses) {
        util.imprimirCabecalhoComFuncao();
        funcionarios.forEach(funcionario -> {
            if (listaComMeses.contains(funcionario.getDataNascimento().getMonthValue()))
                util.imprimirInformacoesFuncionarioComFuncao(funcionario.getNome(), funcionario.getDataNascimentoFormatada(), funcionario.getSalarioFormatado(), funcionario.getFuncao());
        });
    }

    public void imprimirFuncionarioMaisVelho() {
        List<Funcionario> novaListaFuncionario = new ArrayList<>(funcionarios);
        novaListaFuncionario.sort(Comparator.comparing(Funcionario::getDataNascimento));

        int idade = util.calcularIdade(novaListaFuncionario.get(0).getDataNascimento());
        util.imprimirTexto(String.format("Nome: %s \nIdade: %d anos.", novaListaFuncionario.get(0).getNome(), idade));
    }

    public void listarFuncionariosPorOrdemAlfabetica() {
        List<Funcionario> novaListaFuncionario = new ArrayList<>(funcionarios);
        novaListaFuncionario.sort(Comparator.comparing(Funcionario::getNome));

        novaListaFuncionario.forEach(funcionario -> util.imprimirInformacoesFuncionarioComFuncao(funcionario.getNome(), funcionario.getDataNascimentoFormatada(), funcionario.getSalarioFormatado(), funcionario.getFuncao()));
    }

    public void imprimirTotalDosSalarios() {
        BigDecimal totalSalarios = BigDecimal.ZERO;
        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        DecimalFormat decimalFormat = new  DecimalFormat("'R$ '###,###,##0.00");
        util.imprimirTexto(decimalFormat.format(totalSalarios));
    }

    public void listarFuncionariosComQuantidadeSalarioMinimo(String salarioMinimo) {
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimo = funcionario.getSalario().divide(new BigDecimal(salarioMinimo), 2, RoundingMode.HALF_UP);
            util.imprimirTexto(String.format("Nome: %s \nSalários Mínimos: %s\n", funcionario.getNome(), salariosMinimo));
        });
    }

    private Map<String, List<Funcionario>> getFuncinariosAgrupadoPorFuncao () {
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();
        for (Funcionario funcionario : funcionarios) {
            String funcao = funcionario.getFuncao();
            if (!funcionariosPorFuncao.containsKey(funcao)) {
                funcionariosPorFuncao.put(funcao, new ArrayList<>());
            }
            funcionariosPorFuncao.get(funcao).add(funcionario);
        }

        return funcionariosPorFuncao;
    }

}
