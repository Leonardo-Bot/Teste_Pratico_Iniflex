package industriaPackage.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class util {

    public static final String MSG_DATA_INVALIDA = "Data inválida";
    public static final String MSG_SALARIO_INVALIDO = "Salário inválido";

    public static LocalDate stringToDate(String data) {
        try {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(data, dateTimeFormatter);
         } catch (Exception e) {
            throw new RuntimeException(MSG_DATA_INVALIDA);
        }
    }

    public static BigDecimal stringToBigDecimal(String salario) {
        try {
            return new BigDecimal(salario);
        } catch (Exception e) {
            throw new RuntimeException(MSG_SALARIO_INVALIDO);
        }
    }

    public static void imprimirTexto(String texto) {
        System.out.println(texto);
    }


    public static void imprimirCabecalho() {
        util.imprimirTexto(String.format("%-10s | %-18s | %-14s", "Nome", "Data de Nascimento", "Salário"));
    }

    public static void imprimirCabecalhoComFuncao() {
        util.imprimirTexto(String.format("%-10s | %-18s | %-14s | %-15s", "Nome", "Data de Nascimento", "Salário", "Função"));
    }

    public static void imprimirInformacoesFuncionario(String nome, String dataNascimento, String salario) {
        util.imprimirTexto( String.format("%-10s | %-18s | %14s", nome, dataNascimento, salario));
    }

    public static void imprimirInformacoesFuncionarioComFuncao(String nome, String dataNascimento, String salario, String funcao) {
        util.imprimirTexto( String.format("%-10s | %-18s | %14s | %-15s", nome, dataNascimento, salario, funcao));
    }

    public static int calcularIdade(LocalDate dataNascimento) {
        Period periodo = Period.between(dataNascimento, LocalDate.now());
        return periodo.getYears();
    }

}
