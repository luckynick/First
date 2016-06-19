package luckynick;

class Hidden extends TableStructure{
    private char s1;
    public static int counter;

    Hidden(int width, int height, int number, char s1){
        super(height, width, number);
        this.s1 = s1;
        init_hiden();
    }
    Hidden(int width, int height, int number){
        super(height, width, number);
        this.s1 = 'H';
        init_hiden();
    }

    public void init_hiden() {
        open = new boolean[super.getW()][super.getH()];
        for(int x = 0; x < getW(); x++){
            for(int y = 0; y < getH(); y++){
                open[x][y] = false;
            }
        }
    }

    public void print(){
        System.out.print("\t");
        for(int y = 0; y < getH(); y++){
            System.out.print(" " + y%10);
        }
        System.out.println();
        for(int x = 0; x < getW(); x++){
            System.out.println();
            System.out.print(x%10 + "\t");
            for(int y = 0; y < getH(); y++){
                if(open[x][y]) {
                    if(tab[x][y] == -1) System.out.print(" X");
                    else System.out.print(" " + tab[x][y]);
                }
                else System.out.print(" " + s1);
            }
        }
    }

    public boolean action(int y, int x){
        open[x][y] = true;
        counter++;
        if(tab[x][y] == -1) return false;
        if(tab[x][y] == 0) expand(x, y);
        return true;
    }

    public void expand(int x, int y){
        for(int x1 = x - 1; x1 < x + 2; x1++){
            for(int y1 = y - 1; y1 < y + 2; y1++){
                exp_sub(x1, y1);
            }
        }
    }

    public void exp_sub(int x, int y){
        try {
            if(tab[x][y] == 0 && !open[x][y]) {
                open[x][y] = true;
                counter++;
                expand(x, y);
                return;
            }
            if(tab[x][y] != -1 && !open[x][y]){
                open[x][y] = true;
                counter++;
            }
        }catch(ArrayIndexOutOfBoundsException ex){

        }
    }


    public void print_struct(){
        super.print_struct();
    }
    public char getS1() {
        return s1;
    }
}