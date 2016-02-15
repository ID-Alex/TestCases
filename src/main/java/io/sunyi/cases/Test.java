package io.sunyi.cases;

import org.apache.commons.lang.StringUtils;
import org.joda.money.Money;
import org.springframework.util.CollectionUtils;
import sun.nio.ch.DirectBuffer;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author sunyi
 *         Created on 15/9/25
 */
public class Test {
	public static void main(String args[]) {

		System.out.println(Runtime.getRuntime().availableProcessors());
	}
}
