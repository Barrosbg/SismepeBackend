package br.gov.pe.sismepe.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MaskFormatter;

public class Utils {

	private static NumberFormat currencyFormat;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Converte um valor com mascara de numero decimal para um BigDecimal
	 *
	 * @param mascaraValor Valor decimal com mascara
	 * @return Valor convertido em BigDecimal. Null se valor for invalido ou null.
	 */
	public static BigDecimal converteMascaraValorParaBigDecimal(String mascaraValor) {
		Double valor = null;
		String valorStr = retirarFormatacaoNumeroDecimal(mascaraValor);
		if (valorStr != null) {
			try {
				valor = Double.parseDouble(valorStr);
			} catch (Exception e) {
			}
		}
		if (valor == null) {
			return null;
		}
		return new BigDecimal(valor);
	}

	/**
	 * Converte um valor com mascara de numero decimal para um double
	 *
	 * @param mascaraValor Valor decimal com mascara
	 * @return Valor convertido em double. Null se valor for invalido ou null.
	 */
	public static Double converteMascaraValorParaDouble(String mascaraValor) {
		Double valor = null;
		String valorStr = retirarFormatacaoNumeroDecimal(mascaraValor);
		if (valorStr != null) {
			try {
				valor = Double.parseDouble(valorStr);
			} catch (Exception e) {
			}
		}
		return valor;
	}

	/**
	 * Converte um BigDecimal para string com mascara de valor
	 *
	 * @param valor BigDecimal que deseja-se formatar
	 * @return Valor com mascara
	 */
	public static String formatarBigDecimalParaMascaraValor(BigDecimal valor) {
		if (valor == null) {
			return null;
		}
		return formatarDoubleParaMascaraValor(valor.doubleValue(), getLocaleBrasil());
	}

	public static String formatarCNPJ(String cnpj) {
		try {
			MaskFormatter mask = new MaskFormatter("##.###.###/####-##");
			mask.setValueContainsLiteralCharacters(false);
			return mask.valueToString(cnpj);
		} catch (ParseException ex) {
		}
		return cnpj;
	}

	/**
	 * Converte um double para string com mascara de valor
	 *
	 * @param valor Double que deseja-se formatar
	 * @return Valor com mascara
	 */
	public static String formatarDoubleParaMascaraValor(Double valor) {
		return formatarDoubleParaMascaraValor(valor, getLocaleBrasil());
	}

	/**
	 * Converte um double para string com mascara de valor
	 *
	 * @param valor Double que deseja-se formatar
	 * @return Valor com mascara
	 */
	public static String formatarDoubleParaMascaraValor(Double valor, Locale locale) {
		String valorMascara = "";
		if (valor != null) {
			valorMascara = getCurrencyFormat(locale).format(valor);
		}
		return valorMascara;
	}

	public static NumberFormat getCurrencyFormat(Locale locale) {
		if (currencyFormat == null) {
			currencyFormat = NumberFormat.getInstance(locale);
			currencyFormat.setGroupingUsed(true);
			currencyFormat.setMinimumFractionDigits(2);
			currencyFormat.setMaximumFractionDigits(2);
		}
		return currencyFormat;
	}

	public static Optional<String> getExtensionByStringHandling(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	public static Locale getLocaleBrasil() {
		return new Locale("pt", "BR");
	}

	public static long getRandomIntegerBetweenRange(double min, double max) {
		double x = (int) (Math.random() * ((max - min) + 1)) + min;
		return (long) x;
	}

	public static boolean isCNPJValido(String CNPJ) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") || CNPJ.equals("22222222222222")
				|| CNPJ.equals("33333333333333") || CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555")
				|| CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") || CNPJ.equals("88888888888888")
				|| CNPJ.equals("99999999999999") || (CNPJ.length() != 14)) {
			return (false);
		}

		char dig13, dig14;
		int sm, i, r, num, peso;

