interface Yigit{
    void accept(Integer x);
}class Senkara{
    public static void main(String[] args) {
        Yigit yigit =new Yigit () {
            public void accept(Integer x){
                System.out.println(x);}
        };
        yigit.accept(1);
        Yigit yigit2=number->{
            number++;
            System.out.println(number);
        };
        yigit2.accept(1);
    }
}