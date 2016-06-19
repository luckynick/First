package luckynick;

class TableStructure{
    private static int w, h;
    public static int limit;
    private int num;
    public int[][] tab;
    public boolean open[][];
    /** створює таблицю з бомбами і заповнює значеннями 0
     * @param width ширина таблиці
     * @param height висота таблиці
     * @param number кількість бомб
     */
    TableStructure(int width, int height, int number){
        w = width;
        h = height;
        num = number;
        if(num > (limit = w*h)) System.exit(-300);
        tab = new int[w][h];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tab[x][y] = 0;
            }
        }
        init_rand(number);
        limit -= num;
    }
    /** заповнення окремо вибраної ячейки
     * @param x положення вибору на осі ОХ
     * @param y положення вибору на осі ОУ
     */
    public void place(int x, int y){
        tab[x][y] = -1;
    }
    public void eval(){
        for(int x = 0; x < w; x++){
            for(int y = 0; y < h; y++){
                if(tab[x][y] == -1) neigh_str(x, y);
            }
        }
    }
    /**
     * працює у методі eval()
     * @param x вісь ОХ
     * @param y вісь ОУ
     */
    public void neigh_str(int x, int y){
        for(int x1 = x - 1; x1 < x + 2; x1++){
            if(x1 == -1 || x1 >= w) continue;
            for(int y1 = y - 1; y1 < y + 2; y1++){
                if(y1 == -1 || y1 >= h) continue;
                if(tab[x1][y1] == -1) continue;
                tab[x1][y1] += 1;
            }
        }
    }
    public void init_rand(int num){
        for(int t = 1; t <= num; t++) {
            int rx = (int) (Math.random()*w);
            int ry = (int) (Math.random()*h);
            if(tab[rx][ry] == -1){
                t--;
                continue;
            }
            place(rx, ry);
        }
        eval();
    }
    public void print_struct(){
        for(int x = 0; x < getW(); x++){
            System.out.println();
            for(int y = 0; y < getH(); y++){
                if(tab[x][y] == -1) System.out.print(" X"/*tab[x][y]*/);
                else System.out.print(" " + tab[x][y]);
            }
        }
    }


    public static int getH() {
        return h;
    }
    public static int getW() {
        return w;
    }
}
