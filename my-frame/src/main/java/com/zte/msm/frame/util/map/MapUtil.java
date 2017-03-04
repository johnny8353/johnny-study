package com.zte.msm.frame.util.map;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
public class MapUtil {
	/**
	 * 对象转为Map
	 * @param pojo
	 * @return
	 */
	public static HashMap<String,Object> toMap(Object pojo)
	{
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		if(pojo==null) return hashMap;
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
