package com.ada.ecosystem.core.v1.utility;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ada.ecosystem.connections.v1.response.EcosystemSuccessResponse;
import com.ada.ecosystem.core.v1.enums.ExecutionContext;
import com.ada.ecosystem.core.v1.enums.RomanNumeral;
import com.ada.ecosystem.core.v1.kafka.KafkaMessage;
import com.ada.ecosystem.core.v1.kafka.Status;
import com.ada.ecosystem.core.v1.kafka.TaskSend;
import com.ada.ecosystem.core.v1.kafka.TaskStatus;
import com.ada.ecosystem.core.v1.process.ProcessResult;

import jakarta.servlet.http.HttpServletRequest;

/**
 * The Class EcosystemUtilities.
 */
public class EcosystemUtilities {

	/** The log. */
	private static final Logger log = LoggerFactory.getLogger(EcosystemUtilities.class);

	/** The instance. */
	private static EcosystemUtilities instance;

	/** The application title. */
	private static String applicationTitle = "";

	/** The application summary. */
	private static String applicationVersion = "";

	/** The Constant hex. */
	private static final char[] hex = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/** The INDEFINITE_PROCESS. */
	public static final String INDEFINITE_PROCESS = "INDEFINITE PROCESS";

	/** The Constant KEY_DEFAULT. */
	public static final String KEY_DEFAULT = "CREATE PROCESS";

	/** The kafka text format. */
	private static String kafkaTextFormat = "UUID((%s)) PROCESS<<%s>> MESSAGE[[%s]]\n";

	/** The fmt. */
	private static Formatter fmt = new Formatter();

	/** The Constant CONSULTA_REALIZADA_CORRECTAMENTE. */
	public static final String CONSULTA_REALIZADA_CORRECTAMENTE = "Consulta realizada correctamente.";

	/** The Constant REGISTRO_ELIMINADO_CORRECTAMENTE. */
	public static final String REGISTRO_ELIMINADO_CORRECTAMENTE = "Registro eliminado correctamente.";

	/** The Constant REGISTRO_CREADO_CORRECTAMENTE. */
	public static final String REGISTRO_CREADO_CORRECTAMENTE = "Registro creado correctamente.";

	/** The Constant REGISTRO_ACTUALIZADO_CORRECTAMENTE. */
	public static final String REGISTRO_ACTUALIZADO_CORRECTAMENTE = "Registro actualizado correctamente.";

	/** The Constant CODIGO_MEMPRESA_DEFAULT. */
	public static final String CODIGO_MEMPRESA_DEFAULT = "9999999999";

	/** The Constant PROCESS_STARTED. */
	public static final String PROCESS_STARTED = "process started";

	/** The Constant PROCESS_COMPLETED_SUCCESSFULLY. */
	public static final String PROCESS_COMPLETED_SUCCESSFULLY = "process completed successfully";

	/** The Constant PROCESS_CANCELED. */
	public static final String PROCESS_CANCELED = "canceled process";

	/** The Constant PROCESS_ENDED_ABRUPTLY. */
	public static final String PROCESS_ENDED_ABRUPTLY = "process ended abruptly";

	/** The Constant NO_SE_PUEDE_REALIZAR_EL_PROCESO. */
	public static final String NO_SE_PUEDE_REALIZAR_EL_PROCESO = "No se puede realizar el proceso.";

	/** The codigo mempresa. */
	public static String codigoMempresa = "";

	/** The Constant CODIGO_MEMPRESA_DEFAULT. */
	public static final BigDecimal CERO_DEFAULT = new BigDecimal(0L);

	/** The RANDOM. */
	private static final Random RANDOM = new SecureRandom();
	
	/** The SYMBOLS. */
	private static final String SYMBOLS = "!@#$%^&*()_+=-{}[]:\";'<>?,./";

	/**
	 * Gets the formatted text.
	 *
	 * @param kafkaMessage the kafka message
	 * @return the formatted text
	 */
	public static String getFormattedText(KafkaMessage kafkaMessage) {
		return fmt.format(kafkaTextFormat, kafkaMessage.getUuid(), kafkaMessage.getMessage()).toString();
	}