		// "try" - protege o código para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 11; i >= 0; i--) {
				// converte o i-ésimo caractere do CNPJ em um número:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posição de '0' na tabela ASCII)
				num = CNPJ.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10) {
					peso = 2;
				}
			}

			r = sm % 11;
			if ((r == 0) || (r == 1)) {
				dig13 = '0';
			} else {
				dig13 = (char) ((11 - r) + 48);
			}

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 2;
			for (i = 12; i >= 0; i--) {
				num = CNPJ.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso + 1;
				if (peso == 10) {
					peso = 2;
				}
			}

			r = sm % 11;
			if ((r == 0) || (r == 1)) {
				dig14 = '0';
			} else {
				dig14 = (char) ((11 - r) + 48);
			}

			// Verifica se os dígitos calculados conferem com os dígitos informados.
			if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	public static boolean isCPFValido(String CPF) {
		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if (CPF.equals("00000000000") || CPF.equals("11111111111") || CPF.equals("22222222222")
				|| CPF.equals("33333333333") || CPF.equals("44444444444") || CPF.equals("55555555555")
				|| CPF.equals("66666666666") || CPF.equals("77777777777") || CPF.equals("88888888888")
				|| CPF.equals("99999999999") || (CPF.length() != 11)) {
			return (false);
		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		// "try" - protege o codigo para eventuais erros de conversao de tipo (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for (i = 0; i < 9; i++) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = CPF.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig10 = '0';
			} else {
				dig10 = (char) (r + 48); // converte no respectivo caractere numerico
			}

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++) {
				num = CPF.charAt(i) - 48;
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11)) {
				dig11 = '0';
			} else {
				dig11 = (char) (r + 48);
			}

			// Verifica se os digitos calculados conferem com os digitos informados.
			if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
				return (true);
			} else {
				return (false);
			}
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

	/**
	 * Verifica se a Lista passada nao e nula e nem vazia
	 *
	 * @param lista Lista a ser verificada
	 * @return TRUE se a Lsita nao e nula e nem vazia
	 */
	public static boolean isListNaoNulaNaoVazia(final Collection<?> lista) {
		return (lista != null) && !lista.isEmpty();
	}

	/**
	 * Verifica se a string passada nao e nula nem vazia
	 *
	 * @param string String a ser verificada
	 * @return true se a string nao e nula nem vazia
	 */
	public static boolean isStringNaoNulaNaoVazia(final String string) {
		return (string != null && !string.isEmpty());
	}

	public static String limparFormatacaoCNPJ(String cnpj) {
		String tmp = cnpj;
		tmp = tmp.replace(".", "");
		tmp = tmp.replace("/", "");
		tmp = tmp.replace("-", "");
		return tmp.trim();
	}

	public static String limparFormatacaoCPF(String cpf) {
		String tmp = cpf;
		tmp = tmp.replace(".", "");
		tmp = tmp.replace("-", "");
		return tmp.trim();
	}

	public static String limparFormatacaoTelefone(String telefone) {
		String tmp = telefone;
		tmp = tmp.replace("(", "");
		tmp = tmp.replace(")", "");
		tmp = tmp.replace("-", "");
		tmp = tmp.replace(" ", "");
		return tmp;
	}

	
	public static String adicionarFormatacaoTelefone(String telefone) {
		String tmp = telefone;
		
		if(tmp.length() >= 11) {
			return "(" + tmp.substring(0, 2) + ") " + tmp.substring(2, 7) + "-" + tmp.substring(7, tmp.length());
		} else if(tmp.length() == 10) {
			return "(" + tmp.substring(0, 2) + ") " + tmp.substring(2, 6) + "-" + tmp.substring(6, tmp.length());			
		} else {
			return telefone;
		}
		
	}
	
	/**
	 * Converte a string passada para um valor decimal que utiliza o ponto (.) como
	 * separador.
	 *
	 * @param valor String que deseja-se converter
	 * @return String de valor convertida
	 */
	public static String retirarFormatacaoNumeroDecimal(String valor) {
		String retorno = null;
		if ((valor != null) && !valor.isEmpty()) {
			retorno = valor.replaceAll("\\.", "").replaceAll(",", ".");
		}
		return retorno;
	}

	/**
	 * M&eacute;todo respons&aacute;vel por validar se o email informado possui um
	 * padr&atilde;o v&aacute;lido.
	 *
	 * Obs: <b>N&atilde;o &eacute; NullPointer-Safe.</b>
	 *
	 * @param emailTela - Email recebido.
	 * @return <code>true</code> se o email for valido e <code>false</code> caso
	 *         contr&aacute;rio.
	 * @author Diego Ferreira
	 * @since 30-10-2019
	 */
	public static boolean validarEmail(String emailTela) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailTela);
		return matcher.find();

	}

	public static boolean validarNumeroDecimal(String numero) {

		if (numero == null || numero.isEmpty()) {
			return true;
		}
		Double valor = null;
		String valorStr = retirarFormatacaoNumeroDecimal(numero);
		if (valorStr != null) {
			try {
				valor = Double.parseDouble(valorStr);
			} catch (Exception e) {
				return false;
			}
		}

		return true;
	}

	public static Date getDate(String data) throws ParseException {
		Date retorno = null;		
		SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
		retorno = f1.parse(data);		
		return retorno;
	}
	
	
	public static String dateToString(Date data) {
		String retorno = "";
		try {
			String pattern = "yyyy-MM-dd HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			retorno = df.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static String ptDateToString(Date data) {
		String retorno = "";
		try {
			String pattern = "dd/MM/yyyy HH:mm";
			DateFormat df = new SimpleDateFormat(pattern);
			retorno = df.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static String ptOnlyDateToString(Date data) {
		String retorno = "";
		try {
			String pattern = "dd/MM/yyyy";
			DateFormat df = new SimpleDateFormat(pattern);
			retorno = df.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}
	
	public static String ptTimeToString(Date data) {
		String retorno = "";
		try {
			String pattern = "HH:mm:ss";
			DateFormat df = new SimpleDateFormat(pattern);
			retorno = df.format(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retorno;
	}

	public static String parseToMD5(String text) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		messageDigest.update(text.getBytes(), 0, text.length());
		return new BigInteger(1, messageDigest.digest()).toString(16);
	}

	public static String firstDayOfWeek() {	
		LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek();
		return now.with(fieldISO, 1).toString();		
	}
	
	public static String lastDayOfWeek() {			
		LocalDate now = LocalDate.now();
		TemporalField fieldISO = WeekFields.of(Locale.getDefault()).dayOfWeek();
		return now.with(fieldISO,  7).toString();			
	}	
	
	public static String firstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE));
		Date firstDateOfMonth = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(firstDateOfMonth);
	}
	
	public static String lastDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE)); 
		Date lastDateOfMonth = cal.getTime();
		return new SimpleDateFormat("yyyy-MM-dd").format(lastDateOfMonth);
	}
	
	
	public static String firstDayOfYear() {
		LocalDate now = LocalDate.now();
		return now.getYear() + "-01-01";
	}
	
	public static String lastDayOfYear() {
		LocalDate now = LocalDate.now();
		return now.getYear() + "-12-31";
	}
	
	public static String idadePorDataDeNascimento(Date dataNascimento) {
		if(dataNascimento != null) {
			LocalDate localDate = LocalDateTime.ofInstant(dataNascimento.toInstant(), ZoneOffset.UTC).toLocalDate();
			return Period.between(localDate, LocalDate.now()).getYears() + "";
		} 
		
		return "";
	}

	public static Date stringToDate(String dataCadastro) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
        try {
            date = formatter.parse(dataCadastro);
        } catch (ParseException e) {
            e.printStackTrace();
        }
		return date;
	}

	
	public static Date datePlusDays(Date date, int days) {
		Calendar c = Calendar.getInstance(); 
		c.setTime(date); 
		c.add(Calendar.DATE, days);
		return c.getTime();
	}
	
	
	/**
	 * Formata numero em String com uma quantidade de zeros a esquerda.
	 *
	 * @param length int, tamanho da String após a formatação
	 * @param value int, valor que será formatado para o padrão estabelecido
	 * @return String de valor formatado
	 */
	
	public static String formatZerosLeft(int length, int value) {
		
		String format = String.valueOf(value);
		
		for(int i = String.valueOf(value).length(); i < length; i++) {
			format = "0" + format;
		}
		
		return format;
	}
	public static Date trim(Date date, int hora, int minutos, int segundos, int milisegundos) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MILLISECOND, milisegundos);
        calendar.set(Calendar.SECOND, segundos);
        calendar.set(Calendar.MINUTE, minutos);
        calendar.set(Calendar.HOUR,hora);

        return calendar.getTime();
    }
}
