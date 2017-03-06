package com.zte.msm.frame.util.cookie;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieHelper
{
    public static String getByName(HttpServletRequest request, String name)
    {
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie cookie : cookies)
            {
                if (cookie.getName().equals(name))
                {
                    return cookie.getValue();
                }
            }
        }
        return null;

    }
}
