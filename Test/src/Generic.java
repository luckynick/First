/**
 * Created by luckynick on 01-May-16.
 */
public class Generic {
    static void test()
    {
        Type <String> integer = new Type <String> ("abv");
        System.out.println(integer.getOb().getClass().getName());
        System.out.println(integer.getOb());

    }
}

class Type<T>
{
    T ob;
    Type(T ob)
    {
        this.ob = ob;
    }

    T getOb()
    {
        return ob;
    }
}