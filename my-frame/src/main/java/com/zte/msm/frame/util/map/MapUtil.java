package com.zte.msm.frame.util.map;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
public class MapUtil {
	public static HashMap returnMap(Object pojo)
	{
		HashMap hashMap = new HashMap();

		// Class c = BaseObject.class;
		Method m[] = pojo.getClass().getDeclaredMethods();
		Field f[] = pojo.getClass().getDeclaredFields();
		for (Method md : m)
		{
			for (Field d : f)
			{
				String prpName = d.getName();
				String getMetd = "get"
						+ d.getName().substring(0, 1).toUpperCase()
						+ d.getName().substring(1);
				if (getMetd.equals(md.getName()))
				{
					try
					{
						Object v = md.invoke(pojo, new Object[0]);
						if(v != null)
						{
						    hashMap.put(prpName, v);
						}
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}

		return hashMap;
	}
}
