package until;

public class MakeSerialNumber {
	public static String makeCartNumber(int initNumber) {
		return String.format("cart%04d", ++initNumber);
	}

	public static String makeOrderNumber(int initNumber) {
		return String.format("order%04d", ++initNumber);
	}

	public static String makeOrderItemNumber(int initNumber) {
		return String.format("orderItem%04d", ++initNumber);
	}
	
	public static String makeProNumber(int initNumber) {
		return String.format("pro%04d", ++initNumber);
	}
}
