import industriaPackage.controller.FuncionarioController;
import industriaPackage.util.util;

import java.util.Arrays;

public class Industria {
    public static void main(String[] args) {
    try {
        FuncionarioController funcionarioController = new FuncionarioController();
        funcionarioController.inserir("Maria", "18/10/2000", "2009.44", "Operador");
        funcionarioController.inserir("João", "12/05/1990", "2284.38", "Operador");
        funcionarioController.inserir("Caio", "02/05/1961", "9836.14", "Coordenador");
        funcionarioController.inserir("Miguel", "14/10/1988", "19119.88", "Diretor");
        funcionarioController.inserir("Alice", "05/01/1995", "2234.68", "Recepcionista");
        funcionarioController.inserir("Heitor", "19/11/1999", "1582.72", "Operador");
        funcionarioController.inserir("Arthur", "31/03/1993", "4071.84", "Contador");
        funcionarioController.inserir("Laura", "08/07/1994", "3017.45", "Gerente");
        funcionarioController.inserir("Heloísa", "24/05/2003", "1606.85", "Eletricista");
        funcionarioController.inserir("Helena", "02/09/1996", "2799.93", "Gerente");

        util.imprimirTexto("Relação de todos os funcionários:");
        funcionarioController.listarInformacaoFuncionarios();
        util.imprimirTexto(String.format("Total de Registros: %d\n", funcionarioController.quantidade()));

        funcionarioController.remover("JOÃO");
        util.imprimirTexto("Relação de todos os funcionários após remover o João:");
        funcionarioController.listarInformacaoFuncionarios();
        util.imprimirTexto(String.format("Total de Registros: %d\n", funcionarioController.quantidade()));

        util.imprimirTexto("Relação de todos os funcionários com a data de nascimento e salário formatados:");
        funcionarioController.listarInformacaoFuncionariosComDataNascimentoESalarioFormatados();
        util.imprimirTexto(String.format("Total de Registros: %d\n", funcionarioController.quantidade()));

        util.imprimirTexto("Relação de todos os funcionários após aumento salarial de 10%:");
        funcionarioController.aumentoSalarial(10.0);
        funcionarioController.listarInformacaoFuncionariosComDataNascimentoESalarioFormatados();
        util.imprimirTexto(String.format("Total de Registros: %d\n", funcionarioController.quantidade()));

        util.imprimirTexto("Relação de todos os funcionários agrupados for função:");
        funcionarioController.listarFuncionariosAgrupadosPorFuncao();

        util.imprimirTexto("Relação de todos os funcionários que fazem aniversário nos meses 10 e 12:");
        funcionarioController.listarFuncionariosPeloMesDeAniversario(Arrays.asList(10, 12));

        util.imprimirTexto("\n");
        util.imprimirTexto("O funcionário mais velho é:");
        funcionarioController.imprimirFuncionarioMaisVelho();

        util.imprimirTexto("\n");
        util.imprimirTexto("Relação de todos os funcionário por ordem alfabética:");
        funcionarioController.listarFuncionariosPorOrdemAlfabetica();

        util.imprimirTexto("\n");
        util.imprimirTexto("O total dos salários dos funcionários é:");
        funcionarioController.imprimirTotalDosSalarios();

        util.imprimirTexto("\n");
        util.imprimirTexto("Relação de salários mínimos por funcinário:");
        funcionarioController.listarFuncionariosComQuantidadeSalarioMinimo("1212.00");

    } catch (Exception e) {
        util.imprimirTexto(e.getMessage());
    }


    }
}