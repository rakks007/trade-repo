package com.trade.app.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.trade.app.vo.ErrorVO;
import com.trade.app.vo.TradeError;
import com.trade.app.vo.TradeMessageVO;

public class TradeMessageValidator {

	private final static String COUNTY_PARTY_ID_FORMAT = "[Cc][Pp][-][0-9]*";
	private final static String TRADE_ID_FORMAT = "[Tt][0-9]*";
	private final static String BOOKING_ID_FORMAT = "[Bb][0-9]*";

	/**
	 * The Following Method validate if the message contents are correct or not the
	 * content is not correct will create a ErrorVO with errorCode and the tag value
	 * 
	 * @param messageVO
	 * @return
	 */
	public static List<ErrorVO> validate(TradeMessageVO messageVO) {
		List<ErrorVO> listErrors = new ArrayList<>();
		validateDateFormat(messageVO.getCreatedDate(), listErrors);
		validateDateFormat(messageVO.getMaturityDate(), listErrors);
		validateExpiredText(messageVO.getMaturityDate(), listErrors);
		validateCountryId(messageVO.getMaturityDate(), listErrors);
		validatBookingId(messageVO.getMaturityDate(), listErrors);
		validateCreateDateToMaturityId(messageVO.getMaturityDate(), messageVO.getCreatedDate(), listErrors);
		validateTradeId(messageVO.getTradeIdentifier(), listErrors);
		return listErrors;
	}

	/**
	 * Validate
	 * 
	 * @param maturityDate
	 * @param listErrors
	 */
	private static void validateTradeId(String tradeId, List<ErrorVO> listErrors) {
		if (tradeId == null || tradeId.trim().length() == 0 || validText(TRADE_ID_FORMAT, tradeId) == 0) {
			listErrors.add(new ErrorVO(TradeError.TRADEFORMATINCORRECT.toString(), tradeId,
					TradeError.TRADEFORMATINCORRECT.getDescription()));
		}
	}

	/**
	 * Checks if the
	 * 
	 * @param maturityDate
	 * @param listErrors
	 */
	private static void validateCreateDateToMaturityId(String maturityDateStr, String createDateStr,
			List<ErrorVO> listErrors) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		DateValidator validator = new DateValidator(dateFormatter);
		if (maturityDateStr != null && createDateStr != null && validator.isValid(createDateStr)
				&& validator.isValid(maturityDateStr)) {
			LocalDate maturityDate = LocalDate.parse(maturityDateStr, dateFormatter);
			LocalDate createdDate = LocalDate.parse(createDateStr, dateFormatter);
			if (createdDate.isAfter(maturityDate)) {
				listErrors.add(new ErrorVO(TradeError.MATUREDATELESSTHANCREATEDDATE.toString(), maturityDateStr,
						TradeError.MATUREDATELESSTHANCREATEDDATE.getDescription()));
			}

		}
	}

	/**
	 * Validate the Booking Id for Format [Bb][0-9]*
	 * 
	 * @param bookingId
	 * @param listErrors
	 */
	private static void validatBookingId(String bookingId, List<ErrorVO> listErrors) {
		if (bookingId == null || bookingId.trim().length() == 0 || validText(BOOKING_ID_FORMAT, bookingId) == 0) {
			listErrors.add(new ErrorVO(TradeError.BOOKINGIDISNULL.toString(), bookingId,
					TradeError.BOOKINGIDISNULL.getDescription()));
		}

	}

	/**
	 * Validate the Country party format in the give below format Format
	 * [Cc][Pp][-][0-9]*
	 * 
	 * @param maturityDate
	 * @param listErrors
	 */
	private static void validateCountryId(String countryId, List<ErrorVO> listErrors) {
		if (countryId == null || countryId.trim().length() == 0 || validText(COUNTY_PARTY_ID_FORMAT, countryId) == 0) {
			listErrors.add(new ErrorVO(TradeError.COUNTRYFORMATNOTCORRECT.toString(), countryId,
					TradeError.COUNTRYFORMATNOTCORRECT.getDescription()));
		}
	}

	/**
	 * Validate if the expired value is specified in the message or not and format
	 * is correct
	 * 
	 * @param isExpired
	 * @param listErrors
	 */
	private static void validateExpiredText(String isExpired, List<ErrorVO> listErrors) {
		if (isExpired == null || isExpired.trim().length() == 0 || isExpired.trim().length() > 1
				|| !("N".equals(isExpired) || "Y".equals(isExpired))) {
			listErrors.add(new ErrorVO(TradeError.EXPIREDCANNOTBENULL.toString(), isExpired,
					TradeError.EXPIREDCANNOTBENULL.getDescription()));
		}
	}

	/**
	 * Validates if the Date format is as per JSON Date Format
	 * 
	 * @param date
	 * @param listErrors
	 */
	private static void validateDateFormat(String date, List<ErrorVO> listErrors) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
		DateValidator validator = new DateValidator(dateFormatter);
		if (date != null && !validator.isValid(date)) {
			listErrors.add(new ErrorVO(TradeError.DATEFORMAT_INCORRECT.toString(), date,
					TradeError.DATEFORMAT_INCORRECT.getDescription()));
		}
	}

	/**
	 * The static class to validate the LocalDate from String
	 * 
	 * @author
	 *
	 */
	static class DateValidator {
		private DateTimeFormatter dateFormatter;

		public DateValidator(DateTimeFormatter dateFormatter) {
			this.dateFormatter = dateFormatter;
		}

		public boolean isValid(String dateStr) {
			try {
				LocalDate.parse(dateStr, this.dateFormatter);
			} catch (DateTimeParseException e) {
				return false;
			}
			return true;
		}
	}

	/**
	 * Validates the Reg Expression against the text
	 * 
	 * @param regex
	 * @param text
	 * @return
	 */
	private static int validText(String regex, String text) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		int matches = 0;
		while (matcher.find()) {
			matches++;
		}
		return matches;
	}
}
