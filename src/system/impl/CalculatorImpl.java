package system.impl;

import datamodel.OrderItem;
import datamodel.Order;
import datamodel.TAX;
import system.Calculator;

public class CalculatorImpl implements Calculator {

	@Override
	public long calculateOrderItemValue(OrderItem item) {
		if (item == null) {
			throw new IllegalArgumentException("argument item is null.");
		}
		return item.getArticle().getUnitPrice() * item.getUnitsOrdered();
	}

	@Override
	public long calculateOrderItemVAT(OrderItem item) {
		if (item == null) {
			throw new IllegalArgumentException("argument item is null.");
		}
		return calculateVAT(calculateOrderItemValue(item), item.getArticle().getTax());
	}

	@Override
	public long calculateOrderValue(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("argument order is null.");
		}
		long totalValue = 0;
		for (OrderItem item : order.getItems()) {
			totalValue += calculateOrderItemValue(item);
		}
		return totalValue;
	}

	@Override
	public long calculateOrderVAT(Order order) {
		if (order == null) {
			throw new IllegalArgumentException("argument order is null.");
		}
		long totalVAT = 0;
		for (OrderItem item : order.getItems()) {
			totalVAT += calculateOrderItemVAT(item);
		}
		return totalVAT;
	}

	@Override
	public long calculateVAT(long grossValue, TAX taxRate) {
		if (taxRate == null) {
			throw new IllegalArgumentException("argument taxRate is null.");
		}

		if (grossValue <= 0) {
			return 0;
		}

		double taxPercentage = value(taxRate) / 100.0;
		return Math.round(grossValue * taxPercentage / (1 + taxPercentage));
	}

	@Override
	public double value(TAX taxRate) {
		if (taxRate == null) {
			throw new IllegalArgumentException("argument taxRate is null.");
		}
		switch (taxRate) {
			case TAXFREE:
				return 0.0;
			case GER_VAT:
				return 19.0;
			case GER_VAT_REDUCED:
				return 7.0;
			default:
				throw new IllegalArgumentException("Invalid tax rate");
		}
	}
}
