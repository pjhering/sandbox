package org.petehering.sandbox;

import static java.lang.System.out;

public final class Utility
{
    private Utility ()
    {
    }
    
    public static float clamp (float value, float min, float max)
    {
        if (value < min)
        {
            return min;
        }
        
        if (value > max)
        {
            return max;
        }
        
        return value;
    }
    
    public static int clamp (int value, int min, int max)
    {
        if (value < min)
        {
            return min;
        }
        
        if (value > max)
        {
            return max;
        }
        
        return value;
    }
    
    public static <T> T[] requireNonNull (T[] array)
    {
        if (array != null)
        {
            for (T elem : array)
            {
                if (elem == null)
                {
                    throw new RuntimeException ("null element");
                }
            }
            
            return array;
        }
        
        throw new RuntimeException ("null array");
    }
    
    public static int requireGreaterThan (int min, int value)
    {
        if (value > min)
        {
            return value;
        }
        
        throw new RuntimeException (min + " <= " + value);
    }
    
    public static long requireGreaterThan (long min, long value)
    {
        if (value > min)
        {
            return value;
        }
        
        throw new RuntimeException (min + " <= " + value);
    }

    public static float requireGreaterThan (float min, float value)
    {
        if (value > min)
        {
            return value;
        }

        throw new RuntimeException (min + " <= " + value);
    }

    public static int requireGreaterThanOrEqualTo (int min, int value)
    {
        if (value >= min)
        {
            return value;
        }

        throw new RuntimeException (min + " <= " + value);
    }

    public static float requireGreaterThanOrEqualTo (float min, float value)
    {
        if (value >= min)
        {
            return value;
        }

        throw new RuntimeException (min + " <= " + value);
    }

    public static void log (Object obj, Object msg)
    {
        log (obj.getClass (), msg);
    }

    public static void log (Class c, Object msg)
    {
        out.print (c.getSimpleName ());
        out.print (':');
        out.println (msg);
    }
}
