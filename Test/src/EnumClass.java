enum Clouds
{
    ALLIGATOR(100), BEAR(300), GOAT(50), UNICORN(250), CAT(5), DOG(10);

    int i;
    Clouds(int i)
    {
        this.i = i;
    }

    public static String toStringArr()
    {
        Clouds [] arr = Clouds.values();
        String res = "";
        for (Clouds x: arr)
        {
            res += x.toString() + ' ';
        }
        return res;
    }
}

public class EnumClass
{
    static void act()
    {
        Clouds cl;
        cl = Clouds.CAT.ALLIGATOR;
        cl.i = 10000;
        System.out.println(Clouds.ALLIGATOR.i);
    }
}
