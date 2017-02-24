package com.zte.msm.spring.user.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class BeanHelper
{
    @SuppressWarnings("rawtypes")
    public static List copyList(List<? extends Object> objectList, Class<?> resultClass)
    {
        List<Object> voList = new ArrayList<Object>();

        Object voObj = null;
        for (Object poObj : objectList)
        {
            try
            {
                voObj = resultClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
            BeanUtils.copyProperties(poObj, voObj);
            
            voList.add(voObj);
        }
        return voList;

    }
}
