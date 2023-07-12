package com.cg.shopping.utils;

import java.util.ArrayList;
import java.util.List;

import com.cg.shopping.entity.Orders;
import com.cg.shopping.model.OrdersDTO;

public class OrdersUtil {
	/*
	 * This will convert the Orders DTO to entity.
	 */
	public static Orders convertToOrders(OrdersDTO ordersdto) {
		Orders orders = new Orders();
		orders.setUserEmail(ordersdto.getUserEmail());
		orders.setProductName(ordersdto.getProductName());
		orders.setProductQty(ordersdto.getProductQty());
		orders.setProductPrice(ordersdto.getProductPrice());
		orders.setDate(ordersdto.getOrderedDate());
		return orders;
	}

	/*
	 * This will convert the Orders entity to DTO.
	 */
	public static OrdersDTO convertToOrdersDTO(Orders orders) {
		OrdersDTO ordersdto = new OrdersDTO();
		ordersdto.setOrderId(orders.getOrderid());
		ordersdto.setUserEmail(orders.getUserEmail());
		ordersdto.setProductName(orders.getProductName());
		ordersdto.setProductQty(orders.getProductQty());
		ordersdto.setProductPrice(orders.getProductPrice());
		ordersdto.setOrderedDate(orders.getDate());
		return ordersdto;
	}

	/*
	 * This will convert the Orders entity list to DTO list.
	 */
	public static List<OrdersDTO> convertToOrdersDTOList(List<Orders> orderslist) {
		List<OrdersDTO> ordersdtolist = new ArrayList<OrdersDTO>();
		for (Orders orders : orderslist) {
			ordersdtolist.add(convertToOrdersDTO(orders));
		}
		return ordersdtolist;
	}

}