	/** The Constant types. */
	private static final byte[] types = { Character.COMBINING_SPACING_MARK, Character.CONNECTOR_PUNCTUATION,
			Character.CONTROL, Character.CURRENCY_SYMBOL, Character.DASH_PUNCTUATION, Character.DECIMAL_DIGIT_NUMBER,
			Character.ENCLOSING_MARK, Character.END_PUNCTUATION, Character.FORMAT, Character.LETTER_NUMBER,
			Character.LINE_SEPARATOR, Character.LOWERCASE_LETTER, Character.MATH_SYMBOL, Character.MODIFIER_SYMBOL,
			Character.NON_SPACING_MARK, Character.OTHER_LETTER, Character.OTHER_NUMBER, Character.OTHER_PUNCTUATION,
			Character.OTHER_SYMBOL, Character.PARAGRAPH_SEPARATOR, Character.PRIVATE_USE, Character.SPACE_SEPARATOR,
			Character.START_PUNCTUATION, Character.SURROGATE, Character.TITLECASE_LETTER, Character.UNASSIGNED,
			Character.UPPERCASE_LETTER };

	/** The Constant typeNames. */
	private static final String[] typeNames = { "Combining spacing mark", "Connector punctuation", "Control",
			"Currency symbol", "Dash punctuation", "Decimal digit number", "Enclosing mark", "End punctuation",
			"Format", "Letter number", "Line separator", "Lowercase letter", "Math symbol", "Modifier symbol",
			"Non spacing mark", "Other letter", "Other number", "Other punctuation", "Other symbol",
			"Paragraph separator", "Private use", "Space separator", "Start punctuation", "Surrogate",
			"Titlecase letter", "Unassigned", "Uppercase letter" };

	/** The execution context. */
	private static ExecutionContext executionContext;
	
	/** The Constant ROMAN_NUMERALS. */
	private static final Map<Integer, String> ROMAN_NUMERALS = new HashMap<>();
	
	static {
        ROMAN_NUMERALS.put(1, "I");
        ROMAN_NUMERALS.put(4, "IV");
        ROMAN_NUMERALS.put(5, "V");
        ROMAN_NUMERALS.put(9, "IX");
        ROMAN_NUMERALS.put(10, "X");
        ROMAN_NUMERALS.put(40, "XL");
        ROMAN_NUMERALS.put(50, "L");
        ROMAN_NUMERALS.put(90, "XC");
        ROMAN_NUMERALS.put(100, "C");
        ROMAN_NUMERALS.put(400, "CD");
        ROMAN_NUMERALS.put(500, "D");
        ROMAN_NUMERALS.put(900, "CM");
        ROMAN_NUMERALS.put(1000, "M");
    }

	/**
	 * Instantiates a new generator api utility.
	 * 
	 * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.1
	 */
	private EcosystemUtilities() {
	}

	/**
	 * Gets the single instance of GeneratorApiUtility.
	 *
	 * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.1
	 * @return single instance of GeneratorApiUtility
	 */
	public static EcosystemUtilities getInstance() {
		if (instance == null)
			instance = new EcosystemUtilities();
		log.debug("Instance Singleton: EcosystemUtilities Ok.");
		return instance;
	}

	/**
	 * Gets the types.
	 *
	 * @return the types
	 */
	public static byte[] getTypes() {
		return types;
	}

	/**
	 * Gets the type names.
	 *
	 * @return the type names
	 */
	public static String[] getTypeNames() {
		return typeNames;
	}

	/**
	 * Convert from util date.
	 *
	 * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.1
	 * @param utilDate the util date
	 * @return the java.sql.date
	 */
	public static java.sql.Date convertFromUtilDate(java.util.Date utilDate) {
		if (utilDate == null)
			return null;
		return new java.sql.Date(utilDate.getTime());
	}

