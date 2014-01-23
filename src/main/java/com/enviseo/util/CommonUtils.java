package com.enviseo.util;

import java.util.List;

public class CommonUtils {
	public static boolean isEmpty(List list) {
		if (list == null || list.isEmpty()) {
			return true;
		}
		return false;
	}
}