	/**
	 * Byte array 2 hex.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	public static String byteArray2Hex(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * 2);
		for (final byte b : bytes) {
			sb.append(hex[(b & 0xF0) >> 4]);
			sb.append(hex[b & 0x0F]);
		}
		return sb.toString();
	}

	/**
	 * Convert from sql date.
	 *
	 * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.1
	 * @param sqlDate the sql date
	 * @return the java.util. date
	 */
	public static java.util.Date convertFromSqlDate(java.sql.Date sqlDate) {
		if (sqlDate == null)
			return null;
		return new java.util.Date(sqlDate.getTime());
	}

	/**
	 * Gets the string from SHA 256.
	 *
	 * @param stringToEncrypt the string to encrypt
	 * @return the string from SHA 256
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static String getStringFromSHA256(String stringToEncrypt) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(stringToEncrypt.getBytes());
		return byteArray2Hex(messageDigest.digest());
	}

	/**
	 * Pack entry.
	 *
	 * @author Carlos Torres - torrescamargo@gmail.com
	 * @version 0.0.1
	 * @param src  the src
	 * @param zo   the zo
	 * @param path the path
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void packEntry(Path src, ZipOutputStream zo, Path path) throws IOException {
		String name = src.relativize(path).toString().replace('\\', '/');
		boolean isDir = Files.isDirectory(path);
		if (isDir) {
			name += "/";
		}
		ZipEntry e = new ZipEntry(name);
		zo.putNextEntry(e);
		if (!isDir) {
			Files.copy(path, zo);
		}
		zo.closeEntry();
	}

	/**
	 * Gets the message online service.
	 *
	 * @param request the request
	 * @return the message online service
	 */
	public static String getMessageOnlineService(HttpServletRequest request) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		StringBuilder info = new StringBuilder();
		if (!applicationTitle.isEmpty()) {
			info.append("Service " + applicationTitle + " status: Online!!!");
		} else {
			info.append("Service Online!!!");
		}
		if (!applicationVersion.isEmpty())
			info.append("\nVersion Release: " + applicationVersion);
		return info.append("\nDate Request: " + dtf.format(now)).append("\nMethod: " + request.getMethod())
				.append("\nIp: " + request.getRemoteAddr()).toString();
	}

	/**
	 * Gets the application title.
	 *
	 * @return the application title
	 */
	public static String getApplicationTitle() {
		return applicationTitle;
	}

	/**
	 * Sets the application title.
	 *
	 * @param applicationTitle the new application title
	 */
	public static void setApplicationTitle(String applicationTitle) {
		EcosystemUtilities.applicationTitle = applicationTitle;
	}

	/**
	 * Gets the application version.
	 *
	 * @return the application version
	 */
	public static String getApplicationVersion() {
		return applicationVersion;
	}

	/**
	 * Sets the application version.
	 *
	 * @param applicationVersion the new application version
	 */
	public static void setApplicationVersion(String applicationVersion) {
		EcosystemUtilities.applicationVersion = applicationVersion;
	}

	/**
	 * Gets the execution context.
	 *
	 * @return the execution context
	 */
	public static ExecutionContext getExecutionContext() {
		return EcosystemUtilities.executionContext;
	}

	/**
	 * Sets the execution context.
	 *
	 * @param executionContext the new execution context
	 */
	public static void setExecutionContext(ExecutionContext executionContext) {
		EcosystemUtilities.executionContext = executionContext;
	}

	/**
	 * Gets the ecosystem uuid.
	 *
	 * @return the ecosystem uuid
	 */
	public static String getEcosystemUuid() {
		return java.util.UUID.randomUUID().toString();
	}

	/**
	 * Creates the default task process.
	 *
	 * @param topicName the topic name
	 * @param taskId    the task id
	 * @param taskName  the task name
	 * @return the task send
	 */
	public static TaskSend createDefaultTaskProcess(String topicName, String taskId, String taskName) {
		var taskStatus = new TaskStatus();
		taskStatus.setTaskId(taskId);
		taskStatus.setTaskName(taskName);
		taskStatus.setPercentageComplete(0.0f);
		taskStatus.setStatus(Status.SUBMITTED);
		var taskSend = new TaskSend();
		taskSend.setTopicName(topicName);
		taskSend.setKey(KEY_DEFAULT);
		taskSend.setValue(taskStatus);
		return taskSend;
	}

	/**
	 * Creates the default task process.
	 *
	 * @param topicName the topic name
	 * @param key       the key
	 * @param taskId    the task id
	 * @param taskName  the task name
	 * @return the task send
	 */
	public static TaskSend createDefaultTaskProcess(String topicName, String key, String taskId, String taskName) {
		var taskStatus = new TaskStatus();
		taskStatus.setTaskId(taskId);
		taskStatus.setTaskName(taskName);
		taskStatus.setPercentageComplete(0.0f);
		taskStatus.setStatus(Status.SUBMITTED);
		var taskSend = new TaskSend();
		taskSend.setTopicName(topicName);
		taskSend.setKey(key);
		taskSend.setValue(taskStatus);
		return taskSend;
	}

	/**
	 * Gets the task status.
	 *
	 * @param taskId     the task id
	 * @param taskName   the task name
	 * @param status     the status
	 * @param percentage the percentage
	 * @return the task status
	 */
	public static TaskStatus getTaskStatus(String taskId, String taskName, Status status, Float percentage) {
		var taskStatus = new TaskStatus();
		taskStatus.setTaskId(taskId);
		taskStatus.setTaskName(taskName);
		taskStatus.setPercentageComplete(percentage);
		taskStatus.setStatus(status);
		return taskStatus;
	}

	/**
	 * Gets the date without time using format.
	 *
	 * @return the date without time using format
	 * @throws ParseException the parse exception
	 */
	public static Date getDateWithoutTimeUsingFormat() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.parse(formatter.format(new Date()));// Formatear la fecha actual sin hora y convertirla a Date
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public static String getNumero() {
		String formato = "yyyyMMddHHmmss";
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
		LocalDateTime ahora = LocalDateTime.now();
		return formateador.format(ahora);
	}

	/**
	 * Gets the internal client response.
	 *
	 * @param <T>      the generic type
	 * @param response the response
	 * @return the internal client response
	 */
	public static <T> T getInternalClientResponse(EcosystemSuccessResponse<T> response) {
		return response.getResponse().getBody();
	}

	/**
	 * Gets the internal process result.
	 *
	 * @param <T>    the generic type
	 * @param result the result
	 * @return the internal process result
	 */
	public static <T> T getInternalProcessResult(ProcessResult<T> result) {
		return result.getResult();
	}

	/**
	 * Gets the simple date format vigencia.
	 *
	 * @param fecha the fecha
	 * @return the simple date format vigencia
	 */
	public static String getSimpleDateFormatVigencia(Date fecha) {
		SimpleDateFormat vigenciaPeriodo = new SimpleDateFormat("yyyy");
		return vigenciaPeriodo.format(fecha);
	}

	/**
	 * Gets the simple date format periodo.
	 *
	 * @param fecha the fecha
	 * @return the simple date format periodo
	 */
	public static String getSimpleDateFormatPeriodo(Date fecha) {
		SimpleDateFormat formatoPeriodo = new SimpleDateFormat("yyyyMM");
		return formatoPeriodo.format(fecha);
	}

	/**
	 * Invalid string.
	 *
	 * @param text the text
	 * @return the boolean
	 */
	public static Boolean invalidString(String text) {
		return text == null || text.isBlank();
	}

	/**
	 * Checks if is numeric.
	 *
	 * @param numeric the numeric
	 * @return the boolean
	 */
	public static Boolean isNumeric(String numeric) {
		return StringUtils.isNumeric(numeric);
	}

	/**
	 * Gets the percentage complete.
	 *
	 * @param totalSegment       the total segment
	 * @param percentageComplete the percentage complete
	 * @return the percentage complete
	 */
	public static Float getPercentageComplete(Float totalSegment, Float percentageComplete) {
		return (percentageComplete * 100) / totalSegment;
	}

	/**
	 * Gets the fecha sin horas.
	 *
	 * @param fechaConHoras the fecha con horas
	 * @return the fecha sin horas
	 */
	public static Date getFechaSinHoras(Date fechaConHoras) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(fechaConHoras);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	
	/**
	 * Generate secure code.
	 *
	 * @param size the size
	 * @return the string
	 */
	public static String generateSecureCode(Integer size) {
		var code = new StringBuilder();
		for (int i = 0; i < size; i++) {
			int selector = EcosystemUtilities.RANDOM.nextInt(4);
			switch (selector) {
			case 0:
				char randomChar = (char) (EcosystemUtilities.RANDOM.nextInt(10) + 48);
				code.append(randomChar);
				break;
			case 1:
				char randomLowerChar = (char) (EcosystemUtilities.RANDOM.nextInt(26) + 97);				
				code.append(randomLowerChar);
				break;
			case 2:
				char randomUpperChar = (char) (EcosystemUtilities.RANDOM.nextInt(26) + 65);				
				code.append(randomUpperChar);
				break;
			case 3:
				char randomSymbol = EcosystemUtilities.SYMBOLS.charAt(EcosystemUtilities.RANDOM.nextInt(EcosystemUtilities.SYMBOLS.length()));				
				code.append(randomSymbol);
				break;
			default:
				char defaultChar = (char) (EcosystemUtilities.RANDOM.nextInt(10) + 48);
				code.append(defaultChar);
				break;
			}
		}
		return code.toString();
	}
	
	/**
	 * To roman.
	 *
	 * @param number the number
	 * @return the string
	 */
	public static String toRoman(Integer number) {
//        if (number <= 0 || number > 3999) {
//            throw new IllegalArgumentException("Número fuera de rango: 1 - 3999");
//        }
//        StringBuilder roman = new StringBuilder();
//        for (Map.Entry<Integer, String> entry : ROMAN_NUMERALS.entrySet()) {
//            while (number >= entry.getKey()) {
//                roman.append(entry.getValue());
//                number -= entry.getKey();
//            }
//        }
//        return roman.toString();
		return EcosystemUtilities.arabicToRoman(number);
    }
	
	/**
	 * Roman to arabic.
	 *
	 * @param input the input
	 * @return the integer
	 */
	public static Integer romanToArabic(String input) {
	    String romanNumeral = input.toUpperCase();
	    int result = 0;
	        
	    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

	    int i = 0;

	    while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
	        RomanNumeral symbol = romanNumerals.get(i);
	        if (romanNumeral.startsWith(symbol.name())) {
	            result += symbol.getValue();
	            romanNumeral = romanNumeral.substring(symbol.name().length());
	        } else {
	            i++;
	        }
	    }

	    if (romanNumeral.length() > 0) {
	        throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
	    }

	    return result;
	}
	
	/**
	 * Arabic to roman.
	 *
	 * @param number the number
	 * @return the string
	 */
	public static String arabicToRoman(Integer number) {
	    if ((number <= 0) || (number > 4000)) {
	        throw new IllegalArgumentException(number + " is not in range (0,4000]");
	    }

	    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

	    int i = 0;
	    StringBuilder sb = new StringBuilder();

	    while ((number > 0) && (i < romanNumerals.size())) {
	        RomanNumeral currentSymbol = romanNumerals.get(i);
	        if (currentSymbol.getValue() <= number) {
	            sb.append(currentSymbol.name());
	            number -= currentSymbol.getValue();
	        } else {
	            i++;
	        }
	    }

	    return sb.toString();
	}
	
	/**
	 * Gets the exclude text if exists.
	 *
	 * @param excludeText the exclude text
	 * @param text the text
	 * @return the exclude text if exists
	 */
	public static String getExcludeTextIfExists(String excludeText, String text) {
		return text.contains(excludeText) ? text : excludeText + text;
	}
}